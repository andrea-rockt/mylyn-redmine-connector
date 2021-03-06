package com.taskadapter.redmineapi.bean;

import java.io.Serializable;

/**
 * Redmine Issue Status ("new", "in progress", etc)
 */
public class IssueStatus implements Identifiable, Serializable {
	private static final long serialVersionUID = -2221390098554222099L;

	private Integer id;

    private String name;
    private boolean defaultStatus = false;
    private boolean closed = false;

    
    public IssueStatus()
    {
    }
    
    public IssueStatus(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueStatus that = (IssueStatus) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isDefaultStatus() {
        return defaultStatus;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setDefaultStatus(boolean defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status [id=" + id + ", name=" + name + ", isDefault=" + defaultStatus + ", closed=" + closed + "]";
    }

}
