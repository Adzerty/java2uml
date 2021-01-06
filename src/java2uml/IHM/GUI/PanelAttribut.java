/**
  Classe : PanelAttribut
  @Author : InnovAction
  @version : 1.0
  @since : 2021
*/

package java2uml.IHM.GUI;

import java2uml.metier.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelAttribut extends JPanel {

	/*////////////////////////////////////////////////////////////////////////////
	//                        déclaration des attributs                        //
	///////////////////////////////////////////////////////////////////////////*/
	private ArrayList<Attribut> ens;
	
	public PanelAttribut( ArrayList<Attribut> ens )
	{ 
		
		this.ens = ens;
		this.setBorder(BorderFactory.createLineBorder(Color.black,1));
		int size = this.ens.size();
		this.setLayout( new GridLayout( size, 1 ));
		
		for( Attribut a : ens )
		{
			JLabel tmp = new JLabel();
			if(a.toString().contains("¯"))
			{
				tmp.setText("<html><u>"+a.toString().replaceAll("¯", "")+"</u></html>"); //souligne l'attribut si il est abstract
			}
			else
			{
				tmp.setText(a.toString()); 
			}
			this.add(tmp); //ajout de l'attribut au panelAttribut
		}
		this.setVisible(true);
		
	}
}
