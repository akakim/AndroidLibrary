package com.dream.kimlibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.kimlibrary.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class GsonArray extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_array);

        findViewById(R.id.btnParsing).setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnParsing:

                break;
        }
    }

    public void parsingUSERJSONArray() throws IOException{
        InputStream is = getResources().openRawResource(R.raw.user);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( is != null )
            is.close();
        }

        String jsonString = writer.toString();
    }
}