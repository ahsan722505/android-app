package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Spinner genderSpinner;

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        genderSpinner = findViewById(R.id.genderSpinner);
        Button signupButton = findViewById(R.id.signupButton);
        // Populate the Spinner with gender options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        // Set a selection listener for the Spinner
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected gender here
                String selectedGender = (String) parentView.getItemAtPosition(position);
                // You can store the selected gender in a variable or use it as needed.
                gender=selectedGender;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle when nothing is selected (optional).
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.isEmpty()) {
                    // Handle empty fields
                    // You can display an error message or toast here.
                    Toast.makeText(SignupActivity.this,"Make sure all the fields are filled",Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    // Handle password mismatch
                    // You can display an error message or toast here.
                    Toast.makeText(SignupActivity.this,"password and confirm password should match.",Toast.LENGTH_SHORT).show();
                } else {
                    // Signup successful
                     Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                     intent.putExtra("username", username);
                    intent.putExtra("password", password);
                     startActivity(intent);
                    finish();
                }
            }
        });
    }
}

