package java2uml.IHM.GUI;

import java2uml.metier.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class PanelPrc extends JPanel {
	
	private ArrayList<PanelEntite> ensPanelEntite = new ArrayList<>();
	private ArrayList<Coord> ensCoord = new ArrayList<>();
	private FramePrc framePrincipale;
	
	//press action
	
	private int posSourisX;
	private int posSourisY;
		
		
	public PanelPrc(ArrayList<Entite> ensEntite , FramePrc framePrincipale) {
		
		this.setLayout(null);
		
		this.framePrincipale = framePrincipale;
		
		int identifiant = 0;
		
		for(Entite e : ensEntite) {
			
			this.ensPanelEntite.add(new PanelEntite(e,this,identifiant));
			identifiant++;
			
		}
		
		int x , y ;//coord de panel entite 
		x=10;
		y=10;
		
		int hauteurMax = 0;
		
		
		for(PanelEntite pe : ensPanelEntite){
			
			pe.setSize(pe.getPreferredSize());
			
			ensCoord.add(new Coord(x,y));
			
			pe.setLocation(ensCoord.get(pe.getId()).getX(),ensCoord.get(pe.getId()).getY());
			
			this.add(pe);
			
			if(hauteurMax<pe.getHeight()) {
				hauteurMax = pe.getHeight();
				
			}
			
			if(x+pe.getWidth()>=this.framePrincipale.getWidth()) {
				x=0;
				y+=hauteurMax+30;
				hauteurMax = 0;
				
			}
			
			x+=pe.getWidth()+30;
			
		}
		
		for(Coord c : ensCoord) {
			System.out.println(c);
		}
		this.setVisible(true);
	}
	
	public void press(MouseEvent e)
	{
		if(e.getSource() instanceof PanelEntite)
		{
			this.posSourisX = e.getX();	
			this.posSourisY = e.getY();	
			
			
			System.out.println("x : "+this.posSourisX + " y : "+this.posSourisY + " "+ ((PanelEntite) e.getSource()).getId());
		}
		
	}
	
	public void release(MouseEvent e)
	{
		
	}
	


}
