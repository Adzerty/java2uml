package java2uml.IHM.GUI;

import java2uml.IHM.GUI.NAVIGATION.FrameAccueil;
import java2uml.metier.*;

public class IHMGUI
{
	/*private ConfigReader conf;
	private FramePrc framePrincipale;*/
	
	private FrameAccueil frameAccueil;

	public IHMGUI() {
		
		this.frameAccueil = new FrameAccueil();
	}
	
	public static void main( String[] argv ) {
		IHMGUI ihm = new IHMGUI();
	}
}