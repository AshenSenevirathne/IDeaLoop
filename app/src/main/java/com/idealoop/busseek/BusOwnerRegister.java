package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.idealoop.busseek.model.BusOwner;

import java.nio.charset.Charset;
import java.util.Random;

public class BusOwnerRegister extends AppCompatActivity {

    ImageView imageView;
    EditText fname;
    EditText lname;
    EditText nic;
    EditText address;
    EditText email;
    EditText contactno;
    EditText noBus,pass,cpass;
    Uri imguri;
    String url;
    String downloadimgurl;
    Button register,clear,select;
    private StorageReference RefImg;
    private DatabaseReference DBRef;
    private static final int GalleryPic =1;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_owner_register);

        imageView = findViewById(R.id.profilePic);
        fname = findViewById(R.id.fname);
        lname  = findViewById(R.id.usename);
        nic = findViewById(R.id.nic);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        contactno = findViewById(R.id.contactno);
        noBus = findViewById(R.id.noBus);
        register = findViewById(R.id.register);
        clear = findViewById(R.id.clear);
        select = findViewById(R.id.select);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);

        RefImg = FirebaseStorage.getInstance().getReference().child("BusOwnerImages");
        DBRef = FirebaseDatabase.getInstance().getReference().child("BusOwner");

            DBRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int childcount = (int) dataSnapshot.getChildrenCount();
                    id = ++childcount;

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busownerID = "BO-"+id;
                if(pass.getText().toString().equals(cpass.getText().toString())) {
                StoreImage(busownerID); //Img Store
                }
                else
                    Toast.makeText(BusOwnerRegister.this,"Password and Confirm Password Do not Match",Toast.LENGTH_LONG).show();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });



    }

    private void OpenGallery() {
        Intent galaeryintent = new Intent();
        galaeryintent.setAction(Intent.ACTION_GET_CONTENT);
        galaeryintent.setType("image/*");
        startActivityForResult(galaeryintent,GalleryPic);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPic && resultCode==RESULT_OK && data!=null );
        imguri = data.getData();
        imageView.setImageURI(imguri);


    }

    private void StoreImage(final String busownerID1){

        String productRandomKey  = generateRandomString();



        final StorageReference filepath = RefImg.child(imguri.getLastPathSegment() + productRandomKey+ ".jpg");
        final UploadTask uploadTask= filepath.putFile(imguri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String massege  = e.toString();
                Toast.makeText(BusOwnerRegister.this, "Error" + massege , Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(BusOwnerRegister.this, "Image upload successfully....", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask= uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();

                        }
                        url = filepath.getDownloadUrl().toString();
                        System.out.println("###################\n##############\n##############\n##############\n##############\n##############\n");
                        System.out.println("onSuccesslisner "+url);
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){

                            url = task.getResult().toString();
                             downloadimgurl = task.getResult().toString();
                            System.out.println("###################\n##############\n##############\n##############\n##############\n##############\n");
                            System.out.println("Complete listner "+downloadimgurl);


                            Toast.makeText(BusOwnerRegister.this, " got Product Image URL  Successfully", Toast.LENGTH_SHORT).show();

                            String customertype = "busowner";
                            BusOwner busOwner = new BusOwner(downloadimgurl,fname.getText().toString(), lname.getText().toString(),nic.getText().toString(), address.getText().toString(), contactno.getText().toString(), email.getText().toString(), noBus.getText().toString(), busownerID1, customertype,pass.getText().toString());
                            busOwner.setEmail(email.getText().toString());
                            busOwner.setImgurl(downloadimgurl);
                            busOwner.setPassword(pass.getText().toString());
                            DBRef.child(busOwner.getLname()).setValue(busOwner);
                            id++;
                            clearAll();


                        }
                    }
                });
            }
        });
        //return downloadimgurl;
        Intent intent = new Intent(BusOwnerRegister.this, MainActivity.class);
        startActivity(intent);
    }

    public String generateRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
        return generatedString;
    }

    public void clearAll(){
        String imagesUri = "drawable://"+R.drawable.user;
        imageView.setImageURI(Uri.parse(imagesUri));
        fname.setText("");
        lname.setText("");
        nic.setText("");
        address.setText("");
        email.setText("");
        contactno.setText("");
        noBus.setText("");
        pass.setText("");
        cpass.setText("");
    }


}
