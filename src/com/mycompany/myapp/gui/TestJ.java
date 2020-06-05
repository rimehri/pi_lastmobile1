/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Log;
import com.codename1.ui.Form;
import com.mycompany.entities.societe;
import com.mycompany.myapp.services.ServiceFournisseur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author LENOVO
 */
public class TestJ extends Form  {
    public TestJ() {
        Log.p("start");
        ServiceFournisseur myServices = new ServiceFournisseur();
         ArrayList<societe> list = myServices.getAllTasks();
         ArrayList<societe> socTrie = list;
        Collections.sort(socTrie, new Comparator<societe>() {

        public int compare( societe r1, societe r2 ) {
            if( r1.getVue() != r2.getVue() ) return r2.getVue()-r1.getVue();
            else return r2.getVue()-r1.getVue();
    }

            
    });
        for (societe r: socTrie) {
            Log.p("tri : " + r.getVue());
        }
        Log.p("finish");
        Stat st = new Stat(socTrie);
        st.show();
    }
}
