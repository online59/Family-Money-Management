package com.example.famtrack.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.famtrack.R;
import com.example.famtrack.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInit();
        Log.e(TAG, "onCreate: called" + getIntentResult());

        if (getIntentResult() != null) {
            openSelectedWallet(getIntentResult());
        } else {
            inflateWalletFragment();
        }

    }

    private String getIntentResult() {
        String walletId = null;

        if (getIntent() != null) {
            walletId = getIntent().getStringExtra(Constants.WALLET_UID_KEY);
        }

        return walletId;
    }

    private void openSelectedWallet(String uid) {
        Bundle walletUid = new Bundle();
        walletUid.putString(Constants.WALLET_UID_KEY, uid);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PaymentFragment.class, walletUid)
                .addToBackStack(null)
                .commit();
    }

    private void inflateWalletFragment() {
        // First start the wallet fragment to show all user's wallets
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WalletFragment.class, null, null)
                .addToBackStack(null) // Add fragment to back stack
                .commit();
    }

    private void myInit() {

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ActionBar topBar = getSupportActionBar();
        if (topBar != null) {
            topBar.setHomeAsUpIndicator(R.drawable.icon_arrow_back); // Set icon for back button
            topBar.setDisplayHomeAsUpEnabled(true); // Add back button with selected icon
            topBar.setDisplayShowTitleEnabled(false); // Remove default title
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent toAddPaymentPage = new Intent(this, AddPayment.class);

        int menuItemId = item.getItemId();
        if (menuItemId == R.id.menu_add_payment) {
            startActivity(toAddPaymentPage);
        } else if (menuItemId == android.R.id.home) {
            onBackPressed(); // Back to previous page
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        // Check if item in back stack is not less than 1
        if (!(getSupportFragmentManager().getBackStackEntryCount() <= 1)) {
            super.onBackPressed();
        }
    }
}