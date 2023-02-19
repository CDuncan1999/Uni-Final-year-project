package com.example.fyp3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.button.MaterialButton;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LogInScreen extends AppCompatActivity {
    //nonfacebook
    ImageView imageViewLogo;
    Button btnLogin;
    TextView Email;
    TextView Password;
    Button FBC;
    //facebook
    CallbackManager callbackManager;
    LoginButton login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
        //logo
        imageViewLogo = findViewById(R.id.imageViewLogo2);
        imageViewLogo.setImageResource(R.drawable.logo);
        //
        //////////////////////////////////////////////////////////////////////non-facebook login
        btnLogin = findViewById(R.id.btnLogin);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.emailPassword);
        FBC = findViewById(R.id.continue_button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                usrLogin(email, password);
            }
        });

        FBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInScreen.this, LoginSuccessActivity.class);
                startActivity(intent);

            }
        });
    }
        public void GoToSignUp(View view){
            Intent intent = new Intent(LogInScreen.this, SignUpActivity.class);
            startActivity(intent);
        }

        public void usrLogin(String u_name, String p_word){
            ParseUser.logInInBackground(u_name, p_word, (parseUser, e)-> {
                if(parseUser != null){
                    Toast.makeText(LogInScreen.this, "Successful login, welcome back"
                            + u_name + "!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent (this, LoginSuccessActivity.class);
                    intent.putExtra("Name", u_name);
                    startActivity(intent);
                }else{
                    ParseUser.logOut();
                    Toast.makeText(LogInScreen.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

///////////////////////////////////////////////////////////facebook
        login_button = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
            login_button.setPermissions(Arrays.asList("user_gender, user_friends, public_profile"));
            login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { Log.d("Demo", "Login successful"); }

            @Override
            public void onCancel() {
                Log.d("Demo", "Login cancelled");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Log.d("Demo", "Login error");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        Intent intent = new Intent(LogInScreen.this,LoginSuccessActivity.class);
        startActivity(intent);
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                LoginManager.getInstance().logOut();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.startTracking();
    }
}