package com.taskadapter.redmineapi.bean;

import java.util.Date;

/**
 * Repository Change for a Redmine issue
 */
public class Changeset {
	
	private String revision;
	
    private User user;
    private String comments;
    private Date committedOn;
    public Changeset()
	{
		
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Changeset changeset = (Changeset) o;

        if (revision != null ? !revision.equals(changeset.revision) : changeset.revision != null) return false;

        return true;
    }

    public String getComments() {
        return comments;
    }

    public Date getCommittedOn() {
        return committedOn;
    }

    public String getRevision() {
        return revision;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return revision != null ? revision.hashCode() : 0;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCommittedOn(Date committedOn) {
        this.committedOn = committedOn;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Changeset{" +
                "comments='" + comments + '\'' +
                ", revision='" + revision + '\'' +
                ", user=" + user +
                ", committedOn=" + committedOn +
                '}';
    }

}
