package com.lenkasr.fetchapplication;

public class Hiring {
    private int id;
    private int listId;
    private String name;

    public Hiring(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

}
