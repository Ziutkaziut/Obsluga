package com.example.obsluga;

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

public class MainActivity extends AppCompatActivity {

    private TextView textView6;
    private double firstNumber = 0.0;
    private String operator = "";
    private boolean isNewNumber = true;

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

        View.OnClickListener digitListener = v -> {
            Button button = (Button) v;
            appendSymbol(button.getText().toString());
        };

        findViewById(R.id.button20).setOnClickListener(digitListener); // 0
        findViewById(R.id.button16).setOnClickListener(digitListener); // 1
        findViewById(R.id.button18).setOnClickListener(digitListener); // 2
        findViewById(R.id.button17).setOnClickListener(digitListener); // 3
        findViewById(R.id.button12).setOnClickListener(digitListener); // 4
        findViewById(R.id.button13).setOnClickListener(digitListener); // 5
        findViewById(R.id.button14).setOnClickListener(digitListener); // 6
        findViewById(R.id.button8).setOnClickListener(digitListener);  // 7
        findViewById(R.id.button9).setOnClickListener(digitListener);  // 8
        findViewById(R.id.button10).setOnClickListener(digitListener); // 9
        findViewById(R.id.button21).setOnClickListener(digitListener); // ,

        View.OnClickListener operatorListener = v -> {
            Button button = (Button) v;
            try {
                String currentText = textView6.getText().toString().replace(",", ".");
                firstNumber = Double.parseDouble(currentText);
                operator = button.getText().toString();
                isNewNumber = true;
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Błąd odczytu liczby!", Toast.LENGTH_SHORT).show();
            }
        };

        findViewById(R.id.button3).setOnClickListener(operatorListener); // +
        findViewById(R.id.button4).setOnClickListener(operatorListener); // -
        findViewById(R.id.button5).setOnClickListener(operatorListener); // x
        findViewById(R.id.button6).setOnClickListener(operatorListener); // /

        findViewById(R.id.button7).setOnClickListener(v -> {
            textView6.setText("0");
            firstNumber = 0.0;
            operator = "";
            isNewNumber = true;
        });

        findViewById(R.id.button23).setOnClickListener(v -> {
            try {
                String currentText = textView6.getText().toString().replace(",", ".");
                double secondNumber = Double.parseDouble(currentText);
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "x":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber == 0) {
                            throw new ArithmeticException("Dzielenie przez zero");
                        }
                        result = firstNumber / secondNumber;
                        break;
                    default:
                        return;
                }

                String resultStr = (result == (long) result) ? String.format("%d", (long) result) : String.format("%s", result);
                resultStr = resultStr.replace(".", ",");

                if (resultStr.length() > 10) {
                    resultStr = resultStr.substring(0, 10);
                }

                textView6.setText(resultStr);
                isNewNumber = true;

            } catch (ArithmeticException e) {
                Toast.makeText(this, "Nie można dzielić przez zero!", Toast.LENGTH_SHORT).show();
                textView6.setText("0");
                isNewNumber = true;
            } catch (Exception e) {
                Toast.makeText(this, "Wystąpił błąd obliczeń!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void appendSymbol(String symbol) {
        String currentText = textView6.getText().toString();

        if (isNewNumber) {
            if (symbol.equals(",")) {
                textView6.setText("0,");
            } else {
                textView6.setText(symbol);
            }
            isNewNumber = false;
            return;
        }

        if (currentText.equals("0") && !symbol.equals(",")) {
            textView6.setText(symbol);
            return;
        }

        if (currentText.length() >= 10) {
            return;
        }

        textView6.setText(currentText + symbol);
    }
}