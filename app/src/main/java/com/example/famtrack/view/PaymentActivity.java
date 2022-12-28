package com.example.famtrack.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.famtrack.R;
import com.example.famtrack.api.Payment;
import com.example.famtrack.utils.Utils;
import com.example.famtrack.vm.MainViewModel;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.PaymentActivity";
    private MainViewModel viewModel;
    private final String path = "trans/HTtFP8Oh1hd1nDUxzufhdMBzHx93";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        myInit();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_payment);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        PaymentAdapter adapter = new PaymentAdapter(path, viewModel, this);
        ItemSeparatorDecoration separatorDecoration = new ItemSeparatorDecoration(this);
        HeaderItemDecoration headerDecoration = new HeaderItemDecoration(true, getHeaderCallback(adapter.getDataList().getValue()));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(separatorDecoration);
        recyclerView.addItemDecoration(headerDecoration);

        adapter.setOnItemClickListener(position -> Log.e(TAG, "onItemClick: " + position));
    }

    private void myInit() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == androidx.appcompat.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private HeaderItemDecoration.HeaderCallback getHeaderCallback(final List<Payment> paymentList) {

        return new HeaderItemDecoration.HeaderCallback() {
            @Override
            public boolean isHeader(int position) {

                // Because the payment list maybe null, so we check for null
                if (paymentList == null) {
                    return false;
                }

                // If there is no data, not drawing header
                if (paymentList.size() == 0) {
                    return false;
                }

                // At the first item of the list, make it the header
                if (position == 0) {
                    return true;
                }

                String currentHeader = Utils.getDate(paymentList.get(position).getTransDate());
                String previousHeader = Utils.getDate(paymentList.get(position - 1).getTransDate());

                // If the current header name match the previous header, not drawing header, else draw header
                return !currentHeader.equalsIgnoreCase(previousHeader);
            }

            @Override
            public String getHeader(int position) {

                if (paymentList == null || paymentList.size() == 0) {
                    return "";
                }

                // Text to put as a header
                return Utils.getDate(paymentList.get(position).getTransDate());
            }
        };
    }
}