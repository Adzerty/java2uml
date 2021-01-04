package java2uml.IHM.GUI;

import java2uml.metier.*;

import static java.awt.geom.AffineTransform.*;
import java.awt.geom.AffineTransform;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

public class PanelPrc extends JPanel {
	
	private ArrayList<PanelEntite> ensPanelEntite = new ArrayList<>();
	private ArrayList<Coord> ensCoord = new ArrayList<>();
	private ArrayList<Association> ensAssociation = new ArrayList<>();
	private FramePrc framePrincipale;
	
	//press action
	static int posSourisX;
	static int posSourisY;
	
	private final int ARR_SIZE = 7;
	Thread worker;
		
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
		
		double centerx, centery, x , y , angle, degreInc;//coord de panel entite 
        centerx=framePrincipale.getWidth()/2;
        centery=framePrincipale.getHeight()/3;
        degreInc = 360/this.ensPanelEntite.size();
        double hauteurMax = framePrincipale.getHeight()/3;
        System.out.println(hauteurMax);
        
        angle = 0;
        x = Math.cos(angle)*hauteurMax + centerx;
        y = Math.sin(angle)*hauteurMax + centery;
        
        for(PanelEntite pe : ensPanelEntite){
            
            pe.setSize(pe.getPreferredSize());
            
            ensCoord.add(new Coord((int)Math.round(x),(int)Math.round(y)));
            
            pe.setLocation(ensCoord.get(pe.getId()).getX(),ensCoord.get(pe.getId()).getY());
            
            this.add(pe);
            
            angle += degreInc;
            x = (Math.cos(Math.toRadians(angle))*hauteurMax) + centerx;
            y = (Math.sin(Math.toRadians(angle))*hauteurMax) + centery;
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
			int ident = (((PanelEntite) e.getSource()).getId());
			int offsetx = e.getX() - this.ensCoord.get(ident).getX();
			int offsety = e.getY() - this.ensCoord.get(ident).getY();
			worker = new Worker(ident, (PanelEntite)e.getSource(), this.ensCoord, this.framePrincipale, offsetx, offsety);
			worker.start();
		}
		
	}
	

	public void release(MouseEvent e)
	{
		 if (worker != null) {
	            worker.interrupt();
	            worker = null;
		 }
	}
	
	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2, String type)
	{
		Graphics2D g = (Graphics2D) g1.create();
		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx*dx + dy*dy);
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g.transform(at);
		//Draw horizontal arrow starting in (0, 0)
		g.drawLine(0, 0, len, 0);
		if(type == "unidirectionnelle")
		{	
			g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
			new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
		}
		if(type == "composition")
		{	
			g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
			new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
			g.fillPolygon(new int[] {len-2*ARR_SIZE, len-ARR_SIZE, len-ARR_SIZE, len-2*ARR_SIZE},
			new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
		}
		if(type == "agrégation")
		{	
			g.setColor(Color.GRAY);
			g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
			new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
			g.fillPolygon(new int[] {len-2*ARR_SIZE, len-ARR_SIZE, len-ARR_SIZE, len-2*ARR_SIZE},
			new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
		}
		if(type == "généralisation/spécialisation")
		{
			g.setColor(Color.GRAY);
			g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
			new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		for(PanelEntite pe : ensPanelEntite) { pe.setEnsCoord(); }
		for(Association a: this.ensAssociation)
		{
			ArrayList<Coord> ensCoordGauche = new ArrayList<>();
			ArrayList<Coord> ensCoordDroite = new ArrayList<>();
			
				for(int i = 0; i < this.ensPanelEntite.size(); i++)
				{					
					if(this.ensPanelEntite.get(i).getNom().equals(a.getClasseGauche()))
					{
						ensCoordGauche = this.ensPanelEntite.get(i).getEnsCoord();
					}
					if(this.ensPanelEntite.get(i).getNom().equals(a.getClasseDroite()))
					{
						ensCoordDroite = this.ensPanelEntite.get(i).getEnsCoord();
					}
					
				}
				double xGauche = 0, yGauche = 0, xDroite = 0, yDroite = 0;
				
				
				xGauche = ensCoordGauche.get(0).getX();
				yGauche = ensCoordGauche.get(0).getY();
				
				xDroite = ensCoordDroite.get(0).getX();
				yDroite = ensCoordDroite.get(0).getY();
				
				double xTmpGauche = 0;
				double xTmpDroite = 0;
				double yTmpGauche = 0;
				double yTmpDroite = 0;
				double tmpLongueur = 0;
				
				
				double longueur = Math.sqrt( Math.pow(xGauche - xDroite, 2) + Math.pow(yGauche - yDroite, 2) );
				
				for( int cpt = 0; cpt < ensCoordGauche.size(); cpt++ ) {
					xTmpGauche = ensCoordGauche.get(cpt).getX();
					yTmpGauche = ensCoordGauche.get(cpt).getY();
					for( int tmp = 0; tmp < ensCoordDroite.size(); tmp++ ) {
						xTmpDroite = ensCoordDroite.get(tmp).getX();
						yTmpDroite = ensCoordDroite.get(tmp).getY();
						tmpLongueur = Math.sqrt( Math.pow(xTmpGauche - xTmpDroite, 2) + Math.pow(yTmpGauche - yTmpDroite, 2) );
						if( tmpLongueur < longueur ) {
							xGauche = xTmpGauche;
							xDroite = xTmpDroite;
							yGauche = yTmpGauche;
							yDroite = yTmpDroite;
							longueur = tmpLongueur;
						}
					}
				}
				String multGauche;
				String multDroite;
				//multGauche = a.getMultipliciteGauche().replace("[", "");
				//multGauche = multGauche.replace("]", "");
				//multDroite = a.getMultipliciteDroite().replace("[", "");
				//multDroite = multDroite.replace("]", "");
				multGauche = "0..0";
				multDroite = "1..1";
				/*System.out.println(a.getMultipliciteDroite());
				System.out.println(a.getTypeAssociation());*/
				drawArrow(g, (int)Math.round(xGauche) , (int)Math.round(yGauche), (int)Math.round(xDroite),(int)Math.round(yDroite), a.getTypeAssociation());	
				if((int)xGauche >  (int)xDroite)
				{
					if((int)yGauche <  (int)yDroite)		
					{
						g.drawString(multDroite, (int)xDroite+20, (int)yDroite-15);
						g.drawString(multGauche, (int)xGauche-20, (int)yGauche+15);	
					}
					else
					{
						g.drawString(multDroite, (int)xDroite+20, (int)yDroite+15);
						g.drawString(multGauche, (int)xGauche-20, (int)yGauche-15);	
					}			
					
				}
				else
				{
					if((int)yGauche <  (int)yDroite)		
					{
						g.drawString(multDroite, (int)xDroite-20, (int)yDroite-15);
						g.drawString(multGauche, (int)xGauche+20, (int)yGauche+15);	
					}
					else
					{
						g.drawString(multDroite, (int)xDroite-20, (int)yDroite+15);
						g.drawString(multGauche, (int)xGauche+20, (int)yGauche-15);	
					}			
					
				}
				
		}
	}
	
	class Worker extends Thread {
	    int n=0;
	    int ident;
	    int offsetx;
	    int offsety;
	    PanelEntite pe;
	    FramePrc framePrincipale;
	    ArrayList<Coord> ensCoord = new ArrayList<>();
	    
	    public Worker(int ident, PanelEntite pe, ArrayList<Coord> ensCoord, FramePrc framePrincipale, int offsetx, int offsety) {
	    	this.ident = ident;
	    	this.pe = pe;
	    	this.ensCoord = ensCoord;
	    	this.framePrincipale = framePrincipale;
	    	this.offsetx = offsetx;
	    	this.offsety = offsety;
	    }
	    
	    public void run() {
	      while(true) {
	        PointerInfo a = MouseInfo.getPointerInfo();
	        Point b = a.getLocation();

	        int x = (int) b.getX() - (int)framePrincipale.getLocation().getX() - framePrincipale.getInsets().left + offsetx;
	        int y = (int) b.getY() - (int)framePrincipale.getLocation().getY() - framePrincipale.getInsets().top + offsety;
	        
	        this.ensCoord.get(ident).setX(x);
			this.ensCoord.get(ident).setY(y);
			
			
			pe.setLocation(this.ensCoord.get(ident).getX(),this.ensCoord.get(ident).getY());
			repaint();
	        
	        if (isInterrupted())
	          break;
	      }
	    }
	  };
}
	
