package com.example.tryyourhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tryyourhair.Singleton.Singleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    ImageView chose_hairstyle_img;
    ImageView confirmed_face_img;
    Singleton singleton;
    Button btn_generate;
    final String SERVER_IP = "192.168.1.12";
    final int SERVER_PORT = 9000;
    final int CLIENT_PORT = 9999;
    final int BUFFER_SIZE = 65536;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        chose_hairstyle_img = findViewById(R.id.img_chose_hairstyle);
        confirmed_face_img = findViewById(R.id.img_confirmed_face);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.item_home);
        btn_generate = findViewById(R.id.btn_generate);

        singleton = Singleton.getInstance();


        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!singleton.getConfirmedFace() && !singleton.getChoseHair()) {
                    Toast.makeText(
                            HomeScreen.this,
                            "Please provide a photo of you and choose a hairstyle",
                            Toast.LENGTH_SHORT).show();
                }
                else if (!singleton.getChoseHair()) {
                    Toast.makeText(
                            HomeScreen.this,
                            "Please choose a hairstyle",
                            Toast.LENGTH_SHORT).show();
                }
                else if (!singleton.getConfirmedFace()) {
                    Toast.makeText(
                            HomeScreen.this,
                            "Please provide a photo of you",
                            Toast.LENGTH_SHORT).show();
                }
                else if (singleton.getConfirmedFace() && singleton.getChoseHair()) {
                    Thread SendAndReceiveMessage = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // In UDP, before receiving message from server,
                            // we need to send message to the server firs
                            DatagramSocket udpSocket = null;
                            try {
                                udpSocket = new DatagramSocket(CLIENT_PORT);

                                String message = "hello server from android";

                                byte[] data = message.getBytes();
                                InetAddress serverAddress = null;
                                serverAddress = InetAddress.getByName(SERVER_IP);
                                DatagramPacket sendingPacket = new DatagramPacket(
                                        data,
                                        data.length,
                                        serverAddress,
                                        SERVER_PORT
                                );
                                udpSocket.send(sendingPacket);

                                // Receive response from the server
                                byte[] messageBuffer = new byte[BUFFER_SIZE];
                                DatagramPacket receivingMessagePacket = new DatagramPacket(
                                        messageBuffer,
                                        messageBuffer.length);
                                udpSocket.receive(receivingMessagePacket);
                                String str_Message = new String(
                                        messageBuffer,
                                        0,
                                        receivingMessagePacket.getLength());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(HomeScreen.this, str_Message, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                udpSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    SendAndReceiveMessage.start();
                }
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_camera:
                        OpenCameraActivity();
                        break;

                    case R.id.item_hairstyle:
                        OpenListHairActivity();
                        break;
                }
                return true;
            }
        });

        if(singleton.getChoseHair() == false) {
            chose_hairstyle_img.setImageResource(R.drawable.img_ex2);
        }
        else if (singleton.getChoseHair() == true) {
            Glide.with(this).load(singleton.getChoseHairURL()).into(chose_hairstyle_img);
        }

        if(singleton.getConfirmedFace() == false) {
            confirmed_face_img.setImageResource(R.drawable.img_ex1);
        }
        else if (singleton.getConfirmedFace() == true) {
            byte[] Confirmed_Face_ByteArray = singleton.getConfirmedFaceImage();
            Bitmap Confirmed_Face_Bitmap = BitmapFactory.decodeByteArray(Confirmed_Face_ByteArray,0, Confirmed_Face_ByteArray.length);
            confirmed_face_img.setImageBitmap(Confirmed_Face_Bitmap);
        }
    }

    public void OpenCameraActivity() {
        Intent CameraIntent = new Intent(this, OpenCamera.class);
        startActivity(CameraIntent);
    }

    public void OpenGallery(){}

    public void OpenHomeActivity(){
        Intent HomeIntent = new Intent(this, HomeScreen.class);
        startActivity(HomeIntent);
    }

    public void OpenListHairActivity() {
        Intent ListHairIntent = new Intent(this, HairStyleRecyclerViewActivity.class);
        startActivity(ListHairIntent);
    }
}