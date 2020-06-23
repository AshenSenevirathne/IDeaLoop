package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idealoop.busseek.model.Bus;

import java.util.ArrayList;

public class SeatBook extends AppCompatActivity {

    EditText from,to;
    TextView timeset;
    Button settime,search;
    String sfrom,sto,stime,dfrom,dto,dtime;
    ListView list;
    ArrayAdapter adapter,beforeadapter;
    DatabaseReference DBF,DBFB;
    ArrayList<String> searchedbus = new ArrayList<>();
    ArrayList<Bus> buslist = new ArrayList<>();
    ArrayList<String> beforesearchedbus = new ArrayList<>();
    ArrayList<Bus> beforebuslist = new ArrayList<>();
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_book);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        timeset = findViewById(R.id.timeset);
        settime = findViewById(R.id.settime);
        search = findViewById(R.id.search);
        list = findViewById(R.id.list);

        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.timeset);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });




        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchedbus.clear();

               if(!timeset.getText().toString().substring(0, 2).equalsIgnoreCase("Se")) {
                   sfrom = from.getText().toString();
                   sto = to.getText().toString();
                   stime = timeset.getText().toString();
                   System.out.println("Sto:"+sto+";;;;");
                   if(!(sto.isEmpty())) {
                        if(!(sfrom.isEmpty())) {
                            DBF = FirebaseDatabase.getInstance().getReference().child(stime.substring(0, 2));
                            DBF.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        Bus bus = dataSnapshot1.getValue(Bus.class);


                                        if (sfrom.equalsIgnoreCase(bus.getFrom()) && sto.equalsIgnoreCase(bus.getTo())) {

                                            for (int i = 0; i < 8; i++) {
                                                System.out.println(stime);

                                                if (bus.getTimeSlots().get(i).substring(0, 2).equalsIgnoreCase(stime.substring(0, 2))) {
                                                    System.out.println("##################################################################");
                                                    time = bus.getTimeSlots().get(i);
                                                }
                                            }

                                            String listing = "Bus ID: " + bus.getBusID() + "\nBus Route: " + bus.getRouteno() + "\nFrom: " + bus.getFrom() + "\nTo: " + bus.getTo() + "\nTime: " + time;
                                            searchedbus.add(listing);
                                            buslist.add(bus);
                                            System.out.println(listing);
                                        }

                                    }
                                    adapter = new ArrayAdapter(SeatBook.this, android.R.layout.simple_list_item_1, searchedbus);
                                    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListAll.this, android.R.layout.simple_list_item_1,deliveries);
                                    list.setAdapter(adapter);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        else
                            Toast.makeText(SeatBook.this,"From is empty, Please insert it", Toast.LENGTH_SHORT).show();
                   }
                   else
                       Toast.makeText(SeatBook.this,"To is empty, Please insert it", Toast.LENGTH_SHORT).show();
               }
               else
                   Toast.makeText(SeatBook.this,"Set time to Search", Toast.LENGTH_SHORT).show();
                //Array list ekata danna one username eka seat array ekta
                //eka wenas karanna

            }
        });

    }

    public void onStart() {

        super.onStart();
        DBFB = FirebaseDatabase.getInstance().getReference().child("BusList");

        DBFB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Bus bus = dataSnapshot1.getValue(Bus.class);
                    System.out.println("From DB: "+bus.getBusID());

                    String listing = "Bus ID: " + bus.getBusID() + "\nBus Route: " + bus.getRouteno() + "\nFrom: " + bus.getFrom() + "\nTo: " + bus.getTo() + "\nTime 1: " + bus.getTimeSlots().get(0) + "\nTime 2: " + bus.getTimeSlots().get(2) + "\nTime 3: " + bus.getTimeSlots().get(4) + "\nTime 4: " + bus.getTimeSlots().get(6);
                    beforesearchedbus.add(listing);
                    beforebuslist.add(bus);

                }
                beforeadapter = new ArrayAdapter(SeatBook.this, android.R.layout.simple_list_item_1,beforesearchedbus);
                list.setAdapter(beforeadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onBackPressed(){
        onStart();
        Toast.makeText(SeatBook.this,"Double Press Back to Dashboard", Toast.LENGTH_SHORT).show();
    }

}
