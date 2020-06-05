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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.entities.CommandeF;
import com.mycompany.entities.produit;
import com.mycompany.entities.societe;
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
//import static jdk.nashorn.internal.objects.ArrayBufferView.length;
//import static jdk.nashorn.internal.objects.NativeString.substring;


/**
 *
 * @author ASUS
 */
public class ServiceCommandef {
    
    
    
        public ArrayList<CommandeF> tasks;
        public ArrayList<produit> products;
        public ArrayList<societe> societies;
    
    public static ServiceCommandef instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCommandef() {
         req = new ConnectionRequest();
    }

    public static ServiceCommandef getInstance() {
        if (instance == null) {
            instance = new ServiceCommandef();
        }
        return instance;
    }
     public ArrayList<CommandeF> parseTasks(String jsonText) throws ParseException{
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
        
              CommandeF t = new CommandeF();
             
                float id = Float.parseFloat(obj.get("idm").toString());
                t.setIdm((int)id);
                t.setQuantite(((int)Float.parseFloat(obj.get("quantite").toString())));
                SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
                
                
                  Map<String, Object> prch = (Map<String, Object>) obj.get("date");
                  Date dateStr;
                dateStr = ((Date)sdf.parse(prch.get("date").toString()));
               
                
              
                t.setDate(dateStr);
          /* String d=     obj.get("date").toString();
                String substring = d.substring(11,length(d));
                t.setDate(substring);*/
                
           //      
                // SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
                  //  t.setDate((Date)(sdf.parse(obj.get("date").toString())));

                   Map<String, Object> produi = (Map<String, Object>) obj.get("produit");
                  
                   String s = obj.get("NOM produit").toString();
                   Float pr= Float.parseFloat(obj.get("prix unitaire").toString());
                   
                    produit p = new produit(pr,s);
                    p.setNom(s);
                    p.setPrix(pr);
                   t.setProduit(p);
                     Map<String, Object> produiti = (Map<String, Object>) obj.get("societe");
                  
                   String l = obj.get("nom fournisseur").toString();
                   
                  societe k = new societe(l);
                    k.setNames(l);
                   
                   t.setSociete(k);
                 
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return tasks;
    }
    
    public ArrayList<CommandeF> getAllTasks(){
        String url = "http://localhost/FINAL symfony/final/web/app_dev.php/listcom";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    tasks = parseTasks(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
     public boolean addcomm(CommandeF t) {
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/newcomm" + "/" + t.getQuantite(); // cree l'url
        ConnectionRequest req = new ConnectionRequest(url); // créer la requete 
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
     
    public boolean addcomm2(String d, String q, String s, String p) {
        String url = "http://192.168.93.1/rimehService/addColoc.php?date="+d+"&quantite="+q+"&idp="+p+"&ids="+s; // cree l'url
        ConnectionRequest req = new ConnectionRequest(url); // créer la requete 
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
                        Log.p("okk" + obj);
                        Log.p("prix" + obj.get("prix").toString());
                        produit p = new produit();
                        p.setIdp(Integer.parseInt(obj.get("id").toString()));
                        Log.p("id ok" + obj);
                        p.setNom((String) obj.get("nom"));
                        Log.p("nom ok" + obj);
                        p.setPrix(Integer.parseInt(obj.get("prix").toString()));
                        Log.p("prix ok");
                        products.add(p);
                        Log.p("parse poduct = " + products);
                        Log.p("fine4");
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        };
        connectionRequest.setUrl("http://192.168.93.1/rimehService/allColoc.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        Log.p("proooduuu = " + products);
        return products;
    }
    
    
    public ArrayList<societe> parseSocieties(){
        societies = new ArrayList<>();
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    if ( societies.size() > 0) {
                        societies.clear();
                  }
                    for (Map<String, Object> obj : content) {
                        societe s = new societe();
                        s.setIds(Integer.parseInt(obj.get("id").toString()));
                        s.setNames((String) obj.get("nom"));
                        societies.add(s);
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        };
        connectionRequest.setUrl("http://192.168.93.1/rimehService/allSoc.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        return societies;
    }
    
    
    public boolean delComm(int id) {
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/delCom/" + id; // cree l'url
        ConnectionRequest req = new ConnectionRequest(url); // créer la requete 
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
    
    public ArrayList<produit> getAllProducts() {
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/listprodRi";
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
    
    
    
    //************************************
    public ArrayList<societe> getAllSocieties() {
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/listsocieties";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    societies = parseSocieties(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return societies;
    }
    
    public ArrayList<societe> parseSocieties(String jsonText) throws ParseException {
        try {
            societies = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                societe s = new societe();
                        float id = Float.parseFloat(obj.get("id").toString());
                        Log.p("id :  "+id);
                        Log.p("nom : "+(String) obj.get("nom"));
                        s.setIds((int) id);
                        s.setNames((String) obj.get("nom"));
                        Log.p("okkkk");
                        societies.add(s);
                        Log.p("finess");
                //   t.setCategorie((CategorieR) obj.get("Categorie"));
                //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {

        }

        return societies;
    }
}
