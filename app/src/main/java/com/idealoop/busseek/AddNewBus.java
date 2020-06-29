package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.util.ArrayList;
import java.util.Random;

public class AddNewBus extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static final  String[] DISTRICTS = new String[]{
            "Mahiyanganaya", "Diyathalawa", "Kandy", "Galigamuwa", "Kataragama", "Ramboada", "Paranthan", "Kottawa", "Kamburupitiya", "Girithale", "Karawanella", "Wellawaya", "Medagama", "Buttala", "Point", "Pedro", "Hakgala", "Kaduruwela", "Urani", "Thirukkovil", "Ginigathhena", "Madampe", "Medawachchiya", "Badulla", "Vavuniya", "Kurunegala", "Valaichchenai", "Thihagoda", "Batticaloa", "Minneriya", "Hunnasgiriya", "Ampara", "Digana", "Katunayake", "Kegalle", "Bandaragama", "Welikanda", "Maharagama", "Alawwa", "Dickwella", "Ranna", "Negombo", "Pulmudai", "Tangalle", "Mahaoya", "Senkalady", "Mannar", "Kaduwela", "Polonnaruwa", "Kelaniya", "Kinniya", "Ambalangoda", "Anuradhapura", "Watawala", "Maradana", "Katunayake", "Airport", "Weligamuwa", "Katugastota", "Udawalawe", "Trincomalee", "Pottuvil", "Kadawatha", "Habarana", "Minuwangoda", "Kankesanthurai", "Monaragala", "Warakapola", "Bandarawela", "Puttalam", "Nittambuwa", "Nuwaraeliya", "Mullaitivu", "Chenkalady", "Weerawila", "Arayampathy", "Middeniya", "Debarawewa", "Gampola", "Bibile", "Galoya", "Avissawella", "Pettah", "Kilinochchi", "Embilipitiya", "Nawalapitiya", "ELLA", "Passara", "Godagama", "Dehiattakandiya", "Jaffna", "Anamaduwa", "Melsiripura", "Gelioya", "Naula", "Panandura", "Ambalantota", "Hatton", "Oddamavadi", "Dambulla", "Lunugala", "Gelanigama", "Kanthalai", "Vandarumulai", "Mihintale", "Kallady", "Kitulgala", "Hambantota", "Chilaw", "Kotahena", "Matale", "Yatiyanthota", "Punanai", "Ambepussa", "Kumbalwela", "Kattankudy", "Kiran", "Polannaruwa", "Beruwala", "Nintavur", "Mawanella", "Thalawakele", "Akurana", "Karainagar", "Ratnapura", "Galle", "Kosgama", "Eheliyagoda", "Kadugannawa", "Dondra", "Makumbara", "Bampalapittya", "Girandurukotte", "Kalmunai", "Haputale", "Wellawatta", "Elpitiya", "Peradeniya", "Walasmulla", "Colombo", "Welimada", "Dayagama", "Pussellawa", "Matara", "Balangoda", "Horana", "Mathugama", "Hungama", "Nonagama", "Bentota", "Thihariya", "Eravur", "Talalla", "Akkaraipattu", "Galewela", "Vakarai", "Padiyathalawa", "Tissamaharama"
    };

    TextView bustype,time1from,time2from,time3from,time4from,time1to,time2to,time3to,time4to; //Time selecting Variables
    EditText vehicleno,regno,drivername,drivercontact,routeno;
    AutoCompleteTextView from,to;
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
    DatabaseReference DBRef,DBTime;

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
        time1to = findViewById(R.id.time1to);
        time2from = findViewById(R.id.time2from);
        time2to = findViewById(R.id.time2to);
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

        ArrayAdapter<String> autoadapater = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,DISTRICTS);
        from.setAdapter(autoadapater);
        to.setAdapter(autoadapater);
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

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAll();
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
                            ArrayList <String> time = new ArrayList<>();
                            time.add(time1from.getText().toString());
                            time.add(time1to.getText().toString());
                            time.add(time2from.getText().toString());
                            time.add(time2to.getText().toString());
                            time.add(time3from.getText().toString());
                            time.add(time3to.getText().toString());
                            time.add(time4from.getText().toString());
                            time.add(time4to.getText().toString());


                            String customertype = "busowner";
                           // Bus bus = new Bus(bustype.getText().toString(), vehicleno.getText().toString(), regno.getText().toString(), drivername.getText().toString(), drivercontact.getText().toString(), routeno.getText().toString(), from.getText().toString(), to.getText().toString(), username, busid, time1from.getText().toString(), time2from.getText().toString(), time3from.getText().toString(), time4from.getText().toString(),time1to.getText().toString(),time2to.getText().toString(),time3to.getText().toString(),time4to.getText().toString());
                            Bus bus = new Bus(bustype.getText().toString(), vehicleno.getText().toString(), regno.getText().toString(), drivername.getText().toString(), drivercontact.getText().toString(), routeno.getText().toString(), from.getText().toString(), to.getText().toString(), username, busid);
                            bus.setTimeSlots(time);
                            bus.setDownloadImgEx(downloadimgurlEx);
                            bus.setDownloadImgIn(downloadimgurlIn);
                            bus.setNoSeats(AddingSeats(bus.getBustype()));
                            DBRef.child(bus.getBusID()).setValue(bus);
                            testID++;
                            AddToTimeSlots(bus);
                        }
                    }
                });
                Intent intent = new Intent(AddNewBus.this,Dashboard.class);
                intent.putExtra("username",username);
                intent.putExtra("id",id);
                intent.putExtra("url",url);
                intent.putExtra("fullname",fullname);
                intent.putExtra("email",email);
                intent.putExtra("customertype",customertype);
                startActivity(intent);
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

    public ArrayList AddingSeats(String bustype){
        ArrayList<Integer> seats = new ArrayList<>();

        if(bustype.equals("A/C")){
            for(int i=0; i<42; i++){
                seats.add(0);
            }
        }
        else if(bustype.equals("Semi Luxury")){
            for(int i=0; i<59; i++){
                seats.add(0);
            }
        }
        else if(bustype.equals("Normal")){
            for(int i=0; i<51; i++){
                seats.add(0);
            }
        }

        return seats;
    }

    public void ClearAll(){
        bustype.setText("");
        btnShow.setText("");
        time1from.setText("");
        time1to.setText("");
        time2from.setText("");
        time2to.setText("");
        time3from.setText("");
        time3to.setText("");
        time4from.setText("");
        time4to.setText("");
        time1from.setHint("From");
        time1to.setHint("TO");
        time2from.setHint("From");
        time2to.setHint("TO");
        time3from.setHint("From");
        time3to.setHint("TO");
        time4from.setHint("From");
        time4to.setText("TO");
        vehicleno.setText("");
        regno.setText("");
        drivername.setText("");
        drivercontact.setText("");
        routeno.setText("");
        from.setText("");
        to.setText("");
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

    public void FindTimeSlots(String from, Bus bus){
        //0,2,4,6

        if(from.substring(0,2).equals("00")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("00");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("01")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("01");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("02")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("02");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("03")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("03");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("04")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("04");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("05")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("05");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("06")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("06");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("07")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("07");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("08")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("09");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("10")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("10");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("11")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("11");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("12")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("12");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("13")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("13");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("14")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("15");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("16")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("16");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("17")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("17");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("18")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("18");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("19")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("19");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("20")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("20");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("21")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("21");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("22")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("22");
            DBTime.child(bus.getBusID()).setValue(bus);
        }
        else if(from.substring(0,2).equals("23")){
            DBTime= FirebaseDatabase.getInstance().getReference().child("23");
            DBTime.child(bus.getBusID()).setValue(bus);
        }





    }

    public void AddBus(View view) {
        Toast.makeText(AddNewBus.this,"Successfully added",Toast.LENGTH_LONG).show();
    }
}


