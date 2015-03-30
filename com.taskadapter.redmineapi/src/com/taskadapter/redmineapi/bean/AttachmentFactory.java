package com.taskadapter.redmineapi.bean;

public class AttachmentFactory {
    public static Attachment create() {
        return new Attachment(null);
    }

    public static Attachment create(Integer id) {
        return new Attachment(id);
    }
}
