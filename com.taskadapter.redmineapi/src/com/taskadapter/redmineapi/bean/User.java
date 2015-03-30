package com.taskadapter.redmineapi.bean;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Redmine's User.
 */
public class User implements Identifiable {

    public static final Integer STATUS_LOCKED = 3;

    public static final Integer STATUS_ACTIVE = 1;

    /**
     * database ID.
     */
    private  Integer id;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String mail;
    private Date createdOn;
    private Date lastLoginOn;
    private String apiKey;
    private Integer authSourceId;
    private Integer status;
    // TODO add tests
    private final Set<CustomField> customFields = new HashSet<CustomField>();
	private final Set<Membership> memberships = new HashSet<Membership>();
	private final Set<Group> groups = new HashSet<Group>();

	
	public User()
    {
    
    }
	
    /**
     * Use UserFactory to create instances of this class.
     *
     * @param id database ID.
     *
     * @see UserFactory
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * If there is a custom field with the same ID already present,
     * the new field replaces the old one.
     *
     * @param customField the field to add.
     */
    public void addCustomField(CustomField customField) {
        customFields.add(customField);
    }
    
    /**
     * NOTE: The custom field(s) <strong>must have correct database ID set</strong> to be saved to Redmine. This is Redmine REST API's limitation.
     * ID can be seen in database or in Redmine administration when editing the custom field (number is part of the URL!).
     */
    public void addCustomFields(Collection<CustomField> customFields) {
        this.customFields.addAll(customFields);
    }

    public void addGroups(Collection<Group> groups) {
	   this.groups.addAll(groups);
	}

    public void addMemberships(Collection<Membership> memberships) {
		this.memberships.addAll(memberships);
	}

    public void clearCustomFields() {
        customFields.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    public String getApiKey() {
        return apiKey;
    }

    /**
     * This value is not returned by redmine on existing users.
     * 
     * @return - The Authentication Source ID, if you set it on your own.
     */
    @Deprecated
    public Integer getAuthSourceId() {
		return authSourceId;
	}

    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @return the value or NULL if the field is not found
     */
    public String getCustomField(String fieldName) {
        for (CustomField f : customFields) {
            if (f.getName().equals(fieldName)) {
                return f.getValue();
            }
        }
        return null;
    }

    /**
     * @return Custom Fields, NEVER NULL.
     */
    public Collection<CustomField> getCustomFields() {
        return Collections.unmodifiableCollection(customFields);
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * @return firstName + space + lastName
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Collection<Group> getGroups() {
	   return Collections.unmodifiableCollection(groups);
	   }

    @Override
    public Integer getId() {
        return id;
    }

    public Date getLastLoginOn() {
        return lastLoginOn;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

	/**
     * This field is empty when using issues.get(i).getAssignee().getMail()
     */
    public String getMail() {
        return mail;
    }

	public Collection<Membership> getMemberships() {
		return Collections.unmodifiableCollection(memberships);
	}

    public String getPassword() {
        return password;
    }

    /**
     * Returns the user status. As defined in Redmine:
     * <ul>
     *   <li>1: status active ({@link #STATUS_ACTIVE})</li>
     *   <li>3: status locked ({@link #STATUS_LOCKED})</li>
     * </ul>
     * 
     * @return User status
     */
    public Integer getStatus() {
        return status;
    }

    // TODO add junit test

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * APIKey property is read-only. This setter is only for serialization JSon.
     * The value you set using this method will be ignored by the server.
     */
    @Deprecated
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setAuthSourceId(Integer authSource) {
		this.authSourceId = authSource;
	}

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This is a BIG HACK just to workaround the crappy Redmine REST API limitation.
     * see http://www.redmine.org/issues/7487
     */
    public void setFullName(String fullName) {
        int ind = fullName.indexOf(' ');
        if (ind != -1) {
            this.firstName = fullName.substring(0, ind);
            this.lastName = fullName.substring(ind + 1);
        } else {
            this.firstName = fullName;
        }
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastLoginOn(Date lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public void setLogin(String login) {
        this.login = login;
    }

	public void setMail(String mail) {
        this.mail = mail;
    }

	public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user status.
     * 
     * @param status must be one of {@link #STATUS_ACTIVE} or {@link #STATUS_LOCKED}
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
