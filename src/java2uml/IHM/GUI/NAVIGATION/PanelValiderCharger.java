package java2uml.IHM.GUI.NAVIGATION;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelValiderCharger extends JPanel  implements ActionListener{
	
	private JButton btnValider;
	private FrameCharger frame;
	
	public PanelValiderCharger(FrameCharger frame) {
		
		this.frame = frame;
		
		this.setLayout(new GridLayout(1,1));
		
		//creation du bouton valider
		
		this.btnValider = new JButton("Valider");
		this.btnValider.addActionListener(this);
		
		this.add(this.btnValider);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnValider) {
			this.frame.setValider();
		}
	}

}
