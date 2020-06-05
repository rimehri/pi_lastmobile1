/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.CategorieF;
import com.mycompany.entities.CommandeF;
import com.mycompany.entities.produit;
import com.mycompany.entities.societe;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceFournisseur {
   
        public ArrayList<societe> tasks;
    
    public static ServiceFournisseur instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFournisseur() {
         req = new ConnectionRequest();
    }

    public static ServiceFournisseur getInstance() {
        if (instance == null) {
            instance = new ServiceFournisseur();
        }
        return instance;
    }
     public ArrayList<societe> parseTasks(String jsonText) throws ParseException{
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
        
              societe t = new societe();
             
                float id = Float.parseFloat(obj.get("ids").toString());
                t.setIds((int)id);
           //     t.setVue(((int)Float.parseFloat(obj.get("vue").toString())));
                         t.setNames(obj.get("names").toString());
                            


                   t.setAddress(obj.get("address").toString());
                            t.setEmail(obj.get("email").toString());
                            t.setTel(obj.get("tel").toString());
                            t.setLogo(obj.get("logo").toString());
                            float vues = Float.parseFloat(obj.get("vue").toString());
                            t.setVue((int) vues);
                            
                /*  Map<String, Object> produi = (Map<String, Object>) obj.get("categorie");
                  CategorieF pk = new CategorieF();
                  float idf = Float.parseFloat(obj.get("idf").toString());
                 String s = produi.get("nomC").toString();
               
               
                 
                  pk.setIdf((int)idf);
                  pk.setNomC(s);
                 t.setCategorie(pk);
                 //   CategorieF p = new CategorieF(();
                    
                   
                  t.setCategorie(pk);*/
                            //  Map<String, Object> categorie = (Map<String, Object>) obj.get("categorie");
                           //   CategorieF c = new CategorieF();
               // float idUsr = Float.parseFloat(user.get("id").toString());
               // usr.setId((int)idUsr);
            //    c.setNomC(obj.get("nomC").toString());
              
                
                
//                t.setCategorie(c);
              
             
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return tasks;
    }
    
    public ArrayList<societe> getAllTasks(){
        String url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/societe/indexA";
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
     public void Detailf(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/FINAL%20symfony/final/web/app_dev.php/societe/showa";
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     
     
}
