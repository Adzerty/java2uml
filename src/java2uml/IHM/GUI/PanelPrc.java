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
				System.out.println("Ici :" + a.getClasseDroite()+" "+a.getClasseGauche());
			}
			
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
			
		}
		
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
		
		int x1 = this.ensCoord.get(0).getX()+this.ensPanelEntite.get(0).getWidth();
		int y1 = (this.ensCoord.get(0).getY()+(this.ensPanelEntite.get(0).getHeight()/2)); 
		int x2 = this.ensCoord.get(1).getX();
		int y2 = (this.ensCoord.get(1).getY()+(this.ensPanelEntite.get(1).getHeight()/2));
		
		g.drawLine(x1 , y1, x2,y2 );
	}
	


}
