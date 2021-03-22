package com.dream.kimlibrary.model;

public class DataMovie {

    public static final int HEADER = 0;
    public static final int CHILD  = 1;

    // image를 위한 값 .
    public static final int DEFAULT = -1;
    int image;

    int viewType;
    String content;

    public DataMovie(int type, String title) {
        this (DEFAULT,type,title);
    }

    public DataMovie(int image, int viewType, String title) {
        this.image = image;
        this.viewType = viewType;
        this.content = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
