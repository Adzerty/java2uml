package java2uml.IHM.GUI.NAVIGATION;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener{
	
	JMenu propos ;
	JMenuItem credit;
	
	public MenuBar() {
		
		this.propos = new JMenu("A propos");
		this.credit = new JMenuItem("L'équipe");
		this.credit.addActionListener(this);
		this.propos.add(this.credit);
		this.add(propos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new FrameEquipe();
	}
}
