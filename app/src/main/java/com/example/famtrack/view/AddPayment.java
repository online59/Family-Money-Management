package com.example.famtrack.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.famtrack.R;
import com.example.famtrack.api.Category;

public class AddPayment extends AppCompatActivity {

    private static final String TAG = "com.example.famtrack.view.AddPayment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        myInit();
        setupCategoryRecycler();
    }

    private void setupCategoryRecycler() {
        RecyclerView recyclerView = findViewById(R.id.category_recycler_view);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL);
        CategoryAdapter adapter = new CategoryAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG, "onItemClick: " + position );
            }
        });
    }

    private void myInit() {
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