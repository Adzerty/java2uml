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
		
		this.setTitle("Modifier un fichier"); //Affichage du titre
		this.setSize(300,100);                // taille de la frame
		this.setLocation(x,y);                //placement de la frame au centre de l'ecran
		this.setLayout(new GridLayout(2,1));  // gridlayout de 2 par 1
		
		//creation des differents panel
		
		this.panelModifier = new PanelModifier(this);
		this.panelValider = new PanelValiderModifier(this);
		
		//ajout des differents panel
		
		this.add(panelModifier);
		this.add(panelValider);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	//methode recuperant fichier .txt a incorporer dans un .txt
	public void setFichier(String fichier) {
		this.nomFichier = fichier;
		
	}
	
	//methode permettant ouvrir avec un editeur le fichier .txt
	public void setValider() {
		this.ctrl.ouvrirEnEdit(this.nomFichier);
		this.dispose();
	}
	
}
