package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idealoop.busseek.model.Bus;

import java.util.ArrayList;

public class ViewBus extends AppCompatActivity {
    ImageView[] s;
    ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
    String url,fullname,customertype,email,id,username, busID;
    Bus bus;
    DatabaseReference DBRseat, DBRbus, DBRtime;
    int count, seatnumber;
    Button change,remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus);

            change = findViewById(R.id.clear);
            remove = findViewById(R.id.remove);

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
                    change.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(int i=0; i<bus.getNoSeats().size(); i++){
                                bus.getNoSeats().set(i,0);
                            }
                            DBRbus = FirebaseDatabase.getInstance().getReference().child("BusList").child(bus.getBusID()).child("noSeats");
                            DBRbus.setValue(bus.getNoSeats());
                            Intent intent = new Intent(ViewBus.this, myBusList.class);
                            intent.putExtra("bus",  bus);
                            intent.putExtra("username",username);
                            intent.putExtra("id",id);
                            intent.putExtra("url",url);
                            intent.putExtra("busID",bus.getBusID());
                            intent.putExtra("fullname",fullname);
                            intent.putExtra("email",email);
                            intent.putExtra("customertype",customertype);
                            startActivity(intent);
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });





        }

}