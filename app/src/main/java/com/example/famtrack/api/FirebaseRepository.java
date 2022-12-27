package com.example.famtrack.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseRepository {

    private static final String TAG = "com.example.famtrack.api.FirebaseRepository";
    private static FirebaseRepository instance;
    private final DatabaseReference databaseReference;

    public FirebaseRepository() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }

        return instance;
    }

    public LiveData<List<Wallet>> requestAllWallet(@NonNull String path) {

        MutableLiveData<List<Wallet>> walletList = new MutableLiveData<>();

        databaseReference.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String, Object> map = new HashMap<>();

                for (DataSnapshot data : snapshot.getChildren()) {
                    map.put(data.getKey(), data.getValue());
                    Log.e(TAG, "onDataChange: " + map);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                walletList.setValue(null);
            }
        });

        return walletList;
    }

    public LiveData<List<Payment>> requestAllPayment(@NonNull String path) {

        MutableLiveData<List<Payment>> paymentDataList = new MutableLiveData<>();

        databaseReference.child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<Payment> list = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {
                    list.add(data.getValue(Payment.class));
                }
                paymentDataList.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                paymentDataList.setValue(null);
            }
        });

        return paymentDataList;
    }

}
