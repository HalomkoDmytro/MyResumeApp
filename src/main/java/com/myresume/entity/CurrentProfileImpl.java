package com.myresume.entity;

import lombok.Setter;

@Setter
public class CurrentProfileImpl implements CurrentProfile {

    private String id;
    private String uid;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUid() {
        return uid;
    }
}
