package com.taskadapter.redmineapi.bean;

import java.util.Date;

public class TimeEntry implements Identifiable {
    /**
     * database Id
     */
    private  Integer id;

    /**
     * database Id of the Issue
     */
    private Integer issueId;

    /**
     * database Id of the project
     */
    private Integer projectId;
    private String projectName;
    private String userName;
    private Integer userId;
    private String activityName;
    private Integer activityId;
    private Float hours;
    private String comment;
    private Date spentOn;
    private Date createdOn;
    private Date updatedOn;

    public TimeEntry() {
        
    }
    
    /**
     * @param id database Id
     */
    public TimeEntry(Integer id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeEntry timeEntry = (TimeEntry) o;

        if (id != null ? !id.equals(timeEntry.id) : timeEntry.id != null) return false;

        return true;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Float getHours() {
        return hours;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Date getSpentOn() {
        return spentOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public boolean isValid() {
        return (hours != null) && (projectId != null || issueId != null);
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setHours(Float hours) {
        this.hours = hours;
    }

    public Integer setId() {
        return id;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setSpentOn(Date spentOn) {
        this.spentOn = spentOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User \"" + userName + "\" spent " + hours
                + " hours on task " + issueId + " (project \"" + projectName
                + "\") doing " + activityName;
    }
}
