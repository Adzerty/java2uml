package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java2uml.IHM.GUI.FramePrc;
import java2uml.metier.ConfigReader;

public class FrameCharger extends JFrame{
	
	private PanelCharger panelCharger;
	private PanelValiderCharger panelValider;
	private String nomFichier;
	
	public FrameCharger() {
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Charger un fichier");//Affichage du titre
		this.setSize(300,100);              //Taille de la frame
		this.setLocation(x,y);              //Placement de la frame au centre de l'ecran
		this.setLayout(new GridLayout(2,1));//Gridlayout de 2 par 1
		
		//creation des differents panel
		
		this.panelCharger = new PanelCharger(this);
		this.panelValider = new PanelValiderCharger(this);
		
		//ajout des differents panel
		
		this.add(panelCharger);
		this.add(panelValider);
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	//Methode recuperant le nom du fichier à charger
	
	public void setFichier(String fichier) {
		this.nomFichier = fichier;
		
	}
	
	//Methode permettant de charger un fichier .txt apres la validation
	
	public void setValider() {
		new FramePrc(new ConfigReader(this.nomFichier), this.nomFichier);
	}

	
}
