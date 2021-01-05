package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java2uml.Controleur;

public class FrameCreer extends JFrame{
	
	private PanelCreer panelCreer;
	private PanelCreerValider panelValider;
	private String[] nomFichier;
	private String nom;
	private String titre;
	
	private Controleur ctrl;
	
	public FrameCreer(Controleur ctrl) {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.ctrl = ctrl;

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Creer un fichier");
		//this.setSize(300,200);
		this.setLocation(x,y);
		this.setLayout(new GridLayout(2,1));
		
		this.panelCreer = new PanelCreer(this);
		this.panelValider = new PanelCreerValider(this);
		
		this.add(this.panelCreer);
		this.add(this.panelValider);
		 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void setFichier(String[] fichier) {
		// TODO Auto-generated method stub
		this.nomFichier = fichier;
		
	}
	
	public void setValider() {
		// TODO Auto-generated method stub
		
		this.ctrl.creerNouvDiagramme(this.nomFichier);
		this.ctrl.creerNouvConfig(this.panelCreer.getFichier() , this.panelCreer.getUtil());
	}
}
