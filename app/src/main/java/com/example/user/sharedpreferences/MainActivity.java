package com.example.user.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.user.sharedpreferences.LoginActivity.mSettings;

public class MainActivity extends AppCompatActivity {

    TextView loginTv;
    Button signOutBtn;
    public static final String LOGIN = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginTv = findViewById(R.id.login_main);
        signOutBtn = findViewById(R.id.sign_out);

        Bundle arguments = getIntent().getExtras();
        loginTv.setText("Login: "+arguments.get(LOGIN).toString());

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSettings.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(new Intent(MainActivity.this, LoginActivity.class));
                startActivity(intent);
            }
        });
    }
}
