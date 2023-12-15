package com.example.recyclerview;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private LiveData<List<ContactEntity>> contactsLiveData;
    private ContactDao contactDao;

    public ContactViewModel(Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        contactDao = appDatabase.contactDao();
        contactsLiveData = contactDao.getAllContactsLiveData();
    }

    public LiveData<List<ContactEntity>> getContactsLiveData() {
        return contactsLiveData;
    }

    public void insertContact(ContactEntity contact) {
        // Perform database insertion on a background thread
        AsyncTask.execute(() -> contactDao.insert(contact));
    }
}


