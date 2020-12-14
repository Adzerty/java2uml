package java2uml;

import iut.algo.Console;
import java2uml.IHM.*;

public class Controleur
{
	private Object metier;
	private IHMCUI ihmCUI;
	private IHMGUI ihmGUI;
	
	public Controleur()
	{ 
		metier = new Object();
		this.ihmCUI = new IHMCUI (this);
		
		if(this.ihmCUI.choixGraphique() == 'G')	{ this.ihmGUI = new IHMGUI(this); } 
		else                                    { this.ihmCUI.start(); }
	}
	
	public static void main(String[] args)
	{
		new Controleur();
		
		Console.print("\n\t---- FIN DU PROGRAMME ----\n");
	}
}
