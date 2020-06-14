package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.app.DialogFragment;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.idealoop.busseek.model.Bus;
import com.idealoop.busseek.model.BusOwner;

import java.nio.charset.Charset;
import java.util.Random;

public class AddNewBus extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView bustype,time1from,time2from,time3from,time4from,time1to,time2to,time3to,time4to; //Time selecting Variables
    EditText vehicleno,regno,drivername,drivercontact,routeno,from,to;
    Button btnShow,addbus,clear;
    String url;
    String fullname;
    String customertype;
    String email;
    String id;
    String busid;
    int testID;
    String username;//Variables for getting value
    private static final int GalleryPicEx =1;
    private static final int GalleryPicIn =2;
    Uri imguriEx, imguriIn;
    String urlEx, urlIn;
    String downloadimgurlEx, downloadimgurlIn;
    ImageView imgIn, imgEx;
    StorageReference RefImg;
    DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bus);

        RefImg = FirebaseStorage.getInstance().getReference().child("BusImages");
        DBRef = FirebaseDatabase.getInstance().getReference().child("BusList");


        imgEx = findViewById(R.id.imgEx);
        imgIn = findViewById(R.id.imgIn);
        bustype = findViewById(R.id.bustype);
        btnShow = findViewById(R.id.btnShow);
        time1from = findViewById(R.id.time1from);
        time1to = findViewById(R.id.time2to);
        time2from = findViewById(R.id.time2from);
        time2to = findViewById(R.id.time1to);
        time3from = findViewById(R.id.time3from);
        time3to = findViewById(R.id.time3to);
        time4from = findViewById(R.id.time4from);
        time4to = findViewById(R.id.time4to);
        vehicleno = findViewById(R.id.vehicleno);
        regno = findViewById(R.id.regno);
        drivername = findViewById(R.id.drivername);
        drivercontact = findViewById(R.id.drivercontact);
        routeno = findViewById(R.id.routeno);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        addbus= findViewById(R.id.addbus);
        clear= findViewById(R.id.clear);

        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int childcount = (int) dataSnapshot.getChildrenCount();
                testID = ++childcount;
                busid = "BUS-"+testID;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//Taking Values from DashBoard
        final Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        id = extras.getString("id");
        url = extras.getString("url");
        fullname = extras.getString("fullname");
        email = extras.getString("email");


//Add New Activity Functions##########################################################
        time1from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time1from);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        time2from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time2from);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        time3from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time3from);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        time4from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time4from);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        time1to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time1to);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        time2to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time2to);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        time3to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time3to);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        time4to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.time4to);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });


//Select Bus Type
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(AddNewBus.this, v);
                popup.setOnMenuItemClickListener(AddNewBus.this);
                popup.inflate(R.menu.menu_bustype);
                popup.show();
            }
        });
//Add New Activity Functions Ended ##########################################################

        imgEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGalleryEx();
            }
        });

        imgIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGalleryIn();
            }
        });

        addbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreData();
            }
        });

    }

  /*
  //Pop up Menu
   public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_bustype, popup.getMenu());
        popup.show();
    }*/

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        bustype.setText(item.getTitle());
        return true;
       // Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      /*  switch (item.getItemId()) {
            case R.id.ac:
                bustype.setHint("");
                bustype.setText("A/C");
                return true;
            case R.id.semi:
                bustype.setHint("");
                bustype.setText("Semi Luxury");
                return true;
            case R.id.normal:
                bustype.setHint("");
                bustype.setText("Normal");
                return true;
            default:
                return false;
        }*/
    }

    private void OpenGalleryEx() {
        Intent galaeryintent = new Intent();
        galaeryintent.setAction(Intent.ACTION_GET_CONTENT);
        galaeryintent.setType("image/*");
        startActivityForResult(galaeryintent,GalleryPicEx);

    }
    private void OpenGalleryIn() {
        Intent galaeryintent = new Intent();
        galaeryintent.setAction(Intent.ACTION_GET_CONTENT);
        galaeryintent.setType("image/*");
        startActivityForResult(galaeryintent,GalleryPicIn);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Requested ######################## "+requestCode);
        if(requestCode == 1) {
            if (requestCode == GalleryPicEx && resultCode == RESULT_OK && data != null) ;
            imguriEx = data.getData();
            imgEx.setImageURI(imguriEx);
        }
        else {
            if (requestCode == GalleryPicIn && resultCode == RESULT_OK && data != null) ;
            imguriIn = data.getData();
            imgIn.setImageURI(imguriIn);
        }

    }

    public void StoreData(){
        String productRandomKey  = generateRandomString()+username;



        final StorageReference filepathEx = RefImg.child(imguriEx.getLastPathSegment() + productRandomKey+ ".jpg");
        final StorageReference filepathIn = RefImg.child(imguriIn.getLastPathSegment() + productRandomKey+ ".jpg");
        final UploadTask uploadTaskEx = filepathEx.putFile(imguriEx);
        final UploadTask uploadTaskIn = filepathIn.putFile(imguriIn);

        //Uploading External View Image
        uploadTaskEx.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String massege  = e.toString();
                Toast.makeText(AddNewBus.this, "Error" + massege , Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddNewBus.this, "Exterior Image upload successfully....", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask= uploadTaskEx.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();

                        }
                        url = filepathEx.getDownloadUrl().toString();

                        return filepathEx.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){

                            url = task.getResult().toString();
                            downloadimgurlEx = task.getResult().toString();

                            //Toast.makeText(AddNewBus.this, " got Product Image URL  Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        uploadTaskIn.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String massege  = e.toString();
                Toast.makeText(AddNewBus.this, "Error Uploading 2 Image" + massege , Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddNewBus.this, "Interior Image upload successfully....", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask= uploadTaskIn.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();

                        }
                        url = filepathIn.getDownloadUrl().toString();

                        return filepathIn.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){

                            url = task.getResult().toString();
                            downloadimgurlIn = task.getResult().toString();

                            Toast.makeText(AddNewBus.this, " got Product Image URL  Successfully", Toast.LENGTH_SHORT).show();

                            System.out.println("Adding Bus Info");

                            String[] timeslot1 = new String[2];
                            String timeslot1f= time1from.getText().toString();
                            timeslot1[1] = time1to.getText().toString();

                            String[] timeslot2 = new String[2];
                            timeslot2[0] = time2from.getText().toString();
                            timeslot2[1] = time2to.getText().toString();

                            String[] timeslot3 = new String[2];
                            timeslot3[0] = time3from.getText().toString();
                            timeslot3[1] = time3to.getText().toString();

                            String[] timeslot4 = new String[2];
                            timeslot4[0] = time4from.getText().toString();
                            timeslot4[1] = time4to.getText().toString();


                            String customertype = "busowner";
                            Bus bus = new Bus(bustype.getText().toString(), vehicleno.getText().toString(), regno.getText().toString(), drivername.getText().toString(), drivercontact.getText().toString(), routeno.getText().toString(), from.getText().toString(), to.getText().toString(), username, busid, time1from.getText().toString(), time2from.getText().toString(), time3from.getText().toString(), time4from.getText().toString(),time1to.getText().toString(),time2to.getText().toString(),time3to.getText().toString(),time4to.getText().toString());
                            
                            DBRef.child(bus.getBusID()).setValue(bus);
                            testID++;
                        }
                    }
                });
            }
        });
        //return downloadimgurl;
     //   Intent intent = new Intent(AddNewBus.this, Dashboard.class);
     //   startActivity(intent);
    }
    public String generateRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
        return generatedString;
    }

}


