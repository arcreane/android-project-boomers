package com.safewalk.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button emergencyButton;

    private EditText locationInput;

    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CONNECT XML ELEMENTS TO JAVA

        startButton = findViewById(R.id.startButton);

        emergencyButton = findViewById(R.id.emergencyButton);

        locationInput = findViewById(R.id.locationInput);

        statusText = findViewById(R.id.statusText);

        // START SAFE WALK BUTTON

        startButton.setOnClickListener(v -> {

            String destination =
                    locationInput.getText().toString().trim();

            // CHECK EMPTY INPUT

            if(destination.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please enter destination",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // UPDATE STATUS

            statusText.setText(
                    "Status: Navigating to " + destination
            );

            // OPEN GOOGLE MAPS

            Uri mapsUri = Uri.parse(
                    "geo:0,0?q=" + destination
            );

            Intent mapsIntent = new Intent(
                    Intent.ACTION_VIEW,
                    mapsUri
            );

            mapsIntent.setPackage(
                    "com.google.android.apps.maps"
            );

            startActivity(mapsIntent);
        });

        // EMERGENCY SOS BUTTON

        emergencyButton.setOnClickListener(v -> {

            statusText.setText(
                    "Status: EMERGENCY ALERT!"
            );

            Intent dialIntent = new Intent(
                    Intent.ACTION_DIAL
            );

            dialIntent.setData(
                    Uri.parse("tel:112")
            );

            startActivity(dialIntent);

        });
    }
}