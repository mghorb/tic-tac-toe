package com.example.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Settings extends AppCompatActivity {

    TextInputLayout playerX;
    TextInputLayout playerO;
    Button updatebutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Intent intent = getIntent();

        playerX = findViewById(R.id.playerXname);
        // playerX.setHint(intent.getStringExtra("XNAME"));

        playerO = findViewById(R.id.playerOname);
        // playerO.setHint(intent.getStringExtra("ONAME"));

        updatebutton = findViewById(R.id.updatebutton);

        updatebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(Settings.this, MainActivity.class);
                //intent.putExtra("XNAME", playerX.getText().toString());
                //intent.putExtra("ONAME", playerO.getContext());
                //startActivity(intent);
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
