package com.xwzx.equipmanager.mvp.models;

import com.xwzx.equipmanager.mvp.ModelInterface;

public class EquipTabModel implements ModelInterface {
    private int id;
    private String title;

    public EquipTabModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EquipTabModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
