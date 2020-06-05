/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.CommandeF;
import com.mycompany.myapp.services.ServiceCommandef;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.produit;
import com.mycompany.entities.societe;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class commandeaff2 extends Form{
        SpanLabel lb;
    Form accueilf;
    String[] commandes = {"Modifier", "Details", "Supprimer"};
       ServiceCommandef myServices = new ServiceCommandef();
        ArrayList<CommandeF> listInitial = myServices.getAllTasks();
        ArrayList<CommandeF> list = listInitial;
        ArrayList<produit> products = new ArrayList<>() ;
        ArrayList<societe> societies = new ArrayList<>() ;
        accueil a = new accueil();
    public commandeaff2(Form previous) {
        ServiceCommandef myService = new ServiceCommandef();
        if ( products.size() > 0 ) {
        } else {
           products = myService.getAllProducts();
            Log.p("proood = " + list);
        }
        if ( societies.size() > 0 ) {
        } else {
           societies = myService.getAllSocieties();
            Log.p("proood = " + list);
        }
        accueilf=this;
        setTitle("les commandes");
        setLayout(BoxLayout.y());
        
        Toolbar.setGlobalToolbar(true);
/*
accueilf.getToolbar().addSearchCommand(e -> {
});*/           
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MENU, s);
                TextField searchField = new TextField("", "Search..."); 
                searchField.getHintLabel().setUIID("Title");
                searchField.setUIID("Title");
                searchField.getAllStyles().setAlignment(Component.LEFT);
                accueilf.getToolbar().setTitleComponent(searchField);
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
 if (list.isEmpty()) {
            SpanLabel lb = new SpanLabel(" acune commandes !");
           accueilf.add(lb);
        } else {

            for (CommandeF r : list) {

                MultiButton b = new MultiButton("");
                b.getStyle().setBgColor(ColorUtil.WHITE);
                b.getStyle().setBgTransparency(255);
                b.getUnselectedStyle().setBorder(Border.createGrooveBorder(2, ColorUtil.rgb(51, 153, 0)));
               // b.getUnselectedStyle().setBorder(Border.createGrooveBorder(4, 0xff));
                b.setTextLine2("Num° \n:" + r.getIdm());
                b.setTextLine3("Societe \n:" + r.getSociete().getNames());
                  accueilf.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                    ModifC  c = null;
                            try {
                                c = new ModifC(r.getIdm(), r.getDate(), r.getQuantite(), r.getSociete(), r.getProduit(), products, societies);
                            } catch (IOException ex) {
                                System.out.println("ok");
                            }
                                   c.show();
                                    
                                    break;
                                case "Supprimer":   
                                    delete(r.getIdm(), d);  
                                    break;
                                default:

                            
                                   
                                 DetailsC k = new DetailsC(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);

                });
                 /* b.addActionListener(e->{
                    
 DetailsC d = new DetailsC(r);
 d.show();
 });        
            }*/
          
 }}
        // **********************************
        searchField.addDataChangeListener((i1, i2) -> { 
                    String t = searchField.getText();
                    if(t.length() < 1) {
                        accueilf.removeAll();
                        list = listInitial;
                        Display();
                    } else {
                        t = t.toLowerCase();
                        int i = 0;
                        accueilf.removeAll();
                        for (CommandeF r : list) {
                            if ( r.getSociete().getNames().indexOf(t) > -1 ) {
                            MultiButton b = new MultiButton("");
                            b.getStyle().setBgColor(ColorUtil.WHITE);
                            b.getStyle().setBgTransparency(255);
                            b.getUnselectedStyle().setBorder(Border.createGrooveBorder(2, ColorUtil.rgb(51, 153, 0)));
                            b.setTextLine2("Num° \n:" + r.getIdm());
                            b.setTextLine3("Societe \n:" + r.getSociete().getNames());
                            accueilf.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                    ModifC  c = null;
                            try {
                                c = new ModifC(r.getIdm(), r.getDate(), r.getQuantite(), r.getSociete(), r.getProduit(), products, societies);
                            } catch (IOException ex) {
                                System.out.println("ok");
                            }
                                   c.show();
                                    
                                    break;
                                case "Supprimer": 
                                    Log.p("deleted");
                                    delete(r.getIdm(), d);
                                    break;
                                default:

                            
                                   
                                 DetailsC k = new DetailsC(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);

                });
                            }
                        }}
        });
        // **********************************
         
       

       
        
        //accueilf.add(new InfiniteProgress());
        getToolbar().addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
            try {
                new addcomm(accueilf, products, societies).show();
            } catch (IOException ex) {
                System.out.println("err");            }
        });
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->a.show());
   
    }
    
     public void Display() {
            for (CommandeF r : list) {

                MultiButton b = new MultiButton("");
                b.getStyle().setBgColor(ColorUtil.WHITE);
                b.getStyle().setBgTransparency(255);
                b.getUnselectedStyle().setBorder(Border.createGrooveBorder(2, ColorUtil.rgb(51, 153, 0)));
               // b.getUnselectedStyle().setBorder(Border.createGrooveBorder(4, 0xff));
                b.setTextLine2("Num° \n:" + r.getIdm());
                b.setTextLine3("Societe \n:" + r.getSociete().getNames());
                  accueilf.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                    ModifC  c = null;
                            try {
                                c = new ModifC(r.getIdm(), r.getDate(), r.getQuantite(), r.getSociete(), r.getProduit(), products, societies);
                            } catch (IOException ex) {
                                System.out.println("ok");
                            }
                                   c.show();
                                    
                                    break;
                                case "Supprimer": 
                                    Log.p("deleted");
                                    delete(r.getIdm(), d);
                                    break;
                                default:

                            
                                   
                                 DetailsC k = new DetailsC(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);

                });
                 /* b.addActionListener(e->{
                    
 DetailsC d = new DetailsC(r);
 d.show();
 });        
            }*/
          
        }
        }
     public void delete(int id, Dialog d) {
            Log.p("clicked too");
                    ConnectionRequest con = new ConnectionRequest();
                    con.setPost(false);
                    con.setUrl("http://localhost/FINAL%20symfony/final/web/app_dev.php/delCom/"+id);
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                    System.out.println("done comment!");
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println("response : " + s);
                    System.out.println("response : " + evt.getMetaData().equals("true"));
                    if ( s.equals("\"success\"")) {
                        reloadForm();
                        Dialog.show("Confirmation", "deleted successfulyy", "Ok", null);
                        d.dispose();
                    }
                    else {
                    Dialog.show("Error", "not deleted", "Not Ok", null);
                    }
                    }
                    });
                    NetworkManager.getInstance().addToQueue(con);
        }
     
     public void start() {
         reloadForm();
     }
     
     public void reloadForm() {
         listInitial.clear();
         listInitial = myServices.getAllTasks();
         accueilf.removeAll();
         list.clear();
         list = listInitial;
         if (list.isEmpty()) {
            SpanLabel lb = new SpanLabel(" acune commandes !");
           accueilf.add(lb);
        } else {

            for (CommandeF r : list) {

                MultiButton b = new MultiButton("");
                b.getStyle().setBgColor(ColorUtil.WHITE);
                b.getStyle().setBgTransparency(255);
                b.getUnselectedStyle().setBorder(Border.createGrooveBorder(2, ColorUtil.rgb(51, 153, 0)));
               // b.getUnselectedStyle().setBorder(Border.createGrooveBorder(4, 0xff));
                b.setTextLine2("Num° \n:" + r.getIdm());
                b.setTextLine3("Societe \n:" + r.getSociete().getNames());
                  accueilf.add(b);
                  
                  
                  
                   b.addActionListener(e -> {
                    Dialog d = new Dialog();
                    d.setLayout(BoxLayout.y());
                    d.getContentPane().setScrollableY(true);
                    for (String cmd : commandes) {
                        MultiButton mb = new MultiButton(cmd);
                        d.add(mb);
                        mb.addActionListener(ee -> {

                            switch (cmd) {

                                case "Modifier":
                                    ModifC  c = null;
                            try {
                                c = new ModifC(r.getIdm(), r.getDate(), r.getQuantite(), r.getSociete(), r.getProduit(), products, societies);
                            } catch (IOException ex) {
                                System.out.println("ok");
                            }
                                   c.show();
                                    
                                    break;
                                case "Supprimer": 
                                    delete(r.getIdm(), d);
                                    break;
                                default:

                            
                                   
                                 DetailsC k = new DetailsC(r);
                                   k.show();
                            
                                   
                                    
                                    break;
                            }

                            
                        });
                    }
                    d.showPopupDialog(b);

                });
                 /* b.addActionListener(e->{
                    
 DetailsC d = new DetailsC(r);
 d.show();
 });        
            }*/
          
 }}
     }
}

