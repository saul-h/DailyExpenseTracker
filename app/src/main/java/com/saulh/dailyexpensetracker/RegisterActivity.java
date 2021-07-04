package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.User;

public class RegisterActivity extends AppCompatActivity {
    private AppDatabase db;
    private Button mBtnRegister;
    private Context context;
    private EditText mEditTextUsername,
                     mEditTextEmail,
                     mEditTextPassword,
                     mEditTextReenterPass;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        db = AppDatabase.getDBInstance(context);
        userDao = db.userDao();

        mBtnRegister = findViewById(R.id.btnRegister);

        mEditTextUsername = findViewById(R.id.editTextUsername);
        mEditTextEmail = findViewById(R.id.editTextEmail);
        mEditTextPassword = findViewById(R.id.editTextRegPassword);
        mEditTextReenterPass = findViewById(R.id.editTextReenterPassword);

        mBtnRegister.setOnClickListener(v -> {
            if (inputIsValid()) {
                registerUser();
            } else {
                Toast.makeText(context, "Please check errors", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registerUser() {
        User user = new User();
        String usernameInput = mEditTextUsername.getText().toString();
        String emailInput = mEditTextEmail.getText().toString();
        String passwordInput = mEditTextPassword.getText().toString();

        user.username = usernameInput;
        user.email = emailInput;
        user.password = passwordInput;

        db.userDao().registerUser(user);
        finish();
    }

    private boolean inputIsValid() {
        boolean isValid = true;
        String usernameInput = mEditTextUsername.getText().toString();
        String emailInput = mEditTextEmail.getText().toString();
        String passwordInput = mEditTextPassword.getText().toString();
        String passwordReenterInput = mEditTextReenterPass.getText().toString();

        if (usernameInput.isEmpty()) {
            mEditTextUsername.setError("Username cannot be empty");
            isValid = false;
        } else if (db.userDao().getUserByUsername(usernameInput) != null) {
            mEditTextUsername.setError("Username is already taken");
            isValid = false;
        } else {
            mEditTextUsername.setError(null);
        }

        if (emailInput.isEmpty()) {
            mEditTextEmail.setError("Email cannot be empty");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mEditTextEmail.setError("Please enter a valid email");
            isValid = false;
        } else {
            mEditTextEmail.setError(null);
        }

        if (passwordInput.isEmpty()) {
            mEditTextPassword.setError("Password cannot be empty");
            isValid = false;
        } else if (passwordInput.length() < 6) {
            mEditTextPassword.setError("Password should be at least 6 characters");
            isValid = false;
        } else {
            mEditTextPassword.setError(null);
        }

        if (!passwordReenterInput.equals(passwordInput)) {
            mEditTextReenterPass.setError("Passwords do not match");
            isValid = false;
        } else {
            mEditTextReenterPass.setError(null);
        }

        return isValid;
    }
}