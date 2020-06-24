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
    DatabaseReference DBRseat, DBRbus, DBRtime;
    int count, seatnumber;
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
            DBRseat.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    count = (int) dataSnapshot.getChildrenCount();
                    count++;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            String seatId = "S-"+count;

            Seat seat = new Seat(seatId, bus.getBusID(), "1", username, bus.getUsername(), Integer.toString(seatnumber));
            DBRseat = FirebaseDatabase.getInstance().getReference().child("Seats").child(username).child(seatId);
            DBRseat.setValue(seat);

            //Setting to BusList
            DBRbus = FirebaseDatabase.getInstance().getReference().child("BusList").child(bus.getBusID()).child("noSeats");
            bus.getNoSeats().set((seatnumber-1),1);
            DBRbus.setValue(bus.getNoSeats());

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

}