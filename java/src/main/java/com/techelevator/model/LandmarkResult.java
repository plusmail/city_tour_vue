package com.techelevator.model;

public class LandmarkResult {
    private String id;
    private DisplayName displayName;

    public DisplayName getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public void setDisplayName(DisplayName displayName) {
        this.displayName = displayName;
    }

    public void setId(String id) {
        this.id = id;
    }
}
