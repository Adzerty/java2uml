package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;

import javax.swing.JFrame;



import java2uml.Controleur;
import java2uml.IHM.GUI.FramePrc;
import java2uml.metier.ConfigReader;

public class FrameModifier extends JFrame{
	
	
	private PanelModifier panelModifier;
	private PanelValiderModifier panelValider;
	private String nomFichier;
	
	private Controleur ctrl;
	
	public FrameModifier(Controleur ctrl) {
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.ctrl = ctrl;

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Modifier un fichier");
		this.setSize(300,100);
		this.setLocation(x,y);
		this.setLayout(new GridLayout(2,1));
		
		this.panelModifier = new PanelModifier(this);
		this.panelValider = new PanelValiderModifier(this);
		
		this.add(panelModifier);
		this.add(panelValider);
		
		//this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void setFichier(String fichier) {
		this.nomFichier = fichier;
		
	}

	public void setValider() {
		// TODO Auto-generated method stub
		//new FramePrc(new ConfigReader(this.nomFichier));
		this.ctrl.ouvrirEnEdit(this.nomFichier);
		//System.out.println(this.ctrl +" "+ this.nomFichier);
	}
	
	
}
