package com.taskadapter.redmineapi.bean;

import java.util.Date;

/**
 * File Attachment for a Redmine issue
 */
public class Attachment implements Identifiable {

    private Integer id;
    private String fileName;
    private long fileSize;
    private String contentType;
    private String contentURL;
    private String description;
    private Date createdOn;
    private User author;
    private String token;

    
    public Attachment()
    {
    	
    }
    
    /**
     * Use AttachmentFactory to create instances of this class.
     *
     * @param id database ID.
     * @see com.taskadapter.redmineapi.bean.AttachmentFactory
     */
    public Attachment(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attachment that = (Attachment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    public User getAuthor() {
        return author;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContentURL() {
        return contentURL;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    @Override
    /**
     * @return id. NULL for attachments not added to Redmine yet.
     */
    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer setId() {
        return id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", contentType='" + contentType + '\'' +
                ", contentURL='" + contentURL + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", author=" + author +
                ", token=" + token +
                '}';
    }
}
