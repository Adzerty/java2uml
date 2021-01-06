package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

public class FrameEquipe  extends JFrame{
	
	private ArrayList<Membre> ensMembre = new ArrayList<>();
	
	public FrameEquipe() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (int)(dim.width);
        int y = (int)(dim.height*0.5);
        
		this.setTitle("L'équipe");   //Affichage du titre
		this.setSize(600,500);       //Taille de la frame
		this.setLocation(x, y);      //Placement de la frame au centre de l'ecran
		
		this.ensMembre.add(new Membre("InnovAction"));
		this.ensMembre.add(new Membre("Pestel","Adrien","./image/adrien.png"));
		this.ensMembre.add(new Membre("Godefroy","Antoine","./image/antoine.png"));
		this.ensMembre.add(new Membre("Pallier","Colin","./image/colin.jpg"));
		this.ensMembre.add(new Membre("Bouyer",",Nathan","./image/nathan.png"));
		this.ensMembre.add(new Membre("Devos","Nicolas","./image/nicolas.png"));
		this.ensMembre.add(new Membre("Cohathanay","Victor","./image/victor.png"));
		
		
		this.setLayout(new GridLayout(this.ensMembre.size(),1)); //Frame en GridLayout
		
		//ajout des panel membre dans la frame 
		int cpt=0;
		for(Membre m: ensMembre) {
			this.add(new PanelMembre(m, cpt));
			cpt++;
		}
	
		this.setVisible(true);
	}
}
