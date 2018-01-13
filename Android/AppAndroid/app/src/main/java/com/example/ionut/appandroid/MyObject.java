package com.example.ionut.appandroid;

public class MyObject {

    String title;
    String elevation;
    String lat;
    String lng;
    String wikipediaUrl;


    public MyObject(String title, String elevation, String lat, String lng, String wikipediaUrl) {
        this.title = title;
        this.elevation = elevation;
        this.lat = lat;
        this.lng = lng;
        this.wikipediaUrl = wikipediaUrl;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }
}
