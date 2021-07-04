package com.saulh.dailyexpensetracker.optionMenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.saulh.dailyexpensetracker.R;

import org.jetbrains.annotations.NotNull;

public class ChangePasswordDiaglog extends AppCompatDialogFragment {

    private EditText new_password;
    private ChangePasswordDiaglogListener listener;


    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflator = getActivity().getLayoutInflater();
        View view = inflator.inflate(R.layout.layout_password_dialog, null);

        builder.setView(view)
                .setTitle("Change Password")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String this_new_password = new_password.getText().toString();
                        listener.applyText(this_new_password);
                    }
                });
        new_password = view.findViewById(R.id.textView_new_password);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        try {
            listener = (ChangePasswordDiaglogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implemeent ChangePasswordDiaglogListener");
        }
    }

    public interface ChangePasswordDiaglogListener{
        void applyText(String new_password);
    }
}
