package com.safewalk.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button emergencyButton = findViewById(R.id.emergencyButton);
        TextView statusText = findViewById(R.id.statusText);

        // 🔥 TEST 1: check if views are connected
        if (startButton == null || emergencyButton == null || statusText == null) {
            Toast.makeText(this, "UI NOT CONNECTED", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "UI CONNECTED", Toast.LENGTH_SHORT).show();

        startButton.setOnClickListener(v -> {
            statusText.setText("Status: Walking Safe Route");
            Toast.makeText(this, "START CLICKED", Toast.LENGTH_SHORT).show();
        });

        emergencyButton.setOnClickListener(v -> {
            statusText.setText("Status: EMERGENCY ALERT!");
            Toast.makeText(this, "SOS CLICKED", Toast.LENGTH_SHORT).show();
        });
    }
}