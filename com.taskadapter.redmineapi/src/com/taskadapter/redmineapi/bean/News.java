package com.taskadapter.redmineapi.bean;

import java.io.Serializable;
import java.util.Date;

public class News implements Identifiable, Serializable {
    private static final long serialVersionUID = 1L;

    private  Integer id;

    private Project project;
    private User user;
    private String title;
    private String description;
    private Date createdOn;
    private String link;

    public News() {
        
    }
    
    public News(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != null ? !id.equals(news.id) : news.id != null) return false;

        return true;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public Project getProject() {
        return project;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void setCreatedOn(Date aCreated) {
        this.createdOn = aCreated;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    public void setLink(String aLink) {
        this.link = aLink;
    }

    public void setProject(Project aProject) {
        this.project = aProject;
    }

    public void setTitle(String aTitle) {
        this.title = aTitle;
    }

    public void setUser(User aUser) {
        this.user = aUser;
    }

    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + "]";
    }
}
