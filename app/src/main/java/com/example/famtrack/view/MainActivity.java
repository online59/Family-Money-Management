package com.example.famtrack.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
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
                .commit();
    }

    private void myInit() {
        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.icon_arrow_back); // Set icon for back button
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Add back button with selected icon
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Remove default title
        }

        // Set click listener for navigation button
        toolbar.setNavigationOnClickListener(navigationClickListener());
    }

    private View.OnClickListener navigationClickListener() {

        Log.e(TAG, "navigationClickListener: called" );
        // Check if the current fragment is the WalletFragment
        if (getCurrentFragment()) {
            return view -> setupFragment();
        } else {
            return null;
        }
    }

    private boolean getCurrentFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Log.e(TAG, "getCurrentFragment: " + currentFragment );
        return currentFragment instanceof PaymentFragment;
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