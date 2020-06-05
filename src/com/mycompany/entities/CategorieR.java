/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author admin
 */
public class CategorieR {
    private int idc;
    private String nomR; 

    public CategorieR(int idc, String nomR) {
        this.idc = idc;
        this.nomR = nomR;
    }

    public CategorieR() {
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    @Override
    public String toString() {
        return "CategorieR{" + "idc=" + idc + ", nomR=" + nomR + '}';
    }
    
}
