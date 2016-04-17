package com.example.simon.tripbuddy_v0;

public class Case {

    private int row;
    private int col;
    private int nLignes;
    private int nColonnes;

    public int width;
    public int height;

    public boolean isOccupe;

    public Case(int row, int col, int nLignes, int nColonnes) {
        this.row = row;
        this.col = col;
        this.nLignes = nLignes;
        this.nColonnes = nColonnes;
        isOccupe = false;
        width = 1;
        height = 1;
    }


}
