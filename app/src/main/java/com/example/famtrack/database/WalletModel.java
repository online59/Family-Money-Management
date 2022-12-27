package com.example.famtrack.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WalletModel {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "wallet_image_res")
    private int ivWallet;

    @ColumnInfo(name = "wallet_name")
    private String tvWalletName;

    @ColumnInfo(name = "current_balance")
    private String tvCurrentBalance;

    @ColumnInfo(name = "last_active_time")
    private   String tvLastActive;

    @ColumnInfo(name = "member_count")
    private  String tvMemberCount;

    public WalletModel() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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

    public String getTvMemberCount() {
        return tvMemberCount;
    }

    public void setTvMemberCount(String tvMemberCount) {
        this.tvMemberCount = tvMemberCount;
    }
}
