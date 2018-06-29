package com.example.abmoiz.myapplication2.Common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HTTPdataHandler {
    static  String stream=null;
     public HTTPdataHandler(){}


    public  String getHTTPdata(String urlString) {
         try {
             URL url=new URL(urlString);
             HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
             if(urlConnection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                 InputStream in =new BufferedInputStream(urlConnection.getInputStream());
                 BufferedReader r=new BufferedReader(new InputStreamReader(in));
                 StringBuilder sb=new StringBuilder();
                 String Line;
                 while((Line=r.readLine())!=null){

                     sb.append(Line);
                     stream= sb.toString();
                     urlConnection.disconnect();
                 }


             }


         }catch (MalformedURLException ex){

             ex.printStackTrace();
         }catch (IOException ex){

             ex.printStackTrace();
         }




        return stream;
    }
}
