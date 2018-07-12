package me.ashikada.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private EditText emailInput;
    private Button loginBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the variables
        usernameInput = findViewById(R.id.username_et);
        passwordInput = findViewById(R.id.password_et);             //et means EditText
        emailInput = findViewById(R.id.email_et);
        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.signUp_btn);


        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                login(username, password);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                final String email = emailInput.getText().toString();

                signUp(username, password, email);
            }
        });

    }

    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null ){          //if there's no errors
                    Log.d("LoginActivity", "Login successful!");

                    final Intent intent = new Intent(MainActivity.this, PostActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                    Log.e("LoginActivity", "Login failure.");
                    e.printStackTrace();
                }
            }
        });

    }

        //my own method
    private void signUp(String username, String password, String email){
        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

            // Set custom properties
//        user.put("phone", "650-253-0000");

            // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("LoginActivity", "Sign up successful!");


                    final Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_LONG).show();

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                    Log.e("LoginActivity", "Sign up failure.");
                    e.printStackTrace();
                }
            }
        });
    }



}
