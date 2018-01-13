package com.example.ionut.appandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyData {

    private static MyData mInstance;
    private static List<MyObject> myList = new ArrayList<>();

    private MyData() {

    }

    public static MyData getInstance() {
        if (mInstance == null) {
            mInstance = new MyData();
        }
        return mInstance;
    }

    public List<MyObject> getMyList() {
        return myList;
    }

    public static void addObjectToList(MyObject obj){
        myList.add(obj);
    }

    public static void printMyList(){
        System.out.println("Lungime lista:"+ myList.size());
        for(MyObject obj : myList){
            System.out.println("Obj Title"+obj.getTitle());
            System.out.println("Obj Lat"+obj.getLat());
        }
    }
}