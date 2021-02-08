package com.dream.kimlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.dream.kimlibrary.activity.FragmentContainerAct;
import com.dream.kimlibrary.activity.GsonArray;
import com.dream.kimlibrary.activity.UIComponentEx;
import com.dream.kimlibrary.adapter.TestListAdapter;
import com.dream.kimlibrary.model.TestActivityItem;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements TestListAdapter.OnClickListener {

    RecyclerView rvMain;

    TestListAdapter listAdapter;

    List<TestActivityItem> lst = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        rvMain = findViewById(R.id.rvMain);

        addTestListAdapter();
        listAdapter= new TestListAdapter(this,this);
        listAdapter.setTestActivityItems( lst );
        rvMain.setAdapter( listAdapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setHasFixedSize( true );



    }

    @Override
    public void onClick(TestActivityItem clickedItem) {

        Intent i = new Intent(this,clickedItem.getaClass());

        startActivity( i );
    }


    private void addTestListAdapter(){
        lst.add( new TestActivityItem("ContainerFragment","Strongswan에서 들어갈 뷰 샘플 예시.", FragmentContainerAct.class));
        lst.add( new TestActivityItem("GSONArray 테스트",getString(R.string.title_gson_array), GsonArray.class));
        lst.add( new TestActivityItem("UI Fragment 예시",getString(R.string.title_gson_array), UIComponentEx.class));
    }
}