package com.tuannq.tflat.control;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.tuannq.tflat.Model.Word;
import com.tuannq.tflat.activity.TranslateActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DictionaryRequest extends AsyncTask<String,Integer,String>{
    final String app_id = "6b1f6cae";
    final String app_key = "a68b0b254a7d42f295118179935bf538";
    String myURL;
    TextView tv;
    TranslateActivity context;
    String word;
    public DictionaryRequest(TranslateActivity context, String w) {
        this.tv= context.getTvMeaning();
        this.context=context;
        this.word=w;
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

            JSONObject entries = lArr.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject temp = e.getJSONObject(0);
            JSONArray temp1 = temp.getJSONArray("senses");

            JSONObject temp2 = temp1.getJSONObject(0);
            JSONArray def = temp2.getJSONArray("definitions");
            JSONArray ex = temp2.getJSONArray("examples");
            JSONArray syn = temp2.getJSONArray("synonyms");
            String list_ex="";
            String eg="";
            for(int i=0;i<ex.length();i++){

                String example=ex.getJSONObject(i).getString("text");
                if(i==0){
                    eg=example;
                }
                list_ex= list_ex +"- " + example + "\n";
                //Toast.makeText(context,eg,Toast.LENGTH_SHORT).show();
            }

            String list_syn="";
            for(int i=0;i<syn.length();i++){
                String example=syn.getJSONObject(i).getString("text");
                list_syn= list_syn + "- "+example + "\n";
            }

            String rs1;
            rs1 = def.getString(0);
            rs1=rs1.replace("'","");
            eg=eg.replace("'","");

//            Log.d("temp","Defintion:\n"+rs1+"\nExample:\n"+list_ex+"\nSynonysm:\n"+list_syn);
            CRUD crud=new CRUD(context);
            Word w=new Word();
            w.setWord(word);
            w.setMean(rs1);
            w.setExamp(eg);
            context.setW(w);
            if(crud.findWord(word).getWord().equals(word)){

            }else{
                crud.insertWord(new Word(1, word, rs1, eg));
            }
            tv.setText("Defintion:\n"+rs1+"\n\nExample:\n"+list_ex+"\nSynonysm:\n"+list_syn);


        }
        catch(Exception e){
            tv.setText("NO WORD!");
            e.printStackTrace();
        }

    }
}
