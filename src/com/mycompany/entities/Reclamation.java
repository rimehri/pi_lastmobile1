/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Reclamation {
    
    private int id ;
    private String designation;
    private String description;
    private boolean etat;
    private Date date;
    private CategorieR  categorie ; 
    private produit produit;

    public Reclamation() {
    }

    public Reclamation(int id, String designation, String description, boolean etat, Date date, CategorieR categorie, produit produit) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.categorie = categorie;
        this.produit = produit;
    }
    
    

    public Reclamation(String designation, String description, boolean etat, Date date, CategorieR categorie, produit produit) {
        this.designation = designation;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.categorie = categorie;
        this.produit = produit;
    }
    
    public Reclamation(String designation, String description, boolean etat, Date date, CategorieR categorie) {
        this.designation = designation;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.categorie = categorie;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CategorieR getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieR categorie) {
        this.categorie = categorie;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", designation=" + designation + ", description=" + description + ", etat=" + etat + ", date=" + date + ", categorie=" + categorie + ", produit=" + produit + '}';
    }

    

}
