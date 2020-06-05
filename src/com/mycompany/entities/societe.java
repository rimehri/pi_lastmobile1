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
public class societe {

    public CategorieF getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieF categorie) {
        this.categorie = categorie;
    }
    private int ids;
    private String names;
    private String address;
    private String email;
    private String tel;
    private String logo;
    private int vue;
    private CategorieF categorie;

    public societe(String names, String address, String email, String tel, String logo, int vue, CategorieF categorie) {
        this.names = names;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.logo = logo;
        this.vue = vue;
        this.categorie = categorie;
    }

    public societe() {
    }

    public societe(String names, String address, String email, String tel, String logo, int vue) {
        this.names = names;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.logo = logo;
        this.vue = vue;
    }

    public societe(int ids, String names, String address, String email, String tel, String logo, int vue) {
        this.ids = ids;
        this.names = names;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.logo = logo;
        this.vue = vue;
    }

    public societe(String names, String address, String email, String tel, String logo) {
        this.names = names;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.logo = logo;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getVue() {
        return vue;
    }

    public void setVue(int vue) {
        this.vue = vue;
    }

    public societe(String names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "societe{" + "ids=" + ids + ", names=" + names + ", address=" + address + ", email=" + email + ", tel=" + tel + ", logo=" + logo + ", vue=" + vue + '}';
    }
    
    
    
}
