package com.example.obsluga;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvCounter = findViewById(R.id.tvCounter);

        // SPOSÓB 1: Wspólny listener zdefiniowany jako zmienna z lambdą (obsługuje +1 oraz -1)
        View.OnClickListener stepListener = v -> {
            if (v.getId() == R.id.btnPlus) {
                count++;
            } else if (v.getId() == R.id.btnMinus) {
                if (count > 0) {
                    count--;
                } else {
                    // Obsługa błędu: wartość ujemna wywołuje Toast, aplikacja nie gaśnie
                    Toast.makeText(this, R.string.error_negative, Toast.LENGTH_SHORT).show();
                }
            }
            updateCounterDisplay();
        };

        // Podpięcie wspólnego listenera do przycisków +1 i -1
        findViewById(R.id.btnPlus).setOnClickListener(stepListener);
        findViewById(R.id.btnMinus).setOnClickListener(stepListener);

        // SPOSÓB 2: Inline lambda (bezpośrednie wyrażenie lambda) przypisana do przycisku Reset
        findViewById(R.id.btnReset).setOnClickListener(v -> {
            count = 0;
            updateCounterDisplay();
        });
    }

    // Metoda pomocnicza do odświeżania tekstu na ekranie
    private void updateCounterDisplay() {
        tvCounter.setText("Licznik: " + count);
    }
}