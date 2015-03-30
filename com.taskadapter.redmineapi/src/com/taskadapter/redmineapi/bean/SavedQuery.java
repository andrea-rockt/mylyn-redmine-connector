package com.taskadapter.redmineapi.bean;

public class SavedQuery {
    private Integer id;

    private String name;
    private boolean publicQuery;
    private Integer projectId;

    public SavedQuery() {
        
    }

    /**
     * @param id database Id
     */
    public SavedQuery(Integer id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SavedQuery that = (SavedQuery) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }
    
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public boolean isPublicQuery() {
        return publicQuery;
    }

    public Integer setId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectId(Integer value) {
        this.projectId = value;
    }

    public void setPublicQuery(boolean isPublic) {
        this.publicQuery = isPublic;
    }

    @Override
    public String toString() {
        return "SavedQuery [id=" + id + ", name=" + name + ", publicQuery="
                + publicQuery + ", projectId=" + projectId + "]";
    }

}
