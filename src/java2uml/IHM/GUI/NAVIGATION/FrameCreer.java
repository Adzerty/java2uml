package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameCreer extends JFrame{
	
	private PanelCreer panelCreer;
	private PanelCreerValider panelValider;
	
	public FrameCreer() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = dim.width/2;
        int y = dim.height/2;
		
		this.setTitle("Creer un fichier");
		this.setSize(300,100);
		this.setLocation(x,y);
		this.setLayout(new GridLayout(2,1));
		
		this.panelCreer = new PanelCreer(this);
		this.panelValider = new PanelCreerValider(this);
		
		this.add(this.panelCreer);
		this.add(this.panelValider);
		 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void setFichier(String selectedItem) {
		// TODO Auto-generated method stub
		
	}
}
