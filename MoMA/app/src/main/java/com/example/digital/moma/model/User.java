package com.example.digital.moma.model;

import android.os.Parcelable;

public class User {
    private String name;
    private Parcelable image;

    public User(String name, Parcelable image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Parcelable getImage() {
        return image;
    }
}
