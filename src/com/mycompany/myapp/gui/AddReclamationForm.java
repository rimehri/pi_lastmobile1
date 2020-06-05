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
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.CategorieR;
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.produit;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class AddReclamationForm  extends Form{
Form me ;
     
      
       ArrayList<produit> list = new ArrayList<>() ;
       ArrayList<CategorieR> list2 = new ArrayList<>() ;
       
      public AddReclamationForm(Form previous, ArrayList<produit> products, ArrayList<CategorieR> categories) throws IOException {
        ArrayList<String> stringsArray;
        
        ArrayList<String> stringsArray2;
        ServiceReclamation myService = new ServiceReclamation();
        stringsArray = new ArrayList<String>();
        /*     if ( products.size() > 0 ) {
        } else {
        list = myService.parseProducts();
        }
        if ( categories.size() > 0 ) {
        } else {
        list2 = myService.parseCategories();
        }*/
        stringsArray2 = new ArrayList<String>();
        
        me=this;
        setTitle("Passer une reclamation");
        setLayout(BoxLayout.y());
        Label catLabel = new Label("selectionner categorie");
        Label prodLabel = new Label("selectionner produit");
        TextField designation= new TextField("", "désignation");
        TextField description = new TextField("", "Description", 20, TextArea.ANY);
        description.setMaxSize(300);

         Picker stringPicker = new Picker();
         Picker stringPicker2 = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker2.setType(Display.PICKER_TYPE_STRINGS);
        
        me.getStyle().setBgColor(ColorUtil.WHITE);
        me.getStyle().setBgTransparency(255);
        Log.p("prooodee = " + list);
        Log.p("index ==  " + stringPicker.getSelectedStringIndex());
        stringsArray.clear();
        if (products.size() > 0) {
            for (produit p : products) {
            //stringPicker.setPropertyValue(String.valueOf(p.getIdp()), p.getNom());
            stringsArray.add(p.getNom());
            }
            String[] strings = new String[products.size()];
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
        
        for (CategorieR car : categories) {
            stringsArray2.add(car.getNomR());
        }
        String[] strings2 = new String[categories.size()];
        stringsArray2.toArray(strings2);
        
        stringPicker2.setStrings(strings2);
        
        
                  

       Button btnValider = new Button("Ajouter Reclamation");
        addAll(designation,description,prodLabel,stringPicker,catLabel,stringPicker2,btnValider);
        btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int idp = 20;
                int idc = 20;
                if ((designation.getText().length()==0) || (description.getText().length()==0) ) {
                    Dialog.show("Alert", "Veuillez remplir vos champs svp", new Command("OK"));
                }
                else
                {
                    idp = products.get(stringPicker.getSelectedStringIndex()).getIdp();
                    idc = categories.get(stringPicker2.getSelectedStringIndex()).getIdc();
                    ConnectionRequest con = new ConnectionRequest();
                    con.setPost(false);
                    String desi = designation.getText();
                    String desc = description.getText();
                    int userId = 1;
                    con.setUrl("http://localhost/FINAL%20symfony/final/web/app_dev.php/addRec/"+idp+"/"+idc+"/"+desc+"/"+desi);
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                    System.out.println("done comment!");
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    Log.p("hhhh "+s);
                    System.out.println("response : " + s);
                    System.out.println("response : " + evt.getMetaData().equals("true"));
                    if ( s.equals("\"success\"")) {
                    Dialog.show("Confirmation", "Reclamation ajoutée avec succés", "Ok", null);
                  
                  //  Mail m = new Mail();
                    // previous.showBack();
                    ListReclamationForm l = new ListReclamationForm(me);
                    l.show();
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
   
      
         
    
       
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> {
                previous.showBack();
                stringsArray.clear();
                //stringsArray2.clear();
                        });
            
           
           }
      /*  private void showToast(String text) {
      Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
      ToastBar.Status status = ToastBar.getInstance().createStatus();
      status.setMessage(text);
      status.setIcon(errorImage);
      status.setExpires(2000);
      status.show();
      }*/
}