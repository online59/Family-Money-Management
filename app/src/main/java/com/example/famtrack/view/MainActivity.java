package com.example.famtrack.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.utils.Constants;
import com.example.famtrack.vm.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.MainActivity";
    private final String walletUid = "HTtFP8Oh1hd1nDUxzufhdMBzHx93";
    private final String userUid = "HTtFP8Oh1hd1nDUxzufhdMBzHx93";
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInit();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_wallet);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        WalletAdapter adapter = new WalletAdapter(walletUid, viewModel, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        // Handling click events
        Intent toPaymentPage = new Intent(this, PaymentActivity.class);
        adapter.setOnItemClickListener((position, walletUid) -> {
            Log.e(TAG, "onItemClick: Position = " + position);
            toPaymentPage.putExtra(Constants.INTENT_WALLET_UID_KEY, walletUid);
            startActivity(toPaymentPage);
        });
    }

    private void myInit() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.requestAllWallet(userUid);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent toAddPaymentPage = new Intent(this, AddPayment.class);

        if (item.getItemId() == R.id.menu_add_payment) {
            startActivity(toAddPaymentPage);
        }
        return true;
    }
}