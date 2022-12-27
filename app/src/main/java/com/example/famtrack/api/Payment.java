package com.example.famtrack.api;

public class Payment {

    private long postTime, transDate;
    private int transImage;
    private String transCategory, transCategoryId, transNote, transTotal, transWalletId;

    public Payment() {
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public long getTransDate() {
        return transDate;
    }

    public void setTransDate(long transDate) {
        this.transDate = transDate;
    }

    public int getTransImage() {
        return transImage;
    }

    public void setTransImage(int transImage) {
        this.transImage = transImage;
    }

    public String getTransCategory() {
        return transCategory;
    }

    public void setTransCategory(String transCategory) {
        this.transCategory = transCategory;
    }

    public String getTransCategoryId() {
        return transCategoryId;
    }

    public void setTransCategoryId(String transCategoryId) {
        this.transCategoryId = transCategoryId;
    }

    public String getTransNote() {
        return transNote;
    }

    public void setTransNote(String transNote) {
        this.transNote = transNote;
    }

    public String getTransTotal() {
        return transTotal;
    }

    public void setTransTotal(String transTotal) {
        this.transTotal = transTotal;
    }

    public String getTransWalletId() {
        return transWalletId;
    }

    public void setTransWalletId(String transWalletId) {
        this.transWalletId = transWalletId;
    }
}
