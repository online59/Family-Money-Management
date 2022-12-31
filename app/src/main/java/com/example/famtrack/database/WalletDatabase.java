package com.example.famtrack.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WalletDatabaseModel.class}, version = 2)
public abstract class WalletDatabase extends RoomDatabase {

    public abstract WalletDao walletDao();
    private static WalletDatabase instance;

    public static synchronized WalletDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), WalletDatabase.class, "wallets")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
