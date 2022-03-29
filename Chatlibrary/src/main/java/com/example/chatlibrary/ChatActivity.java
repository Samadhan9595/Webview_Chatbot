package com.example.chatlibrary;

import static android.content.ContentValues.TAG;
import static com.example.chatlibrary.Constants.BOT_ID;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatActivity extends AppCompatActivity {

    WebView wv1;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        wv1=(WebView)findViewById(R.id.webView);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        wv1.setWebViewClient(new MyBrowser());
        String abc=BuildConfig.LIBRARY_PACKAGE_NAME;
        Intent intent = getIntent();
        String Botid = intent.getStringExtra("data");
        System.out.println("dependency pass data"+Botid);
        //String intentdata = (getIntent().getStringExtra("data");
        String androidId= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        String devicemodel= Build.MODEL;
        System.out.println("android Id Chat Activity"+androidId);
        System.out.println("device model chat Activity"+devicemodel);
       // String apikey=BuildConfig.API_KEY;
       // String botkey=BuildConfig.BOT_KEY;

        //String url="https://www.google.com/";
       // String url= "https://app.gaadibaazar.in/page/?h=aef2f0fc-e635-4531-bea5-506d612f0f42";
        //String deviceUrl=Constants.BASE_URL+androidId
        String url=Constants.BASE_URL+Botid+"&"+"store_session=1"+"&"+"mobile=1"+"&"+"device_id="+androidId+"&"+"device_name="+devicemodel;
        System.out.println("device details url"+url);

      //  String url=BuildConfig.API_KEY+BuildConfig.BOT_KEY;//apikey+botkey;
      //  System.out.println("url of gaddibazzar"+url);
        //String url=Constants.BASE_URL+botkey;
        //wv1.loadUrl("http://www.tutorialspoint.com");
        WebSettings settings = wv1.getSettings();
        wv1.getSettings().setLoadsImagesAutomatically(true);
        this.wv1.getSettings().setDomStorageEnabled(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        WebBackForwardList wbfl = wv1.copyBackForwardList();
        int currentSize = wbfl.getSize();
        System.out.println("current size"+currentSize);
        for(int i = 0; i < currentSize; ++i)
        {
            WebHistoryItem item = wbfl.getItemAtIndex(i);
             url = item.getUrl();
            Log.d(TAG, "The URL at index: "+Integer.toString(i)+"is"+url);
        }
       // this.wv1.getSettings().setJavaScriptEnabled(true);

       /* settings.javaScriptEnabled = true;
        settings.useWideViewPort = true;*/
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);

        wv1.clearView();
        wv1.measure(100, 100);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //wv1.destroy();
                finish();
            }
        });
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}