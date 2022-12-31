package com.example.famtrack.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.famtrack.R;
import com.example.famtrack.utils.Constants;
import com.example.famtrack.vm.MainViewModel;

public class WalletFragment extends Fragment {

    private static final String TAG = "com.example.famtrack.view.WalletFragment";
    private final String userUid = "HTtFP8Oh1hd1nDUxzufhdMBzHx93";
    private MainViewModel viewModel;

    public WalletFragment() {
        // Required empty public constructor
    }

    public static WalletFragment newInstance() {
        return new WalletFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        myInit();
        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_wallet);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        WalletAdapter adapter = new WalletAdapter(userUid, viewModel, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        // Handling click events
        adapter.setOnItemClickListener((position, walletUid) -> {
            Log.e(TAG, "onItemClick: Position = " + position);
            openSelectedWallet(walletUid);
        });
    }

    private void myInit() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void openSelectedWallet(String uid) {
        Bundle walletUid = new Bundle();
        walletUid.putString(Constants.WALLET_UID_KEY, uid);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PaymentFragment.class, walletUid)
                .commit();
    }
}