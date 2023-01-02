package com.example.famtrack.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.famtrack.R;
import com.example.famtrack.api.Payment;
import com.example.famtrack.utils.Constants;
import com.example.famtrack.utils.Utils;
import com.example.famtrack.vm.MainViewModel;

import java.util.Calendar;

public class AddPayment extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.AddPayment";
    private MainViewModel viewModel;
    int day, month, year;
    private long transDate;
    private int transImage;
    private String transCategory, transCategoryId, transNote, transTotal, transWalletId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        myInit();
        setupCategoryRecycler();
        setupSmallWalletRecycler();
        requestAddPayment();
    }

    private void requestAddPayment() {
        EditText edtNote = findViewById(R.id.edt_note);
        EditText edtAmount = findViewById(R.id.edt_amount);

        Button btnSave = findViewById(R.id.btn_save);
        Payment payment = new Payment();

        transImage = 0;
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtNote.getText() != null) {
                    transNote = edtNote.getText().toString();
                } else {
                    transNote = "";
                }

                if (edtAmount.getText() != null || edtAmount.getText().toString().equals("")) {
                    transTotal = edtAmount.getText().toString();
                } else {
                    edtAmount.getText().toString();
                    edtAmount.requestFocus();
                    edtAmount.setError("This field cannot be empty");
                    return;
                }

                if (transWalletId == null) {
                    Toast.makeText(AddPayment.this, "Please select wallet", Toast.LENGTH_SHORT).show();
                    return;
                }

                transDate = Utils.getDateInLong(year, month, day);

                payment.setPostTime(Utils.getSystemCurrentTimeInMilli());
                payment.setTransDate(transDate);
                payment.setTransCategory(transCategory);
                payment.setTransCategoryId(transCategoryId);
                payment.setTransNote(transNote);
                payment.setTransTotal(transTotal);
                payment.setTransWalletId(transWalletId);

                viewModel.requestInsertPayment(transWalletId, payment);

                // Launch wallet page
                Intent toWallet = new Intent(AddPayment.this, MainActivity.class);
                toWallet.putExtra(Constants.WALLET_UID_KEY, transWalletId);
                startActivity(toWallet);
                finish();
            }
        });
    }

    private void setupSmallWalletRecycler() {
        RecyclerView walletRecyclerView = findViewById(R.id.wallet_recycler_view);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        SmallWalletAdapter smallWalletAdapter = new SmallWalletAdapter(viewModel, this);

        walletRecyclerView.setHasFixedSize(true);
        walletRecyclerView.setClipToPadding(true);
        walletRecyclerView.setLayoutManager(linearLayoutManager);
        walletRecyclerView.setAdapter(smallWalletAdapter);

        smallWalletAdapter.setOnItemClickListener(new SmallWalletAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@Nullable String selectWalletId) {
                Log.e(TAG, "onItemClick: " + selectWalletId);

                transWalletId = selectWalletId;
            }
        });
    }

    private void setupCategoryRecycler() {
        RecyclerView categoryRecyclerView = findViewById(R.id.category_recycler_view);
        RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
        CategoryAdapter categoryAdapter = new CategoryAdapter();
        categoryAdapter.setHasStableIds(true);

        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setClipToPadding(true);
        categoryRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String categoryId, String categoryName) {
                Log.e(TAG, "onItemClick: " + categoryId);

                transCategory = categoryName;
                transCategoryId = categoryId;
            }
        });
    }

    private void myInit() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ActionBar topBar = getSupportActionBar();
        if (topBar != null) {
            topBar.setHomeAsUpIndicator(R.drawable.icon_arrow_back); // Set icon for back button
            topBar.setDisplayHomeAsUpEnabled(true); // Add back button with selected icon
            topBar.setDisplayShowTitleEnabled(false); // Remove default title
        }

        TextView tvSelectData = findViewById(R.id.tv_payment_date);
        tvSelectData.setText(Utils.getCurrentDate());
        tvSelectData.setOnClickListener(selectDateDialog(tvSelectData)); // Open dialog to select date
    }

    private View.OnClickListener selectDateDialog(TextView tvDate) {

        final Calendar calendar = Calendar.getInstance();

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        return view -> {

            new DatePickerDialog(AddPayment.this, (datePicker, y, m, d) -> {

                tvDate.setText(Utils.getDate(y, m, d));

                day = d;
                month = m;
                year = y;

            }, year, month, day).show();
        };
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}