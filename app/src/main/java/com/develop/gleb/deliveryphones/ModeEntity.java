package com.develop.gleb.deliveryphones;

public class ModeEntity {
    private String title;
    private String descriptionHeader;
    private String description;
    private ModeIdentifier id;
    private int resId;

    public ModeEntity(String title, String descriptionHeader, String description, ModeIdentifier id, int resId) {
        this.title = title;
        this.descriptionHeader = descriptionHeader;
        this.description = description;
        this.id = id;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public ModeIdentifier getId() {
        return id;
    }

    public void setId(ModeIdentifier id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionHeader() {
        return descriptionHeader;
    }

    public void setDescriptionHeader(String descriptionHeader) {
        this.descriptionHeader = descriptionHeader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
