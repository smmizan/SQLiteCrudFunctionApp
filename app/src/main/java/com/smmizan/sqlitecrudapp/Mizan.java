package com.smmizan.sqlitecrudapp;

/**
 * Created by Mizan on 17/03/2019.
 */

public class Mizan {
    String id,name;


    public Mizan(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return id +" "+ name;
    }
}
