package java2uml.IHM.GUI.NAVIGATION;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java2uml.Controleur;


public class FrameAccueil extends JFrame{
	
	private PanelAccueil panelAccueil;
	private PanelCreeDiag panelCreeDiag;
	private PanelChargMod panelChargMod;

	private Controleur ctrl;
	
	public FrameAccueil(Controleur ctrl) {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.ctrl = ctrl;

        int x = dim.width/2;
        int y = dim.height/2;
        
  
		this.setTitle("Java2UML");
		this.setLocation(x, y);
		this.setSize(600,500);
		this.setLayout(new GridLayout(3,1));
		
		this.panelAccueil = new PanelAccueil();
		this.panelCreeDiag = new PanelCreeDiag(this,this.ctrl);
		this.panelChargMod = new PanelChargMod(this,this.ctrl);
				
		this.add(this.panelAccueil);
		this.add(this.panelCreeDiag);
		this.add(this.panelChargMod);
		
		this.setJMenuBar(new MenuBar());
		
		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
}
