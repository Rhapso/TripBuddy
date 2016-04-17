package com.example.simon.tripbuddy_v0;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by simon on 16/04/2016.
 */
public class DataHolder {

    private String nom;

    private ArrayList<Lieux> itineraire;

    public String getNom() {
        return nom;
    }

    public ArrayList<Lieux> getItineraire() {
        return itineraire;
    }

    public void setData(String nom) {
        this.nom = nom;
    }

    public void addEtape(Lieux lieux) {
        itineraire.add(lieux);
    }

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }
}