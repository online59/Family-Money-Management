package com.example.famtrack.helper;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.famtrack.R;

public class CreateWalletDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_new_wallet, container, false);
        myInit(view);
        return view;
    }

    private void myInit(View view) {
        Button btnCreateWallet = view.findViewById(R.id.btn_create_wallet);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        EditText edtWalletName = view.findViewById(R.id.edt_wallet_name);

        btnCreateWallet.setOnClickListener(view1 -> {
            if (!edtWalletName.getText().toString().trim().isEmpty()) {
                onDialogClickListener.onSaveClick(edtWalletName.getText().toString().trim());
                dismiss();
            } else {
                edtWalletName.setError("This field cannot be empty");
            }
        });

        btnCancel.setOnClickListener(view2 -> dismiss());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customDialog();
    }

    private void customDialog() {
        Dialog dialog = getDialog();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener{
        void onSaveClick(String walletName);
    }
}
