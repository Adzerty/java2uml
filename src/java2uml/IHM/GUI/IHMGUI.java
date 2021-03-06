/**
  Classe : IHMGUI
  @Author : InnovAction
  @version : 1.0
  @since : 2021
*/

package java2uml.IHM.GUI;

import java2uml.Controleur;
import java2uml.IHM.CUI.IHMCUI;
import java2uml.IHM.GUI.NAVIGATION.FrameAccueil;
import java2uml.metier.*;

/**
 * <b>IHMGUI est la classe qui sert d'intermédiaire entre l'utilisateur et l'application.</b>
 * <p>
 * IHMGUI possède les attributs suivant :
 * <ul>
 * <li>Une Frame ... .</li>
 * <li>Un Controleur ... .</li>
 * </ul>
 * </p>
 * 
 * @see IHMCUI
 * @see Controleur
 * 
 * @author InnovAction
 * @version 1.0
 */
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