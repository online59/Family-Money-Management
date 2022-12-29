package com.example.famtrack.api;

import java.util.List;

public class Wallet {
    private String groupId;
    private int groupMember;
    private String groupName;
    private String groupImage;
    private int groupBalance;
    private long groupActiveTime;

    public Wallet() {
    }

    public int getGroupBalance() {
        return groupBalance;
    }

    public void setGroupBalance(int groupBalance) {
        this.groupBalance = groupBalance;
    }

    public long getGroupActiveTime() {
        return groupActiveTime;
    }

    public void setGroupActiveTime(long groupActiveTime) {
        this.groupActiveTime = groupActiveTime;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(int groupMember) {
        this.groupMember = groupMember;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
