/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.CategorieR;
import com.mycompany.entities.CommandeF;
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.produit;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ListReclamationForm extends Form {

    SpanLabel lb;
    Form malek;
    String[] commandes = {"Modifier", "Details", "Supprimer"};
    ServiceReclamation myService = new ServiceReclamation();
    ArrayList<Reclamation> list2 = myService.getAllrecs();
    ArrayList<Reclamation> list = list2;
    ArrayList<produit> products = new ArrayList<>();
    ArrayList<CategorieR> categories = new ArrayList<>();
    accueil a= new accueil();

    public ListReclamationForm(Form previous) {
        if (products.size() > 0) {
        } else {
            //products = myService.parseProducts();
            products = myService.getAllProducts();
        }
        if (categories.size() > 0) {
        } else {
            // categories = myService.parseCategories();
            categories = myService.getAllCategories();
        }
        malek = this;
        setTitle("les Reclamations");
        setLayout(BoxLayout.y());

        Toolbar.setGlobalToolbar(true);
        /*
accueilf.getToolbar().addSearchCommand(e -> {
});*/
        Style s = UIManager.getInstance().getComponentStyle("TitleRecl");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MENU, s);
        TextField searchField = new TextField("", "Search...");
        /*searchField.getHintLabel().setUIID("Title");*/
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        malek.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        malek.getStyle().setBgColor(ColorUtil.WHITE);
        malek.getStyle().setBgTransparency(255);
        if (list.isEmpty()) {
            SpanLabel lb = new SpanLabel(" aucune reclamation !");
            malek.add(lb);
        } else {

            for (Reclamation r : list) {
                MultiButton b = new MultiButton("");

                b.getStyle().setBgColor(ColorUtil.WHITE);
                b.getStyle().setBgTransparency(255);
                b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
                String etat = "";
                if (r.isEtat() == true) {
                    etat = "traitée";
                } else {
                    etat = "non traitée";
                }
                b.setTextLine2("Désignation \n:" + r.getDesignation());
                b.setTextLine3("Etat  " + etat);
                malek.add(b);

                b.addActionListener(e -> {
                    if (r.isEtat() == true) {
                        ReclamationDetails k = new ReclamationDetails(r);
                        k.show();
                    } else {
                        Dialog d = new Dialog();
                        d.setLayout(BoxLayout.y());
                        d.getContentPane().setScrollableY(true);
                        for (String cmd : commandes) {
                            MultiButton mb = new MultiButton(cmd);
                            d.add(mb);
                            mb.addActionListener(ee -> {

                                switch (cmd) {

                                    case "Modifier":
                                        ReclamationUpdate c = null;
                                         {
                                            try {
                                                c = new ReclamationUpdate(r, products, categories);
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                        c.show();

                                        break;
                                    case "Supprimer":
                                        delete(r.getId(), d);
                                        break;
                                    default:

                                        ReclamationDetails k = new ReclamationDetails(r);
                                        k.show();

                                        break;
                                }

                            });
                        }
                        d.showPopupDialog(b);
                    }
                });
                /* b.addActionListener(e->{
                    
 DetailsC d = new DetailsC(r);
 d.show();
 });        
            }*/

            }
        }
        // **********************************
        searchField.addDataChangeListener((i1, i2) -> {
            String t = searchField.getText();
            if (t.length() < 1) {
                malek.removeAll();
                list = list2;
                refreshForm();
            } else {
                t = t.toLowerCase();
                malek.removeAll();
                for (Reclamation r : list) {
                    if (r.getDesignation().indexOf(t) > -1) {
                        MultiButton b = new MultiButton("");
                        b.getStyle().setBgColor(ColorUtil.WHITE);
                        b.getStyle().setBgTransparency(255);
                        b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
                        String etat = "";
                        if (r.isEtat() == true) {
                            etat = "traitée";
                        } else {
                            etat = "non traitée";
                        }
                        b.setTextLine2("Désignation \n:" + r.getDesignation());
                        b.setTextLine3("Traité  " + etat);
                        malek.add(b);

                        b.addActionListener(e -> {
                            Dialog d = new Dialog();
                            d.setLayout(BoxLayout.y());
                            d.getContentPane().setScrollableY(true);
                            for (String cmd : commandes) {
                                MultiButton mb = new MultiButton(cmd);
                                d.add(mb);
                                mb.addActionListener(ee -> {

                                    switch (cmd) {

                                        case "Modifier":
                                            ReclamationUpdate c = null;
                                          
                                            c.show();

                                            break;
                                        default:

                                            ReclamationDetails k = new ReclamationDetails(r);
                                            k.show();

                                            break;
                                    }

                                });
                            }
                            d.showPopupDialog(b);

                        });
                    }
                }
            }
        });
        // **********************************

        //accueilf.add(new InfiniteProgress());
        getToolbar().addMaterialCommandToRightBar(
                "", FontImage.MATERIAL_ADD, 6f, (ActionEvent e) -> {
                    try {
                        new AddReclamationForm(malek, products, categories).show();
                    } catch (IOException ex) {
                        System.out.println("err");
                    }
                });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> a.show());

    }

    public void refreshForm() {
        for (Reclamation r : list) {
            MultiButton b = new MultiButton("");
            b.getStyle().setBgColor(ColorUtil.WHITE);
            b.getStyle().setBgTransparency(255);
            b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
            String etat = "";
            if (r.isEtat() == true) {
                etat = "traitée";
            } else {
                etat = "non traitée";
            }
            b.setTextLine2("Désignation \n:" + r.getDesignation());
            b.setTextLine3("Traité  " + etat);
            malek.add(b);

            b.addActionListener(e -> {
                Dialog d = new Dialog();
                d.setLayout(BoxLayout.y());
                d.getContentPane().setScrollableY(true);
                for (String cmd : commandes) {
                    MultiButton mb = new MultiButton(cmd);
                    d.add(mb);
                    mb.addActionListener(ee -> {

                        switch (cmd) {

                            case "Modifier":
                                ReclamationUpdate c = null;
                                // c = new ReclamationUpdate(r.getIdm(), r.getDate(), r.getQuantite(), r.getSociete(), r.getProduit(), products, societies);
                                c.show();

                                break;
                            default:

                                ReclamationDetails k = new ReclamationDetails(r);
                                k.show();

                                break;
                        }

                    });
                }
                d.showPopupDialog(b);

            });

        }
    }

    public void delete(int id, Dialog d) {
        Log.p("clicked too");
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://localhost/FINAL%20symfony/final/web/app_dev.php/delRec/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("done comment!");
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.out.println("response : " + s);
                System.out.println("response : " + evt.getMetaData().equals("true"));
                if (s.equals("\"success\"")) {
                    reloadForm();
                    Dialog.show("Confirmation", "votre reclamation a été supprimée avec succés", "Ok", null);
                    d.dispose();
                } else {
                    Dialog.show("Error", "non supprimée", "Not Ok", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void reloadForm() {
        list2.clear();
        list2 = myService.getAllrecs();
        malek.removeAll();
        list.clear();
        list = list2;
        if (list.isEmpty()) {
            SpanLabel lb = new SpanLabel(" aucune reclamation !");
            malek.add(lb);
        } else {

            for (Reclamation r : list) {
                MultiButton b = new MultiButton("");

                b.getStyle().setBgColor(ColorUtil.WHITE);
                b.getStyle().setBgTransparency(255);
                b.getUnselectedStyle().setBorder(Border.createGrooveBorder(1, ColorUtil.GRAY));
                String etat = "";
                if (r.isEtat() == true) {
                    etat = "traitée";
                } else {
                    etat = "non traitée";
                }
                b.setTextLine2("Désignation \n:" + r.getDesignation());
                b.setTextLine3("Etat : " + etat);
                malek.add(b);

                b.addActionListener(e -> {
                    if (r.isEtat() == true) {
                        ReclamationDetails k = new ReclamationDetails(r);
                        k.show();
                    } else {
                        Dialog d = new Dialog();
                        d.setLayout(BoxLayout.y());
                        d.getContentPane().setScrollableY(true);
                        for (String cmd : commandes) {
                            MultiButton mb = new MultiButton(cmd);
                            d.add(mb);
                            mb.addActionListener(ee -> {

                                switch (cmd) {

                                    case "Modifier":
                                        ReclamationUpdate c = null;
                                         {
                                            try {
                                                c = new ReclamationUpdate(r, products, categories);
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                        c.show();

                                        break;
                                    case "Supprimer":
                                        delete(r.getId(), d);
                                        break;
                                    default:

                                          ReclamationDetails k = new ReclamationDetails(r);
                                        k.show();

                                        break;
                                }

                            });
                        }
                        d.showPopupDialog(b);
                    }
                });

            }
        }
    }
}
