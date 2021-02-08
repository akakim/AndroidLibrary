package com.dream.kimlibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dream.kimlibrary.R;
import com.dream.kimlibrary.fragment.SimpleDialog;

public class UIComponentEx extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_component_ex);

        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SimpleDialog(UIComponentEx.this,"title","message").show(getSupportFragmentManager(),"simpleTag");
            }
        });
    }
}