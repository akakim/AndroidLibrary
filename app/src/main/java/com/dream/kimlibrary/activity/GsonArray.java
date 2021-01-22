package com.dream.kimlibrary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

import com.dream.kimlibrary.R;
import com.dream.kimlibrary.common.ParseTask;
import com.dream.kimlibrary.common.ThreadCallback;


public class GsonArray extends AppCompatActivity implements View.OnClickListener, ThreadCallback {

    AppCompatTextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_array);

        findViewById(R.id.btnParsing).setOnClickListener( this );

        tvResult = findViewById( R.id.tvResult);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnParsing:

                ParseTask task = new ParseTask(this,getApplicationContext(),R.raw.user );
                    task.start();
                break;
        }
    }




    @Override
    public void responseUser(User aUser) {

    }

    @Override
    public void responseUser(User[] users) {

        StringBuilder builder = new StringBuilder();
        for (User user : users ) {
            builder.append( user.toString() );
        }

        tvResult.setText( builder.toString());
    }

    @Override
    public void onError(String msg) {

    }
}