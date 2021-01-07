package java2uml.IHM.GUI.NAVIGATION;

import java.awt.BorderLayout;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelAccueil extends JPanel
{
	
	private JLabel photo;
	private JPanel image;
	
	public PanelAccueil()
	{
		this.setLayout(new BorderLayout());
		
		this.image = new JPanel();
		ImageIcon icon = new ImageIcon("../image/logo.png");
		this.photo = new JLabel(icon);
		
		this.image.add(this.photo);

		this.add(this.image,BorderLayout.CENTER);
		this.setVisible(true);
	}

}
