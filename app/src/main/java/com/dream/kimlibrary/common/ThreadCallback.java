package com.dream.kimlibrary.common;

import com.dream.kimlibrary.activity.User;

public interface ThreadCallback {
    void  responseUser(User aUser);
    void  responseUser(User[] users);
    void onError( String msg );
}
