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
import com.dream.kimlibrary.activity.User;
import com.dream.kimlibrary.model.TestActivityItem;

import java.util.ArrayList;
import java.util.List;


public class UserListAdapter extends Adapter<UserListAdapter.UserListItemHolder> {

    private List<User> userList = new ArrayList<>();
    private Context context;
    private OnClickListener onClickListener;

    public UserListAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public UserListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final UserListItemHolder holder =  new UserListItemHolder( LayoutInflater.from(context)
                .inflate(  R.layout.user_item , parent, false) );
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull UserListItemHolder holder, final int position) {

        holder.tvID.setText( String.valueOf( userList.get(position).getId() ));
        holder.tvName.setText( userList.get(position).getName() );

        holder.testItem.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onClickListener.onClick( userList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    static class UserListItemHolder extends RecyclerView.ViewHolder{
        View testItem;
        AppCompatTextView tvID;
        AppCompatTextView tvName;

        public UserListItemHolder(@NonNull View itemView) {
            super(itemView);
            testItem = itemView;
            tvID   = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
        }


        @Override
        public String toString() {
            return super.toString();
        }
    }

    public interface OnClickListener{
        public void onClick( final User clickedItem);
    }
}
