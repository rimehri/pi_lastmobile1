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
import com.mycompany.entities.CategorieR;
import com.mycompany.entities.produit;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author LENOVO
 */
public class ReclamationUpdate extends Form{
     
     
     Form me ;
     ComboBox combo;
     ArrayList<produit> list = new ArrayList<>() ;
     ArrayList<CategorieR> list2 = new ArrayList<>() ;
      Form previous = Display.getInstance().getCurrent();
        int indexp = 0, indexs = 0;
      // public ModifC(CommandeF f) throws IOException {
       public ReclamationUpdate(Reclamation rec, ArrayList<produit> products, ArrayList<CategorieR> categories) throws IOException {
           //*********************
        for (int i = 0; i < products.size(); i++) {
            if ( products.get(i).getNom().equals(rec.getProduit().getNom()) ) {
                indexp = i;
            }
        }
        for (int i = 0; i < categories.size(); i++) {
            if ( categories.get(i).getNomR().equals(rec.getCategorie().getNomR()) ) {
                indexs = i;
            }
        }
        ArrayList<String> stringsArray;
        ArrayList<String> stringsArray2;
        stringsArray = new ArrayList<String>();
        stringsArray2 = new ArrayList<String>();
        ServiceReclamation myService = new ServiceReclamation();
        if ( products.size() > 0 ) {
        } else {
            list = myService.parseProducts();
            Log.p("proood = " + list);
        }
        if ( categories.size() > 0 ) {
        } else {
            list2 = myService.parseCategories();
            Log.p("proood = " + list);
        }
           //*********************
        me=this;
        setTitle("Modifier reclamation");
        setLayout(BoxLayout.y());
        
       Button btnValider = new Button("Modifier Reclamation");
       TextField designation= new TextField("", "Designation",0,TextField.NUMERIC);
       designation.setText(rec.getDesignation());
       TextField description= new TextField("", "Description",0,TextField.NUMERIC);
       description.setText(rec.getDescription());
       Label designationLabel = new Label("Désignation");
       Label descriptionLabel = new Label("Description");
      
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
       
       //******************
       Label catLabel = new Label("selctionner catégorie");
        Label prodLabel = new Label("selctionner produit");

         Picker stringPicker = new Picker();
         Picker stringPicker2 = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker2.setType(Display.PICKER_TYPE_STRINGS);
        //stringPicker.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
        //"A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        //stringPicker.setSelectedString("A Game of Thrones");
        me.add(designationLabel);
        me.add(designation);
        me.add(descriptionLabel);
        me.add(description);
        me.add(prodLabel);
        me.add(stringPicker);
        me.add(catLabel);
        me.add(stringPicker2);
        me.getStyle().setBgColor(ColorUtil.WHITE);
        me.getStyle().setBgTransparency(255);
        stringsArray.clear();
        if (products.size() > 0) {
            for (produit p2 : products) {
            //stringPicker.setPropertyValue(String.valueOf(p.getIdp()), p.getNom());
            stringsArray.add(p2.getNom());
            }
            String[] strings = new String[products.size()];
            stringsArray.toArray(strings);
            stringPicker.setStrings(strings);
        } else {
            Log.p("noo val");
        }
        stringPicker.setSelectedString(rec.getProduit().getNom());
        for (CategorieR c2 : categories) {
            stringsArray2.add(c2.getNomR());
        }
        stringPicker2.setSelectedString(rec.getCategorie().getNomR());
        String[] strings2 = new String[categories.size()];
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
           int idc = categories.get(indexS).getIdc();
           int idp = products.get(indexP).getIdp();
           if ((designation.getText().length()==0) || (description.getText().length()==0))
           Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
           else
           {
               int identifiant = rec.getId();
               ConnectionRequest con = new ConnectionRequest();
               con.setPost(false);
               String newDesi = designation.getText();
               String newDesc = description.getText();
               con.setUrl("http://localhost/FINAL%20symfony/final/web/app_dev.php/updRec/"+identifiant+"/"+idp+"/"+idc+"/"+newDesc+"/"+newDesi);
               //con.setUrl("http://192.168.93.1/rimehService/MalekService/recUpd.php?id="+identifiant+"&idc="+idc+"&idp="+idp+"&designation="+newDesi+"&description="+newDesc);
               con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        System.out.println("done comment!");
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        Log.p("response : " + s);
                        if ( s.equals("\"success\"")) {
                            Dialog.show("Confirmation", "modified successfully", "Ok", null);
                            ListReclamationForm f2 = new ListReclamationForm(me);
                    f2.show();
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

    

