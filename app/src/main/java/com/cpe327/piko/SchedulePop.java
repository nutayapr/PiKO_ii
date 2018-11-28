package com.cpe327.piko;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

@SuppressLint("Registered")
class SchedulePop extends Activity {

    String day;
    Button btn_addSchedule, btn_cancelSchedule;
    EditText startTime, endTime, activity;
    FirebaseDatabase database;
    DatabaseReference ref;
    StudentSchedule studentSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_schedule);

        /// Pop up
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));


        /// Select day(spinner)
        String[] week = getResources().getStringArray(R.array.sche_weekday);
        Spinner day_spin = findViewById(R.id.scheFillDay);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, week);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day_spin.setAdapter(adapter);
        day_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /// Add schedule
        studentSchedule = new StudentSchedule();
        btn_addSchedule = findViewById(R.id.scheBtnAdd);
        btn_cancelSchedule = findViewById(R.id.scheBtnCancel);
        startTime = findViewById(R.id.scheFillStartTime);
        endTime = findViewById(R.id.scheFillEndTime);
        activity = findViewById(R.id.scheFillActivity);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        btn_addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSchedule(day,startTime.getText().toString(),endTime.getText().toString(),activity.getText().toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,Object> updateSchedule = (Map<String, Object>) dataSnapshot.getValue();
                        assert updateSchedule != null;
                        Log.i("SchedulePop","updateSchedule ="+ updateSchedule.toString());
                        Toast.makeText(SchedulePop.this,"New schedule added...",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("SchedulePop","onCancelled");
                    }
                });
            }
        });
    }

    public void addNewSchedule(String day,String startTime,String endTime,String activity) {
        StudentSchedule studentSchedule = new StudentSchedule(day, startTime, endTime, activity);
        ref.child(studentSchedule.scheDay).setValue(studentSchedule);
    }
}
