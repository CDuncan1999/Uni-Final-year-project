package com.example.fyp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class VetDetails extends AppCompatActivity {
    ImageView imageViewLogo;
    EditText Company, Location1, Location2, Postcode, Reason, Bill;
    Button btnSubmit, btnBack;
    String editTextCompany , editTextLocation1, editTextLocation2, editTextPostcode, editTextReason;
    Integer editTextBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_details);

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
        Company = findViewById(R.id.editTextCompany);
        Location1 = findViewById(R.id.editTextLocation1);
        Location2 = findViewById(R.id.editTextLocation2);
        Postcode = findViewById(R.id.editTextPostcode);
        Reason = findViewById(R.id.editTextReason);
        Bill = findViewById(R.id.editTextBill);
        btnSubmit = findViewById(R.id.btnSubmitDetails);
        btnBack = findViewById(R.id.btnBack);
        //////////////////////////////////////////////////////////////
        // Takes user int inputs
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextCompany = Company.getText().toString();
                editTextLocation1 = Location1.getText().toString();
                editTextLocation2 = Location2.getText().toString();
                editTextPostcode = Postcode.getText().toString();
                editTextReason = Reason.getText().toString();
                editTextBill = Integer.parseInt(Bill.getText().toString());
                addToDatabase(editTextCompany, editTextLocation1, editTextLocation2,editTextPostcode, editTextReason,editTextBill);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VetDetails.this, LoginSuccessActivity.class);
                startActivity(intent);
            }
        });
    }

    // add the information to the back4app database
    public void addToDatabase(String editTextCompany, String editTextLocation1, String editTextLocation2, String editTextPostcode, String editTextReason, Integer editTextBill){
        ParseObject Vet = new ParseObject("Vet");

        Vet.put("Company", editTextCompany);
        Vet.put("Location1", editTextLocation1);
        Vet.put("Location2", editTextLocation2);
        Vet.put("Postcode", editTextPostcode);
        Vet.put("Reason", editTextReason);
        Vet.put("Bill", editTextBill);

        Vet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(VetDetails.this, "Vet details updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VetDetails.this, "Please enter all details!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}