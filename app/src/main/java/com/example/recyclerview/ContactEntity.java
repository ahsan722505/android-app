package com.example.recyclerview;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ContactEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int img;
    public String name;
    public String number;
}

