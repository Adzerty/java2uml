package java2uml.IHM.GUI;

import java.util.ArrayList;

import javax.swing.*;
import java2uml.metier.*;

public class FramePrc extends JFrame {
	
	private PanelPrc panelPrincipal;
	private ArrayList<Entite> ensEntite = new ArrayList<>();
	
    public FramePrc(ConfigReader config) {
    	
        this.setTitle("Java2UML");
        this.setSize(1200, 600);
        this.setLocation(100, 100);
        
        this.ensEntite = config.getEnsEntite();
        
        this.panelPrincipal = new PanelPrc(ensEntite);
        this.add(this.panelPrincipal);
        	
        
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
