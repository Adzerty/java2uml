package java2uml.IHM.GUI;

import java2uml.metier.*;



import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelEntite extends JPanel  {
	
	private Entite entite;
	private PanelAttribut att;
	private PanelMethode met;
	private PanelPrc panelPrincipal;
	public PanelEntite(Entite e, PanelPrc panelPrincipal){
		
		this.entite = e;
		this.panelPrincipal = panelPrincipal;
		this.att = new PanelAttribut(this.entite.getEnsAttribut());
		this.met = new PanelMethode(this.entite.getEnsMethode());
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pNom    = new JPanel();
		JPanel pCentre = new JPanel();
		
		if(this.entite.getEnsAttribut() == null || this.entite.getEnsMethode() == null)
		{
			if(this.entite.getEnsAttribut() == null && this.entite.getEnsMethode() == null)
			{
				this.add(pCentre);
			}
			
			else if(this.entite.getEnsAttribut() == null)
			{
				pCentre.setLayout(new BorderLayout());
				pCentre.add(att, "North");
				pCentre.add(met, "Center");
			}
			
			else if(this.entite.getEnsAttribut() == null)
			{
				pCentre.setLayout(new BorderLayout());
				pCentre.add(att, "Center");
				pCentre.add(met, "South");
			}
		}
		
		else
		{
			pCentre.setLayout(new GridLayout(2,1));
			pCentre.add(att);
			pCentre.add(met);
		}
		
		JLabel nom = new JLabel(this.entite.getType()+" : "+this.entite.getNom(), JLabel.CENTER);
		
		pNom.add(nom);
		
		this.add(pNom, "North");
		this.add(pCentre, "Center");
		
		
		
		this.setVisible(true);		
	}
	

}
