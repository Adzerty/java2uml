package java2uml;

public class Controleur {

	public static void main(String[] args)
	{
		Controleur ctrl = new Controleur();
		ctrl.metier.getLocal().setCoords(RequetesCoord.getCoordonnee(ctrl.metier.getLocal()));
		ctrl.charger();
		
		String ihm;
		do
		{
			Console.print( CouleurConsole.BLANC.getFont () + "\n\t\tAffichage (" +ctrl.col("CUI")+ "/" + ctrl.col("GUI") + ") : ");
			ihm = Clavier.lireString().toUpperCase();
		}
		while( !(ihm.equals("GUI") ^ ihm.equals("CUI")));
		
		if( ihm.equals("GUI")) { new IhmGUI(ctrl); }
		else                   { new IhmCUI(ctrl); }
	}

}
