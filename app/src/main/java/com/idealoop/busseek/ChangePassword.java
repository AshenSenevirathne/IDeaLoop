package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.FloatProperty;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    EditText current,newpass,newpasscon;
    Button change,goback;
    String iurl,ifullname,icustomertype,iemail,iid,iusername,urlfrompic,currentpass,pass;
    private DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final Bundle extras = getIntent().getExtras();
        iusername = extras.getString("username");
        System.out.println(iusername);
        iurl = extras.getString("url");
        ifullname = extras.getString("fullname");
        iemail = extras.getString("email");
        icustomertype = extras.getString("customertype");
        currentpass = extras.getString("currentpass");

        current = findViewById(R.id.current);
        newpass = findViewById(R.id.newpass);
        newpasscon = findViewById(R.id.newpasscon);
        change = findViewById(R.id.change);
        goback = findViewById(R.id.goback);




        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current.getText().toString().equals(currentpass)){
                    if(newpass.getText().toString().equals(newpasscon.getText().toString())){
                        pass = newpass.getText().toString();
                        System.out.println("##############################################################:"+pass);
                        if(icustomertype.equals("busowner")){
                            DBRef = FirebaseDatabase.getInstance().getReference().child("BusOwner").child("kolithakasun").child("password");
                            DBRef.setValue(pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(ChangePassword.this,"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ChangePassword.this,MyProfile.class);
                                    intent.putExtra("username",iusername);
                                    intent.putExtra("url",iurl);
                                    intent.putExtra("fullname",ifullname);
                                    intent.putExtra("email",iemail);
                                    intent.putExtra("customertype",icustomertype);
                                    startActivity(intent);
                                }
                            });


                          /*

                           */
                        }
                        else{
                            DBRef = FirebaseDatabase.getInstance().getReference().child("Passenger").child(iusername).child("password");
                            DBRef.setValue(pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(ChangePassword.this,"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ChangePassword.this,MyProfile.class);
                                    intent.putExtra("username",iusername);
                                    intent.putExtra("url",iurl);
                                    intent.putExtra("fullname",ifullname);
                                    intent.putExtra("email",iemail);
                                    intent.putExtra("customertype",icustomertype);
                                    startActivity(intent);
                                }
                            });

                          /*

                           */
                        }
                    }
                    else
                        Toast.makeText(ChangePassword.this,"New password and Confirm Password \n !!! DO NOT MATCH !!!",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ChangePassword.this,"Current Password Is Wrong, Please Check Again",Toast.LENGTH_SHORT).show();
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this,MyProfile.class);
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
}
