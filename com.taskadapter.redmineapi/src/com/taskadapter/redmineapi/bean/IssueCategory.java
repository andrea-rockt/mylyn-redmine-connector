package com.taskadapter.redmineapi.bean;

import java.io.Serializable;

/**
 * Redmine issue category.
 */
public class IssueCategory implements Identifiable, Serializable {
	private static final long serialVersionUID = -109010410391968475L;

    /**
     * database ID.
     */
	private  Integer id;

    private String name;
    private Project project;
    private User assignee;

    /**
     * @param id database ID.
     */
    public IssueCategory(Integer id) {
        this.id = id;
    }
    
    public IssueCategory()
    {
    	
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueCategory that = (IssueCategory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    public User getAssignee() {
        return assignee;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "IssueCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", assignee=" + assignee +
                '}';
    }
}
