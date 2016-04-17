package com.example.simon.tripbuddy_v0;

import java.util.Random;

public class Grille {

  private int nLignes;
  private int nColonnes;

  public Case[][] grille;

  public Grille(int nLignes, int nColonnes) {
    this.nLignes = nLignes;
    this.nColonnes = nColonnes;
    grille = new Case[nLignes][nColonnes];
    for (int i=0; i<nLignes; ++i) {
      for (int j = 0; j < nColonnes; ++j) {
        grille[i][j] = new Case(i, j, nLignes, nColonnes);
      }
    }
  }

  public void alea() {
    for (int i=0; i<nLignes-1; ++i) {
      for (int j = 0; j < nColonnes-1; ++j) {
        Random r = new Random();
        if(!grille[i][j].isOccupe) {
          int w = 1 + r.nextInt(2);
          int h = 1 + r.nextInt(2);
          if (w == 2) {
            if(!grille[i][j+1].isOccupe)
              grille[i][j+1].isOccupe = true;
            else
              w = 1;
          }
          if (h == 2)
            grille[i+1][j].isOccupe = true;
          if(h == 2 && w == 2)
            grille[i+1][j+1].isOccupe = true;
          grille[i][j].width = w;
          grille[i][j].height = h;
          System.out.println("w: "+w+"h: "+h);
        }
      }
    }
  }

  public void affiche() {
    System.out.println("--------------------");
    for (int i=0; i<nLignes-1; ++i) {
      for (int j = 0; j < nColonnes-1; ++j) {
        if(!grille[i][j].isOccupe)
          System.out.print("X");
        else
          System.out.print("@");
      }
      System.out.println();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


}
