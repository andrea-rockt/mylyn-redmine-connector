package com.taskadapter.redmineapi.bean;

import java.util.List;

public class CustomField {

    private Integer id;
    private String name;
    private String value;
    private boolean multiple = false;
	private List<String> values;

	public CustomField()
	{
		
	}
	/**
     * Use CustomFieldFactory to create instances of this class.
     *
     * @param id database ID.
     */
    public CustomField(Integer id) {
        this.id = id;
    }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomField that = (CustomField) o;

        if (id != that.id) return false;

        return true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
		return !isMultiple() || values.size() == 0 ? value : values.get(0);
    }

    /**
	 * @return values list if this is a multi-line field, NULL otherwise.
	 */
	public List<String> getValues() {
		return values;
	}

    @Override
    public int hashCode() {
        return id;
    }
    
	/**
	 * @return the multiple
	 */
	public boolean isMultiple() {
		return multiple;
	}

	public Integer setId() {
        return id;
    }

	public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
		this.values = null;
		this.multiple = false;
    }

    /**
	 * @param values the values for multi-line custom field.
	 */
	public void setValues(List<String> values) {
		this.values = values;
		this.value = null;
		this.multiple = true;
	}

    @Override
    public String toString() {
        return "CustomField{" + "id=" + id + ", name='" + name + '\''
				+ ", value='" + value + '\'' + ", values=" + values + '}';
    }
}
