package java2uml.IHM.GUI;

import java2uml.Controleur;
import java2uml.IHM.GUI.NAVIGATION.FrameAccueil;
import java2uml.metier.*;

public class IHMGUI
{
	/*private ConfigReader conf;
	private FramePrc framePrincipale;*/
	
	private FrameAccueil frameAccueil;
	private Controleur ctrl;

	public IHMGUI(Controleur ctrl) {
		this.ctrl = ctrl;
		this.frameAccueil = new FrameAccueil(this.ctrl);
	}
}