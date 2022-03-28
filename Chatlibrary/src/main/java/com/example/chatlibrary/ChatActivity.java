package com.example.chatlibrary;

import static android.content.ContentValues.TAG;
import static com.example.chatlibrary.Constants.BOT_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
       // String apikey=BuildConfig.API_KEY;
       // String botkey=BuildConfig.BOT_KEY;

        //String url="https://www.google.com/";
       // String url= "https://app.gaadibaazar.in/page/?h=aef2f0fc-e635-4531-bea5-506d612f0f42";
        String url=Constants.BASE_URL+BOT_ID;
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