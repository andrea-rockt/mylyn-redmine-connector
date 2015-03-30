package com.taskadapter.redmineapi.bean;


public class Group implements Identifiable {
	
    private Integer id;
    private String name;
    public Group() {
    
    }
    /**
     * Use GroupFactory to create instances of this class.
     *
     * @param id database ID.
     */
    public Group(Integer id) {
        this.id = id;
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;

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


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
