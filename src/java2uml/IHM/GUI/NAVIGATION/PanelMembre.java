package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMembre  extends JPanel
{
	
	private Membre membre;
	private int num;
	
	private JLabel prenom;
	private JLabel nom;
	private JLabel titre;
	private JLabel description;
	private JLabel photo;
	
	private JPanel civilite;
	private JPanel presentation;
	private JPanel image;
	
	public PanelMembre(Membre membre , int num)
	{
		this.membre = membre;
		this.num = num;
		
		if(this.num == 0)
		{
			String titre1 = "<html><p color=\"rgb(51,102,152)\">Innov<span color=\"rgb(223,90,39)\">Action</span></p></html>";
			this.setLayout(new GridLayout(1,1));
			this.titre = new JLabel(titre1);
			this.titre.setFont(new Font( "Verdana" ,Font.BOLD, 35));
			this.titre.setHorizontalAlignment(SwingConstants.CENTER); 
			this.add(this.titre);
		}
		else
		{
			this.setLayout(new GridLayout(1,2));
			
			this.image = new JPanel();
			ImageIcon icon = new ImageIcon(membre.getImage());
			this.photo = new JLabel(icon);
			
			this.image.add(this.photo);
			
			this.presentation = new JPanel();
			this.presentation.setLayout(new GridLayout(2,1));
		
			this.prenom = new JLabel(this.membre.getPrenom());
			this.prenom.setFont(new Font( "Verdana" ,Font.BOLD, 20));
			
			this.nom = new JLabel(this.membre.getNom());
			this.nom.setFont(new Font( "Verdana" ,Font.BOLD, 20));
			
			this.civilite = new JPanel();
			
			this.civilite.add(this.prenom);
			this.civilite.add(this.nom);
			
			this.description = new JLabel(this.membre.getDescription());
			this.description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
			
			this.presentation.add(this.civilite);
			this.presentation.add(this.description);
			

			this.add(this.presentation);
			this.add(this.image);
			
		}

	}

}
