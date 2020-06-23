package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idealoop.busseek.model.Bus;
import com.idealoop.busseek.model.BusOwner;
import com.idealoop.busseek.model.Passenger;
import com.squareup.picasso.Picasso;

public class MyProfile extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageView imageView;
    EditText fname;
    EditText lname;
    EditText nic;
    EditText address;
    EditText email;
    EditText contactno;
    EditText noBus,pass,cpass;
    Button update,clear,select,passChange;
    String iurl,ifullname,icustomertype,iemail,iid,iusername,urlfrompic,currentpass;
    DatabaseReference DBF;
    private DatabaseReference DBRef;
    BusOwner busOwner;
    Passenger passenger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        imageView = findViewById(R.id.profilePic);
        fname = findViewById(R.id.fname);
        lname  = findViewById(R.id.usename);
        nic = findViewById(R.id.nic);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        contactno = findViewById(R.id.contactno);
        noBus = findViewById(R.id.noBus);
        update = findViewById(R.id.update);
        passChange = findViewById(R.id.passChange);
        clear = findViewById(R.id.goback2);
        select = findViewById(R.id.select);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);

        final Bundle extras = getIntent().getExtras();
        iusername = extras.getString("username");
        System.out.println(iusername);
        iurl = extras.getString("url");
        ifullname = extras.getString("fullname");
        iemail = extras.getString("email");
        icustomertype = extras.getString("customertype");

        if(!(extras.getString("urlfrompic") == null)){
            urlfrompic = extras.getString("urlfrompic");

        }

        if(icustomertype.equals("busowner")){
            DBF = FirebaseDatabase.getInstance().getReference().child("BusOwner").child(iusername);
            DBF.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    busOwner = dataSnapshot.getValue(BusOwner.class);

                    Picasso.get().load(busOwner.getImgurl()).into(imageView);
                    fname.setText(busOwner.getFname());
                    lname.setText(busOwner.getLname());
                    nic.setText(busOwner.getNIC());
                    address.setText(busOwner.getAddress());
                    email.setText(busOwner.getEmail());
                    contactno.setText(busOwner.getContactno());
                    noBus.setText(busOwner.getNofBuses());
                    iid = busOwner.getBusOwnerId();
                    currentpass = busOwner.getPassword();
                   // cpass.setText(busOwner.getFname());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(MyProfile.this, v);
                    popup.setOnMenuItemClickListener(MyProfile.this);
                    popup.inflate(R.menu.change_profile_list);
                    popup.show();
                }
            });

        }
        else {
            DBF = FirebaseDatabase.getInstance().getReference().child("Passenger").child(iusername);
            DBF.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    passenger = dataSnapshot.getValue(Passenger.class);

                    Picasso.get().load(passenger.getImgurl()).into(imageView);
                    fname.setText(passenger.getFname());
                    lname.setText(passenger.getLname());
                    nic.setText(passenger.getNIC());
                    address.setText(passenger.getAddress());
                    email.setText(passenger.getEmail());
                    contactno.setText(passenger.getContactno());
                    noBus.setText(passenger.getMainbusroute());
                    iid= passenger.getPassengerID();
                    currentpass = passenger.getPassword();
                    // cpass.setText(busOwner.getFname());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(MyProfile.this, v);
                    popup.setOnMenuItemClickListener(MyProfile.this);
                    popup.inflate(R.menu.change_profile_list);
                    popup.show();
                }
            });
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(icustomertype.equals("passenger")){
                    DBRef = FirebaseDatabase.getInstance().getReference().child("Passenger");
                    passenger.setAddress(address.getText().toString());
                    passenger.setEmail(email.getText().toString());
                    passenger.setFname(fname.getText().toString());
                    passenger.setNIC(nic.getText().toString());
                    passenger.setContactno(contactno.getText().toString());
                    passenger.setMainbusroute(noBus.getText().toString());

                    DBRef.child(passenger.getLname()).setValue(passenger);
                }
                else{
                    DBRef = FirebaseDatabase.getInstance().getReference().child("BusOwner");

                    busOwner.setAddress(address.getText().toString());
                    busOwner.setEmail(email.getText().toString());
                    busOwner.setFname(fname.getText().toString());
                    busOwner.setNIC(nic.getText().toString());
                    busOwner.setContactno(contactno.getText().toString());
                    busOwner.setNofBuses(noBus.getText().toString());

                    DBRef.child(busOwner.getLname()).setValue(busOwner);
                }
            }
        });

        passChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyProfile.this, ChangePassword.class);
                intent.putExtra("id",iid);
                intent.putExtra("username",iusername);
                intent.putExtra("url",iurl);
                intent.putExtra("fullname",ifullname);
                intent.putExtra("email",iemail);
                intent.putExtra("customertype",icustomertype);
                intent.putExtra("currentpass",currentpass);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfile.this,Dashboard.class);
                intent.putExtra("username",iusername);
                intent.putExtra("id",iid);
                intent.putExtra("url",iurl);
                intent.putExtra("fullname",ifullname);
                intent.putExtra("email",iemail);
                intent.putExtra("customertype",icustomertype);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (icustomertype.equals("busowner") && item.getTitle().toString().equals("Remove Current Profile Pic")){
            busOwner.setImgurl("data:image/png;base64,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");
        }
        else if (icustomertype.equals("passenger") && item.getTitle().toString().equals("Remove Current Profile Pic")){
            passenger.setImgurl("data:image/png;base64,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");
        }
        else if (item.getTitle().toString().equals("Change Profile Pic")){
            Intent intent = new Intent(this, profilepicupload.class);
            intent.putExtra("customertype", icustomertype);
            intent.putExtra("id",iid);
            intent.putExtra("username",iusername);
            intent.putExtra("url",iurl);
            intent.putExtra("fullname",ifullname);
            intent.putExtra("email",iemail);
            intent.putExtra("customertype",icustomertype);
            startActivity(intent);
        }
        return true;

    }
}
