package java2uml.IHM.GUI;

import java2uml.metier.*;

import java.awt.*;
import javax.swing.*;

public class FrameChoixConfig extends JFrame {

	private PanelChoixConfig pcc;
	
	public FrameChoixConfig() {
		this.setTitle("Java2UML");
		this.setSize(1200, 700);
		this.setLocation(100, 100);
		
		this.pcc = new PanelChoixConfig( this );
		this.add(this.pcc);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public void reduceFrame() { this.setState(Frame.ICONIFIED); }
}
