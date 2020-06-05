/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ASUS
 */
public class CategorieF {
               
    
    
    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    @Override
    public String toString() {
        return "CategorieF{" + "idf=" + idf + ", nomC=" + nomC + '}';
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }
    private int idf ;
    private String nomC ;

    public CategorieF(int idf, String nomC) {
        this.idf = idf;
        this.nomC = nomC;
    }

    public CategorieF() {
    }

    public CategorieF(String nomC) {
        this.nomC = nomC;
    }
    
    
}
