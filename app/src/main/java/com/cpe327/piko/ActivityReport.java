package com.cpe327.piko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityReport extends AppCompatActivity {

    EditText edit_Content;
    Button btn_Send;

    // Firebase //
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        edit_Content = (EditText)findViewById(R.id.editbox);
        btn_Send = (Button)findViewById(R.id.btnSend);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReport();
            }
        });
    }

    private void sendReport() {
        String content = edit_Content.getText().toString();
        Report send = new Report(content);
        databaseReference.push().setValue(send);
        Toast.makeText(ActivityReport.this,"Your message has been sent.",Toast.LENGTH_LONG).show();
        Intent ReportIntent = new Intent (ActivityReport.this,ActivityHome.class);
        startActivity(ReportIntent);
        finish();
    }
}
