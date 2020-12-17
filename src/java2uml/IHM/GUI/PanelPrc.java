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
	private ArrayList<Association> ensAssociation = new ArrayList<>();
	private FramePrc framePrincipale;
	
	//press action
	
	private int posSourisX;
	private int posSourisY;
		
		
	public PanelPrc(ArrayList<Entite> ensEntite , FramePrc framePrincipale) {
		
		this.setLayout(null);
		
		this.framePrincipale = framePrincipale;
		
		int identifiant = 0;
		
		for(Entite e : ensEntite) {
			
			for(Association a: e.getEnsAssociations())
			{
				ensAssociation.add(a);
			}
			
			this.ensPanelEntite.add(new PanelEntite(e,this,identifiant));
			identifiant++;
			
		}
		
		
		// POSITIONNEMENT DES CLASSES AU DEPART 
		
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
			
			// JUSQU'ICI
		}
		
		this.setVisible(true);
	}
	
	
	public void press(MouseEvent e)
	{
		if(e.getSource() instanceof PanelEntite)
		{
			this.posSourisX = e.getX();	
			this.posSourisY = e.getY();	
			
		}
		
		//System.out.println(this.getWidth() +" "+this.getHeight());
		
	}
	
	public void release(MouseEvent e)
	{
		int ident = (((PanelEntite) e.getSource()).getId());
		
		int offSetX = e.getX() - this.posSourisX;
		int offSetY = e.getY() - this.posSourisY;
		
		
		this.ensCoord.get(ident).setX(this.ensCoord.get(ident).getX()+offSetX);
		this.ensCoord.get(ident).setY(this.ensCoord.get(ident).getY()+offSetY);
		
		
		((PanelEntite) e.getSource()).setLocation(this.ensCoord.get(ident).getX(),this.ensCoord.get(ident).getY());
		
		repaint();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		int x1, y1, x2, y2;
		x1 = y1 = x2 = y2 =0;
		
		for(Association a: this.ensAssociation)
		{
				for(int i = 0; i < this.ensPanelEntite.size(); i++)
				{					
					if(this.ensPanelEntite.get(i).getNom().equals(a.getClasseDroite()))
					{
						x1 = this.ensCoord.get(i).getX()+this.ensPanelEntite.get(i).getWidth();
						y1 = (this.ensCoord.get(i).getY()+(this.ensPanelEntite.get(i).getHeight()/2));	
					}
					if(this.ensPanelEntite.get(i).getNom().equals(a.getClasseGauche()))
					{
						x2 = this.ensCoord.get(i).getX();
						y2 = (this.ensCoord.get(i).getY()+(this.ensPanelEntite.get(i).getHeight()/2));
					}
					
				}
				
				g.drawLine(x1 , y1, x2,y2 );
		}
	}
}
	
