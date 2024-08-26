package com.example.entregable1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StockOMaticAdventures extends AppCompatActivity {

    private Product product;
    private TextView productName;
    private TextView productPrice;
    private TextView productStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_omatic_adventures);

        product = new Product("Panel solar", 155.99, 10);

        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productStock = findViewById(R.id.productStock);

        Button buttonIncreaseStock = findViewById(R.id.buttonIncreaseStock);
        Button buttonDecreaseStock = findViewById(R.id.buttonDecreaseStock);

        updateUI();

        buttonIncreaseStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.increaseStock(1); // Increment by 1
                updateUI();
            }
        });

        buttonDecreaseStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = product.decreaseStock(1); // Decrement by 1
                updateUI();
                Toast.makeText(StockOMaticAdventures.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        productName.setText(product.getName());
        productPrice.setText("Price: $" + product.getPrice());
        productStock.setText("Stock: " + product.getStock());
    }


}