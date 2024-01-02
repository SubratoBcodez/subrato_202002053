package com.example.subrato_202002053;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ListValueActivity extends AppCompatActivity {

    private TextView valuesTextView;
    private DatabaseHelper databaseHelper;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_value);

        valuesTextView = findViewById(R.id.valuesTextView);
        databaseHelper = new DatabaseHelper(this);

        displayStoredValues();
    }

    private void displayStoredValues() {
        String values = databaseHelper.getAllData();
        valuesTextView.setText(values);
    }

    public void showNextValue(View view) {
        currentIndex++;
        displayStoredValues();
    }

    public void showPreviousValue(View view) {
        if (currentIndex > 0) {
            currentIndex--;
            displayStoredValues();
        }
    }

    public void goToHomePage(View view) {
        finish();
    }
}
