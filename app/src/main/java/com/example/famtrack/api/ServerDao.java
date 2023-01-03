package com.example.famtrack.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.famtrack.R;
import com.example.famtrack.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerDao {

    private static final String TAG = "com.example.famtrack.api.ServerDao";
    private static ServerDao instance;
    private final DatabaseReference firebaseReferenceAPI;

    private ServerDao() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true); // Enable disk persistence

        firebaseReferenceAPI = firebaseDatabase.getReference();
    }

    public static ServerDao getInstance() {
        if (instance == null) {
            instance = new ServerDao();
        }

        return instance;
    }

    public LiveData<List<Wallet>> requestAllWallet(@NonNull String userUid) {

        MutableLiveData<List<Wallet>> mutableWalletList = new MutableLiveData<>();

        firebaseReferenceAPI.child("user").child(userUid).child("groups").addValueEventListener(new ValueEventListener() {
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

    public LiveData<List<Payment>> requestAllPayment(@NonNull String walletUid) {

        MutableLiveData<List<Payment>> mutablePaymentList = new MutableLiveData<>();

        firebaseReferenceAPI.child("trans").child(walletUid).addValueEventListener(new ValueEventListener() {
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

    public void requestInsertPayment(@NonNull String walletUid, Payment paymentData) {

        // Put data in hashmap type
        Map<String, Object> paymentHashMap = new HashMap<>();
        paymentHashMap.put(firebaseReferenceAPI.push().getKey(), paymentData);

        firebaseReferenceAPI.child("trans").child(walletUid).updateChildren(paymentHashMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.i(TAG, "requestInsertPayment: Add payment success fully");
                    }
                })
                .addOnFailureListener(e -> Log.e(TAG, "onFailure: ", e));
    }

    public void requestUpdateWalletData(@NonNull String userUid, @NonNull String walletUid, Payment paymentData) {

        // Put data in hashmap type
        Map<String, Object> walletHashmap = new HashMap<>();
        walletHashmap.put("groupActiveTime", paymentData.getPostTime());
        walletHashmap.put("groupBalance", paymentData.getTransTotal());

        firebaseReferenceAPI.child("user").child(userUid).child("groups").child(walletUid).updateChildren(walletHashmap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.i(TAG, "requestUpdateWalletData: Update payment successfully");
                    }
                })
                .addOnFailureListener(e -> Log.e(TAG, "requestUpdateWalletData: ", e));
    }

    public void requestCreateWallet(String userUid, String walletName) {

        Wallet walletData = new Wallet();
        walletData.setGroupBalance(0);
        walletData.setGroupActiveTime(Utils.getSystemCurrentTimeInMilli());
        walletData.setGroupId(firebaseReferenceAPI.push().getKey());
        walletData.setGroupImage(String.valueOf(R.drawable.european_union));
        walletData.setGroupMember(1);
        walletData.setGroupName(walletName);

        Map<String, Object> walletHashmap = new HashMap<>();
        walletHashmap.put(walletData.getGroupId(), walletData);

        firebaseReferenceAPI.child("user").child(userUid).child("groups").updateChildren(walletHashmap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.i(TAG, "requestCreateWallet: Successfully create new wallet");
                    }
                })
                .addOnFailureListener(e -> Log.e(TAG, "requestCreateWallet: ", e));
    }
}
