/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.CategorieF;
import com.mycompany.entities.CategorieR;
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> recs;
    public static ServiceReclamation instance;
    private final ConnectionRequest req;
    public boolean resultOK;
    public ArrayList<produit> products;
    public ArrayList<CategorieR> categories;

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addReclamation(Reclamation t) {
        String url = Statics.BASE_URL + "/Reclamation/" + t.getDesignation() + "/" + t.getDescription() + "/" + "/" + t.getDate(); // cree l'url
        //ConnectionRequest req = new ConnectionRequest(url); // créer la requete 
        // ajouter une action  à la reception de la réponse
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //vérifier le statut de la réponse
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                //  req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public ArrayList<Reclamation> parseTasks(String jsonText) throws ParseException {
        try {
            recs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setDesignation(obj.get("designation").toString());
                t.setDescription(obj.get("description").toString());
                
                SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
                Map<String, Object> prch = (Map<String, Object>) obj.get("date");
                Date dateStr;
                dateStr = ((Date)sdf.parse(prch.get("date").toString()));
                t.setDate(dateStr);
                t.setEtat(Boolean.valueOf(obj.get("etat").toString()));
                CategorieR cat = new CategorieR();
                float idCat = Float.parseFloat(obj.get("idc").toString());
                cat.setIdc((int) idCat);
                cat.setNomR(obj.get("nomc").toString());
                produit prod = new produit();
                float idProd = Float.parseFloat(obj.get("idp").toString());
                prod.setIdp((int) idProd);
                prod.setNom(obj.get("nomp").toString());
                t.setCategorie(cat);
                t.setProduit(prod);
                //   t.setCategorie((CategorieR) obj.get("Categorie"));
                //Ajouter la tâche extraite de la réponse Json à la liste
                recs.add(t);
            }

        } catch (IOException ex) {

        }

        return recs;
    }

    public ArrayList<Reclamation> getAllrecs() {
        //int userId = userConnecte.getId();        
        //String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/listrec/"+userId;
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/listrec/3";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    recs = parseTasks(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return recs;
    }
    
    public ArrayList<produit> parseProducts(){
        products = new ArrayList<>();
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    if ( products.size() > 0) {
                        products.clear();
                  }
                    Log.p("size content" + content.size());
                    for (Map<String, Object> obj : content) {
                        produit p = new produit();
                        p.setIdp(Integer.parseInt(obj.get("id").toString()));
                        p.setNom((String) obj.get("nom"));
                        p.setPrix(Integer.parseInt(obj.get("prix").toString()));
                        products.add(p);
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        };
        connectionRequest.setUrl("http://192.168.93.1/rimehService/MalekService/allProd.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        Log.p("proooduuu = " + products);
        return products;
    }
    
    public ArrayList<CategorieR> parseCategories(){
        categories = new ArrayList<>();
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    if ( categories.size() > 0) {
                        categories.clear();
                  }
                    Log.p("size content" + content.size());
                    for (Map<String, Object> obj : content) {
                        CategorieR c = new CategorieR();
                        c.setIdc(Integer.parseInt(obj.get("id").toString()));
                        c.setNomR((String) obj.get("nom"));
                        categories.add(c);
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        };
        connectionRequest.setUrl("http://192.168.93.1/rimehService/MalekService/allCat.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        return categories;
    }
    
    
    
    public ArrayList<CategorieR> getAllCategories() {
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/listcat";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    categories = parseCats(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
    
    
    public ArrayList<CategorieR> parseCats(String jsonText) throws ParseException {
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                CategorieR t = new CategorieR();
                float id = Float.parseFloat(obj.get("idc").toString());
                t.setIdc((int) id);
                t.setNomR(obj.get("nomr").toString());
                //   t.setCategorie((CategorieR) obj.get("Categorie"));
                //Ajouter la tâche extraite de la réponse Json à la liste
                categories.add(t);
            }

        } catch (IOException ex) {

        }

        return categories;
    }
    
    
    
    public ArrayList<produit> getAllProducts() {
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/listprod";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    products = parseProds(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
    
    
    public ArrayList<produit> parseProds(String jsonText) throws ParseException {
        try {
            products = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                produit p = new produit();
                        float id = Float.parseFloat(obj.get("id").toString());
                        p.setIdp((int) id);
                        p.setNom((String) obj.get("nom"));
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        p.setPrix(prix);
                        products.add(p);
                //   t.setCategorie((CategorieR) obj.get("Categorie"));
                //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {

        }

        return products;
    }
}
