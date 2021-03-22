package com.dream.kimlibrary.activity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dream.kimlibrary.R;
import com.dream.kimlibrary.adapter.ExpandableRecyclerViewAdapter;
import com.dream.kimlibrary.model.DataMovie;

import java.util.ArrayList;
import java.util.List;

public class ExpandableList extends AppCompatActivity {

    RecyclerView rvExpandable;
    List<DataMovie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        rvExpandable = findViewById(R.id.rvExpandable);

        ExpandableRecyclerViewAdapter adapter= new ExpandableRecyclerViewAdapter();

        adapter.setDefaultData();
        movies.addAll (adapter.getDataList() );

        rvExpandable.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvExpandable.setAdapter( adapter );


    }
}