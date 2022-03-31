package com.example.chatlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingButtonActivity extends AppCompatActivity {

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_button);

        fab=(FloatingActionButton) findViewById(R.id.fab);
        Intent intent = getIntent();
        String Botid = intent.getStringExtra("data");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FloatingButtonActivity.this,ChatActivity.class);
                intent.putExtra("data", Botid);
                startActivity(intent);
            }
        });
    }
}