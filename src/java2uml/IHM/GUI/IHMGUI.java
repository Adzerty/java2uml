package java2uml.IHM.GUI;

import java2uml.metier.*;

public class IHMGUI
{
	private ConfigReader conf;
	private FramePrc framePrincipale;

	public IHMGUI() {
		this.conf = new ConfigReader("test.config");
		this.framePrincipale = new FramePrc(this.conf);
	}
	
	public static void main( String[] argv ) {
		IHMGUI ihm = new IHMGUI();
	}
}