/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.CommandeF;
import com.mycompany.entities.produit;
import com.mycompany.entities.societe;
import com.mycompany.myapp.services.ServiceCommandef;
import com.mycompany.myapp.services.ServiceFournisseur;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class commandefafffich extends Form{
     Form accueilf;
    public commandefafffich(Form previous) {
        accueilf=this;
        setTitle("les commandes");
        setLayout(BoxLayout.y());
        
        Toolbar.setGlobalToolbar(true);

accueilf.getToolbar().addSearchCommand(e -> {
});
 add(new Label(""));
       SpanLabel sp = new SpanLabel();
        sp.setText(ServiceCommandef.getInstance().getAllTasks().toString());
        add(sp);
        accueilf.add(new InfiniteProgress());
        getToolbar().addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
            try {
                new addcomm(accueilf, new ArrayList<produit>(), new ArrayList<societe>()).show();
            } catch (IOException ex) {
                System.out.println("err");            }
        });
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
   
    }
}