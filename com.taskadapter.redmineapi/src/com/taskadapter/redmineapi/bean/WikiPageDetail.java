package com.taskadapter.redmineapi.bean;

import java.util.List;

public class WikiPageDetail extends WikiPage {

    private WikiPageDetail parent;
    private String text;
    private User user;
    private String comments;
    private List<Attachment> attachments;

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public String getComments() {
        return comments;
    }

    public WikiPageDetail getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setParent(WikiPageDetail parent) {
        this.parent = parent;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WikiPageDetail{" +
                "text='" + text + '\'' +
                '}';
    }
}