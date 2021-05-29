package com.tuannq.tflat;
//
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import Model.TranslateParagraphHistory;

public class Translator extends AsyncTask<String,Integer,String> {
    TextView textView;
    String inputLang;
    String outputLang;
    TranslateParagraphHistory history;
    CRUD database;


    public Translator(TextView textView) {
        this.textView = textView;
    }

    public Translator(TextView textView, TranslateParagraphHistory history, CRUD database) {
        this.textView = textView;
        this.inputLang = history.getInputLang();
        this.outputLang = history.getOutputLang();
        this.database = database;
        this.history = history;
    }

    public Translator() {

    }
    // hàm chạy ngầm để gửi get request tới google script
    @Override
    protected String doInBackground(String... params) {
        try{
            String urlStr = "https://script.google.com/macros/s/AKfycbwWT9BNzQRMANmu-hgLYwxYB0fOWKd-1L76A0O5laLhiCcz5bfV2QJCovtOHve5SlVx/exec" +
                    "?q=" + URLEncoder.encode(params[0], "UTF-8") +
                    "&target=" + outputLang +
                    "&source=" + inputLang;
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

    // hàm khi gọi execute
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            textView.setText("Đoạn văn đã dịch nghĩa:\n"
                    + "- "+ s);
            // kiểm tra có output paragraph hay không
            if (!s.equals("")) {
                history.setOutputParagraph(s);
                database.insertParagrap(history);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
