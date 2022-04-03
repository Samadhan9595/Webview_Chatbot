package com.example.chatlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingButtonActivity extends AppCompatActivity {

     public FloatingActionButton fab_floatact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_button);

        fab_floatact=(FloatingActionButton) findViewById(R.id.fab);
        Intent intent = getIntent();
        String Botid = intent.getStringExtra("data");
        fab_floatact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FloatingButtonActivity.this,ChatActivity.class);
                intent.putExtra("data", Botid);
                startActivity(intent);

            }
        });

        /*private void OpenPage(View v){

        }*/
    }
}