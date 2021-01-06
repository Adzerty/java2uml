package java2uml.IHM.GUI.NAVIGATION;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import java2uml.Controleur;

public class PanelCreeDiag extends JPanel implements ActionListener
{
	
	private JButton btnCreeDiagramme;
	private Controleur ctrl;
	
	public PanelCreeDiag(Controleur ctrl) 
	{
		
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(1,1));
		
		this.btnCreeDiagramme = new JButton("Créer un diagramme");
		this.btnCreeDiagramme.addActionListener(this);
		this.btnCreeDiagramme.setFont(new Font( "Verdana" ,Font.BOLD, 30));
		
		this.add(btnCreeDiagramme);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnCreeDiagramme) 
		{
			this.ctrl.compilation();
			new FrameCreer(this.ctrl);
		}
		
	}

}
