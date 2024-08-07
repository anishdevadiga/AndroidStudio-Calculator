package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String input = "";
    private boolean isOn = false;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        Button b0 = findViewById(R.id.num0);
        Button b1 = findViewById(R.id.num1);
        Button b2 = findViewById(R.id.num2);
        Button b3 = findViewById(R.id.num3);
        Button b4 = findViewById(R.id.num4);
        Button b5 = findViewById(R.id.num5);
        Button b6 = findViewById(R.id.num6);
        Button b7 = findViewById(R.id.num7);
        Button b8 = findViewById(R.id.num8);
        Button b9 = findViewById(R.id.num9);
        Button dot = findViewById(R.id.dot);

        Button add = findViewById(R.id.btnadd);
        Button mul = findViewById(R.id.btnmul);
        Button sub = findViewById(R.id.btnsub);
        Button div = findViewById(R.id.btndiv);
        Button del = findViewById(R.id.btndel);
        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button ac = findViewById(R.id.btnAc);
        Button eq = findViewById(R.id.btneq);

        View.OnClickListener listener = v -> {
            if (!isOn) {
                Toast.makeText(MainActivity.this, "Calculator is OFF", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
            Toast.makeText(MainActivity.this, "Calculator is ON", Toast.LENGTH_SHORT).show();

            }

            Button button = (Button) v;
            input += button.getText().toString();
            resultTextView.setText(input);
        };

        b0.setOnClickListener(listener);
        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);
        b4.setOnClickListener(listener);
        b5.setOnClickListener(listener);
        b6.setOnClickListener(listener);
        b7.setOnClickListener(listener);
        b8.setOnClickListener(listener);
        b9.setOnClickListener(listener);
        dot.setOnClickListener(listener);

        add.setOnClickListener(listener);
        mul.setOnClickListener(listener);
        sub.setOnClickListener(listener);
        div.setOnClickListener(listener);

        del.setOnClickListener(v -> {
            if (isOn && !input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                resultTextView.setText(input);
            }
        });

        on.setOnClickListener(v -> {
            isOn = true;
            resultTextView.setText("");
            input = "";
        });

        off.setOnClickListener(v -> {
            isOn = false;
            resultTextView.setText("");
            input = "";
        });

        ac.setOnClickListener(v -> {
            if (isOn) {
                input = "";
                resultTextView.setText(input);
            }
        });

        eq.setOnClickListener(v -> {
            if (isOn && !input.isEmpty()) {
                try {
                    double result = evaluate(input);
                    resultTextView.setText(String.valueOf(result));
                    input = String.valueOf(result);
                } catch (Exception e) {
                    resultTextView.setText("Error");
                    input = "";
                }
            }
        });
    }

    private double evaluate(String expression) {
        // Remove spaces
        expression = expression.replaceAll(" ", "");

        char[] arr = expression.toCharArray();

        String operand1 = "";
        String operand2 = "";
        char operator = ' ';

        boolean foundOperator = false;

        for (char ch : arr) {
            if (ch >= '0' && ch <= '9' || ch == '.') {
                if (foundOperator) {
                    operand2 += ch;
                } else {
                    operand1 += ch;
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operator = ch;
                foundOperator = true;
            }
        }

        double result = 0;

        switch (operator) {
            case '+':
                result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                break;
            case '-':
                result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                break;
            case '*':
                result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                break;
            case '/':
                result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                break;
        }

        return result;
    }
}
