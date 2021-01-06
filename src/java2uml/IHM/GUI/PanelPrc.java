package java2uml.IHM.GUI;

import java2uml.metier.*;

import static java.awt.geom.AffineTransform.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelPrc extends JPanel implements ActionListener {

	private ArrayList<PanelEntite> ensPanelEntite = new ArrayList<>();
	private ArrayList<Coord> ensCoord = new ArrayList<>();
	private ArrayList<Association> ensAssociation = new ArrayList<>();
	private FramePrc framePrincipale;
	private JButton buttonSave = new JButton("sauvegarder");
	private int heightBar;
	private int widthBar;
	//press action

	private final int ARR_SIZE = 7;
	private String fileName;
	Thread worker;

	public PanelPrc(ArrayList<Entite> ensEntite , FramePrc framePrincipale, String name) {

		this.setLayout(null);
		this.framePrincipale = framePrincipale;
		int identifiant = 0;
		this.fileName = "";
		for( int cpt = 0; cpt < name.length() && name.charAt(cpt) != '.'; cpt++)
			fileName += name.charAt(cpt);

		for(Entite e : ensEntite) {

			for(Association a: e.getEnsAssociations())
			{
				ensAssociation.add(a);
			}

			this.ensPanelEntite.add(new PanelEntite(e,this,identifiant));
			identifiant++;

		}

		double centerx, centery, x , y , angle, degreInc;//coord de panel entite 
		centerx=framePrincipale.getWidth()/2;
		centery=framePrincipale.getHeight()/3;
		degreInc = 360/this.ensPanelEntite.size();
		double hauteurMax = framePrincipale.getHeight()/3;

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
		this.buttonSave.setSize(this.buttonSave.getPreferredSize());
		this.buttonSave.addActionListener( this );
		this.add(this.buttonSave);
		this.setVisible(true);
		this.repaint();
	}

	public void press(MouseEvent e)
	{
		if(e.getSource() instanceof PanelEntite)
		{
			int ident = (((PanelEntite) e.getSource()).getId());
			int offsetx = e.getX();
			int offsety = e.getY();
			worker = new Worker(ident, (PanelEntite)e.getSource(), this.ensCoord, this.framePrincipale, offsetx, offsety);
			worker.start();
		}

	}
	
	public String getFileName() { return this.fileName;}


	public void release(MouseEvent e)
	{
		if (worker != null) {
			worker.interrupt();
			worker = null;
		}
	}

	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2, String type, Association a)
	{
		Graphics2D g = (Graphics2D) g1.create();
		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx*dx + dy*dy);
		if( a.getClasseGauche().equals(a.getClasseDroite())) {
			PanelEntite pe = null;
			for( PanelEntite p : ensPanelEntite) if( p.getNom().equals(a.getClasseGauche())) pe = p;

			Coord tmp[] = pe.reflexive();
			g.drawLine(tmp[0].getX(), tmp[0].getY(), tmp[1].getX(), tmp[1].getY());
			g.drawLine(tmp[1].getX(), tmp[1].getY(), tmp[2].getX(), tmp[2].getY());
			g.drawLine(tmp[2].getX(), tmp[2].getY(), tmp[3].getX(), tmp[3].getY());
			
			g.drawString(a.getMultipliciteDroite(), tmp[4].getX(), tmp[4].getY());
			g.drawString(a.getMultipliciteGauche(), tmp[5].getX(), tmp[5].getY());	
		}
		else {
			AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
			at.concatenate(AffineTransform.getRotateInstance(angle));
			g.transform(at);

			g.drawLine(0, 0, len, 0);
			if(type == "unidirectionnelle")
			{	
				g.drawLine(len, 0, len+((int)(Math.round(Math.cos(Math.toRadians(135))))*10), ((int)(Math.round(Math.sin(Math.toRadians(135))))*10));
				g.drawLine(len, 0, len+((int)(Math.round(Math.cos(Math.toRadians(135))))*10), ((int)(Math.round(Math.sin(Math.toRadians(225))))*10));
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
			String multGauche = a.getMultipliciteGauche();
			String multDroite = a.getMultipliciteDroite();

			drawArrow(g, (int)Math.round(xGauche) , (int)Math.round(yGauche), (int)Math.round(xDroite),(int)Math.round(yDroite), a.getTypeAssociation(),a);	
			if( !a.getClasseGauche().equals(a.getClasseDroite())) {
				if((int)xGauche >  (int)xDroite)
				{
					if((int)yGauche <  (int)yDroite)		
					{
						g.drawString(multDroite, (int)xDroite+20, (int)yDroite-15);
						g.drawString(multGauche, (int)xGauche-30, (int)yGauche+15);	
					}
					else
					{
						g.drawString(multDroite, (int)xDroite+20, (int)yDroite+15);
						g.drawString(multGauche, (int)xGauche-30, (int)yGauche-15);	
					}			

				}
				else
				{
					if((int)yGauche <  (int)yDroite)		
					{
						g.drawString(multDroite, (int)xDroite-30, (int)yDroite-15);
						g.drawString(multGauche, (int)xGauche+20, (int)yGauche+15);	
					}
					else
					{
						g.drawString(multDroite, (int)xDroite-30, (int)yDroite+15);
						g.drawString(multGauche, (int)xGauche+20, (int)yGauche-15);	
					}			

				}
			}
			this.widthBar = framePrincipale.getInsets().left;
			this.heightBar = framePrincipale.getInsets().top;
			this.buttonSave.setLocation(framePrincipale.getWidth()-this.buttonSave.getWidth()-15-widthBar, framePrincipale.getHeight()-this.buttonSave.getHeight()-heightBar-15);
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

				int x = (int)b.getX() - (int)framePrincipale.getLocation().getX() - framePrincipale.getInsets().left - offsetx;
				int y = (int)b.getY() - (int)framePrincipale.getLocation().getY() - framePrincipale.getInsets().top - offsety;

				this.ensCoord.get(ident).setX(x);
				this.ensCoord.get(ident).setY(y);

				pe.setLocation(this.ensCoord.get(ident).getX(),this.ensCoord.get(ident).getY());
				repaint();

				if (isInterrupted())
					break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == this.buttonSave) {
			BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			paint(g2);
			try{
				ImageIO.write(image, "png",new File("./diagramme/blabla.png"));
			} catch (Exception io) {
				io.printStackTrace();
			}
		}
	};
}

