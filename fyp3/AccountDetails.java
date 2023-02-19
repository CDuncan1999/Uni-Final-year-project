package com.example.fyp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class AccountDetails extends AppCompatActivity {
    EditText Title, FirstName, LastName;
    Button btnSubmit, btnBack;
    String editTextTitle, editTextFirstName, editTextLastName;
    ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        //logo
        imageViewLogo = findViewById(R.id.imageViewLogo2);
        imageViewLogo.setImageResource(R.drawable.logo);
        //
        // initializing our Parse application with
        // our application id, client key and server url
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                // at last we are building our
                // parse with the above credentials
                .build());
        //initialise
        Title = findViewById(R.id.editTextTitle);
        FirstName = findViewById(R.id.editTextFirstName);
        LastName = findViewById(R.id.editTextLastName);
        btnSubmit = findViewById(R.id.btnSubmitDetails);
        btnBack = findViewById(R.id.btnBack);

        //////////////////////////////////////////////////////////////
        // Takes user int inputs
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTitle = Title.getText().toString();
                editTextFirstName = FirstName.getText().toString();
                editTextLastName = LastName.getText().toString();
                addToDatabase(editTextTitle, editTextFirstName, editTextLastName);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountDetails.this, LoginSuccessActivity.class);
                startActivity(intent);
            }
        });
    }

    // add the information to the back4app database
    public void addToDatabase(String editTextTitle, String editTextFirstName, String editTextLastName){
        ParseObject Account = new ParseObject("Account");

        Account.put("Title", editTextTitle);
        Account.put("FirstName", editTextFirstName);
        Account.put("LastName", editTextLastName);

        Account.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(AccountDetails.this, "User details updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AccountDetails.this, "Please enter all details!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}