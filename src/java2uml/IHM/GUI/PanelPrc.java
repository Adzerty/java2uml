package java2uml.IHM.GUI;

import java2uml.metier.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelPrc extends JPanel {
	
	private ArrayList<PanelEntite> ensPanelEntite = new ArrayList<>();
	
	public PanelPrc(ArrayList<Entite> ensEntite) {
		
		this.setLayout(null);
		
		for(Entite e : ensEntite) {
			
			this.ensPanelEntite.add(new PanelEntite(e));
			
		}
		
		int x , y ;//coord de panel entite 
		x=y=0;
		
		for(PanelEntite pe : ensPanelEntite){
			
			pe.setSize(100,100);
			pe.setLocation(x,y);
			this.add(pe);
			x+=50;
			y+=50;
		}
		
		this.setVisible(true);
	}
}
