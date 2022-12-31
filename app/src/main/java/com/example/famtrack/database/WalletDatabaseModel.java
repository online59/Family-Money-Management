package com.example.famtrack.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WalletDatabaseModel {

    @NonNull
    @PrimaryKey()
    private String uid;

    @ColumnInfo(name = "wallet_image_res")
    private int ivWallet;

    @ColumnInfo(name = "wallet_name")
    private String tvWalletName;

    @ColumnInfo(name = "current_balance")
    private String tvCurrentBalance;

    @ColumnInfo(name = "last_active_time")
    private   String tvLastActive;

    @ColumnInfo(name = "member_count")
    private  int tvMemberCount;

    public WalletDatabaseModel() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getIvWallet() {
        return ivWallet;
    }

    public void setIvWallet(int ivWallet) {
        this.ivWallet = ivWallet;
    }

    public String getTvWalletName() {
        return tvWalletName;
    }

    public void setTvWalletName(String tvWalletName) {
        this.tvWalletName = tvWalletName;
    }

    public String getTvCurrentBalance() {
        return tvCurrentBalance;
    }

    public void setTvCurrentBalance(String tvCurrentBalance) {
        this.tvCurrentBalance = tvCurrentBalance;
    }

    public String getTvLastActive() {
        return tvLastActive;
    }

    public void setTvLastActive(String tvLastActive) {
        this.tvLastActive = tvLastActive;
    }

    public int getTvMemberCount() {
        return tvMemberCount;
    }

    public void setTvMemberCount(int tvMemberCount) {
        this.tvMemberCount = tvMemberCount;
    }
}
