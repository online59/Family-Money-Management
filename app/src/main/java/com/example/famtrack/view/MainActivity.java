package com.example.famtrack.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.example.famtrack.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInit();
        setupFragment();
    }

    private void setupFragment() {
        // First start the wallet fragment to show all user's wallets
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WalletFragment.class, null, null)
                .addToBackStack(null) // Add fragment to back stack
                .commit();
    }

    private void myInit() {

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.icon_arrow_back); // Set icon for back button
            actionBar.setDisplayHomeAsUpEnabled(true); // Add back button with selected icon
            actionBar.setDisplayShowTitleEnabled(false); // Remove default title
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

        int id = item.getItemId();
        if (id == R.id.menu_add_payment) {
            startActivity(toAddPaymentPage);
        } else if (id == android.R.id.home) {
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