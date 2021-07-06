package com.saulh.dailyexpensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saulh.dailyexpensetracker.daos.ExpenseDao;
import com.saulh.dailyexpensetracker.daos.UserDao;
import com.saulh.dailyexpensetracker.entities.Expense;
import com.saulh.dailyexpensetracker.entities.User;
import com.saulh.dailyexpensetracker.optionMenu.AboutActivity;
import com.saulh.dailyexpensetracker.optionMenu.EditProfileActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    FloatingActionButton mFAB;
    Context context;
    RecyclerView mRecyclerView;
    String username;
    List<Expense> userExpenseList;
    public static AppDatabase db;

    private RecyclerView recyclerView;
    EditText annualIncome, ed_total_expense, annualSavings;
    Button btn_save_annual_income;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = Spashscreen.this_username;
        Log.d(TAG,"Username from Main activity " + username);

        context = getApplicationContext();
        db = AppDatabase.getDBInstance(context);
        ExpenseDao expenseDao = db.expenseDao();
        UserDao userDao = db.userDao();
        User user = userDao.getUserByUsername(username);

        userExpenseList = new ArrayList<>();
        userExpenseList = expenseDao.getExpensesForUser(username);

        double totalExpense = 0.00;
        //get total user expense
        for(int i = 0;i < userExpenseList.size(); i++){
            totalExpense += userExpenseList.get(i).amount;
        }

        //set total expense to the total expense text view
        ed_total_expense = findViewById(R.id.editTextTextPersonName3);
        ed_total_expense.setText("$"+String.valueOf(totalExpense));
        ed_total_expense.setFocusable(false);

        annualIncome = findViewById(R.id.editTextTextPersonName2);
        annualIncome.setText("$"+String.valueOf(user.annualIncome) );

        btn_save_annual_income = findViewById(R.id.button_annual_income);
        btn_save_annual_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.annualIncome = Double.parseDouble(annualIncome.getText().toString());
                db.userDao().updateUser(user);
            }
        });



        mFAB = findViewById(R.id.floatingActionButton);

        recyclerView = findViewById(R.id.new_recylerView);

        //
        if(userExpenseList.size() >= 1){
            //add to list the recycler view
            MyAdapter myAdapter = new MyAdapter(getApplicationContext(), userExpenseList);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }else{
            Log.d(TAG, "List is empty");
        }

        mFAB.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddExpense.class);
            intent.putExtra(LoginActivity.LOGGED_IN_USERNAME, username);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu); //set option menu to our own option menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_profile:
                Log.d(TAG,"edit_profile is clickred");
                Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.help:
                Log.d(TAG,"help is clickred");
                return true;
            case R.id.about:
                Log.d(TAG,"about is clickred");
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                Log.d(TAG,"logout is clickred");

                //this block of code set to logout
                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.putString("username", " " );
                editor.commit();

                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }



    private class NewThread extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}