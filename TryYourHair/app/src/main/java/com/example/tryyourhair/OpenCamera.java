package com.example.tryyourhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OpenCamera extends CameraActivity {

    CameraBridgeViewBase cameraBridgeViewBase;
    ImageView btn_taking_picture;
    int take_image = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_camera);

        getPermission();

        cameraBridgeViewBase = findViewById(R.id.camera_view);
        btn_taking_picture = findViewById(R.id.btn_take_picture);
        cameraBridgeViewBase.setCameraIndex(1); // Use front camera

        cameraBridgeViewBase.setCvCameraViewListener(new CameraBridgeViewBase.CvCameraViewListener2() {
            @Override
            public void onCameraViewStarted(int width, int height) {

            }

            @Override
            public void onCameraViewStopped() {

            }

            @Override
            public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
                take_image = TakePicture(take_image, inputFrame.rgba());
                return inputFrame.rgba();
            }
        });

        if(OpenCVLoader.initDebug()) {
//            Log.d("OPENCV", "success");
            cameraBridgeViewBase.enableView();
        }

        btn_taking_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (take_image == 0) {
                    take_image = 1;
                }
                else {
                    take_image = 0;
                }
            }
        });
    }

    private int TakePicture(int take_image, Mat rgba) {
       if (take_image == 1) {
           // add permission for writing in local storage first
           // create new mat that you want to save
           Mat matForSave = new Mat();

           // rotate image by 90 degrees
           Core.flip(rgba.t(), matForSave, 1);

           // Convert image from RGBA to BGRA
           Imgproc.cvtColor(matForSave, matForSave, Imgproc.COLOR_RGBA2BGRA);

           // Create a folder TYH-YourPhoto
           // and save image into that folder
           File folder = new File(Environment.getExternalStorageDirectory().getPath() + "/TYH");

           // Check if the folder is exist
           boolean isExist = true;
           if(folder.exists() == false) {
               isExist = folder.mkdirs();
           }

           // Create unique filename for the captured image
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy_HH-mm-ss");
           String currentDateTime = dateFormat.format(new Date());

//           String fileName =  currentDateTime + ".jpg";
           String fileName = Environment.getExternalStorageDirectory().getPath()
                   + "/TYH-YourPhoto"
                   + currentDateTime
                   + ".jpg";

           // Write the mat to the storage
           Imgcodecs.imwrite(fileName, matForSave);
           take_image = 0;
           Log.d("TAKE PICTURE", fileName);
       }
        return take_image;
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraBridgeViewBase.enableView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraBridgeViewBase.disableView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraBridgeViewBase.enableView();
    }



    @Override
    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(cameraBridgeViewBase);
    }

    void getPermission() {
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
        }

        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            getPermission();
        }
    }
}