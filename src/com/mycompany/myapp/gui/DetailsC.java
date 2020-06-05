/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.mycompany.entities.CommandeF;
import com.mycompany.entities.societe;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.TextField;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author ASUS
 */
public class DetailsC extends Form{
    Form current;
  
       
    public DetailsC(CommandeF s) {
        // SpanLabel sp = new SpanLabel("Partie Acueil");
       current=this;
          Form previous = Display.getInstance().getCurrent();
                      setTitle("Farcture");                                                                                                                                                                                                               
         Label k = new Label("Facture Num°"+s.getIdm());
           Label m=new Label("                      ");
            
                
     Label lnom = new Label("Societe");
     lnom.setText("nom Societe : "+s.getSociete().getNames());
     Label n=new Label("                   ");
  
            
     Label laddress= new Label("Produit ");
     laddress.setText("Produit:"+s.getProduit().getPrix());
      Label mail = new Label("Quantite");
      Label tel = new Label("Date");
      mail.setText("Ouantite: "+s.getQuantite());
      tel.setText("Date: "+s.getDate());
   Label total = new Label ("Total");
   int Quant=s.getQuantite();
   Float pr= s.getProduit().getPrix();
   total.setText("Total =:"+pr*Quant);
   Label nn=new Label("                   ");
        SpanLabel d = new SpanLabel("prix par unité:", "");
        d.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        SpanLabel date = new SpanLabel(""+s.getDate());
        //date.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        //d.getStyle().setFgColor(ColorUtil.YELLOW);
        //d.setUIID(id);
   Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
                        new Label("numéro:", ""),
                        new Label(""+s.getIdm()),
                        new Label("societé:", ""),
                        new Label(s.getSociete().getNames()),
                        new Label("Produit:", ""),
                        //createTxtName(c),
                        new Label(s.getProduit().getNom()),
                        //createTxtName(c),
                        d,
                        //new Label("prix par unité:", ""),
                        new Label(""+s.getProduit().getPrix()),
                        new Label("quantité:", ""),
                        //createTxtName(c),
                        new Label(""+s.getQuantite()),
                        new Label("date:", ""),
                        date,
                        //new Label(""+s.getDate()),
                        new Label("total:", ""),
                        new Label(""+pr*Quant)
                        //createTxtAge(c)
                )            
            );
        

   
        current.getStyle().setBgColor(ColorUtil.WHITE);
        current.getStyle().setBgTransparency(255);
        current.add(cDetail);
      
       current.show();
      
 
     
     
                 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

        
    }}
