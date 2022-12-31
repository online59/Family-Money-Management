package com.example.famtrack.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.famtrack.R;
import com.example.famtrack.helper.MyItemDetailsLookup;
import com.example.famtrack.helper.MyItemKeyProvider;
import com.example.famtrack.vm.MainViewModel;

public class AddPayment extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.AddPayment";
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        myInit();
        setupCategoryRecycler();
        setupSmallWalletRecycler();
    }

    private void setupSmallWalletRecycler() {
        RecyclerView recyclerView = findViewById(R.id.wallet_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        SmallWalletAdapter adapter = new SmallWalletAdapter(viewModel, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SmallWalletAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@Nullable String selectWalletId) {
                Log.e(TAG, "onItemClick: " + selectWalletId );
            }
        });
    }

    private void setupCategoryRecycler() {
        RecyclerView recyclerView = findViewById(R.id.category_recycler_view);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
        CategoryAdapter adapter = new CategoryAdapter();
        adapter.setHasStableIds(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String categoryId) {
                Log.e(TAG, "onItemClick: " + categoryId);
//                view.setBackgroundColor(getResources().getColor(R.color.dark_green));
            }
        });

        SelectionTracker<Long> selectionTracker = new SelectionTracker.Builder<>(
                "category_selection",
                recyclerView,
                new MyItemKeyProvider(recyclerView),
                new MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .build();

        selectionTracker.addObserver(
                new SelectionTracker.SelectionObserver<Long>() {
                    @Override
                    public void onSelectionChanged() {
                        super.onSelectionChanged();
                        Log.e(TAG, "onSelectionChanged: You can't select more than 1 item");
                    }
                }
        );

        adapter.setSelectionTracker(selectionTracker);
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
        }
        return super.onOptionsItemSelected(item);
    }
}