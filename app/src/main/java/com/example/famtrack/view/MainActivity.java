package com.example.famtrack.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.famtrack.R;
import com.example.famtrack.helper.CreateWalletDialog;
import com.example.famtrack.utils.Constants;
import com.example.famtrack.vm.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.MainActivity";
    private String userUid = "HTtFP8Oh1hd1nDUxzufhdMBzHx93";
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInit();
        inflateWalletFragment();
        addPayment();
    }

    private void addPayment() {
        FloatingActionButton fabAddPayment = findViewById(R.id.fab_add_payment);

        Intent toAddPaymentPage = new Intent(this, AddPayment.class);
        fabAddPayment.setOnClickListener(view -> startActivity(toAddPaymentPage));
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
                .replace(R.id.fragment_container, WalletFragment.class, null, Constants.TAG_WALLET_FRAGMENT)
                .addToBackStack(Constants.STACK_WALLET_PAGE) // Add fragment to back stack
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

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent toAddPaymentPage = new Intent(this, AddPayment.class);

        int menuItemId = item.getItemId();

        if (menuItemId == android.R.id.home) {

            onBackPressed(); // Back to previous page

        } else if (menuItemId == R.id.menu_add_payment) {

            startActivity(toAddPaymentPage);

        } else if (menuItemId == R.id.menu_new_wallet) {

            CreateWalletDialog createWalletDialog = new CreateWalletDialog();
            createWalletDialog.show(getSupportFragmentManager(), null);
            createWalletDialog.setOnDialogClickListener(walletName -> viewModel.requestCreateWallet(userUid, walletName));

        } else if (menuItemId == R.id.menu_invite_member) {

            Toast.makeText(this, "Invite Member", Toast.LENGTH_SHORT).show();

        } else if (menuItemId == R.id.menu_edit_group) {

            Toast.makeText(this, "Edit Group", Toast.LENGTH_SHORT).show();

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        // Find the wallet fragment by its tag
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment walletFragment = fragmentManager.findFragmentByTag(Constants.TAG_WALLET_FRAGMENT);

        if (walletFragment != null && fragmentManager.getBackStackEntryCount() > 0) {

            // Go back util find a wallet fragment
            while (!walletFragment.isVisible()) {
                super.onBackPressed();
            }
        }
    }


    // Get intent from AddPayment class
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Get walletUid
        String walletUid = intent.getStringExtra(Constants.WALLET_UID_KEY);

        if (walletUid != null) {

            // Open a fragment and show the payments of the specific wallet
            openSelectedWallet(walletUid);
        }
    }
}