package com.example.famtrack.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WalletDao {

    @Query("SELECT * FROM WalletDatabaseModel ORDER BY last_active_time DESC")
    LiveData<List<WalletDatabaseModel>> getAllWallet();

    @Insert(entity = WalletDatabaseModel.class, onConflict = OnConflictStrategy.IGNORE)
    void insetNewWallet(WalletDatabaseModel walletDatabaseModel);

    @Query("DELETE FROM WalletDatabaseModel")
    void deleteAllWallet();
}
