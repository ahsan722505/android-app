package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   ArrayList<ContactModel> arrContacts = new ArrayList<>();
    RecyclerContactAdapter adapter;
    RecyclerView recyclerView;
    private ContactViewModel contactViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        recyclerView= findViewById(R.id.recyclerContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton btnOpenDialog = findViewById(R.id.fab);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_contact);
                dialog.show();

                EditText nameEditText= dialog.findViewById(R.id.nameEditText);
                EditText contactNumberEditText= dialog.findViewById(R.id.contactNumberEditText);
                Button submitButton = dialog.findViewById(R.id.submitButton);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name= nameEditText.getText().toString();
                        String number = contactNumberEditText.getText().toString();

                        if(name.equals("") || number.equals("")){
                            Toast.makeText(MainActivity.this,"Make sure both fields are filled",Toast.LENGTH_SHORT).show();
                        }else{
                            // Insert into database
                            ContactEntity contactEntity = new ContactEntity();
                            contactEntity.img = R.mipmap.ic_launcher;
                            contactEntity.name = name;
                            contactEntity.number = number;
                            contactViewModel.insertContact(contactEntity);
                            arrContacts.add(new ContactModel(R.mipmap.ic_launcher,name,number));
                            adapter.notifyItemInserted(arrContacts.size()-1);
                            recyclerView.scrollToPosition(arrContacts.size()-1);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });


         adapter = new RecyclerContactAdapter(this,arrContacts);

        recyclerView.setAdapter(adapter);

        loadContactsFromDatabase();

    }

    private void loadContactsFromDatabase() {
        contactViewModel.getContactsLiveData().observe(this, contactEntities -> {
            arrContacts.clear();
            for (ContactEntity contactEntity : contactEntities) {
                arrContacts.add(new ContactModel(contactEntity.img, contactEntity.name, contactEntity.number));
            }
            adapter.notifyDataSetChanged();
        });
    }



}