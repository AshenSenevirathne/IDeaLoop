package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class SeatBook extends AppCompatActivity {

    public static final  String[] DISTRICTS = new String[]{
            "Mahiyanganaya", "Diyathalawa", "Kandy", "Galigamuwa", "Kataragama", "Ramboada", "Paranthan", "Kottawa", "Kamburupitiya", "Girithale", "Karawanella", "Wellawaya", "Medagama", "Buttala", "Point", "Pedro", "Hakgala", "Kaduruwela", "Urani", "Thirukkovil", "Ginigathhena", "Madampe", "Medawachchiya", "Badulla", "Vavuniya", "Kurunegala", "Valaichchenai", "Thihagoda", "Batticaloa", "Minneriya", "Hunnasgiriya", "Ampara", "Digana", "Katunayake", "Kegalle", "Bandaragama", "Welikanda", "Maharagama", "Alawwa", "Dickwella", "Ranna", "Negombo", "Pulmudai", "Tangalle", "Mahaoya", "Senkalady", "Mannar", "Kaduwela", "Polonnaruwa", "Kelaniya", "Kinniya", "Ambalangoda", "Anuradhapura", "Watawala", "Maradana", "Katunayake", "Airport", "Weligamuwa", "Katugastota", "Udawalawe", "Trincomalee", "Pottuvil", "Kadawatha", "Habarana", "Minuwangoda", "Kankesanthurai", "Monaragala", "Warakapola", "Bandarawela", "Puttalam", "Nittambuwa", "Nuwaraeliya", "Mullaitivu", "Chenkalady", "Weerawila", "Arayampathy", "Middeniya", "Debarawewa", "Gampola", "Bibile", "Galoya", "Avissawella", "Pettah", "Kilinochchi", "Embilipitiya", "Nawalapitiya", "ELLA", "Passara", "Godagama", "Dehiattakandiya", "Jaffna", "Anamaduwa", "Melsiripura", "Gelioya", "Naula", "Panandura", "Ambalantota", "Hatton", "Oddamavadi", "Dambulla", "Lunugala", "Gelanigama", "Kanthalai", "Vandarumulai", "Mihintale", "Kallady", "Kitulgala", "Hambantota", "Chilaw", "Kotahena", "Matale", "Yatiyanthota", "Punanai", "Ambepussa", "Kumbalwela", "Kattankudy", "Kiran", "Polannaruwa", "Beruwala", "Nintavur", "Mawanella", "Thalawakele", "Akurana", "Karainagar", "Ratnapura", "Galle", "Kosgama", "Eheliyagoda", "Kadugannawa", "Dondra", "Makumbara", "Bampalapittya", "Girandurukotte", "Kalmunai", "Haputale", "Wellawatta", "Elpitiya", "Peradeniya", "Walasmulla", "Colombo", "Welimada", "Dayagama", "Pussellawa", "Matara", "Balangoda", "Horana", "Mathugama", "Hungama", "Nonagama", "Bentota", "Thihariya", "Eravur", "Talalla", "Akkaraipattu", "Galewela", "Vakarai", "Padiyathalawa", "Tissamaharama"
    };
    AutoCompleteTextView from,to;
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
    String url,fullname,customertype,email,id,username;


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
        ArrayAdapter <String> autoadapater = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,DISTRICTS);
        from.setAdapter(autoadapater);
        to.setAdapter(autoadapater);
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

        final Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
//        id = extras.getString("id");
        url = extras.getString("url");
        fullname = extras.getString("fullname");
        email = extras.getString("email");
        customertype = extras.getString("customertype");



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

                                        int seats = 0;

                                        if (sfrom.equalsIgnoreCase(bus.getFrom()) && sto.equalsIgnoreCase(bus.getTo())) {

                                            for (int i = 0; i < 8; i++) {
                                                System.out.println(stime);

                                                if (bus.getTimeSlots().get(i).substring(0, 2).equalsIgnoreCase(stime.substring(0, 2))) {
                                                    System.out.println("##################################################################");
                                                    time = bus.getTimeSlots().get(i);
                                                }
                                            }

                                            for(int i=0; i<bus.getNoSeats().size(); i++){
                                                if(bus.getNoSeats().get(i) == 1){
                                                    seats++;
                                                }
                                            }

                                            String listing = "Bus ID: " + bus.getBusID() + "\nBus Route: " + bus.getRouteno() + "\nFrom: " + bus.getFrom() + "\nTo: " + bus.getTo() + "\nTime: " + time + " \nNo of Booked Seats: "+seats;
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(searchedbus.isEmpty()){
                    Bus bus = beforebuslist.get(position);
                    System.out.println("Previous "+bus.getNoSeats().get(0));

                    Intent intent = new Intent(SeatBook.this, SelectSeat.class);
                    intent.putExtra("bus",  bus);
                    intent.putExtra("username",username);
                    intent.putExtra("id",id);
                    intent.putExtra("url",url);
                    intent.putExtra("fullname",fullname);
                    intent.putExtra("busID",bus.getBusID());
                    intent.putExtra("email",email);
                    intent.putExtra("customertype",customertype);
                    startActivity(intent);
                }else{
                    Bus bus = buslist.get(position);

                    Intent intent = new Intent(SeatBook.this, SelectSeat.class);
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
            }
        });

    }

    public void onStart() {

        super.onStart();
        beforesearchedbus.clear();
        DBFB = FirebaseDatabase.getInstance().getReference().child("BusList");

        DBFB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Bus bus = dataSnapshot1.getValue(Bus.class);
                    System.out.println("From DB: "+bus.getBusID());

                    int seats = 0;
                    for(int i=0; i < bus.getNoSeats().size(); i++){
                        if(bus.getNoSeats().get(i) == 1){
                            seats++;
                        }
                    }

                    String listing = "Bus ID: " + bus.getBusID() + "\nBus Route: " + bus.getRouteno() + "\nFrom: " + bus.getFrom() + "\nTo: " + bus.getTo() + "\nTime 1: " + bus.getTimeSlots().get(0) + "\nTime 2: " + bus.getTimeSlots().get(2) + "\nTime 3: " + bus.getTimeSlots().get(4) + "\nTime 4: " + bus.getTimeSlots().get(6) +  " \nNo of Booked Seats: "+seats;
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
   /* public void onBackPressed(){
        onStart();
        Toast.makeText(SeatBook.this,"Double Press Back to Dashboard", Toast.LENGTH_SHORT).show();
    }*/

}
