package java2uml.IHM;

import java2uml.Controleur;

public class IHMGUI
{
	private Controleur ctrl;
	public IHMGUI(Controleur ctrl)
	{
		this.ctrl = ctrl;
		try {Thread.sleep(800);} catch (Exception ex) {}
		System.out.println("\tAh bah non :D");
		
	}
}
