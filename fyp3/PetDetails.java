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

public class PetDetails extends AppCompatActivity {
    ImageView imageViewLogo;
    EditText Type, Breed, Gender, DOB, LVV, LGV;
    Button btnSubmit, btnBack;
    String editTextType , editTextBreed , editTextDOB,editTextGender,editTextLVV,editTextLGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

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
        Type = findViewById(R.id.editTextType);
        Breed = findViewById(R.id.editTextBreed);
        Gender = findViewById(R.id.editTextGender);
        DOB = findViewById(R.id.editTextDOB);
        LVV = findViewById(R.id.editTextLVV);
        LGV = findViewById(R.id.editTextLGV);
        btnSubmit = findViewById(R.id.btnSubmitDetails);
        btnBack = findViewById(R.id.btnBack);

//////////////////////////////////////////////////////////////
    // Takes user int inputs
        btnSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editTextType = Type.getText().toString();
            editTextBreed = Breed.getText().toString();
            editTextGender = Gender.getText().toString();
            editTextDOB = DOB.getText().toString();
            editTextLVV = LVV.getText().toString();
            editTextLGV = LGV.getText().toString();
            addToDatabase(editTextType, editTextBreed, editTextGender, editTextDOB, editTextLVV,editTextLGV);
        }
    });

        btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PetDetails.this, LoginSuccessActivity.class);
            startActivity(intent);
        }
    });
}

    // add the information to the back4app database
    public void addToDatabase(String editTextType, String editTextBreed,String editTextGender,String editTextDOB,String editTextLVV,String editTextLGV){
        ParseObject Pet = new ParseObject("Pet");

        Pet.put("Type", editTextType);
        Pet.put("Breed", editTextBreed);
        Pet.put("GENDER", editTextGender);
        Pet.put("DOB", editTextDOB);
        Pet.put("LVV", editTextLVV);
        Pet.put("LGV", editTextLGV);

        Pet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(PetDetails.this, "Pet details updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PetDetails.this, "Please enter all details!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}