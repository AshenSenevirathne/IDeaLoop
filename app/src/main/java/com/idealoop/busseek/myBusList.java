package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idealoop.busseek.adaptors.Owner_buslist_adaptor;
import com.idealoop.busseek.model.Bus;

import java.util.ArrayList;

public class myBusList extends AppCompatActivity {

    String url,fullname,customertype,email,id,username;
    RecyclerView recyclerMyBus;
    Owner_buslist_adaptor adaptor;
    ArrayList<Bus> list;
    DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bus_list);

        //busArrayList = new ArrayList<>();
        list = new ArrayList<Bus>();

        final Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        id = extras.getString("id");
        url = extras.getString("url");
        fullname = extras.getString("fullname");
        email = extras.getString("email");
        customertype = extras.getString("customertype");

      DBRef = FirebaseDatabase.getInstance().getReference().child("BusList");

      DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Bus bus = dataSnapshot1.getValue(Bus.class);
                    System.out.println("From DB: "+bus.getUsername());
                    System.out.println("From Intenet: "+username);
                    if(bus.getUsername().equals(username)) {

                        System.out.println(bus.getDrivercontact());
                        list.add(dataSnapshot1.getValue(Bus.class));
                    }
                }

                recyclerMyBus = findViewById(R.id.recylerMyBus);
                recyclerMyBus.setLayoutManager(new LinearLayoutManager(myBusList.this));
                adaptor = new Owner_buslist_adaptor(myBusList.this, list);
                recyclerMyBus.setAdapter(adaptor);

                recyclerMyBus.addOnItemTouchListener(new RecyclerItemClickListener(myBusList.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bus bus = list.get(position);
                        Intent intent =new Intent(myBusList.this, ViewBus.class);
                        intent.putExtra("bus",  bus);
                        intent.putExtra("busID",bus.getBusID());
                        intent.putExtra("username",username);
                        intent.putExtra("id",id);
                        intent.putExtra("url",url);
                        intent.putExtra("fullname",fullname);
                        intent.putExtra("email",email);
                        intent.putExtra("customertype",customertype);
                        startActivity(intent);
                    }
                }));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
