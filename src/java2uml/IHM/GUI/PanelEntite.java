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
	private String contrainte = "";
	private String type;
	
	
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
		
		if(this.entite.getContraintes()!=null) for (String cont: this.entite.getContraintes()) contrainte += "{"+cont+"} ";
		
		this.entite.setType(this.entite.getType().replaceAll(" ", ""));
		
		this.type = this.entite.getType();

		if(this.type.equals("Enum"))      {this.type = "<<Enum>>";}
		if(this.type.equals("Interface")) {this.type = "<<Interface>>";}
		if(this.type.equals("Classe"))    {this.type = "";}
		
		JLabel type = new JLabel(this.type, JLabel.CENTER);
		JLabel nom = new JLabel(this.entite.getNom()+" "+contrainte, JLabel.CENTER);

		pNom.setLayout(new GridLayout(2,1));
		pNom.add(type);
		pNom.add(nom);
		
		this.add(pNom, "North");
		this.add(pCentre, "Center");
		
		
		this.addMouseListener(this);
		this.setVisible(true);		
	}
	
	public void setEnsCoord() {
		this.ensCoord = new ArrayList<>();
		this.ensCoord.add(new Coord(this.getX(), this.getY()));
		this.ensCoord.add(new Coord(this.getX()+(this.getWidth()/2), this.getY()));
		this.ensCoord.add(new Coord(this.getX()+this.getWidth(), this.getY()));
		this.ensCoord.add(new Coord(this.getX()+this.getWidth(), this.getY()+(this.getHeight()/2)));
		this.ensCoord.add(new Coord(this.getX()+this.getWidth(), this.getY()+this.getHeight()));
		this.ensCoord.add(new Coord(this.getX()+(this.getWidth()/2), this.getY()+this.getHeight()));
		this.ensCoord.add(new Coord(this.getX(), this.getY()+this.getHeight()));
		this.ensCoord.add(new Coord(this.getX(), this.getY()+(this.getHeight()/2)));
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
	
	public Coord[] reflexive()
	{
		setEnsCoord();
		Coord[] tab = new Coord[6];
		tab[0] = new Coord(this.getX(), this.getY()+this.getHeight());
		tab[1] = new Coord(this.getX(), this.getY()+this.getHeight()+30);
		tab[2] = new Coord(this.getX()+(this.getWidth()/2), this.getY()+this.getHeight()+30);
		tab[3] = new Coord(this.getX()+(this.getWidth()/2), this.getY()+this.getHeight());
		tab[4] = new Coord(tab[0].getX()+5, tab[0].getY()+15);
		tab[5] = new Coord(tab[3].getX()-30, tab[3].getY()+15);
		return tab;
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

