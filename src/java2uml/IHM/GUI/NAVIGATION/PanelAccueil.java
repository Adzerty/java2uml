package java2uml.IHM.GUI.NAVIGATION;

import java.awt.BorderLayout;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelAccueil extends JPanel
{
	
	private JLabel titre;
	
	public PanelAccueil()
	{
		this.setLayout(new BorderLayout());
		
		this.titre = new JLabel("JAVA 2 UML");                     //Affichage du titre
		this.titre.setFont(new Font( "Verdana" ,Font.BOLD, 35));   //Changement de la police 
		this.titre.setHorizontalAlignment(SwingConstants.CENTER);  //Aligmement du texte au centre 
	
		this.add(this.titre,BorderLayout.CENTER);
		this.setVisible(true);
	}

}
