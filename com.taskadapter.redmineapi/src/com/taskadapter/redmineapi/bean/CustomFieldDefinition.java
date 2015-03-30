package com.taskadapter.redmineapi.bean;

import java.util.ArrayList;
import java.util.List;

public class CustomFieldDefinition {

    private Integer id;
    private String name;
    private String customizedType;
    private String fieldFormat;
    private String regexp;
    private Integer minLength;
    private Integer maxLength;
    private boolean required;
    private boolean filter;
    private boolean searchable;
    private boolean multiple;
    private String defaultValue;
    private boolean visible;
    private final List<String> possibleValues = new ArrayList<String>();
    private final List<Tracker> trackers = new ArrayList<Tracker>();
    private final List<Role> roles = new ArrayList<Role>();
    public CustomFieldDefinition()
    {
    	
    }
    
    /**
     * Use CustomFieldDefinitionFactory to create instances of this class.
     *
     * @param id database ID.
     */
    public CustomFieldDefinition(Integer id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomFieldDefinition that = (CustomFieldDefinition) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    public String getCustomizedType() {
        return customizedType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getFieldFormat() {
        return fieldFormat;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public String getName() {
        return name;
    }

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public String getRegexp() {
        return regexp;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Tracker> getTrackers() {
        return trackers;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public boolean isFilter() {
        return filter;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setCustomizedType(String customizedType) {
        this.customizedType = customizedType;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    public void setFilter(Boolean filter) {
        this.filter = filter;
    }

    public Integer setId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "CustomFieldDefinition{" + "id=" + id + ", name=" + name +
                ", customizedType=" + customizedType + ", fieldFormat=" +
                fieldFormat + ", regexp=" + regexp + ", minLength=" + minLength +
                ", maxLength=" + maxLength + ", required=" + required +
                ", filter=" + filter + ", searchable=" + searchable +
                ", multiple=" + multiple + ", defaultValue=" + defaultValue +
                ", visible=" + visible + ", possibleValues=" + possibleValues +
                ", trackers=" + trackers + ", roles=" + roles + '}';
    }
}
