package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFrame;

import java2uml.IHM.GUI.FramePrc;
import java2uml.metier.ConfigReader;

public class FrameSupprimer extends JFrame {
	
	private PanelSupprimer panelSupprimer;
	private PanelValiderSupprimer panelValider;
	private String nomFichier;
	
	public FrameSupprimer() {
		

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Supprimer un fichier");
		this.setSize(300,100);
		this.setLocation(x,y);
		this.setLayout(new GridLayout(2,1));
		
		this.panelSupprimer = new PanelSupprimer(this);
		this.panelValider = new PanelValiderSupprimer(this);
		
		this.add(panelSupprimer);
		this.add(panelValider);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void setFichier(String fichier) {
		this.nomFichier = fichier;
	}

	public void setValider() {
		// TODO Auto-generated method stub
		
		
	}
	
}
