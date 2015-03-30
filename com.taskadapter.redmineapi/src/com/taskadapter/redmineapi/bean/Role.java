package com.taskadapter.redmineapi.bean;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * User's role.
 */
public class Role {

    /**
     * database ID.
     */
    private Integer id;

	private String name;
	private Boolean inherited;

	private final Collection<String> permissions = new HashSet<String>();

    public Role() {
        
    }
    /**
     * Use RoleFactory to create instances of this class.
     *
     * @param id database ID.
     *
     * @see com.taskadapter.redmineapi.bean.RoleFactory
     */
    public Role(Integer id) {
        this.id = id;
    }

    public void addPermissions(Collection<String> permissions) {
        this.permissions.addAll(permissions);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

	public Integer getId() {
		return id;
	}

	public Boolean getInherited() {
		return inherited;
	}

	public String getName() {
		return name;
	}

	public Collection<String> getPermissions() {
        return Collections.unmodifiableCollection(permissions);
    }
	
	@Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Integer setId() {
        return id;
    }

    public void setInherited(Boolean inherited) {
		this.inherited = inherited;
	}

    public void setName(String name) {
		this.name = name;
	}

    @Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", inherited=" + inherited
				+ "]";
	}
}
