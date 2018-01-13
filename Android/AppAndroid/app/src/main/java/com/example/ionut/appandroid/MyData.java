package com.example.ionut.appandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eusebiu on 5/22/2017.
 */

public class MyData {

    private static MyData mInstance = null;

    public static MyData getInstance() {
        if (mInstance == null) {
            mInstance = new MyData();
        }
        return mInstance;
    }

    List<MyObject> myList = new ArrayList<>();

    public List<MyObject> getMyList() {
        return myList;
    }

    public void setMyList(List<MyObject> myList) {
        this.myList = myList;
    }

    private MyData() {

    }
}