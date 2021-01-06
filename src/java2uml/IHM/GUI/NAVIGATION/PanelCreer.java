package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelCreer extends JPanel {

	private FrameCreer frame;
	private String[] tabFichier;
	private JLabel lblFichier;
	private JList listFichier;
	
	private JLabel lblNomFichier;
	private JLabel lblNomUtil;
	
	private JTextField textFichier;
	private JTextField textUtil;
	

	public PanelCreer(FrameCreer frame) {
		
		this.frame = frame;
		
		this.setLayout(new GridLayout(3,2));
		
		File fichier = new File("../fichierJava");
		FilenameFilter filter = new FilenameFilter() {
			
	        @Override
	        public boolean accept(File f, String name) {
	            return name.endsWith(".java");
	        }
	    };
		
	    this.tabFichier = fichier.list(filter);
	   
	    
	 
	    
		this.listFichier = new JList(this.tabFichier);
	    this.listFichier.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    
	    this.listFichier.addListSelectionListener( new ListSelectionListener() {
	    	
	    	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				List<String> list = listFichier.getSelectedValuesList();
				String[] array = list.toArray(new String[0]);
				frame.setFichier(array);
				
			}});
	    
	   

		this.lblFichier = new JLabel("Fichier");
		this.lblFichier.setFont(new Font( "Verdana" ,Font.BOLD, 17));
		
		this.lblNomFichier = new JLabel("Nom du Fichier");
		this.lblNomFichier.setFont(new Font( "Verdana" ,Font.BOLD, 17));
		
		this.textFichier = new JTextField(10);
		
		
		this.lblNomUtil = new JLabel("Proprietaire");
		this.lblNomUtil.setFont(new Font( "Verdana" ,Font.BOLD, 17));
		
		this.textUtil = new JTextField(10);
		
		
		
		this.add(lblFichier);
		this.add(listFichier);
		
		this.add(lblNomFichier);
		this.add(textFichier);
		
		
		this.add(lblNomUtil);
		this.add(textUtil);
	
		
		this.setVisible(true);
		
	}
	
	public String getUtil() {
		return this.textUtil.getText();
	}
	public String getFichier() {
		return this.textFichier.getText();
	}
	
	
}
