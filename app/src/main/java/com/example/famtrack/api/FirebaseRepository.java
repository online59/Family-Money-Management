package com.example.famtrack.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepository {

    private static final String TAG = "com.example.famtrack.api.FirebaseRepository";
    private static FirebaseRepository instance;
    private final DatabaseReference firebaseReferenceAPI;

    public FirebaseRepository() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true); // Enable disk persistence
        firebaseReferenceAPI = firebaseDatabase.getReference();
    }

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }

        return instance;
    }

    public LiveData<List<Wallet>> requestAllWallet(@NonNull String path) {

        MutableLiveData<List<Wallet>> mutableWalletList = new MutableLiveData<>();

        firebaseReferenceAPI.child("user").child(path).child("groups").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<Wallet> walletList = new ArrayList<>();

                for (DataSnapshot walletSnapshotData : snapshot.getChildren()) {
                    walletList.add(walletSnapshotData.getValue(Wallet.class));
                }
                mutableWalletList.setValue(walletList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mutableWalletList.setValue(null);
            }
        });

        return mutableWalletList;
    }

    public LiveData<List<Payment>> requestAllPayment(@NonNull String path) {

        MutableLiveData<List<Payment>> mutablePaymentList = new MutableLiveData<>();

        firebaseReferenceAPI.child("trans").child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<Payment> paymentList = new ArrayList<>();

                for (DataSnapshot paymentSnapshotData : snapshot.getChildren()) {
                    paymentList.add(paymentSnapshotData.getValue(Payment.class));
                }
                mutablePaymentList.setValue(paymentList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mutablePaymentList.setValue(null);
            }
        });

        return mutablePaymentList;
    }

}
