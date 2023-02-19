package com.example.fyp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class SignUpActivity extends AppCompatActivity {
    ImageView imageViewLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //logo
        imageViewLogo = findViewById(R.id.imageViewLogo2);
        imageViewLogo.setImageResource(R.drawable.logo);
        //

        EditText usernameText = (EditText) findViewById(R.id.editText_createUserEmail);
        EditText passwordText = (EditText) findViewById(R.id.editText_createUserPassword);
        EditText confirmPasswordText = (EditText) findViewById(R.id.editText_confirmPassword);
        Button regButton = (Button) findViewById(R.id.button_ca);

        //adding onclick listener for button
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String confPassowrd = confirmPasswordText.getText().toString();

                if(password.equals(confPassowrd))
                    signUp(username, password);
                else
                    Toast.makeText(SignUpActivity.this,
                            "make sure the entries match",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signUp(String uname, String pword){
        ParseUser user = new ParseUser();
        //set the users uname and pword
        user.setUsername(uname);
        user.setPassword(pword);
        user.signUpInBackground(e -> {
            if (e==null){
                //if the error is null then display toast and redirect user
                Toast.makeText(SignUpActivity.this,"User Registered Successfully",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, com.example.fyp3.LogInScreen.class);
                startActivity(intent);
            } else{
                ParseUser.logOut();
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}