package java2uml.IHM.GUI;

import java2uml.metier.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelAttribut extends JPanel {

	private ArrayList<Attribut> ens;
	
	public PanelAttribut( ArrayList<Attribut> ens ) { 
		this.ens = ens;
		int size = this.ens.size();
		this.setLayout( new GridLayout( size, 1 ));
		for( Attribut a : ens ) {
			this.add( new JLabel(a.toString()));
		}
		this.setVisible(true);
	}
}
