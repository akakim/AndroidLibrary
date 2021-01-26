package com.dream.kimlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.dream.kimlibrary.R;
import com.dream.kimlibrary.model.TestActivityItem;

import java.util.ArrayList;
import java.util.List;


public class TestListAdapter extends Adapter<TestListAdapter.TestListItemHolder> {

    private List<TestActivityItem> testActivityItems = new ArrayList<>();
    private Context context;
    private OnClickListener onClickListener;

    public TestListAdapter(Context context,OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TestListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final TestListItemHolder holder =  new TestListItemHolder( LayoutInflater.from(context)
                .inflate(R.layout.test_item, parent, false) );
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull TestListItemHolder holder, final int position) {

        holder.tvTitle.setText( testActivityItems.get(position).getTitle() );
        holder.tvSummary.setText( testActivityItems.get(position).getSummary() );

        holder.testItem.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onClickListener.onClick( testActivityItems.get( position ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return testActivityItems.size();
    }

    public List<TestActivityItem> getTestActivityItems() {
        return testActivityItems;
    }

    public void setTestActivityItems(List<TestActivityItem> testActivityItems) {
        this.testActivityItems.clear();
        this.testActivityItems.addAll(testActivityItems);
    }

    static class TestListItemHolder extends RecyclerView.ViewHolder{
        View testItem;
        AppCompatTextView tvTitle;
        AppCompatTextView tvSummary;

        public TestListItemHolder(@NonNull View itemView) {
            super(itemView);
            testItem = itemView;
            tvTitle   = itemView.findViewById(R.id.tvTitle);
            tvSummary = itemView.findViewById(R.id.tvSummary);
        }


        @Override
        public String toString() {
            return super.toString();
        }
    }

    public interface OnClickListener{
        public void onClick( final TestActivityItem clickedItem);
    }
}
