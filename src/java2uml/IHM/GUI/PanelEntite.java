package java2uml.IHM.GUI;

import java2uml.metier.*;



import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelEntite extends JPanel implements MouseListener  {
	
	private Entite entite;
	private PanelAttribut att;
	private PanelMethode met;
	private PanelPrc panelPrincipal;
	private int identifiant;
	private String nom;
	private ArrayList<Coord> ensCoord = new ArrayList<>();
	
	
	public PanelEntite(Entite e, PanelPrc panelPrincipal, int identifiant){
		
		this.entite = e;
		this.nom = e.getNom();
		this.panelPrincipal = panelPrincipal;
		this.identifiant = identifiant;
		this.att = new PanelAttribut(this.entite.getEnsAttribut());
		this.met = new PanelMethode(this.entite.getEnsMethode());
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pNom    = new JPanel();
		JPanel pCentre = new JPanel();
		if( e.getEnsAttribut().size() == 0 || e.getEnsMethode().size() == 0 ) {
			pCentre.setLayout(new GridLayout(1,1));
			if( e.getEnsAttribut().size() != 0 )      pCentre.add(att);
			else if(e.getEnsMethode().size() != 0 ) pCentre.add(met);
		}
		else {
			pCentre.setLayout(new GridLayout(2,1));
			pCentre.add(att);
			pCentre.add(met);
		}
		
		JLabel nom = new JLabel(this.entite.getType()+" : "+this.entite.getNom(), JLabel.CENTER);
		
		pNom.add(nom);
		
		this.add(pNom, "North");
		this.add(pCentre, "Center");
		
		
		this.addMouseListener(this);
		this.setVisible(true);		
	}
	
	public void setEnsCoord() {
		this.ensCoord = new ArrayList<>();
		this.ensCoord.add(new Coord(this.getX(), this.getY(), false));
		this.ensCoord.add(new Coord(this.getX()+(this.getWidth()/2), this.getY(), false));
		this.ensCoord.add(new Coord(this.getX()+this.getWidth(), this.getY(), false));
		this.ensCoord.add(new Coord(this.getX()+this.getWidth(), this.getY()+(this.getHeight()/2), false));
		this.ensCoord.add(new Coord(this.getX()+this.getWidth(), this.getY()+this.getHeight(), false));
		this.ensCoord.add(new Coord(this.getX()+(this.getWidth()/2), this.getY()+this.getHeight(), false));
		this.ensCoord.add(new Coord(this.getX(), this.getY()+this.getHeight(), false));
		this.ensCoord.add(new Coord(this.getX(), this.getY()+(this.getHeight()/2), false));
	}
	public String getNom()
	{
		return this.nom;
	}
	public int getId()
	{
		return this.identifiant;
	}
	
	public ArrayList<Coord> getEnsCoord() {
		return this.ensCoord;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		panelPrincipal.press(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		panelPrincipal.release(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

