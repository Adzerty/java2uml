package java2uml.IHM.GUI;

import java2uml.metier.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelMethode extends JPanel {

	private ArrayList<Methode> ens;
	
	public PanelMethode( ArrayList<Methode> ens ) { 
		this.ens = ens;
		this.setBorder(BorderFactory.createLineBorder(Color.black,1));
		int size = this.ens.size();
		this.setLayout( new GridLayout( size, 1 ));
		for( Methode m : ens ) {
			this.add( new JLabel(m.toString()));
		}
		this.setVisible(true);
	}
}
