package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    private String signupUsername;

    private String signupPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        // Retrieve the data passed from SignupActivity
        Intent intent = getIntent();
        if (intent != null) {
             signupUsername = intent.getStringExtra("username");
             signupPassword = intent.getStringExtra("password");
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Compare with the signup data received
                if (enteredUsername.equals(signupUsername) && enteredPassword.equals(signupPassword)) {
                    // Successful login, navigate to CalculatorActivity
                    Intent calculatorIntent = new Intent(LoginActivity.this, CalculatorActivity.class);
                    calculatorIntent.putExtra("username",enteredUsername);
                    startActivity(calculatorIntent);
                    finish();
                } else {
                    // Handle login failure (e.g., display an error message)
                    Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

