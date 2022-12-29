package com.example.famtrack.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.famtrack.R;
import com.example.famtrack.vm.MainViewModel;


public class PaymentActivity extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.PaymentActivity";
    private MainViewModel viewModel;
    private final String walletUid = "HTtFP8Oh1hd1nDUxzufhdMBzHx93";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        myInit();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_payment);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        PaymentAdapter paymentAdapter = new PaymentAdapter(walletUid, viewModel, this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        HeaderItemDecoration headerItemDecoration = new HeaderItemDecoration(true, getHeaderCallback(paymentAdapter));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(paymentAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.addItemDecoration(headerItemDecoration);

        // Set item click listener
        paymentAdapter.setOnItemClickListener(position -> Log.e(TAG, "onItemClick: " + position));
    }

    private void myInit() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == androidx.appcompat.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private HeaderItemDecoration.HeaderCallback getHeaderCallback(final PaymentAdapter paymentAdapter) {

        return new HeaderItemDecoration.HeaderCallback() {
            @Override
            public boolean isHeader(int position) {

                return paymentAdapter.isHeader(position);
            }

            @Override
            public String getHeader(int position) {

                return paymentAdapter.getHeader(position);
            }
        };
    }
}