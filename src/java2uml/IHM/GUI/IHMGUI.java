package java2uml.IHM.GUI;

import java2uml.Controleur;
import java2uml.metier.*;

public class IHMGUI
{
	//private Controleur ctrl;
	private ConfigReader conf;
	public IHMGUI(/*Controleur ctrl*/)
	{
		//this.ctrl = ctrl;
		this.conf = new ConfigReader("test.config");
	}
	
	public static void main( String[] argv ) {
		IHMGUI ihm = new IHMGUI();
	}
}