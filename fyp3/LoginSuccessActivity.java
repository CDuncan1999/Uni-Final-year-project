package com.example.fyp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSuccessActivity extends AppCompatActivity {
    ImageView imageViewLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        //logo
        imageViewLogo = findViewById(R.id.imageViewLogo2);
        imageViewLogo.setImageResource(R.drawable.logo);
        //

        TextView txtname = (TextView) findViewById(R.id.textView_success);
        Intent intent = getIntent();
        String loginName = intent.getStringExtra("Name" );


        if (loginName == null){
            txtname.setText("Welcome, Facebook user");
        } else {
            txtname.setText("Welcome, " + loginName);}
    }

    public void logOutOk(View view){
        Intent intent = new Intent(LoginSuccessActivity.this, LogInScreen.class);
        startActivity(intent);
        finish();
    }

    public void CameraAccount(View view){
            Intent intent = new Intent(LoginSuccessActivity.this, PhoneLink.class);
        startActivity(intent);
    }

    public void ViewDetails(View view){
        Intent intent = new Intent(LoginSuccessActivity.this, AccountDetails.class);
        startActivity(intent);
    }
    public void ViewPetDetails(View view){
        Intent intent = new Intent(LoginSuccessActivity.this, PetDetails.class);
        startActivity(intent);
    }
    public void ViewVetDetails(View view){
        Intent intent = new Intent(LoginSuccessActivity.this, VetDetails.class);
        startActivity(intent);
    }

}