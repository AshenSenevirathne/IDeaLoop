package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idealoop.busseek.model.Bus;
import com.idealoop.busseek.model.Seat;

import java.util.ArrayList;

public class mubooks extends AppCompatActivity {

    ListView list;
    DatabaseReference DBRef;
    String url,fullname,customertype,email,id,username;
    ArrayAdapter<String> seatlist;
    ArrayList<String> seats = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mubooks);

        list = findViewById(R.id.list);

        final Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        id = extras.getString("id");
        url = extras.getString("url");
        fullname = extras.getString("fullname");
        email = extras.getString("email");
        customertype = extras.getString("customertype");

        seatlist = new ArrayAdapter(mubooks.this, android.R.layout.simple_list_item_1,seats);

        DBRef = FirebaseDatabase.getInstance().getReference().child("Seats").child(username);

        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Seat seat = dataSnapshot1.getValue(Seat.class);

                    String seatinfo = "Seat ID: "+seat.getSeatId()+"\nBus ID: "+seat.getBusID()+"\nSeat No: "+seat.getSeatno();
                    seats.add(seatinfo);

                }
                list.setAdapter(seatlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}