package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java2uml.Controleur;

public class FrameCreer extends JFrame
{
	
	private PanelCreer panelCreer;
	private PanelCreerValider panelValider;
	private String[] nomFichier;
	
	private Controleur ctrl;
	
	public FrameCreer(Controleur ctrl)
	{
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.ctrl = ctrl;

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Créer un fichier");  //Affichage du titre
		this.setLocation(x,y);              //Placement de la frame au centre de l'ecran
		this.setLayout(new GridLayout(2,1));//Gridlayout de 2 par 1
		
		//creation des differents panel
		
		this.panelCreer = new PanelCreer(this);
		this.panelValider = new PanelCreerValider(this);
		
		//ajout des differents panel
		
		this.add(this.panelCreer);
		this.add(this.panelValider);
		 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();                     //Taille de la frame
		this.setVisible(true);
	}
	
	//methode recuperant un tableau des fichiers a incorporer dans un .txt
	
	public void setFichier(String[] fichier)
	{
		this.nomFichier = fichier;
	}
	
	//mathode qui genere un .txt suite a la validation de l'utilisateur 
	
	public void setValider()
	{
		this.ctrl.creerNouvDiagramme(this.nomFichier);
		this.ctrl.creerNouvConfig(this.panelCreer.getFichier() , this.panelCreer.getUtil());
		this.dispose();
	}
}
