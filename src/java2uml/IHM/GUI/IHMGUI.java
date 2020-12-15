package java2uml.IHM.GUI;

import java2uml.metier.*;

public class IHMGUI
{
	private ConfigReader conf;
	public IHMGUI() {
		this.conf = new ConfigReader("test.config");
	}
	
	public static void main( String[] argv ) {
		IHMGUI ihm = new IHMGUI();
	}
}