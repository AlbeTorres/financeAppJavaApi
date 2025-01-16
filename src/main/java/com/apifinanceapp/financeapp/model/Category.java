package com.apifinanceapp.financeapp.model;

public class Category {
    private String id;
    private String userId;
    private String name;

    public Category() {
    }

    public Category(String id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", userId=" + userId + ", name=" + name + "]";
    }

}
