package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.User;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    public static String PREFS_NAME = "MyPrefsFile";


    Button mBtnLogin, mBtnRegister;
    public static EditText mEditTextUsername;
    private EditText mEditTextPassword;

    Context context;

    public User user;

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

            //set hasLoggedIn to true to keep user logged in
            SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("hasLoggedIn", true);
            editor.putString("username", mEditTextUsername.getText().toString());
            Spashscreen.this_username = mEditTextUsername.getText().toString();
            editor.commit();

            user = loginWithCredentials(mEditTextUsername.getText().toString(),
                    mEditTextPassword.getText().toString());
            if (user != null) {
                mEditTextPassword.setText("");
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(LOGGED_IN_USERNAME, user.username);
                Toast.makeText(context, "Welcome " + user.username + "!", Toast.LENGTH_LONG).show();
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