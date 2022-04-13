package com.example.firebasepractice.ui.chat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.firebasepractice.MainActivity;
import com.example.firebasepractice.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class ChatFragment extends Fragment
{
    Button chat_button_send;
    EditText editTextMessage;
    ListView listView;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ProgressDialog  progressDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        @SuppressLint("InflateParams") View view =  inflater.inflate(R.layout.fragment_chat, null, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Messages...");
        progressDialog.show();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("groupChat");

        chat_button_send   = view.findViewById(R.id.chat_button_send);
        editTextMessage    = view.findViewById(R.id.editTextMessage);
        listView           = view.findViewById(R.id.listView);

        ArrayList<String>arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), R.layout.chat_list_item, arrayList);
        listView.setAdapter(adapter);

        chat_button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String name = MainActivity.user_name; //get from database
                String message = editTextMessage.getText().toString();

                if(message.equals(""))
                {
                    Toast.makeText(getContext(),"Please enter Message", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    myRef.push().setValue(name + " : " + message);
                    editTextMessage.setText("");
                }
            }
        });

        load();

        return view;
    }

    public void load()
    {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String s = Objects.requireNonNull(snapshot.getValue()).toString();
                adapter.add(s);
                if(progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}