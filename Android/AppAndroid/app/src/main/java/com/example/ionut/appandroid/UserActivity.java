package com.example.ionut.appandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    Button btnStart;
    EditText edusername, edlocation;
    String username, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btnStart = (Button) findViewById(R.id.btnStart);
        edusername = (EditText) findViewById(R.id.username);
        edlocation = (EditText) findViewById(R.id.location);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                username = edusername.getText().toString();
                location = edlocation.getText().toString();
                intent.putExtra("Username", username);
                intent.putExtra("Location", location);
                if (TextUtils.isEmpty(username)) {
                    edusername.setError("Username is empty");
                    return;
                } else if (TextUtils.isEmpty(location)) {
                    edlocation.setError("Location is empty");
                    return;
                } else {
                    startActivity(intent);
                }
            }
        });
    }
}
