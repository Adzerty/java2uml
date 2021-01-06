package java2uml.IHM.GUI;

import java.util.ArrayList;

import javax.swing.*;

import java2uml.IHM.GUI.NAVIGATION.MenuBar;
import java2uml.metier.*;

public class FramePrc extends JFrame {
	
	private PanelPrc panelPrincipal;
	private ArrayList<Entite> ensEntite = new ArrayList<>();
	
	
    public FramePrc(ConfigReader config, String name) {
    	
        this.setTitle("Java2UML");
        this.setSize(1200, 600);
        this.setLocation(100, 100);
        
        this.ensEntite = config.getEnsEntite();
       
        
        this.panelPrincipal = new PanelPrc(ensEntite , this, name);
        this.add(this.panelPrincipal);
        	
        
        //this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setJMenuBar(new MenuBarPrc(this.panelPrincipal));
        this.setVisible(true);
    }
}
