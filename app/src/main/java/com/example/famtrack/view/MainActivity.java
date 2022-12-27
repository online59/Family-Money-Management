package com.example.famtrack.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.famtrack.R;
import com.example.famtrack.vm.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.MainActivity";
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
        WalletAdapter adapter = new WalletAdapter(viewModel, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        // Handling click events
        adapter.setOnItemClickListener(new WalletAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e(TAG, "onItemClick: Position = " + position + " View = " + view);
            }
        });
    }

    private void myInit() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getAllWallet();

        String path = "user/HTtFP8Oh1hd1nDUxzufhdMBzHx93";
        viewModel.requestUserData(path, this);
    }
}