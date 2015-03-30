package com.taskadapter.redmineapi.bean;

public class IssuePriority {
    /**
     * database ID.
     */
    private final Integer id;

    private String name;
    private boolean isDefault;

    public IssuePriority() {
        this.id = null;
    }
    
    /**
     * Use IssuePriorityFactory to create instances of this class.
     *
     * @param id database ID.
     *
     * @see com.taskadapter.redmineapi.bean.IssuePriorityFactory
     */
    public IssuePriority(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssuePriority that = (IssuePriority) o;

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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer setId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Priority [id=" + id + ", name=" + name + ", isDefault="
                + isDefault + "]";
    }

}
