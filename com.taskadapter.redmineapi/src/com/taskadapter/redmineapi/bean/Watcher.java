package com.taskadapter.redmineapi.bean;

/**
 * Issue Watcher for a Redmine issue
 */
public class Watcher {

    private Integer id;
    private String name;

    public Watcher() {
    }

    /**
     * Use WatcherFactory to create instances of this class.
     *
     * @param id database Id
     */
    public Watcher(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Watcher watcher = (Watcher) o;

        if (id != null ? !id.equals(watcher.id) : watcher.id != null) return false;

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

    @Override
    public String toString() {
        return "Watcher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
