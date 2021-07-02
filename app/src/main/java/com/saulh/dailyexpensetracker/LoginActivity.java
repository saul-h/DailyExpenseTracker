package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.User;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {
    Button mBtnLogin, mBtnRegister;
    EditText mEditTextUsername, mEditTextPassword;

    Context context;

    User user;

    public static final String LOGGED_IN_USERNAME = "com.saulh.dailyexpensetracker.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();

        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnRegister = findViewById(R.id.btnToRegistration);

        mEditTextUsername = findViewById(R.id.editTextLoginUsername);
        mEditTextPassword = findViewById(R.id.editTextLoginPassword);

        mBtnLogin.setOnClickListener(v -> {
            user = loginWithCredentials(mEditTextUsername.getText().toString(),
                    mEditTextPassword.getText().toString());
            if (user != null) {
                mEditTextPassword.setText("");
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(LOGGED_IN_USERNAME, user.username);
                startActivity(intent);
            }
        });

        mBtnRegister.setOnClickListener(v -> {
            mEditTextPassword.setText("");
            Intent intent = new Intent(context, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private User loginWithCredentials(String username, String password) {
        AppDatabase db = AppDatabase.getDBInstance(context);
        UserDao userDao = db.userDao();
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            if (password.equals(user.password)) {
                return user;
            } else {
                return null;
            }
        } else {
            Toast.makeText(context,
                    "Username and/or password are incorrect",
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }
}