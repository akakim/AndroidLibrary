package com.dream.kimlibrary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.dream.kimlibrary.R;
import com.dream.kimlibrary.adapter.CommonDecorator;
import com.dream.kimlibrary.adapter.UserListAdapter;
import com.dream.kimlibrary.common.ParseTask;
import com.dream.kimlibrary.common.ThreadCallback;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GsonArray extends AppCompatActivity implements View.OnClickListener, ThreadCallback
            ,UserListAdapter.OnClickListener{

    AppCompatTextView tvResult;
    UserListAdapter userListAdapter;
    RecyclerView rvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_array);

        findViewById(R.id.btnParsing).setOnClickListener( this );



        rvMain = findViewById( R.id.rvMain);
        rvMain.setLayoutManager( new LinearLayoutManager(this));

        userListAdapter = new UserListAdapter(this ,this );
        rvMain.setAdapter( userListAdapter );
        rvMain.addItemDecoration( new CommonDecorator());

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

        List<User> lst = Arrays.asList(users);
        userListAdapter.setUserList( lst );

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onClick(User clickedItem) {

    }
}