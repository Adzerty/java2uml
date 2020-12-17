package java2uml.IHM.GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelChargMod extends JPanel implements ActionListener {
	
	private JButton btnCharger;
	private JButton btnModifier;
	private JButton btnSupprimer;
	
	
	private FrameAccueil frame;
	
	public PanelChargMod(FrameAccueil frame){
		
		
		this.frame = frame;
		
		
		this.setLayout(new GridLayout(1,3));
		this.btnCharger = new JButton("Charger un diagramme");
		this.btnModifier = new JButton("Modifier un diagramme");
		this.btnSupprimer = new JButton("Supprimer un fichier");
		
		
		this.btnCharger.setFont(new Font( "Verdana" ,Font.BOLD, 13));
		this.btnModifier.setFont(new Font( "Verdana" ,Font.BOLD, 13));
		this.btnSupprimer.setFont(new Font( "Verdana" ,Font.BOLD, 13));
		
		
		this.btnCharger.addActionListener(this);
		this.btnModifier.addActionListener(this);
		this.btnSupprimer.addActionListener(this);
		
		this.add(btnCharger);
		this.add(btnModifier);
		this.add(btnSupprimer);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.btnCharger) {
			
			new FrameCharger();
			//this.frame.dispose();
			
		}
		else if(e.getSource() == this.btnModifier) {
			System.out.println("btnmodifier");
		}
		else {
			System.out.println("btnsupprimer");
		}
		
	}
	
	

}
