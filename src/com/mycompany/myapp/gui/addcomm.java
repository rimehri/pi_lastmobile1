/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
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
import com.mycompany.entities.produit;
import com.mycompany.entities.societe;
import com.mycompany.myapp.services.ServiceCommandef;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.messaging.Message;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author ASUS
 */
public class addcomm extends Form{
    Form me ;
     ComboBox combo;
       public ArrayList<CategorieF> tasks;
       ArrayList<produit> list = new ArrayList<>() ;
       ArrayList<societe> list2 = new ArrayList<>() ;
       
      public addcomm(Form previous, ArrayList<produit> products2, ArrayList<societe> societies2) throws IOException {
        ArrayList<String> stringsArray;
        
        ArrayList<String> stringsArray2;
        ServiceCommandef myService = new ServiceCommandef();
        stringsArray = new ArrayList<String>();
        if ( products2.size() > 0 ) {
        } else {
            list = myService.getAllProducts();
        }
        if ( societies2.size() > 0 ) {
        } else {
            list2 = myService.getAllSocieties();
        }
        stringsArray2 = new ArrayList<String>();
        Log.p("fine1");
        //ArrayList<societe> listSoc = myService.parseSocieties();
        
        me=this;
        setTitle("Add  a new commande");
        setLayout(BoxLayout.y());
        Label socLabel = new Label("selctionner societé");
        Label prodLabel = new Label("selctionner produit");
        TextField quantite= new TextField("", "quantite",0,TextField.NUMERIC);
        Log.p("fine2");

         Picker stringPicker = new Picker();
         Picker stringPicker2 = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker2.setType(Display.PICKER_TYPE_STRINGS);
        //stringPicker.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
        //"A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        //stringPicker.setSelectedString("A Game of Thrones");
        me.add(prodLabel);
        Log.p("fine3");
        me.add(stringPicker);
        me.getStyle().setBgColor(ColorUtil.WHITE);
        me.getStyle().setBgTransparency(255);
        Log.p("prooodee = " + list);
        Log.p("index ==  " + stringPicker.getSelectedStringIndex());
        stringsArray.clear();
        if (products2.size() > 0) {
            for (produit p : products2) {
            //stringPicker.setPropertyValue(String.valueOf(p.getIdp()), p.getNom());
            stringsArray.add(p.getNom());
            }
            String[] strings = new String[products2.size()];
            stringsArray.toArray(strings);
            Log.p("strings = " + strings);
            //stringPicker.setStrings("bonjour", "bonsoir");
            stringPicker.setStrings(strings);
            /*
            stringPicker.setPropertyValue("1", "dell");
            stringPicker.getPropertyValue("dell");
            Log.p("value " + stringPicker.getPropertyValue("dell"));*/
        } else {
            Log.p("noo val");
        }
        
        
        //******************
        
        Log.p("size = " + societies2.size());
        int i = 0;
        for (societe s : societies2) {
            //stringPicker.setPropertyValue(String.valueOf(p.getIdp()), p.getNom());
            Log.p("ahaw " + s.getNames());
            Log.p("innn " + i);
            i++;
            stringsArray2.add(s.getNames());
        }
        String[] strings2 = new String[societies2.size()];
        stringsArray2.toArray(strings2);
        Log.p("strings = " + strings2);
        
        stringPicker2.setStrings(strings2);
        me.add(socLabel);
        me.add(stringPicker2);
        
        
                  

       Button btnValider = new Button("Add Commande");
       btnValider.getStyle().setBgColor(ColorUtil.rgb(51, 153, 0));
        btnValider.getStyle().setBgTransparency(255);
        addAll(quantite,btnValider);
       btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int idp = products2.get(stringPicker.getSelectedStringIndex()).getIdp();
                int ids = societies2.get(stringPicker2.getSelectedStringIndex()).getIds();
                if ((quantite.getText().length()==0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    //Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else
                {
                    Log.p("clicked too");
                    ConnectionRequest con = new ConnectionRequest();
                    con.setPost(false);
                    String q = quantite.getText();
                    con.setUrl("http://localhost/FINAL%20symfony/final/web/app_dev.php/addCom/"+idp+"/"+ids+"/"+q);
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                    System.out.println("done comment!");
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println("response : " + s);
                    System.out.println("response : " + evt.getMetaData().equals("true"));
                    if ( s.equals("\"success\"")) {
                    Dialog.show("Confirmation", "ajout ok", "Ok", null);
                    // previous.showBack();
                    commandeaff2 c = new commandeaff2(me);
                    c.show();
                    // to do c.refreshTheme();
                    }
                    else {
                    Dialog.show("Error", "ajout not ok", "Not Ok", null);
                    }
                    }
                    });
                    NetworkManager.getInstance().addToQueue(con);
                }
            }
        });
               /*
           CommandeF t = new CommandeF(date.getDate(),Integer.parseInt(soc.getText()));
        
                       if( ServiceCommandef.getInstance().addcomm2(String.valueOf(date.getDate()), quantite.getText(), soc.getText(), prod.getText() ))
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                       else
                           Dialog.show("ERROR", "Server error", new Command("OK"));
                       
                    } catch (NumberFormatException e) {
                       Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                   }
                    
           }
                
                });*/
      
         
    
       
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> {
                previous.showBack();
                stringsArray.clear();
                //stringsArray2.clear();
                        });
            
           
           }
       private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
}