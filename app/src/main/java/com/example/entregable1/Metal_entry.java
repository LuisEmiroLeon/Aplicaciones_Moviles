package com.example.entregable1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Metal_entry extends AppCompatActivity {

    private EditText editTextWeight, editTextMonth, editTextPrice;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_entry);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextMonth = findViewById(R.id.editTextMonth);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> saveWaterData());
    }

    private void saveWaterData() {
        String volume = editTextWeight.getText().toString();
        String month = editTextMonth.getText().toString();
        String price = editTextPrice.getText().toString();

        if (volume.isEmpty() || month.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar los datos en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("WaterData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int index = preferences.getInt("index", 0);
        editor.putString("volume_" + index, volume);
        editor.putString("month_" + index, month);
        editor.putString("price_" + index, price);
        editor.putInt("index", index + 1);
        editor.apply();

        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
        finish();
    }
}