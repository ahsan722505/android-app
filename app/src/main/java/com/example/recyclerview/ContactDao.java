package com.example.recyclerview;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(ContactEntity contact);

    @Query("SELECT * FROM ContactEntity")
    List<ContactEntity> getAllContacts();
    @Query("SELECT * FROM ContactEntity")
    LiveData<List<ContactEntity>> getAllContactsLiveData();
}

