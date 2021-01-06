package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import java2uml.Controleur;

public class PanelChargMod extends JPanel implements ActionListener {
	
	private JButton btnCharger;
	private JButton btnModifier;
	private JButton btnSupprimer;
	
	private Controleur ctrl;
	
	private FrameAccueil frame;
	
	public PanelChargMod(FrameAccueil frame ,Controleur ctrl){
		
		
		this.frame = frame;
		this.ctrl = ctrl;
		
		
		this.setLayout(new GridLayout(1,3));
		
		//creation des JButton
		this.btnCharger = new JButton("Charger un diagramme");
		this.btnModifier = new JButton("Modifier un diagramme");
		this.btnSupprimer = new JButton("Supprimer un fichier");
		
		//initialisation des polices des boutons
		this.btnCharger.setFont(new Font( "Verdana" ,Font.BOLD, 13));
		this.btnModifier.setFont(new Font( "Verdana" ,Font.BOLD, 13));
		this.btnSupprimer.setFont(new Font( "Verdana" ,Font.BOLD, 13));
		
		//ajout d'un listner sur chaque bouton
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
			
		}
		else if(e.getSource() == this.btnModifier) {
			new FrameModifier(this.ctrl);
		}
		else {
			new FrameSupprimer(this.ctrl);
		}
		
	}
	
	
	
	

}
