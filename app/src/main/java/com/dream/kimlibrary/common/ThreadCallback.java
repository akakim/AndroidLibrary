package com.dream.kimlibrary.common;

import com.dream.kimlibrary.activity.User;

public interface ThreadCallback {
    public void  responseUser(User aUser);
    public void  responseUser(User[] users);
    public void onError( String msg );
}
