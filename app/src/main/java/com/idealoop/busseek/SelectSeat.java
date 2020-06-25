package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idealoop.busseek.MyProfile.MyProfile;
import com.idealoop.busseek.model.Bus;
import com.idealoop.busseek.model.Seat;

import java.util.ArrayList;

public class SelectSeat extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ImageView[] s;
    ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
    String url,fullname,customertype,email,id,username, busID;
    Bus bus;
    DatabaseReference DBRseat, DBRbus, DBTime;
    String seatId;
    int count = 0, seatnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);


        Intent intent = getIntent();
        //  bus = (Bus) intent.getParcelableExtra("bus");
        final Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        url = extras.getString("url");
        fullname = extras.getString("fullname");
        email = extras.getString("email");
        busID = extras.getString("busID");
        customertype = extras.getString("customertype");

        s = new ImageView[51];

        DBRbus = FirebaseDatabase.getInstance().getReference().child("BusList").child(busID);
        DBRbus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bus = dataSnapshot.getValue(Bus.class);
                System.out.println("Done");

                for (int i = 1; i < 51; i++) { //You might have to change that slightly depending on where you want to start/end counting
                    int res = getResources().getIdentifier("s"+i, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                    s[i] = (ImageView) findViewById(res);

                    System.out.println(bus.getNoSeats().get(0));
                    if(bus.getNoSeats().get(i-1) == (1)){
                        s[i].setImageDrawable(getResources().getDrawable(R.drawable.bookedseat));
                        s[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.bookedseat));
                    }

                }

                s[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 1;
                    }
                });
                s[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 2;
                    }
                });
                s[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 3;
                    }
                });
                s[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 4;
                    }
                });
                s[5].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 5;
                    }
                });
                s[6].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 6;
                    }
                });
                s[7].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 7;
                    }
                });
                s[8].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 8;
                    }
                });
                s[9].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 9;
                    }
                });
                s[10].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 10;
                    }
                });
                s[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 11;
                    }
                });
                s[12].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 12;
                    }
                });
                s[13].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 13;
                    }
                });
                s[14].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 14;
                    }
                });
                s[15].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 15;
                    }
                });
                s[16].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 16;
                    }
                });
                s[17].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 17;
                    }
                });
                s[18].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 18;
                    }
                });
                s[19].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 19;
                    }
                });
                s[20].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 20;
                    }
                });
                s[21].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 21;
                    }
                });
                s[22].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 22;
                    }
                });
                s[23].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 23;
                    }
                });
                s[24].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 24;
                    }
                });
                s[25].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 25;
                    }
                });
                s[26].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 26;
                    }
                });
                s[27].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 27;
                    }
                });
                s[28].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 28;
                    }
                });
                s[29].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 29;
                    }
                });
                s[30].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 30;
                    }
                });
                s[31].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 31;
                    }
                });
                s[32].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 32;
                    }
                });
                s[33].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 33;
                    }
                });
                s[34].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 34;
                    }
                });
                s[35].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 35;
                    }
                });
                s[36].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 36;
                    }
                });
                s[37].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 37;
                    }
                });
                s[38].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 38;
                    }
                });
                s[39].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 39;
                    }
                });
                s[40].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 40;
                    }
                });
                s[41].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 41;
                    }
                });
                s[42].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 42;
                    }
                });
                s[43].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 43;
                    }
                });
                s[44].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 44;
                    }
                });
                s[45].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 45;
                    }
                });
                s[46].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 46;
                    }
                });
                s[47].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 47;
                    }
                });
                s[48].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 48;
                    }
                });
                s[49].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 49;
                    }
                });
                s[50].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(SelectSeat.this, v);
                        popup.setOnMenuItemClickListener(SelectSeat.this);
                        popup.inflate(R.menu.seatbook);
                        popup.show();
                        seatnumber = 50;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getTitle().toString().equals("Book Seat")){

            //Setting Seat



            DBRseat = FirebaseDatabase.getInstance().getReference().child("Seats").child(username);
            count++;
            System.out.println("*******************************************************************************" + count);
            seatId = "S-"+count;
            System.out.println("*******************************************************************************" + seatId);
            Seat seat = new Seat(seatId, bus.getBusID(), "1", username, bus.getUsername(), Integer.toString(seatnumber));
            DBRseat.child(seatId).setValue(seat);
            count++;
            //Setting to BusList
            DBRbus = FirebaseDatabase.getInstance().getReference().child("BusList").child(bus.getBusID()).child("noSeats");
            bus.getNoSeats().set((seatnumber-1),1);
            DBRbus.setValue(bus.getNoSeats());

            AddToTimeSlots(bus);

            Intent intent = new Intent(SelectSeat.this, SelectSeat.class);
            intent.putExtra("bus",  bus);
            intent.putExtra("username",username);
            intent.putExtra("id",id);
            intent.putExtra("url",url);
            intent.putExtra("busID",bus.getBusID());
            intent.putExtra("fullname",fullname);
            intent.putExtra("email",email);
            intent.putExtra("customertype",customertype);
            startActivity(intent);



            //Payment Eka methanata enna one
        }
        return true;
    }
    public void AddToTimeSlots(Bus bus){
        //0,2,4,6

        String from1,from2,from3,from4;
        from1 = bus.getTimeSlots().get(0);
        from2 = bus.getTimeSlots().get(2);
        from3 = bus.getTimeSlots().get(4);
        from4 = bus.getTimeSlots().get(6);

        FindTimeSlots(from1,bus);
        FindTimeSlots(from2,bus);
        FindTimeSlots(from3,bus);
        FindTimeSlots(from4,bus);


    }
    public void FindTimeSlots(String from, Bus bus) {
        //0,2,4,6

        if (from.substring(0, 2).equals("00")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("00");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("01")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("01");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("02")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("02");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("03")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("03");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("04")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("04");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("05")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("05");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("06")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("06");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("07")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("07");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("08")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("09");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("10")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("10");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("11")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("11");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("12")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("12");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("13")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("13");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("14")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("15");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("16")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("16");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("17")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("17");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("18")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("18");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("19")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("19");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("20")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("20");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("21")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("21");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("22")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("22");
            DBTime.child(bus.getBusID()).setValue(bus);
        } else if (from.substring(0, 2).equals("23")) {
            DBTime = FirebaseDatabase.getInstance().getReference().child("23");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
    }

    public void onStart() {
        super.onStart();
        DBRseat = FirebaseDatabase.getInstance().getReference().child("Seats").child(username);
        DBRseat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = (int) dataSnapshot.getChildrenCount();

                System.out.println("*******************************************************************************" + count);
                count = count + 1;
                System.out.println("*******************************************************************************" + count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}