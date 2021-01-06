package java2uml.IHM.GUI.NAVIGATION;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java2uml.Controleur;


public class FrameAccueil extends JFrame{
	
	private PanelAccueil panelAccueil;
	private PanelCreeDiag panelCreeDiag;
	private PanelChargMod panelChargMod;
	
	private Controleur ctrl;
	
	public FrameAccueil(Controleur ctrl) {
		
		
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		this.ctrl = ctrl;

        int x = 200;//dim.width;
        int y = 200;//dim.height;
        
  
		this.setTitle("Java2UML");          //Affichage du titre
		this.setLocation(x, y);             //placement de la frame au centre de l'ecran
		this.setSize(600,500);              // taille de la frame
		this.setLayout(new GridLayout(3,1));// gridlayout de 3 par 1
		
		//creation des differents panel
		
		this.panelAccueil = new PanelAccueil();
		this.panelCreeDiag = new PanelCreeDiag(this,this.ctrl);
		this.panelChargMod = new PanelChargMod(this,this.ctrl);
				
		//ajout des differents panel
		
		this.add(this.panelAccueil);
		this.add(this.panelCreeDiag);
		this.add(this.panelChargMod);
		
		//ajout de la Menu Bar
		this.setJMenuBar(new MenuBar());
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
}
