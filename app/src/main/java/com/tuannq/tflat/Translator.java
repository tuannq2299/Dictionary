package com.tuannq.tflat;
//
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Translator extends AsyncTask<String,Integer,String> {
    TextView textView;

    public Translator(TextView textView) {
        this.textView = textView;
    }

    public Translator() {

    }

    @Override
    protected String doInBackground(String... params) {
        try{
            String urlStr = "https://script.google.com/macros/s/AKfycbwWT9BNzQRMANmu-hgLYwxYB0fOWKd-1L76A0O5laLhiCcz5bfV2QJCovtOHve5SlVx/exec" +
                    "?q=" + URLEncoder.encode(params[0], "UTF-8") +
                    "&target=" + "vi" +
                    "&source=" + "en";
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            textView.setText("Đoạn văn đã dịch nghĩa:\n"
                    + "- "+ s);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}