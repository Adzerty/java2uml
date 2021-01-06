package java2uml.IHM.GUI;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarPrc extends JMenuBar implements ActionListener{
	
	JMenu option;
	JMenuItem sauvegarder;
	PanelPrc panelPrc;
	
	public MenuBarPrc( PanelPrc panelPrc) {
		this.panelPrc = panelPrc;
		this.option = new JMenu("option");
		this.sauvegarder = new JMenuItem("sauvegarder");

		this.sauvegarder.addActionListener(this);
		this.option.add(this.sauvegarder);
		this.add(option);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == this.sauvegarder) {
			BufferedImage image = new BufferedImage(this.panelPrc.getWidth(),this.panelPrc.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			this.panelPrc.paint(g2);
			try{
				ImageIO.write(image, "png",new File("../diagrammes/pdf/" + panelPrc.getFileName() + ".png"));
			} catch (Exception io) {
				io.printStackTrace();
			}
		}
	}
}
