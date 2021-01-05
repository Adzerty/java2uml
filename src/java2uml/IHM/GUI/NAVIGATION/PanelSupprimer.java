package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSupprimer extends JPanel implements ItemListener{
	
	private JLabel lblFichier;
	private JComboBox cbFichier;
	private String[] tabFichier;
	
	private FrameSupprimer frame;
	
	public PanelSupprimer(FrameSupprimer frameSupprimer) {
		
		this.frame = frameSupprimer;
		
		this.setLayout(new GridLayout(1,2));
		
		File fichier = new File("../config");
		FilenameFilter filter = new FilenameFilter() {
			
	        @Override
	        public boolean accept(File f, String name) {
	            return name.endsWith(".config");
	        }
	    };
		
	    this.tabFichier = fichier.list(filter);
		
		
		this.cbFichier = new JComboBox(this.tabFichier);
		this.cbFichier.addItemListener(this);
		
		
		this.lblFichier = new JLabel("Fichier");
		this.lblFichier.setFont(new Font( "Verdana" ,Font.BOLD, 17));
		
		this.add(lblFichier);
		this.add(cbFichier);
		
		this.frame.setFichier((String)this.cbFichier.getSelectedItem());
		this.setVisible(true);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		
		if( e.getItem() == this.cbFichier.getSelectedItem()) {
			this.frame.setFichier((String)this.cbFichier.getSelectedItem());
		}
		
	}

}
