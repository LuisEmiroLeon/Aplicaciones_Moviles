package com.example.entregable1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activityestadisticas extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button buttonClear;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityestadisticas);

        tableLayout = findViewById(R.id.myTableLayout);
        buttonClear = findViewById(R.id.buttonClear);
        buttonBack = findViewById(R.id.buttonBack);

        loadData();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y vuelve a la anterior
            }
        });
    }

    private void loadData() {
        SharedPreferences waterPrefs = getSharedPreferences("WaterData", MODE_PRIVATE);
        SharedPreferences electricityPrefs = getSharedPreferences("ElectricityData", MODE_PRIVATE);

        int waterIndex = waterPrefs.getInt("index", 0);
        int electricityIndex = electricityPrefs.getInt("index", 0);

        for (int i = 0; i < waterIndex; i++) {
            String month = waterPrefs.getString("month_" + i, "");
            String service = "Agua";
            String consumption = waterPrefs.getString("volume_" + i, "");
            String price = waterPrefs.getString("price_" + i, "");

            TableRow tableRow = new TableRow(this);

            TextView textViewMonth = new TextView(this);
            textViewMonth.setText(month);
            textViewMonth.setBackgroundResource(R.color.white);
            tableRow.addView(textViewMonth);

            TextView textViewService = new TextView(this);
            textViewService.setText(service);
            textViewService.setBackgroundResource(R.color.white);
            tableRow.addView(textViewService);

            TextView textViewConsumption = new TextView(this);
            textViewConsumption.setText(consumption);
            textViewConsumption.setBackgroundResource(R.color.white);
            tableRow.addView(textViewConsumption);

            TextView textViewPrice = new TextView(this);
            textViewPrice.setText(price);
            textViewPrice.setBackgroundResource(R.color.white);
            tableRow.addView(textViewPrice);

            tableLayout.addView(tableRow);
        }

        for (int i = 0; i < electricityIndex; i++) {
            String month = electricityPrefs.getString("month_" + i, "");
            String service = "Electricidad";
            String consumption = electricityPrefs.getString("kilowatt_" + i, "");
            String price = electricityPrefs.getString("price_" + i, "");

            TableRow tableRow = new TableRow(this);

            TextView textViewMonth = new TextView(this);
            textViewMonth.setText(month);
            textViewMonth.setBackgroundResource(R.color.white);
            tableRow.addView(textViewMonth);

            TextView textViewService = new TextView(this);
            textViewService.setText(service);
            textViewService.setBackgroundResource(R.color.white);
            tableRow.addView(textViewService);

            TextView textViewConsumption = new TextView(this);
            textViewConsumption.setText(consumption);
            textViewConsumption.setBackgroundResource(R.color.white);
            tableRow.addView(textViewConsumption);

            TextView textViewPrice = new TextView(this);
            textViewPrice.setText(price);
            textViewPrice.setBackgroundResource(R.color.white);
            tableRow.addView(textViewPrice);

            tableLayout.addView(tableRow);
        }
    }

    private void clearData() {
        SharedPreferences waterPrefs = getSharedPreferences("WaterData", MODE_PRIVATE);
        SharedPreferences electricityPrefs = getSharedPreferences("ElectricityData", MODE_PRIVATE);

        SharedPreferences.Editor waterEditor = waterPrefs.edit();
        SharedPreferences.Editor electricityEditor = electricityPrefs.edit();

        waterEditor.clear();
        electricityEditor.clear();

        waterEditor.apply();
        electricityEditor.apply();

        tableLayout.removeAllViews();

        Toast.makeText(this, "Datos borrados", Toast.LENGTH_SHORT).show();
    }
}