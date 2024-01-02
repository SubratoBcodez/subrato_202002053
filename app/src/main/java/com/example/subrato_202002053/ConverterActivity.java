package com.example.subrato_202002053;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConverterActivity extends AppCompatActivity {

    private EditText radiusEditText;
    private Button calculateButton;
    private Button databaseButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        radiusEditText = findViewById(R.id.radiusEditText);
        calculateButton = findViewById(R.id.calculateButton);
        databaseButton = findViewById(R.id.databaseButton);

        databaseHelper = new DatabaseHelper(this);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateArea();
            }
        });

        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListValueActivity();
            }
        });
    }

    private void calculateArea() {
        String radiusStr = radiusEditText.getText().toString().trim();

        if (!radiusStr.isEmpty()) {
            try {
                double radius = Double.parseDouble(radiusStr);
                double area = calculateCircleArea(radius);

                // Display area with up to 2 decimal points using Toast
                showToast(String.format("Area: %.2f", area));

                // Store values in SQLite database
                saveToDatabase(radius, area);

            } catch (NumberFormatException e) {
                showToast("Invalid radius");
            }
        } else {
            showToast("Enter radius");
        }
    }

    private double calculateCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    private void saveToDatabase(double radius, double area) {

        databaseHelper.insertData(radius, area);
    }

    private void openListValueActivity() {
        Intent intent = new Intent(this, ListValueActivity.class);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
