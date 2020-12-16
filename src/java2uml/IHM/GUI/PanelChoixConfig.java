package java2uml.IHM.GUI;

import java2uml.metier.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.*;

public class PanelChoixConfig extends JPanel implements ActionListener, ItemListener {

	private JComboBox listeConfig;
	private JButton btnValider;
	private String[] tabConfig;
	private BufferedImage image;
	private JLabel labelImage = new JLabel(" ");
	private FrameChoixConfig fcc;
	
	public PanelChoixConfig( FrameChoixConfig fcc ) {
		this.setLayout( new BorderLayout());
		this.fcc = fcc;
		//gestion des fichiers .config
		File f = new File("./config");
		FilenameFilter filter = new FilenameFilter() {
	        @Override
	        public boolean accept(File f, String name) {
	            return name.endsWith(".config");
	        }
	    };
        tabConfig = f.list(filter);
        listeConfig = new JComboBox(tabConfig);
        listeConfig.addItemListener(this);
        
        //bouton valider
        this.btnValider = new JButton("valider");
        this.btnValider.addActionListener(this);
        
        this.add( new JLabel("  "), "North");
        this.add( new JLabel("  "), "South");
        this.add( this.btnValider, "East");
        this.add(this.listeConfig, "West");
        this.add(this.labelImage, "Center");
        
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == this.btnValider ) { new FramePrc( new ConfigReader((String) this.listeConfig.getSelectedItem() ) ); this.fcc.reduceFrame(); }
	}
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		String name = "";
		if( e.getItem() == this.listeConfig.getSelectedItem() ) {
			for( int cpt = 0; ((String)this.listeConfig.getSelectedItem()).charAt(cpt) != '.'; cpt++ )
				name +=  ((String)this.listeConfig.getSelectedItem()).charAt(cpt);
			System.out.println(name);
			try {
				image = ImageIO.read( new File("./diagramme/"+name+".pdf"));
				this.remove(labelImage);
				this.labelImage = new JLabel(new ImageIcon(image));
				this.add( labelImage, "Center");
				this.validate();
				this.repaint();
			} catch (IOException ex) {}
		}
	}
}
