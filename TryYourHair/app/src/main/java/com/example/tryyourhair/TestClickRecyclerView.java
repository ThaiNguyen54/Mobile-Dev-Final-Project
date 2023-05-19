package com.example.tryyourhair;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TestClickRecyclerView extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_click_recycler_view);

        textView = findViewById(R.id.test_url);

        String url = getIntent().getStringExtra("URL");
        textView.setText(url);
    }
}