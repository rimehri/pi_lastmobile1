/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class CommandeF {
    
    private int idm ;
    private Date date ;
    private int quantite;
    private produit produit;
 private societe societe;
    public societe getSociete() {
        return societe;
    }

    public CommandeF(int idm, Date date, int quantite, produit produit, societe societe) {
        this.idm = idm;
        this.date = date;
        this.quantite = quantite;
        this.produit = produit;
        this.societe = societe;
    }

    public void setSociete(societe societe) {
        this.societe = societe;
    }

    public SimpleDateFormat getFormater() {
        return formater;
    }

    public void setFormater(SimpleDateFormat formater) {
        this.formater = formater;
    }

    public CommandeF(Date date, int quantite, produit produit, societe societe) {
        this.date = date;
        this.quantite = quantite;
        this.produit = produit;
        this.societe = societe;
    }
   
 SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
 
    public CommandeF(int idm, Date date, int quantite, produit produit) {
        this.idm = idm;
        this.date = date;
        this.quantite = quantite;
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "CommandeF{" + "idm=" + idm + ", date=" + date + ", quantite=" + quantite + ", produit=" + produit + '}';
    }

    public CommandeF() {
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    
    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public CommandeF(Date date, int quantite) {
        this.date = date;
        this.quantite = quantite;
    }

    public CommandeF(Date date, int quantite, produit produit) {
        this.date = date;
        this.quantite = quantite;
        this.produit = produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
    
    
    
    
}
