package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
enum Operator {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
}
public class CalculatorActivity extends AppCompatActivity {

    private EditText operand1EditText;
    private EditText operand2EditText;
    private TextView textViewResult;

    private TextView greetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        operand1EditText = findViewById(R.id.operand1);
        operand2EditText = findViewById(R.id.operand2);
        textViewResult = findViewById(R.id.textViewResult);
        greetingTextView= findViewById(R.id.greetingView);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("username");
            greetingTextView.setText("Hello, " + username);
        }

        Button addContactsButton = findViewById(R.id.addContactsButton);

        addContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operator.ADD);
            }
        });

        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operator.SUBTRACT);
            }
        });

        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operator.MULTIPLY);
            }
        });

        Button buttonDivide = findViewById(R.id.buttonDivide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(Operator.DIVIDE);
            }
        });
    }

    private void performOperation(Operator operator) {
        String operand1String = operand1EditText.getText().toString();
        String operand2String = operand2EditText.getText().toString();

        if (operand1String.isEmpty() || operand2String.isEmpty()) {
            textViewResult.setText("Please enter both operands");
            return;
        }

        double operand1 = Double.parseDouble(operand1String);
        double operand2 = Double.parseDouble(operand2String);

        double result=0;
        switch (operator) {
            case ADD:
                result = operand1 + operand2;
                break;
            case SUBTRACT:
                result = operand1 - operand2;
                break;
            case MULTIPLY:
                result = operand1 * operand2;
                break;
            case DIVIDE:
                result = operand1 / operand2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }

        textViewResult.setText(String.format("%.2f", result));
    }
}




