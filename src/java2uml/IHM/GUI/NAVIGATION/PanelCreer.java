package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCreer extends JPanel implements ItemListener{

	private FrameCreer frame;
	private String[] tabFichier;
	private JLabel lblFichier;
	private JComboBox cbFichier;

	public PanelCreer(FrameCreer frame) {
		
		this.frame = frame;
		
		this.setLayout(new GridLayout(1,2));
		
		File fichier = new File("../fichierJava");
		FilenameFilter filter = new FilenameFilter() {
			
	        @Override
	        public boolean accept(File f, String name) {
	            return name.endsWith(".java");
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
		// TODO Auto-generated method stub
		if( e.getItem() == this.cbFichier.getSelectedItem()) {
			this.frame.setFichier((String)this.cbFichier.getSelectedItem());
		}
		
	}
}
