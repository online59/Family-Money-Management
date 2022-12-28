package com.example.famtrack.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.famtrack.api.FirebaseRepository;
import com.example.famtrack.api.Payment;
import com.example.famtrack.api.Wallet;
import com.example.famtrack.database.WalletModel;
import com.example.famtrack.database.WalletRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "com.example.famtrack.vm.MainViewModel";
    private final WalletRepository walletRepository;
    private final FirebaseRepository firebaseRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        walletRepository = WalletRepository.getInstance(application);
        firebaseRepository = FirebaseRepository.getInstance();
    }

    public LiveData<List<WalletModel>> getAllWallet() {
        return walletRepository.getAllWallet();
    }

    public LiveData<List<Wallet>> requestAllWallet(String path) {
        return firebaseRepository.requestAllWallet(path);
    }

    public LiveData<List<Payment>> requestAllPayment(String path) {
        return firebaseRepository.requestAllPayment(path);
    }
}
