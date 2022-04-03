package com.example.chatlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Example extends AppCompatActivity {

    FloatingActionButton fab_examChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        //create floating button
        fab_examChat=(FloatingActionButton) findViewById(R.id.fab_examChat);
        fab_examChat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Example.this,ChatActivity.class);
                intent.putExtra("data", "aef2f0fc-e635-4531-bea5-506d612f0f42");//pass your bot_id here
                startActivity(intent);
            }
        });
    }
}