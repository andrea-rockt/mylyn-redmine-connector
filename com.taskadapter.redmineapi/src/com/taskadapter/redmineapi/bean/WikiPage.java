package com.taskadapter.redmineapi.bean;

import java.util.Date;

public class WikiPage {

    private String title;
    private Integer version;
    private Date createdOn;
    private Date updatedOn;

    public WikiPage() {
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String getTitle() {
        return title;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Integer getVersion() {
        return version;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "WikiPage{" +
                "title='" + title + '\'' +
                ", version=" + version +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}