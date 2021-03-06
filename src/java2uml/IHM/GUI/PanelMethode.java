/**
  Classe : PanelMethode
  @Author : InnovAction
  @version : 1.0
  @since : 2021
*/

package java2uml.IHM.GUI;

import java2uml.metier.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelMethode extends JPanel {
	
	/*////////////////////////////////////////////////////////////////////////////
	//                        déclaration des attributs                        //
	///////////////////////////////////////////////////////////////////////////*/
	private ArrayList<Methode> ens;
	
	public PanelMethode( ArrayList<Methode> ens ) 
	{ 
		
		this.ens = ens;
		this.setBorder(BorderFactory.createLineBorder(Color.black,1));
		int size = this.ens.size();
		this.setLayout( new GridLayout( size, 1 ));
		
		for( Methode m : ens ) 
		{
			JLabel tmp = new JLabel();
			if(m.toString().contains("¯"))
			{
				tmp.setText("<html><u>"+m.toString().replaceAll("¯", "")+"</u></html>"); //souligne la méthode si il est abstract
			}
			else
			{
				tmp.setText(m.toString());
			}
			this.add(tmp); //ajout de la méthode au panelMethode
		}
		this.setVisible(true);
		
	}
}
