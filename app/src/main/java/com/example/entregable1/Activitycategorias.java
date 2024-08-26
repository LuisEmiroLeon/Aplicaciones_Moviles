package com.example.entregable1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activitycategorias extends AppCompatActivity {

    private Button buttonplastico, buttoncarton, buttonmetal, buttonvidrio, buttonBack;
    private ImageButton buttonBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        buttonplastico = findViewById(R.id.buttonplastico);
        buttoncarton = findViewById(R.id.buttoncarton);
        buttonmetal = findViewById(R.id.buttonmetal);
        buttonvidrio = findViewById(R.id.buttonvidrio);
        buttonBack2 = findViewById(R.id.buttonBack2);

        buttonplastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitycategorias.this, Plastic_entry.class);
                startActivity(intent);
            }
        });
        buttonvidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitycategorias.this, Glass_entry.class);
                startActivity(intent);
            }
        });

        buttoncarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitycategorias.this, Paper_entry.class);
                startActivity(intent);
            }
        });

        buttonmetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitycategorias.this, Metal_entry.class);
                startActivity(intent);
            }
        });
        buttonBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y vuelve a la anterior
            }
        });


    }
}