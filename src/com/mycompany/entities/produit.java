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
public class produit {
    
    
    private int idp;
    private float prix ;
    private String nom;

    public produit(int idp, float prix, String nom) {
        this.idp = idp;
        this.prix = prix;
        this.nom = nom;
    }

    public produit() {
    }

    public produit(float prix, String nom) {
        this.prix = prix;
        this.nom = nom;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    @Override
    public String toString() {
        return "produit{" + "idp=" + idp + ", prix=" + prix + ", nom=" + nom + '}';
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
