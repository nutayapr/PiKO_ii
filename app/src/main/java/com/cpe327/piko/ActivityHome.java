package com.cpe327.piko;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.net.URL;

public class ActivityHome extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mLogoutBtn,mReportBtn,mScheduleBtn;
    private TextView mTextViewProfileTel,mTextViewProfileEmail,mTextViewProfileFB;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        // Logout Activity (Go to Main Page) //
        mLogoutBtn = (Button)findViewById(R.id.btlogout);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                Toast.makeText(ActivityHome.this,"You are logged out",Toast.LENGTH_LONG).show();
                Intent MainIntent = new Intent (ActivityHome.this,MainActivity.class);
                startActivity(MainIntent);
                finish();
            }
        });

        // Report Activity (Go to Report page) //
        mReportBtn = (Button)findViewById(R.id.btreport) ;
        mReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ReportIntent = new Intent (ActivityHome.this,ActivityReport.class);
                startActivity(ReportIntent);
                finish();
            }
        });

        mScheduleBtn = (Button)findViewById(R.id.btschedule) ;
        mScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ScheduleIntent = new Intent(ActivityHome.this,ActivitySchedule.class);
                startActivity(ScheduleIntent);
                finish();
            }
        });

        // Profile Display //
//        mTextViewProfileTel = (TextView)findViewById(R.id.tvtel);
        mImageView = (ImageView)findViewById(R.id.imgProfile);
        mTextViewProfileEmail = (TextView)findViewById(R.id.tvemail);
        mTextViewProfileFB = (TextView)findViewById(R.id.tvname);
//        mTextViewProfileDOB = (TextView) findViewById(R.id.tvDOB);

        FirebaseUser user = mAuth.getCurrentUser();
//        mTextViewProfileTel.setText(user.getPhoneNumber());

        new DownloadImageTask().execute(user.getPhotoUrl().toString());
        mTextViewProfileEmail.setText(user.getEmail());
        mTextViewProfileFB.setText(user.getDisplayName());


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap mIcon = null;
            try {
                InputStream in = new URL(urls[0]).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                    mImageView.getLayoutParams().width = (getResources().getDisplayMetrics().widthPixels / 100) * 24;
                mImageView.setImageBitmap(result);
            }
        }
    }


}
