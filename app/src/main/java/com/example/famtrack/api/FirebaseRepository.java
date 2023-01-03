package com.example.famtrack.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FirebaseRepository {

    private static final String TAG = "com.example.famtrack.api.FirebaseRepository";
    private static FirebaseRepository instance;
    private final ServerDao serverDao;

    public FirebaseRepository() {
        serverDao = ServerDao.getInstance();
    }

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    public LiveData<List<Wallet>> requestAllWallet(@NonNull String userUid) {
        return serverDao.requestAllWallet(userUid);
    }

    public LiveData<List<Payment>> requestAllPayment(@NonNull String walletUid) {
        return serverDao.requestAllPayment(walletUid);
    }

    public void requestInsertPayment(@NonNull String walletUid, Payment paymentData) {
        serverDao.requestInsertPayment(walletUid, paymentData);
    }

    public void requestUpdateWalletData(@NonNull String userUid, @NonNull String walletUid, Payment paymentData) {
        serverDao.requestUpdateWalletData(userUid, walletUid, paymentData);
    }
}
