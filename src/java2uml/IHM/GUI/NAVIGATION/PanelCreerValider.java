package java2uml.IHM.GUI.NAVIGATION;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelCreerValider extends JPanel implements ActionListener
{
	
	private JButton btnValider;
	private FrameCreer frame;
	
	
	public PanelCreerValider(FrameCreer frame)
	{
		this.frame = frame;
		
		this.setLayout(new GridLayout(1,1));
		
		//creation du JButton
		
		this.btnValider = new JButton("Valider");
		this.btnValider.addActionListener(this);
		
		this.add(this.btnValider);
	}
	
	//Methode de validation du bouton
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==this.btnValider)
		{
			this.frame.setValider();
		}
	}
	
}
