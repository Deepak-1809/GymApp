package com.example.gymapp.Domain;

import java.io.Serializable;

public class Lessons implements Serializable {
    private String title;
    private String duration;
    private String link;
    private String picPath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Lessons(String title, String duration, String link, String picPath) {
        this.title = title;
        this.duration = duration;
        this.link = link;
        this.picPath = picPath;
    }
}
