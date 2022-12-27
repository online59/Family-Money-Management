package com.example.famtrack.vm;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.famtrack.api.FirebaseRepository;
import com.example.famtrack.database.WalletModel;
import com.example.famtrack.database.WalletRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "com.example.famtrack.vm.MainViewModel";
    private WalletRepository walletRepository;
    private FirebaseRepository firebaseRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        walletRepository = WalletRepository.getInstance(application);
        firebaseRepository = FirebaseRepository.getInstance();
    }

    public LiveData<List<WalletModel>> getAllWallet() {
        return walletRepository.getAllWallet();
    }

    public void requestUserData(String path, LifecycleOwner lifecycleOwner) {
        firebaseRepository.requestAllWallet(path).observe(lifecycleOwner, userData -> {
            Log.e(TAG, "requestUserData: " + userData);
        });
    }
}
