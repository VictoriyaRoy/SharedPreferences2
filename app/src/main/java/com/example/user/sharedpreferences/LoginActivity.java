package com.example.user.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static SharedPreferences mSettings;
    private EditText loginEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Button signIn = findViewById(R.id.sign_in);


        if (mSettings.getString(LOGIN, "") == "") {
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginEditText = findViewById(R.id.login);
                    passwordEditText = findViewById(R.id.password);
                    if (loginEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
                        emptyEditText();
                    } else {
                        String login = loginEditText.getText().toString();
                        String password = passwordEditText.getText().toString();
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putString(LOGIN, login);
                        editor.putString(PASSWORD, password);
                        editor.apply();
                        startMainActivity();

                    }
                }
            });
        }else{
            startMainActivity();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(new Intent(LoginActivity.this, MainActivity.class));
        intent.putExtra(LOGIN,mSettings.getString(LOGIN,""));
        startActivity(intent);
    }

    private void emptyEditText() {
        Toast.makeText(this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
    }


}

