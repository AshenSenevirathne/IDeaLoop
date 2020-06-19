package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idealoop.busseek.model.BusOwner;
import com.idealoop.busseek.model.Passenger;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login,register;
    RadioButton busOwner,passenger;
    DatabaseReference RefB, RefP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        busOwner = findViewById(R.id.busOwner);
        passenger = findViewById(R.id.passenger);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefB = FirebaseDatabase.getInstance().getReference().child("BusOwner").child(username.getText().toString());
                RefP = FirebaseDatabase.getInstance().getReference().child("Passenger").child(username.getText().toString());

                System.out.println(password.getText().toString());
                if(busOwner.isChecked() && passenger.isChecked()){
                    Toast.makeText(LoginActivity.this, "You Have Checked Both Radio Buttons, Please uncheck one", Toast.LENGTH_SHORT).show();
                }
                else if(busOwner.isChecked()) {
                    RefB.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            BusOwner busOwner = dataSnapshot.getValue(BusOwner.class);

                            if(busOwner == null){
                                Toast.makeText(LoginActivity.this, "No USer in this email", Toast.LENGTH_SHORT).show();
                            }
                            else if (password.getText().toString().equals(busOwner.getPassword())) {
                                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                intent.putExtra("username",busOwner.getLname());
                                intent.putExtra("id",busOwner.getBusOwnerId());
                                intent.putExtra("url",busOwner.getImgurl());
                                intent.putExtra("fullname",busOwner.getFname());
                                intent.putExtra("email",busOwner.getEmail());
                                intent.putExtra("customertype",busOwner.getCustomerType());
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "Welcome " + busOwner.getFname() + "!!!\n Successfully Logged As a Bus Owner", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(LoginActivity.this, "Incorrect Password, Try Again Later", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if(passenger.isChecked()){
                        RefP.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Passenger passenger = dataSnapshot.getValue(Passenger.class);

                                if(passenger == null){
                                    Toast.makeText(LoginActivity.this, "No USER in this email", Toast.LENGTH_SHORT).show();
                                }
                                else if(password.getText().toString().equals(passenger.getPassword())){
                                    Intent intent = new Intent(LoginActivity.this,Dashboard.class);
                                    intent.putExtra("username",passenger.getLname());
                                    intent.putExtra("id",passenger.getPassengerID());
                                    intent.putExtra("url",passenger.getImgurl());
                                    intent.putExtra("fullname",passenger.getFname());
                                    intent.putExtra("email",passenger.getEmail());
                                    intent.putExtra("customertype",passenger.getCustomerType());
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this,"Welcome "+passenger.getFname()+"!!!\n Successfully Logged As a Passenger",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(LoginActivity.this,"Incorrect Password, Try Again Later",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                }
                else
                    Toast.makeText(LoginActivity.this,"You have not checked either of the radio buttons. Please check one",Toast.LENGTH_SHORT).show();
            }
        });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
