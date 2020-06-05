/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.codename1.messaging.Message;

/**
 *
 * @author ASUS
 */
public class first extends Form{
    
    public first(Resources res) {
        super(new BorderLayout());
       // getTitleArea().setUIID("Container");
        //setUIID("Welcome");
        Tabs t = new Tabs();
        t.hideTabs();
        t.setUIID("Container");
        t.getContentPane().setUIID("Container");
        
        add(BorderLayout.CENTER, t);
        
        ScaleImageLabel page1 = new ScaleImageLabel(res.getImage("logo-final.gif"));
        ScaleImageLabel page2 = new ScaleImageLabel(res.getImage("092414-control-del-stock.jpg"));
       
        page1.setUIID("Container");
        page2.setUIID("Container");
       
        page1.getAllStyles().setBgTransparency(0);
        page2.getAllStyles().setBgTransparency(0);
     /*   page3.getAllStyles().setBgTransparency(0);
        page4.getAllStyles().setBgTransparency(0);
        page5.getAllStyles().setBgTransparency(0);
        page6.getAllStyles().setBgTransparency(0);*/
        t.addTab("", page1);
        t.addTab("", page2);
        /*t.addTab("", page3);
        t.addTab("", page4);
        t.addTab("", page5);
        t.addTab("", page6);*/
        
        String[] messages = {
            "Bienvenue \nSmart Start",
            "Bienvenue \nSmart Start",
           /* "Bienvenue \nSmart Start",
            "Bienvenue \nSmart Start",
            "Bienvenue \nSmart Start",
            
            "Bienvenue \nSmart Start",*/
        };
        
                        
        /****************************On va met messages[0]*****************************/
        SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");
                
/***************************Les petites cercle**************/
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xcccccc);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xff2d55);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[t.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        t.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
                message.setText(messages[ii]);
            }
        });
        /****************************************PASSER  ***********************/
        
        Button skip = new Button("Passer");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new accueil(res).show());
        
        /*
        skip.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //Mail m = new Mail();
                //TestJ j = new TestJ();
               // Stat st = new Stat();
            }
            });*/
        
        Container welcomeNoteArea = BoxLayout.encloseY(message,
                LayeredLayout.encloseIn(
                        radioContainer,
                        BorderLayout.east(skip)
                )
        );
        welcomeNoteArea.setUIID("WelcomeNoteArea");
        add(BorderLayout.SOUTH, welcomeNoteArea);
    }
    
    
}
