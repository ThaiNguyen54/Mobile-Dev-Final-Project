package com.example.tryyourhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import ErrorDialog.NoFaceDialog;

public class FaceResult extends AppCompatActivity {

    ImageView FaceResultView;

    FaceDetector faceDetector;

    LottieAnimationView ScanEffectView;


    //This factor is used to make the detecting image smaller, to make the process faster
    private static final int SCALING_FACTOR = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_result);

        OpenCVLoader.initDebug();

        FaceResultView = findViewById(R.id.face_img_view);
        ScanEffectView = findViewById(R.id.scan_effect);

        // Receive bitmap from another activity
        byte[] byteArray = getIntent().getByteArrayExtra("userface");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        // Display the bitmap
        FaceResultView.setImageBitmap(bitmap);

        // Init FaceDetector Object
        FaceDetectorOptions realTimeFdo = new FaceDetectorOptions.Builder()
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .build();

        faceDetector = FaceDetection.getClient(realTimeFdo);

        // Create a smaller bitmap to process faster
        Bitmap smallerBitmap = Bitmap.createScaledBitmap(
                bitmap,
                bitmap.getWidth() / SCALING_FACTOR,
                bitmap.getHeight() / SCALING_FACTOR,
                false);


        // Create an input image for detecting face process from the smaller bitmap
        InputImage inputImage = InputImage.fromBitmap(smallerBitmap, 0);

        // Start the detection process
        faceDetector.process(inputImage)
                .addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {

                        // After detecting successfully, we need to disable the animation view
                        ScanEffectView.setVisibility(View.GONE);
                        Rect rect = null;

                        if (faces.size() == 0 ) {
//                            Toast.makeText(FaceResult.this, "Oops! No face detected", Toast.LENGTH_LONG).show();
                            OpenDialog();
                        }
                        else if (faces.size() > 1) {
                            Toast.makeText(FaceResult.this, "More than one face detected", Toast.LENGTH_LONG).show();
                        }
                        else {
                            // There can be multiple faces detected from an image,
                            // manage them using loop from List<Face> faces
                            for(Face face: faces) {
                                // Get detected faces as rectangle
                                rect = face.getBoundingBox();
                                rect.set(rect.left * SCALING_FACTOR,
                                        rect.top * (SCALING_FACTOR - 1),
                                        rect.right * SCALING_FACTOR,
                                        (rect.bottom * SCALING_FACTOR) + 90);
                            }


                            // Draw a bounding box for detected face
                            ScanEffectView.setVisibility(View.GONE);

                            Mat ProcessingMat = new Mat(); // Create a new mat
                            Utils.bitmapToMat(bitmap, ProcessingMat); // Convert the Bitmap image to Mat image
                            Imgproc.rectangle(
                                    ProcessingMat,
                                    new Point((float) rect.left, (float) rect.bottom),
                                    new Point((float) rect.right, (float) rect.top),
                                    new Scalar(255, 0, 0),
                                    5
                            );

                            Bitmap ProcessedBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true); // Copy the original Bitmap to a mutable bitmap
                            Utils.matToBitmap(ProcessingMat, ProcessedBitmap); // Convert the Mat image to Bitmap image to display on image view

                            // Display the processed Bitmap
                            FaceResultView.setImageBitmap(ProcessedBitmap);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Detection failed
                        Toast.makeText(FaceResult.this, "Detection failed due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void OpenDialog() {
        NoFaceDialog noFaceDialog = new NoFaceDialog();
        noFaceDialog.show(getSupportFragmentManager(), "no face");
    }
}