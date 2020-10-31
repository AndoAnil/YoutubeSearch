package com.example.youtube;

public class Youtube_data {
    String thumbnails,title,channelTitle,publishTime;

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Youtube_data(String thumbnails, String title, String channelTitle, String publishTime) {
        this.thumbnails = thumbnails;
        this.title = title;
        this.channelTitle = channelTitle;
        this.publishTime = publishTime;
    }


}
