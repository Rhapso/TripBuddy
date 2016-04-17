package com.example.simon.tripbuddy_v0;

/**
 * Created by simon on 16/04/16.
 */
public class Lieux {



    private int id;
    private String nom;
    private String info;

    public Lieux(int id, String nom, String info) {
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

}
