package com.example.entregable1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewDate;
    private ImageButton IButtoncategory, IButtonstadistic, IButtonconsejo, ButtonHome;
    private Button Buttoncategory, Buttonstadistic, Buttonconsejo;

    private String[] dailyTips = {
            "Separa tus residuos: Clasifica tus desechos en contenedores específicos para papel, plástico, vidrio y orgánicos.",
            "Reduce el consumo de plástico: Opta por productos reutilizables y evita el uso excesivo de envases plásticos.",
            "Reutiliza materiales: Dale una segunda vida a objetos como botellas, bolsas y ropa.",
            "Ahorra energía: Apaga luces y dispositivos cuando no los necesites y utiliza bombillas eficientes.",
            "Compra local y sostenible: Apoya a productores locales y busca opciones amigables con el medio ambiente.",
            "Planta árboles: Contribuye a la reforestación y la captura de carbono."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = findViewById(R.id.textViewDate);
        IButtoncategory = findViewById(R.id.IButtoncategory);
        IButtonstadistic = findViewById(R.id.IButtonstadistic);
        IButtonconsejo = findViewById(R.id.IButtonconsejo);
        Buttoncategory = findViewById(R.id.Buttoncategory);
        Buttonstadistic = findViewById(R.id.Buttonstadistic);
        Buttonconsejo = findViewById(R.id.Buttonconsejo);
        ButtonHome = findViewById(R.id.ButtonHome);

        // Inicializar los SharedPreferences
        SharedPreferences preferences = getSharedPreferences("DailyTips", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Verificar si los consejos ya están guardados
        boolean isInitialized = preferences.getBoolean("isInitialized", false);

        if (!isInitialized) {
            // Guardar los consejos en SharedPreferences
            for (int i = 0; i < dailyTips.length; i++) {
                editor.putString("tip_" + i, dailyTips[i]);
            }
            editor.putBoolean("isInitialized", true);
            editor.apply();
        }

        // Obtener la fecha actual en español de Colombia
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String currentDate = sdf.format(new Date());
        textViewDate.setText(currentDate);

        Buttoncategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activitycategorias.class);
                startActivity(intent);
            }
        });



        Buttonstadistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activityestadisticas.class);
                startActivity(intent);
            }
        });


        Buttonconsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la fecha guardada en SharedPreferences
                String savedDate = preferences.getString("savedDate", "");

                // Comparar la fecha actual con la guardada
                if (!currentDate.equals(savedDate)) {
                    // Si es un nuevo día, guardar un nuevo consejo
                    Random random = new Random();
                    int randomIndex = random.nextInt(10);
                    String newTip = dailyTips[randomIndex];
                    editor.putString("currentTip", newTip);
                    editor.putString("savedDate", currentDate);
                    editor.apply();
                }

                Intent intent = new Intent(MainActivity.this, DailyTipsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item1) {
            Intent intent = new Intent(MainActivity.this, StockOMaticAdventures.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_item2) {
            Intent intent = new Intent(MainActivity.this, Activitycategorias.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_logout) {
            // Restablecer la fecha guardada para generar un nuevo consejo al iniciar sesión de nuevo
            SharedPreferences preferences = getSharedPreferences("DailyTips", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("savedDate", "");
            editor.apply();

            Intent intent = new Intent(MainActivity.this, Activitylogin.class);
            startActivity(intent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}