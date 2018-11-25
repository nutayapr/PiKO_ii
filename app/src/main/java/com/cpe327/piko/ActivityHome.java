package com.cpe327.piko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityHome extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        mLogoutBtn = (Button)findViewById(R.id.btlogout);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                updateUI();
            }
        });
    }

    private void updateUI() {
        Toast.makeText(ActivityHome.this,"You are logged out",Toast.LENGTH_LONG).show();

        Intent MainIntent = new Intent (ActivityHome.this,MainActivity.class);
        startActivity(MainIntent);
        finish();
    }
}
