/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.table.TableLayout;
import com.mycompany.entities.societe;
import com.mycompany.myapp.services.ServiceFournisseur;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class DetailsF extends Form{
    Form current;
  
       
    public DetailsF(societe s) {
        
       current=this;
          Form previous = Display.getInstance().getCurrent();
          SpanLabel mail = new SpanLabel(""+s.getEmail());
          //mail.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
          Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
                        new Label("Numéro:", ""),
                        new Label(""+s.getIds()),
                        new Label("Nom:", ""),
                        new Label(s.getNames()),
                        new Label("Adresse:", ""),
                        //createTxtName(c),
                        new Label(s.getAddress()),
                        //createTxtName(c),
                        new Label("Email", ""),
                        new Label(""+s.getEmail()),
                        new Label("Num Tel:", ""),
                        //createTxtName(c),
                        new Label(""+s.getTel())
                        //createTxtAge(c)
                )            
            );
        current.getStyle().setBgColor(ColorUtil.WHITE);
        current.getStyle().setBgTransparency(255);
        current.add(cDetail);
        current.show();
      
 
     
     
                 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

    
                 
    }
    
}
