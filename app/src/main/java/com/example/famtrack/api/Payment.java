package com.example.famtrack.api;

public class Payment implements Comparable<Payment>{

    private long postTime, transDate;
    private int transImage, transTotal;
    private String transCategory, transCategoryId, transNote, transWalletId;

    // Override compareTo method from Comparable interface
    @Override
    public int compareTo(Payment payment) {

        // CHeck if object is null
        if (getTransDate() == 0 || payment.getTransDate() == 0) {
            return 0;
        }

        // Sort is of payment object by transDate
        return compare(getTransDate(), payment.getTransDate());
    }

    // Compare two long and return negative, zero and positive value
    // if the object is less than, equal to and greater than the specific object
    private int compare(long specificPayment, long thisPayment) {
        int result;

        if ((specificPayment - thisPayment) > 0) {
            result = -1;
        } else if ((specificPayment - thisPayment) < 0) {
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    // Empty constructor
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

    public int getTransTotal() {
        return transTotal;
    }

    public void setTransTotal(int transTotal) {
        this.transTotal = transTotal;
    }

    public String getTransWalletId() {
        return transWalletId;
    }

    public void setTransWalletId(String transWalletId) {
        this.transWalletId = transWalletId;
    }
}
