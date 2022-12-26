package com.example.famtrack.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WalletDao {

    @Query("SELECT * FROM walletmodel")
    LiveData<List<WalletModel>> getAllWallet();

    @Insert(entity = WalletModel.class, onConflict = OnConflictStrategy.IGNORE)
    void insetNewWallet(WalletModel walletModel);

    @Query("DELETE FROM walletmodel")
    void deleteAllWallet();
}
