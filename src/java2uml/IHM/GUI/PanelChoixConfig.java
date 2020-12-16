package java2uml.IHM.GUI;

import java2uml.metier.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;

public class PanelChoixConfig extends JPanel implements ActionListener {

	private JComboBox listeConfig;
	private JButton btnValider;
	private String[] tabConfig;
	private JPanel tmp = new JPanel();
	
	public PanelChoixConfig() {
		this.setLayout( new BorderLayout());
		this.tmp.setLayout( new GridLayout(1, 2));
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
        this.tmp.add(listeConfig);
        
        //bouton valider
        this.btnValider = new JButton("valider");
        this.btnValider.addActionListener(this);
        this.tmp.add(btnValider);
        
        this.add( new JLabel("  "), "North");
        this.add( new JLabel("  "), "South");
        this.add( new JLabel("  "), "East");
        this.add( new JLabel("  "), "West");
        this.add(tmp, "Center");
        
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == this.btnValider ) { new FramePrc( new ConfigReader((String) this.listeConfig.getSelectedItem() ) ); }
	}
}
