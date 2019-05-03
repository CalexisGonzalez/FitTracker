package com.example.fittracker.DbQuery;

public class DbUpdateImagePojo {
    private int userId;
    private String imageUrl;

    public DbUpdateImagePojo(int userId, String imageUrl) {
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getUserId() {
        return userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
