package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelCreer extends JPanel 
{

	private String[] tabFichier;
	private JLabel lblFichier;
	private JList listFichier;
	
	private JLabel lblNomFichier;
	private JLabel lblNomUtil;
	
	private JTextField textFichier;
	private JTextField textUtil;
	

	public PanelCreer(FrameCreer frame)
	{
		
		this.setLayout(new GridLayout(3,2));
		
		// recherche des .java existant 
		
		File fichier = new File("../fichierJava");
		FilenameFilter filter = new FilenameFilter()
		{
			
	        @Override
	        public boolean accept(File f, String name)
	        {
	            return name.endsWith(".java");
	        }
	    };
		
	    this.tabFichier = fichier.list(filter);
	   
	    // creation de la JList
	    
		this.listFichier = new JList(this.tabFichier);
	    this.listFichier.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    
	    this.listFichier.addListSelectionListener( new ListSelectionListener()
	    {
	    	
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				// TODO Auto-generated method stub
				List<String> list = listFichier.getSelectedValuesList();
				String[] array = list.toArray(new String[0]);
				frame.setFichier(array);
				
			}
		});
	    
	   
	    // creation du label fichier
		this.lblFichier = new JLabel("Fichier");
		this.lblFichier.setFont(new Font( "Verdana" ,Font.BOLD, 17));
		
		//creation du label nom de fichier
		this.lblNomFichier = new JLabel("Nom du Fichier");
		this.lblNomFichier.setFont(new Font( "Verdana" ,Font.BOLD, 17));
		
		this.textFichier = new JTextField(10);
		
		//creation du label proprietaire
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
	
	//methode permettant de recuperer le nom de l'utilisateur
	public String getUtil()
	{
		return this.textUtil.getText();
	}
	
	//methode permettant de recuperer le nom du fichier 
	public String getFichier()
	{
		return this.textFichier.getText();
	}
	
	
}
