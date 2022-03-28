package com.example.chatbotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.chatlibrary.ChatActivity;
import com.example.chatlibrary.NotificationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String modal_id = "Model_id";
    public static final String model_name = "Model_Name";
    public static final String brand = "Brand";
    SharedPreferences sharedpreferences;

    TextView android_id,android_info,installDate_tv,brand_tv,device_tv,board,model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.activity_main);
        //String apikey=BuildConfig.BUILD_TYPE;

        String myapikey=BuildConfig.API_KEY;
        System.out.println(myapikey);
        //String myApiKey = BuildConfig.;
        FloatingActionButton fab = findViewById(R.id.fab);
        TextView notification_tv=findViewById(R.id.notification_tv);

        android_id = (TextView)findViewById(R.id.textView2);
        android_info = (TextView)findViewById(R.id.textView3);
        installDate_tv=(TextView)findViewById(R.id.installDate_tv);
        brand_tv=(TextView)findViewById(R.id.brand_tv);
        device_tv=(TextView)findViewById(R.id.device_tv);
        board=(TextView)findViewById(R.id.board);
        model=(TextView)findViewById(R.id.model);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String androidId= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        //android_id.setText("Model Id"+androidId);
        //String android_id=androidId;
        //chat_fab=(FloatingActionButton) findViewById(R.id.chat_fab);
        //floatingButton_fb=(FloatingActionMenu)findViewById(R.id.floatingButton_fb);

        // WebView browser = (WebView) findViewById(R.id.webview);
        PackageManager pm = getApplicationContext().getPackageManager();
        String pkgName = getApplicationContext().getPackageName();
        PackageInfo pkgInfo = null;



        try {
            pkgInfo = pm.getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String date=installDate_tv.getText().toString();

        Long ver = pkgInfo.firstInstallTime;
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date result = new Date(ver);

      /*  installDate_tv.setText("" +simple.format(result));
        android_info.setText("PRODUCT:"+ Build.PRODUCT+"\n");
        brand_tv.setText("BRAND:"+Build.BRAND);
        device_tv.setText("Device:"+Build.DEVICE);
        board.setText("board"+Build.BOARD);
        model.setText("model"+Build.MODEL);*/
        String devicemodel=Build.MODEL;
        String abc="DEVICE"+Build.DEVICE;
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(modal_id,androidId);
        editor.putString(model_name,devicemodel);
        editor.commit();


        //SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String model_id = (shared.getString(modal_id, ""));
        String channel=(shared.getString(model_name,""));

        if(model_id.isEmpty()){
            editor.putString(model_id,androidId);
        }
        else {
            Intent intent=new Intent(MainActivity.this,ChatActivity.class);
            startActivity(intent);
        }


       /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String imgSett = prefs.getString(modal, "");*/
        System.out.println("model_name"+channel);
        System.out.println("model Id"+model_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(i);
            }
        });
        notification_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });
    }
}