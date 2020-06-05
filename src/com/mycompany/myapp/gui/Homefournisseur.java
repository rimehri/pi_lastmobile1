/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.mycompany.entities.CommandeF;
import com.mycompany.entities.societe;
import com.mycompany.myapp.services.ServiceCommandef;
import com.mycompany.myapp.services.ServiceFournisseur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author ASUS
 */
public class Homefournisseur extends Form{
       SpanLabel lb;
    Form accueilf;
       ServiceFournisseur myServices = new ServiceFournisseur();
        ArrayList<societe> list = myServices.getAllTasks();
        accueil a = new accueil();
    public Homefournisseur(Form previous) {
        accueilf=this;
        setTitle("Fournisseurs");
        setLayout(BoxLayout.y());
        Button btnValider = new Button("Consulter statistique");
        Toolbar.setGlobalToolbar(true);
        societe goldS = getGoldSoc(list);
/*
accueilf.getToolbar().addSearchCommand(e -> {
});*/
 // add(new Label("les Fournisseurs"));
        Label lbG = new Label(goldS.getNames().toUpperCase(), "");
        lbG.getStyle().setBgColor(ColorUtil.BLUE);
        lbG.getStyle().setBgTransparency(255);
        Container cont = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
                        new Label("Fournisseur Gold ", ""),
                        lbG));
        cont.getUnselectedStyle().setBorder(Border.createGrooveBorder(2, ColorUtil.BLACK));
        //Label gold = new Label("Fournisseur Gold", goldS.getNames());
        cont.getStyle().setBgColor(ColorUtil.rgb(255, 215, 0));
        cont.getStyle().setBgTransparency(255);
        
        //cont.add(gold);
        accueilf.add(cont);
        accueilf.add(btnValider);
         btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<societe> socTrie = list;
                Collections.sort(socTrie, new Comparator<societe>() {

        public int compare( societe r1, societe r2 ) {
            if( r1.getVue() != r2.getVue() ) return r2.getVue()-r1.getVue();
            else return r2.getVue()-r1.getVue();
    }

            
    });
                Stat st = new Stat(socTrie);
                st.show();
            }}
                );
        /*
        getToolbar().addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
            try {
                new addfournisseur(accueilf).show();
            } catch (IOException ex) {
                System.out.println("err");            }
        });*/
        
         if (list.isEmpty()) {
            SpanLabel lb = new SpanLabel(" acun fournisseur !");
           accueilf.add(lb);
        } else {
             //ArrayList<Map<String, Object>> data = new ArrayList<>();
             ArrayList<Object> data = new ArrayList<>();
             for (societe r : list) {
                //data.add(createListEntry(r));
                Map<String, Object> entry = new HashMap<>();
                entry.put("Line1", r.getNames());
                entry.put("Line2", r.getAddress());
                data.add(entry);
             }
             DefaultListModel<Object> model = new DefaultListModel<>(data);
             MultiList ml = new MultiList(model);
             ml.addActionListener( e -> {
                    //Dialog.show("Info", ""+ml.getSelectedIndex(), "Ok", null);
                    
                    DetailsF d = new DetailsF(list.get(ml.getSelectedIndex()));
                    d.show();
             });
             Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
             FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MENU, s);
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->a.show());
             //getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));
             // -----------------
                TextField searchField = new TextField("", "Search..."); 
                searchField.getHintLabel().setUIID("Title");
                searchField.setUIID("Title");
                searchField.getAllStyles().setAlignment(Component.LEFT);
                accueilf.getToolbar().setTitleComponent(searchField);
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
                searchField.addDataChangeListener((i1, i2) -> { /*
                    String t = searchField.getText();
                    if(t.length() < 1) {
                        for(Component cmp : accueilf.getContentPane()) {
                            cmp.setHidden(false);
                            cmp.setVisible(true);
                        }
                    } else {
                        t = t.toLowerCase();
                        ml.setVisible(false);
                        ml.setHidden(false);
                        ArrayList<Object> data2 = new ArrayList<>();
                        for(Component cmp : accueilf.getContentPane()) {
                            if ( cmp instanceof MultiList ) {
                            MultiList mb = (MultiList)cmp;
                            mb.setHidden(true);
                            mb.setVisible(false);
                        }
                        }
                        for (societe r : list) {
                           //data.add(createListEntry(r));
                           if ( r.getNames().indexOf(t) > -1 ) {
                            Map<String, Object> entry = new HashMap<>();
                           entry.put("Line1", r.getNames());
                           entry.put("Line2", r.getTel());
                           data2.add(entry);
                            }
                           DefaultListModel<Object> model2 = new DefaultListModel<>(data2);
                            MultiList ml2 = new MultiList(model);
                            accueilf.add(ml2);
                        }*/
                        /*
                        for(Component cmp : accueilf.getContentPane()) {
                            MultiButton mb = (MultiButton)cmp;
                            String line1 = mb.getTextLine1();
                            String line2 = mb.getTextLine2();
                            boolean show = line1 != null && line1.toLowerCase().indexOf(t) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(t) > -1;
                            mb.setHidden(!show);
                            mb.setVisible(show);
                        }*/
                    // to add}
                    // to add accueilf.getContentPane().animateLayout(250);
                });
                accueilf.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
                    searchField.startEditingAsync(); 
             });
                /*
                FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
                fab.bindFabToContainer(accueilf.getContentPane());
                */
             // -----------------
             accueilf.add( ml);
/*
            for (societe r : list) {
/*
                MultiButton b = new MultiButton("");
                b.getUnselectedStyle().setBorder(Border.createGrooveBorder(4, 0xff));
                b.setTextLine2("Num° \n:" + r.getIds());
                b.setTextLine3("Societe \n:" + r.getNames());
                  accueilf.add(b);
                b.addActionListener(e->{
                    
                DetailsF d = new DetailsF(r);
                d.show();
                });     
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
   
            SpanLabel lb = new SpanLabel(" acune commandes !");
           accueilf.add(lb);
            } */
        }
}
        /*
        private Map<String, Object> createListEntry(String name, String date) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("Line1", name);
            entry.put("Line2", date);
            return entry;
        }*/
        
        private Object createListEntry(societe e) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("Line1", e.getNames());
            entry.put("Line2", e.getTel());
            return entry;
        }
        // TO DO incrémenter par 1 ajout commmande
        private societe getGoldSoc(ArrayList<societe> societies) {
            societe maxS = new societe();
            int max = 0;
            int index = 0;
            for (int i = 0; i < societies.size(); i++) {
                if ( societies.get(i).getVue() > max ){
                    max = societies.get(i).getVue();
                    index = i;
                }
            }
            maxS = societies.get(index);
            return maxS;
        }
         }

/*  b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   
                    
                    

//preparer les Label dans details
                    Label pre1 = new Label("Names :");
                    Label bes1 = new Label("address:");
                    Label bn1 = new Label("email :");
                    Label dure = new Label("tel");
                    
//preparer les Span dans details
                    Label nom = new Label("");
                    SpanLabel pre = new SpanLabel("");
                    SpanLabel bes = new SpanLabel("");
         
                    SpanLabel bn = new SpanLabel("");
//l'ajout dans les span dans details
                    nom.setText(r.getNames());
                    pre.setText(r.getAddress());
                    bes.setText(r.getEmail());
                    bn.setText(r.getTel()); 
                
                accueilf.add(nom).add(pre).add(bes).add(bn);
                }
                 });*/
