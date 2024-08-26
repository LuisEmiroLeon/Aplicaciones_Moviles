package com.example.entregable1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class DailyTipsActivity extends AppCompatActivity {

    private TextView textViewTip;
    private ImageButton buttonBack;
    private ViewPager viewPager;
    private int[] imageIds = {R.drawable.arboles, R.drawable.energia, R.drawable.reutiliza, R.drawable.separacion, R.drawable.plastico};
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500;
    private final long PERIOD_MS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tips);

        textViewTip = findViewById(R.id.textViewTip);
        buttonBack = findViewById(R.id.buttonBack);
        viewPager = findViewById(R.id.viewPager);

        loadDailyTip();
        setupViewPager();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadDailyTip() {
        SharedPreferences preferences = getSharedPreferences("DailyTips", MODE_PRIVATE);
        String dailyTip = preferences.getString("currentTip", "¡Consejo no disponible!");

        textViewTip.setText(dailyTip);
    }

    private void setupViewPager() {
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageIds);
        viewPager.setAdapter(adapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == imageIds.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }
}