package com.example.famtrack.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.famtrack.R;
import com.example.famtrack.helper.HeaderItemDecoration;
import com.example.famtrack.utils.Constants;
import com.example.famtrack.vm.MainViewModel;

import java.util.Objects;

public class PaymentFragment extends Fragment {

    private static final String TAG = "com.example.famtrack.view.PaymentFragment";
    private MainViewModel viewModel;
    private String walletUid;

    public PaymentFragment() {
        // Required empty public constructor
    }

    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            walletUid = getArguments().getString(Constants.WALLET_UID_KEY);
        }

        // To indicate that the fragment has an options menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        myInit();
        setupPaymentRecyclerView(view);
        return view;
    }

    private void setupPaymentRecyclerView(View view) {
        RecyclerView paymentRecyclerView = view.findViewById(R.id.recycler_view_payment);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        PaymentAdapter paymentAdapter = new PaymentAdapter(walletUid, viewModel, this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        HeaderItemDecoration headerItemDecoration = new HeaderItemDecoration(true, getHeaderCallback(paymentAdapter));

        paymentRecyclerView.setHasFixedSize(true);
        paymentRecyclerView.setLayoutManager(linearLayoutManager);
        paymentRecyclerView.setAdapter(paymentAdapter);
        paymentRecyclerView.addItemDecoration(dividerItemDecoration);
        paymentRecyclerView.addItemDecoration(headerItemDecoration);

        // Set item click listener
        paymentAdapter.setOnItemClickListener(position -> Log.e(TAG, "onItemClick: " + position));
    }

    private void myInit() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.wallet_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        int menuId = menuItem.getItemId();
        if (menuId == R.id.menu_invite_member) {
            Toast.makeText(getContext(), "Invite Member", Toast.LENGTH_SHORT).show();
        } else if (menuId == R.id.menu_edit_group) {
            Toast.makeText(getContext(), "Edit Group", Toast.LENGTH_SHORT).show();
        } else if (menuId == android.R.id.home) {
            requireActivity().onBackPressed();
        }
        return false;
    }
}