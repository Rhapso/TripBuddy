package com.example.simon.tripbuddy_v0;

import android.graphics.Color;

/**
 * Created by simon on 16/04/16.
 */
public class Lieux {

    private int listId;
    private int id;
    private String nom;
    private String info;

    public Lieux(int id, String nom, String info) {
        this.listId = 0;
        this.id = id;
        this.nom = nom;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getInfo() {
        return info;
    }

    public int getResourceId() { return listId;}
    public void setResourceId(int id){ this.listId = id;}

}
