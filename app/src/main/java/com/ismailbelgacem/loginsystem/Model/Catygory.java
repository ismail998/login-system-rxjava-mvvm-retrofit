package com.ismailbelgacem.loginsystem.Model;

public class Catygory {
    int id;
    String name;
    String name_en;
    String photo;
    String percentage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Catygory(int id, String name, String name_en, String photo, String percentage) {
        this.id = id;
        this.name = name;
        this.name_en = name_en;
        this.photo = photo;
        this.percentage = percentage;
    }
}
