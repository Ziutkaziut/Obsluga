package com.example.obsluga;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView6;

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

        textView6 = findViewById(R.id.textView6);
        textView6.setText("0");

        findViewById(R.id.button16).setOnClickListener(v -> appendSymbol("1"));
        findViewById(R.id.button18).setOnClickListener(v -> appendSymbol("2"));
        findViewById(R.id.button17).setOnClickListener(v -> appendSymbol("3"));
    }

    private void appendSymbol(String symbol) {
        textView6.setText(textView6.getText().toString() + symbol);
    }
}