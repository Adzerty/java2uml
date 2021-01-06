package java2uml.IHM.GUI.NAVIGATION;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelValiderModifier extends JPanel  implements ActionListener{
	
	private JButton btnValider;
	private FrameModifier frame;
	
	public PanelValiderModifier(FrameModifier frame) {
		
		this.frame = frame;
		
		this.setLayout(new GridLayout(1,1));
		
		this.btnValider = new JButton("Valider");
		
		this.btnValider.addActionListener(this);
		
		this.add(this.btnValider);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnValider) {
			this.frame.setValider();
			System.out.println("oui");
		}
	}

}
