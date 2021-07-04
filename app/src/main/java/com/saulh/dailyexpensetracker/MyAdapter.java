package com.saulh.dailyexpensetracker;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saulh.dailyexpensetracker.entities.Expense;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<Expense> expense_list;

    public MyAdapter(Context ct, List<Expense> expense_list){
        this.context = ct;
        this.expense_list = expense_list;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.expense_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        holder.expense = expense_list.get(position);
        /*
        holder.expense_description.setText(expense_list.get(position).description);
        String this_amount = String.valueOf( expense_list.get(position).amount ) ;
        holder.expense_amount.setText(this_amount);
        holder.expense_date.setText(expense_list.get(position).date);
        */
        holder.expense_description.setText("this expense");

        holder.expense_amount.setText("15$");
        holder.expense_date.setText("this date");

    }

    @Override
    public int getItemCount() {
        return expense_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView expense_description, expense_date, expense_amount;
        Expense expense;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_description = itemView.findViewById(R.id.rv_expense_desc);
            expense_date = itemView.findViewById(R.id.rv_date);
            expense_amount = itemView.findViewById(R.id.rv_amount);

        }
    }
}
