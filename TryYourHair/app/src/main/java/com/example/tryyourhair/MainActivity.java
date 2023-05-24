package com.example.tryyourhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.tryyourhair.Singleton.Singleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    EditText edt_token;
    Singleton singleton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_token = findViewById(R.id.edt_token);
        String TAG = "TOKEN";
        singleton = Singleton.getInstance();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        singleton.setRegistrationToken(token);

                        edt_token.setText(token);
                    }
                });

    }

    public void OpenCameraActivity(View view) {
        Intent intent = new Intent(this, OpenCamera.class);
        startActivity(intent);
    }

    public void OpenCardView(View view) {
        Intent intent = new Intent(this, HairStyleCardView.class);
        startActivity(intent);
    }

    public void OpenListHair(View view) {
        Intent intent = new Intent(this, HairStyleRecyclerViewActivity.class);
        startActivity(intent);
    }

    public void OpenTestAPI (View view) {
        Intent intent = new Intent(this, TestAPI.class);
        startActivity(intent);
    }

    public void OpenHomeScreen (View view) {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void OpenGeneratedHair (View view) {
        Intent intent = new Intent(this, GeneratedHair.class);
        startActivity(intent);
    }
}