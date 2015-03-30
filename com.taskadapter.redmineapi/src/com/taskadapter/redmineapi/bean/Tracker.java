package com.taskadapter.redmineapi.bean;

import java.io.Serializable;

/**
 * Redmine's Tracker (bug/feature/task/...)
 */
public class Tracker implements Identifiable, Serializable {

    /**
     * database ID.
     */
    private Integer id;

    private String name;

    
    public Tracker() {
        
    }
    
    /**
     * Use TrackerFactory to create instances of this class.
     *
     * @param id database ID.
     *
     * @see com.taskadapter.redmineapi.bean.TrackerFactory
     */
    public Tracker(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tracker tracker = (Tracker) o;

        if (id != null ? !id.equals(tracker.id) : tracker.id != null) return false;

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

    public Integer setId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
