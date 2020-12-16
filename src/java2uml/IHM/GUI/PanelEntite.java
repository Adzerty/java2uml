package java2uml.IHM.GUI;

import java2uml.metier.*;



import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelEntite extends JPanel implements MouseListener  {
	
	private Entite entite;
	private PanelAttribut att;
	private PanelMethode met;
	private PanelPrc panelPrincipal;
	private int identifiant;
	
	
	
	public PanelEntite(Entite e, PanelPrc panelPrincipal, int identifiant){
		
		this.entite = e;
		this.panelPrincipal = panelPrincipal;
		this.identifiant = identifiant;
		this.att = new PanelAttribut(this.entite.getEnsAttribut());
		this.met = new PanelMethode(this.entite.getEnsMethode());
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pNom    = new JPanel();
		JPanel pCentre = new JPanel();
		
		pCentre.setLayout(new GridLayout(2,1));
		pCentre.add(att);
		pCentre.add(met);
		
		JLabel nom = new JLabel(this.entite.getType()+" : "+this.entite.getNom(), JLabel.CENTER);
		
		pNom.add(nom);
		
		this.add(pNom, "North");
		this.add(pCentre, "Center");
		
		
		this.addMouseListener(this);
		this.setVisible(true);		
	}
	
	public int getId()
	{
		return this.identifiant;
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

