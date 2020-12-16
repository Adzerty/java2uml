package java2uml.IHM.GUI;

import java2uml.metier.*;

public class IHMGUI
{
	/*private ConfigReader conf;
	private FramePrc framePrincipale;*/
	
	private FrameChoixConfig fcc;

	public IHMGUI() {
		/*this.conf = new ConfigReader("test-2.config");
		this.framePrincipale = new FramePrc(this.conf);*/
		this.fcc = new FrameChoixConfig();
	}
	
	public static void main( String[] argv ) {
		IHMGUI ihm = new IHMGUI();
	}
}