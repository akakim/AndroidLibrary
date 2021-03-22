package com.dream.kimlibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dream.kimlibrary.R;
import com.dream.kimlibrary.model.DataMovie;

import java.util.ArrayList;
import java.util.List;

public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    public static final String HEADER = "h";
//    public static final String CHILD  = "c";

    private ArrayList<DataMovie> lst = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder view = null;

        float dp = parent.getContext().getResources().getDisplayMetrics().density;

        switch ( viewType ){
            case DataMovie.HEADER:
                view = new HeaderItemHolder (
                            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_header, parent, false)
                        );
                return view;
            case DataMovie.CHILD:
                view = new ChildItemHolder (
                            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false)
                        );
                break;
        }


        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final DataMovie aItem = lst.get(position);

        switch ( aItem.getViewType() ){
            case DataMovie.HEADER:
                final HeaderItemHolder headerItemHolder = (HeaderItemHolder) holder;
                headerItemHolder.tvTitle.setText( aItem.getContent() );
                break;
            case DataMovie.CHILD:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType( lst.get(position).getViewType() );
    }

    public void setDefaultData(){

        lst.clear();

        lst.add(new DataMovie( DataMovie.HEADER,"호러"));
        lst.add(new DataMovie( DataMovie.CHILD,"할머니는 마법사"));
        lst.add(new DataMovie( DataMovie.CHILD,"스토커"));
        lst.add(new DataMovie( DataMovie.CHILD,"뱀파이어"));
        lst.add(new DataMovie( DataMovie.HEADER,"SF"));
        lst.add(new DataMovie( DataMovie.CHILD,"에일리언"));
        lst.add(new DataMovie( DataMovie.CHILD,"아이로봇"));
        lst.add(new DataMovie( DataMovie.CHILD,"기동전사 건담"));
    }

    public ArrayList<DataMovie> getDataList() {
        return lst;
    }

    private static class HeaderItemHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public ImageView ivBtn;
        public DataMovie dataMovie;


        public HeaderItemHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivBtn = itemView.findViewById(R.id.ivBtn);

        }
    }

    private static class ChildItemHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;

        public ChildItemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
