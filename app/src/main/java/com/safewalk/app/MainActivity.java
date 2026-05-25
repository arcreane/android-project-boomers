package com.safewalk.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button emergencyButton;

    private EditText locationInput;

    private TextView statusText;

    private RecyclerView contactsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CONNECT XML VIEWS

        startButton = findViewById(R.id.startButton);

        emergencyButton = findViewById(R.id.emergencyButton);

        locationInput = findViewById(R.id.locationInput);

        statusText = findViewById(R.id.statusText);

        contactsRecyclerView =
                findViewById(R.id.contactsRecyclerView);

        // TRUSTED CONTACTS LIST

        List<Contact> contacts = new ArrayList<>();

        contacts.add(
                new Contact("Mom", "+33123456789")
        );

        contacts.add(
                new Contact("Dad", "+33987654321")
        );

        contacts.add(
                new Contact("Best Friend", "+33666666666")
        );

        ContactAdapter adapter =
                new ContactAdapter(contacts);

        contactsRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        contactsRecyclerView.setAdapter(adapter);

        // START SAFE WALK BUTTON

        startButton.setOnClickListener(v -> {

            String destination =
                    locationInput.getText().toString().trim();

            if(destination.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please enter destination",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            statusText.setText(
                    "Status: Navigating to " + destination
            );

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