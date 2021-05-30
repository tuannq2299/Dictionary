package com.tuannq.tflat;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONObject;

public class DictionaryRequest extends AsyncTask<String,Integer,String>{
    final String app_id = "6b1f6cae";
    final String app_key = "a68b0b254a7d42f295118179935bf538";
    String myURL;
    TextView tv;
    MaterialToolbar topAppBar;
    DictionaryRequest(TextView tv) {
        this.tv= tv;
    }


    @Override
    protected String doInBackground(String... params) {
        myURL = params[0];
        try {
            URL url = new URL(myURL);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
                catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            JSONObject js = new JSONObject(s);
            JSONArray results = js.getJSONArray("results");

            JSONObject lEntries = results.getJSONObject(0);
            JSONArray lArr = lEntries.getJSONArray("lexicalEntries");
//
            JSONObject entries = lArr.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");
//
            JSONObject temp = e.getJSONObject(0);
            JSONArray temp1 = temp.getJSONArray("senses");
//
            JSONObject temp2 = temp1.getJSONObject(0);
            JSONArray def = temp2.getJSONArray("definitions");
            JSONArray ex = temp2.getJSONArray("examples");
            JSONArray syn = temp2.getJSONArray("synonyms");
            String list_ex="";
            for(int i=0;i<ex.length();i++){
                String example=ex.getJSONObject(i).getString("text");
                list_ex= list_ex +"- " + example + "\n";
            }

            String list_syn="";
            for(int i=0;i<syn.length();i++){
                String example=syn.getJSONObject(i).getString("text");
                list_syn= list_syn + "- "+example + "\n";
            }

            String rs1;
            rs1 = def.getString(0);

//            Log.d("temp","Defintion:\n"+rs1+"\nExample:\n"+list_ex+"\nSynonysm:\n"+list_syn);
            tv.setText("Defintion:\n"+rs1+"\nExample:\n"+list_ex+"\nSynonysm:\n"+list_syn);


        }
        catch(Exception e){
            tv.setText("NO WORD!");
            e.printStackTrace();
        }

    }
}
