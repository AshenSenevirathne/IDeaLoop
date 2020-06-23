package com.idealoop.busseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.idealoop.busseek.model.BusOwner;

import java.nio.charset.Charset;
import java.util.Random;

public class profilepicupload extends AppCompatActivity {

    private static final int GalleryPic =1;
    private StorageReference RefImg;
    ImageView imageView;
    Uri imguri;
    String url;
    String downloadimgurl, cutomerty;
    String iurl,ifullname,icustomertype,iemail,iid,iusername,urlfrompic;
    Button change;
    private DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepicupload);

        imageView = findViewById(R.id.imageView);
        change = findViewById(R.id.change);

        final Bundle extras = getIntent().getExtras();
        cutomerty = extras.getString("customertype");
        iusername = extras.getString("username");
        System.out.println("#################################################################"+iusername);
        iid = extras.getString("id");
        iurl = extras.getString("url");
        ifullname = extras.getString("fullname");
        iemail = extras.getString("email");
        icustomertype = extras.getString("customertype");

        if(cutomerty.equals("busowner")){
            RefImg = FirebaseStorage.getInstance().getReference().child("BusOwnerImages");
        }
        else{
            RefImg = FirebaseStorage.getInstance().getReference().child("PassengerImages");
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreImage();
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


    private void StoreImage() {

        String productRandomKey = generateRandomString();

        if(!(imguri == null)){

        final StorageReference filepath = RefImg.child(imguri.getLastPathSegment() + productRandomKey + ".jpg");
        final UploadTask uploadTask = filepath.putFile(imguri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String massege = e.toString();
                Toast.makeText(profilepicupload.this, "Error" + massege, Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(profilepicupload.this, "Image upload successfully....", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();

                        }
                        url = filepath.getDownloadUrl().toString();

                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {

                            url = task.getResult().toString();
                            downloadimgurl = task.getResult().toString();
                            Toast.makeText(profilepicupload.this, " got Product Image URL  Successfully", Toast.LENGTH_SHORT).show();

                            if (icustomertype.equals("busowner")) {
                                DBRef = FirebaseDatabase.getInstance().getReference().child("BusOwner").child(iusername).child("imgurl");
                                DBRef.setValue(downloadimgurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        System.out.println("DONEDONEDONE");
                                        Intent intent = new Intent(profilepicupload.this, MyProfile.class);
                                        intent.putExtra("username", iusername);

                                        intent.putExtra("urlfrompic", downloadimgurl);
                                        intent.putExtra("id", iid);
                                        intent.putExtra("url", iurl);
                                        intent.putExtra("fullname", ifullname);
                                        intent.putExtra("email", iemail);
                                        intent.putExtra("customertype", icustomertype);
                                        startActivity(intent);
                                    }
                                });


                            } else {
                                DBRef = FirebaseDatabase.getInstance().getReference().child("Passenger").child(iusername).child("imgurl");
                                DBRef.setValue(downloadimgurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        System.out.println("DONEDONEDONE");
                                        Intent intent = new Intent(profilepicupload.this, MyProfile.class);
                                        intent.putExtra("username", iusername);

                                        intent.putExtra("urlfrompic", downloadimgurl);
                                        intent.putExtra("id", iid);
                                        intent.putExtra("url", iurl);
                                        intent.putExtra("fullname", ifullname);
                                        intent.putExtra("email", iemail);
                                        intent.putExtra("customertype", icustomertype);
                                        startActivity(intent);
                                    }
                                });
                              /*  //return downloadimgurl;
                                Intent intent = new Intent(profilepicupload.this, MyProfile.class);
                                intent.putExtra("username",iusername);

                                intent.putExtra("urlfrompic",downloadimgurl);
                                intent.putExtra("id",iid);
                                intent.putExtra("url",iurl);
                                intent.putExtra("fullname",ifullname);
                                intent.putExtra("email",iemail);
                                intent.putExtra("customertype",icustomertype);
                                startActivity(intent);

                               */

                            }

                        }

                    }
                });
            }

        });
    }
        else{
            Toast.makeText(profilepicupload.this,"Select a Photo", Toast.LENGTH_SHORT).show();
        }
    }

    public String generateRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}
