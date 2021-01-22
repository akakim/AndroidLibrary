package com.dream.kimlibrary.model;

public class TestActivityItem {

    private String title;
    private String summary;
    private Class<?> aClass;

    public TestActivityItem(String title, String summary, Class<?> aClass) {
        this.title = title;
        this.summary = summary;
        this.aClass = aClass;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
