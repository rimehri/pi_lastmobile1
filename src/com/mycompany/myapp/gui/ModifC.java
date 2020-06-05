/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.CategorieF;
import com.mycompany.entities.CommandeF;
import com.mycompany.entities.produit;
import com.mycompany.entities.societe;
import com.mycompany.myapp.services.ServiceCommandef;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ModifC extends Form{
    Form me ;
     ComboBox combo;
     ArrayList<produit> list = new ArrayList<>() ;
     ArrayList<societe> list2 = new ArrayList<>() ;
      Form previous = Display.getInstance().getCurrent();
        int indexp = 0, indexs = 0;
      // public ModifC(CommandeF f) throws IOException {
       public ModifC(int id, Date dt, int q, societe s, produit p, ArrayList<produit> products2, ArrayList<societe> societies3) throws IOException {
           //*********************
        for (int i = 0; i < products2.size(); i++) {
            if ( products2.get(i).getNom().equals(p.getNom()) ) {
                indexp = i;
            }
        }
        for (int i = 0; i < societies3.size(); i++) {
            if ( societies3.get(i).getNames().equals(s.getNames()) ) {
                indexs = i;
            }
        }
        ArrayList<String> stringsArray;
        ArrayList<String> stringsArray2;
        stringsArray = new ArrayList<String>();
        stringsArray2 = new ArrayList<String>();
           ServiceCommandef myService = new ServiceCommandef();
        if ( products2.size() > 0 ) {
        } else {
            list = myService.parseProducts();
            Log.p("proood = " + list);
        }
        if ( societies3.size() > 0 ) {
        } else {
            list2 = myService.parseSocieties();
            Log.p("proood = " + list);
        }
           //*********************
        me=this;
        setTitle("Modifier commande");
        setLayout(BoxLayout.y());
        TextField quantite= new TextField("", "Quantite",0,TextField.NUMERIC);
        
        quantite.setText(String.valueOf(q));
       Button btnValider = new Button("Modifier Commande");
      
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
       
       //******************
       Label socLabel = new Label("selctionner societé");
        Label prodLabel = new Label("selctionner produit");
        Label quantieLabel = new Label("qauntité");

         Picker stringPicker = new Picker();
         Picker stringPicker2 = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker2.setType(Display.PICKER_TYPE_STRINGS);
        //stringPicker.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
        //"A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        //stringPicker.setSelectedString("A Game of Thrones");
        me.add(prodLabel);
        me.add(stringPicker);
        me.add(socLabel);
        me.add(stringPicker2);
        me.add(quantieLabel);
        me.add(quantite);
        me.getStyle().setBgColor(ColorUtil.WHITE);
        me.getStyle().setBgTransparency(255);
        stringsArray.clear();
        if (products2.size() > 0) {
            for (produit p2 : products2) {
            //stringPicker.setPropertyValue(String.valueOf(p.getIdp()), p.getNom());
            stringsArray.add(p2.getNom());
            }
            String[] strings = new String[products2.size()];
            stringsArray.toArray(strings);
            stringPicker.setStrings(strings);
        } else {
            Log.p("noo val");
        }
        stringPicker.setSelectedString(p.getNom());
        for (societe s2 : societies3) {
            stringsArray2.add(s2.getNames());
        }
        stringPicker2.setSelectedString(s.getNames());
        String[] strings2 = new String[societies3.size()];
        stringsArray2.toArray(strings2);
        
        stringPicker2.setStrings(strings2);
        me.add(btnValider);
       //******************
       btnValider.addActionListener((ActionListener) (ActionEvent evt) -> {
           int indexS = indexs;
           int indexP = indexp;
           if( stringPicker.getSelectedStringIndex() >= 0 ) {
               indexP = stringPicker.getSelectedStringIndex();
           }
           if( stringPicker2.getSelectedStringIndex() >= 0 ) {
               indexS = stringPicker2.getSelectedStringIndex();
           }
           int ids = societies3.get(indexS).getIds();
           int idp = products2.get(indexP).getIdp();
           if (quantite.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
           else
           {
               int identifiant = id;
               ConnectionRequest con = new ConnectionRequest();
               con.setPost(false);
               String q2 = quantite.getText();
               con.setUrl("http://localhost/FINAL%20symfony/final/web/app_dev.php/updCom/"+identifiant+"/"+idp+"/"+ids+"/"+q2);
               con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        System.out.println("done comment!");
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        Log.p("response : " + s);
                        if ( s.equals("\"success\"")) {
                            Dialog.show("Confirmation", "modified successfully", "Ok", null);
                            //commandeaff2 c = new commandeaff2(me);
                            //c.show();
                        }
                        else {
                            Dialog.show("Error", "not modified", "Not Ok", null);
                        }
                    }
                });
               NetworkManager.getInstance().addToQueue(con);
            }
        });
      }

    
}
