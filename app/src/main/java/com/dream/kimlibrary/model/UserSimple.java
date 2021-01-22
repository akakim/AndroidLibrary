package com.dream.kimlibrary.model;

public class UserSimple {
    private String name;
    private String email;
    private boolean isDev;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDev() {
        return isDev;
    }

    public void setDev(boolean dev) {
        isDev = dev;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
