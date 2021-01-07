package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

import java2uml.Controleur;

public class FrameSupprimer extends JFrame
{
	
	private PanelSupprimer panelSupprimer;
	private PanelValiderSupprimer panelValider;
	private String nomFichier;
	
	private Controleur ctrl;
	
	public FrameSupprimer(Controleur ctrl)
	{
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.ctrl = ctrl;

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Supprimer un fichier"); //Affichage du titre
		this.setSize(300,100);                 // taille de la frame
		this.setLocation(x,y);                 //placement de la frame au centre de l'ecran
		this.setLayout(new GridLayout(2,1));   // gridlayout de 2 par 1
		
		//creation des differents panel
		
		this.panelSupprimer = new PanelSupprimer(this);
		this.panelValider = new PanelValiderSupprimer(this);
		
		//ajout des differents panel
		
		this.add(panelSupprimer);
		this.add(panelValider);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	//methode recuperant un fichier .txt
	
	public void setFichier(String fichier)
	{
		this.nomFichier = fichier;
	}
	
	//methode permettant de supprimer le fichier selectionné

	public void setValider()
	{
		String tabFichierSup[] =  new String[1];
		tabFichierSup[0] = this.nomFichier;
		this.ctrl.supprimerFichiers(tabFichierSup);
		this.dispose();
	}
	
}
