/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.entities.Reclamation;

/**
 *
 * @author LENOVO
 */
public class ReclamationDetails extends Form{
    
    Form current;
  
       
    public ReclamationDetails(Reclamation r) {
        // SpanLabel sp = new SpanLabel("Partie Acueil");
       current=this;
          Form previous = Display.getInstance().getCurrent();
                      setTitle("Reclamation");       
   SpanLabel desc = new SpanLabel(""+r.getDescription());
   SpanLabel date = new SpanLabel(""+r.getDate());
          //mail.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
          
   Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
                        new Label("Numéro:", ""),
                        new Label(""+r.getId()),
                        new Label("Catégorie:", ""),
                        new Label(r.getCategorie().getNomR()),
                        new Label("Produit:", ""),
                        //createTxtName(c),
                        new Label(r.getProduit().getNom()),
                        new Label("Désignation:", ""),
                        //createTxtName(c),
                        new Label(r.getDesignation()),
                        new Label("Description:", ""),
                        //createTxtName(c),
                        desc,
                        new Label("Date:", ""),
                        date
                        //createTxtAge(c)
                )            
            );
        

   
        current.getStyle().setBgColor(ColorUtil.WHITE);
        current.getStyle().setBgTransparency(255);
        current.add(cDetail);
       
      
       current.show();
      
 
     
     
                 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

        
    }}
