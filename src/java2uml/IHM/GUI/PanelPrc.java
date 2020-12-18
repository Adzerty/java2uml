package java2uml.IHM.GUI;

import java2uml.metier.*;

import static java.awt.geom.AffineTransform.*;
import java.awt.geom.AffineTransform;
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
	
	private final int ARR_SIZE = 7;
		
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
            System.out.println("angle : " + angle+ "   cos : " + Math.cos(angle) + "   sin : "+ Math.sin(angle));
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
		if(type == "unidirectionnele")
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
		
		int x1, y1, x2, y2;
		x1 = y1 = x2 = y2 =0;
		
		for(Association a: this.ensAssociation)
		{
				for(int i = 0; i < this.ensPanelEntite.size(); i++)
				{					
					if(this.ensPanelEntite.get(i).getNom().equals(a.getClasseGauche()))
					{
						x1 = this.ensCoord.get(i).getX()+this.ensPanelEntite.get(i).getWidth();
						y1 = (this.ensCoord.get(i).getY()+(this.ensPanelEntite.get(i).getHeight()/2));	
					}
					if(this.ensPanelEntite.get(i).getNom().equals(a.getClasseDroite()))
					{
						x2 = this.ensCoord.get(i).getX();
						y2 = (this.ensCoord.get(i).getY()+(this.ensPanelEntite.get(i).getHeight()/2));
					}
					
				}			
				System.out.println(a.getTypeAssociation());
				drawArrow(g,x1 , y1, x2,y2, a.getTypeAssociation());
			 /*////////////////////////////////////////////////////////////:
			  * 															/
			  * 					g.drawString(texte, x, y );				/
			  *									pour plus tard				/
			  //////////////////////////////////////////////////////////////*/
		}
	}
}
	
