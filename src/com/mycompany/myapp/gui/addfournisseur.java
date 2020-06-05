/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridBagConstraints;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class addfournisseur extends Form{
    Form me ;
      public addfournisseur(Form previous) throws IOException {
        me=this;
        setTitle("Add  a new Fournisseur");
        setLayout(BoxLayout.y());
        /*
         TextField tnom = new TextField("","nom");
        TextField tadress= new TextField("", "address");
        TextField tmail= new TextField("", "email");
        TextField ttel= new TextField("", "numéro de telephone");*/
        
        //TextModeLayout tl = new TextModeLayout(4, 1);
            TextComponent tnom = new TextComponent().label("nom");
            TextComponent tadress = new TextComponent().label("address");
            TextComponent tmail = new TextComponent().label("email");
            TextComponent ttel = new TextComponent().label("numéro de telephone");
            Validator val = new Validator();
            val.addConstraint(tnom, new LengthConstraint(2));
            val.addConstraint(tadress, new LengthConstraint(2));
            val.addConstraint(tmail, RegexConstraint.validEmail());
            val.addConstraint(ttel, new NumericConstraint(true));
            //me.setEditOnShow(tnom.getField());
        
           
            
      

            Button btnValider = new Button("Add Fournisseur");
            Button btnValider2 = new Button("Add Fournisseur");
            // btnValider.setUIID("LoginButtonFacebook");
            // btnValider2.setUIID("LoginButtonGoogle");
            /*
            btnValider.getAllStyles().setBorder(Border.createBevelLowered().createEmpty());
            btnValider.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            btnValider.getAllStyles().setBgColor(0x99FFFF);
            btnValider.getAllStyles().setBgTransparency(255);*/
            /*
            Style iconFontStyle = UIManager.getInstance().getComponentStyle("IconFont");
            btnValider.setFontIcon(iconFontStyle, 0);
            btnValider.setIcon(FontImage.create(" \ue96c ", iconFontStyle));
            btnValider2.setIcon(FontImage.create(" \ue996 ", iconFontStyle));*/

            btnValider.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
            btnValider.getUnselectedStyle().setBackgroundGradientEndColor(0xFFBCCA);
            btnValider.getUnselectedStyle().setBackgroundGradientStartColor(0xFFBCCA);
            btnValider.setSize(new Dimension(30, 30));
             //btnValider.getAllStyles().set
            Label i = new Label();
            Resources r = Resources.open("/theme.res");
            //Button b = new Button();
            //b.setIcon(UIManager.initFirstTheme("/theme").getImage("menu.png"));
            //hi.addComponent(c, button);
        /*ew Button(Image.createImage("C:\Users\LENOVO\Desktop\pfe\rime7\menu.png");
        */
        
      
          addAll(tnom,tadress,tmail,ttel, btnValider);
       
                

        
                
            
           
    
   
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
   }
}
