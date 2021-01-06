package java2uml.IHM.GUI.NAVIGATION;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelValiderSupprimer extends JPanel implements ActionListener{
	
	private JButton btnValider;
	private FrameSupprimer frame;

	public PanelValiderSupprimer(FrameSupprimer frame) {

		this.frame = frame;
		
		this.setLayout(new GridLayout(1,1));
		
		this.btnValider = new JButton("Valider");
		
		this.btnValider.addActionListener(this);
		
		this.add(this.btnValider);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnValider) {
			this.frame.setValider();
		}
	}
}
