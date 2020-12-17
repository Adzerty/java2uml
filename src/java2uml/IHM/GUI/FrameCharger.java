package java2uml.IHM.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java2uml.metier.ConfigReader;

public class FrameCharger extends JFrame{
	
	
	private PanelCharger panelCharger;
	private PanelValiderCharger panelValider;
	private String nomFichier;
	
	public FrameCharger() {
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Charger un fichier");
		this.setSize(300,100);
		this.setLocation(x,y);
		this.setLayout(new GridLayout(2,1));
		
		this.panelCharger = new PanelCharger(this);
		this.panelValider = new PanelValiderCharger(this);
		
		this.add(panelCharger);
		this.add(panelValider);
		
		//this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void setFichier(String fichier) {
		this.nomFichier = fichier;
		
	}

	public void setValider() {
		// TODO Auto-generated method stub
		new FramePrc(new ConfigReader(this.nomFichier));
	}

	
}
