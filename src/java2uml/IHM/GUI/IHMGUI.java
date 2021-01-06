/**
  Classe : IHMGUI
  @Author : InnovAction
  @version : 1.0
  @since : 2021
*/

package java2uml.IHM.GUI;

import java2uml.Controleur;
import java2uml.IHM.GUI.NAVIGATION.FrameAccueil;
import java2uml.metier.*;

public class IHMGUI
{

	/*////////////////////////////////////////////////////////////////////////////
	//                        déclaration des attributs                        //
	///////////////////////////////////////////////////////////////////////////*/
	private FrameAccueil frameAccueil;
	private Controleur ctrl;

	public IHMGUI(Controleur ctrl) {
		this.ctrl = ctrl;
		this.frameAccueil = new FrameAccueil(this.ctrl);
	}
}