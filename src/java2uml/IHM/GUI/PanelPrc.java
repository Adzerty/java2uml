package java2uml.IHM.GUI;

import java2uml.metier.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class PanelPrc extends JPanel {
	
	private ArrayList<PanelEntite> ensPanelEntite = new ArrayList<>();
	private FramePrc framePrincipale;
	
	public PanelPrc(ArrayList<Entite> ensEntite , FramePrc framePrincipale) {
		
		this.setLayout(null);
		
		this.framePrincipale = framePrincipale;
		
		for(Entite e : ensEntite) {
			
			this.ensPanelEntite.add(new PanelEntite(e,this));
			
		}
		
		int x , y ;//coord de panel entite 
		x=10;
		y=10;
		
		int hauteurMax = 0;
		
		
		for(PanelEntite pe : ensPanelEntite){
			
			pe.setSize(pe.getPreferredSize());
			
			pe.setLocation(x,y);
			this.add(pe);
			
			if(hauteurMax<pe.getHeight()) {
				hauteurMax = pe.getHeight();
				
			}
			
			if(x+pe.getWidth()>=this.framePrincipale.getWidth()) {
				x=0;
				y+=hauteurMax+30;
				hauteurMax = 0;
				System.out.println(this.getWidth());
			}
			
			x+=pe.getWidth()+30;
			
		}
		
		this.setVisible(true);
	}
	

}
