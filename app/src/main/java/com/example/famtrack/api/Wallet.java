package com.example.famtrack.api;

import java.util.List;

public class Wallet {
    private String groupId;
    private List<String> groupMember;
    private String groupName;

    public Wallet() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(List<String> groupMember) {
        this.groupMember = groupMember;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
