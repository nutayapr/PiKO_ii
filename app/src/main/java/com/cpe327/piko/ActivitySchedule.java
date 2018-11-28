package com.cpe327.piko;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressLint("Registered")
public class ActivitySchedule extends Activity {
//    FirebaseDatabase database;
//    DatabaseReference ref;

    Button b;
//    private ListView listView;
//
//    private ArrayList<String> arrayList = new ArrayList<>();
//    private ArrayAdapter<String> adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        b = findViewById(R.id.addNewSchedule);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivitySchedule.this, SchedulePop.class));
            }
        });

//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference("testpiko-b5984");

//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
//        listView = (ListView) findViewById(R.id.listSche);
//
//        listView.setAdapter(adapter);
//
//        ref.addChildEventListener(new ChildEventListener() {
//
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String string = dataSnapshot.getValue(String.class);
//                arrayList.add(string);
//                adapter.notifyDataSetChanged();
//            }
//        });
    }
}

//    private void getSchedule(){
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                setData(dataSnapshot);
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Schedule","Failed to read value.",error.toException());
//            }
//        });

//
//    ListView list = (ListView) findViewById(R.id.listSche);
//
//    List<List<String>> scheInfo;
//    List<String> temp ;
//    private void setData(DataSnapshot dataSnapshot){
//        scheInfo = new ArrayList<>();
//
//        for (DataSnapshot alert: dataSnapshot.getChildren()) {
//            n = n + 1;
//            while(alert.child("day"+n).exists());
//            temp = new ArrayList<>();
//            Log.d("day", (String) alert.child("day").getValue());
//            Log.d("startTime", (String) alert.child("startTime").getValue());
//            Log.d("endTime", (String) alert.child("endTime").getValue());
//            Log.d("activity", (String) alert.child("activity").getValue());
//            temp.add((String) alert.child("day").getValue());
//            temp.add((String) alert.child("startTime").getValue());
//            temp.add((String) alert.child("endTime").getValue());
//            temp.add((String) alert.child("activity").getValue());
//            scheInfo.add(temp);
//        }
//
//        if(scheInfo.size() > 0){
//            setGridView();
//        }else{
//            Toast.makeText(this, "No data Found.", Toast.LENGTH_SHORT).show();
//        }
//    }

