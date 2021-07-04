package com.saulh.dailyexpensetracker.optionMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saulh.dailyexpensetracker.AppDatabase;
import com.saulh.dailyexpensetracker.LoginActivity;
import com.saulh.dailyexpensetracker.R;
import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.User;

public class EditProfileActivity extends AppCompatActivity implements ChangePasswordDiaglog.ChangePasswordDiaglogListener {

    private static final String TAG = "MyActivity";
    private String username, new_password;
    TextView textView_username, textView_email;
    Context context;

    AppDatabase db;
    UserDao userDao;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Button change_password = findViewById(R.id.button_change_password);
        textView_username = findViewById(R.id.textView_username);
        textView_email = findViewById(R.id.textView_email);
        //
        //email.setText(LoginActivity.user.email);

        username = LoginActivity.mEditTextUsername.getText().toString();

        //get database instance
        db = AppDatabase.getDBInstance(context);
        userDao = db.userDao();
        user = userDao.getUserByUsername(username);

        Log.d(TAG,user.email);
        textView_username.setText((String) user.username);
        textView_email.setText((String) user.email);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        ChangePasswordDiaglog dialog = new ChangePasswordDiaglog();
        dialog.show(getSupportFragmentManager(), "Change Password");
    }

    @Override
    public void applyText(String new_password) {
        this.new_password = new_password;
        user.password = this.new_password;
        Log.d(TAG,user.password);

        //Toast.makeText(getApplicationContext(),new_password,Toast.LENGTH_SHORT).show();
    }
}