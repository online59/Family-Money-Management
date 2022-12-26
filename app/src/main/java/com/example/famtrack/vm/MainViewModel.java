package com.example.famtrack.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.famtrack.database.WalletModel;
import com.example.famtrack.database.WalletRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private WalletRepository walletRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        walletRepository = WalletRepository.getInstance(application);
    }

    public LiveData<List<WalletModel>> getAllWallet() {
        return walletRepository.getAllWallet();
    }
}
