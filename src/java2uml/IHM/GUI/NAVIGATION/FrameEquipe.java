package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;




public class FrameEquipe  extends JFrame{
	
	private ArrayList<Membre> ensMembre = new ArrayList<>();
	private ArrayList<PanelMembre> ensPanelMembre = new ArrayList<>();
	
	public FrameEquipe() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = dim.width/2;
        int y = dim.height/2;
        
		this.setTitle("L'EKIP");
		this.setSize(600,500);
		this.setLocation(x, y);
		
		this.ensMembre.add(new Membre("InnovAction"));
		this.ensMembre.add(new Membre("Pestel","Adrien","./image/adrien.png"));
		this.ensMembre.add(new Membre("Godefroy","Antoine","./image/antoine.png"));
		this.ensMembre.add(new Membre("Pallier","Colin","./image/colin.jpg"));
		this.ensMembre.add(new Membre("Bouyer",",Nathan","./image/nathan.png"));
		this.ensMembre.add(new Membre("Devos","Nicolas","./image/nicolas.png"));
		this.ensMembre.add(new Membre("Cohathanay","Victor","./image/victor.png"));
		
		this.setLayout(new GridLayout(this.ensMembre.size(),1));
		
		int cpt=0;
		for(Membre m: ensMembre) {
			this.ensPanelMembre.add(new PanelMembre(m, cpt));
			cpt++;
		}
		
		for(PanelMembre p: ensPanelMembre) {
			this.add(p);
		}
		
		this.pack();
		this.setVisible(true);
	}
}
