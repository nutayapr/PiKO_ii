package com.cpe327.piko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class testActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        b = (Button)findViewById(R.id.addNewSchedule);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(testActivity.this, SchedulePop.class));
            }
        });
//        Toast.makeText(testActivity.this,"testtest",Toast.LENGTH_LONG).show();
    }
}
