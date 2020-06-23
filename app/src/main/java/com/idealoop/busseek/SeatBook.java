package com.idealoop.busseek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SeatBook extends AppCompatActivity {

    EditText from,to;
    TextView timeset;
    Button settime,search;
    String sfrom,sto,stime;
    DatabaseReference DBF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_book);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        timeset = findViewById(R.id.timeset);
        settime = findViewById(R.id.settime);
        search = findViewById(R.id.search);



        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putInt("testStringArgKey", R.id.timeset);
                newFragment.setArguments(args);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        stime = timeset.getText().toString();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sfrom = from.getText().toString();
                sto = to.getText().toString();
                stime = timeset.getText().toString();
                System.out.println(stime.substring(0,2));
                DBF = FirebaseDatabase.getInstance().getReference().child(stime.substring(0,2));

                //Array list ekata danna one username eka seat array ekta
                //eka wenas karanna

            }
        });

    }
}
