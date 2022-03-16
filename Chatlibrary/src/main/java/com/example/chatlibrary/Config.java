package com.example.chatlibrary;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Config {
   /* private static final String TAG = Config.class.getSimpleName();
    private static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/config.txt";
    private static Config sInstance = null;*/

    private static final String TAG=Config.class.getSimpleName();
    public  Config(){

    }
    public String makeServiceCall(String reqUrl){
        String respose=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //read the response
            InputStream in=new BufferedInputStream(conn.getInputStream());
            respose = convertStreamToString(in);

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respose;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String line;
        try{
            while ((line=reader.readLine())!=null){
                sb.append(line).append('\n');

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
