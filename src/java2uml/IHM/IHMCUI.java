package java2uml.IHM;

import iut.algo.Clavier;
import iut.algo.Console;
import iut.algo.CouleurConsole;

import java2uml.Controleur; 

public class IHMCUI
{
	private Controleur ctrl;
	public IHMCUI(Controleur ctrl)
	{
		this.ctrl = ctrl;
	}
	
	public char choixGraphique()
	{
		
		String ihm = "CUI";//par defaut
		/*
		do
		{
			Console.print( this.col("\n\tAffichage (", '#') + this.col("CUI", 'B') + "/" + this.col("GUI", 'B') + ")? : " + this.setCE('B') );
			ihm = Clavier.lireString().toUpperCase();
			Console.print(this.setCE('#'));
		}
		while( !(ihm.equals("GUI") || ihm.equals("CUI")));*/
		
		Console.print("\n\tDemarrage du Mode " + ihm +" ...");
		
		try {Thread.sleep(800);} catch (Exception ex) {}
		this.entete();
		Console.print(this.setCE('*'));
		
		if( ihm.equals("GUI")){ return 'G';	}
		else                  {	return 'C'; }
		
	}
	
	public void start()
	{
		//this.menu();
	}
	
	private String col(String s, char c)
	{
		return setCE(c) +s+  setCE('#');
	}
	
	private String setCE(char c)//Renvoie un code en String pour changer la couleur d'ECRITURE
	{
		switch(c)
		{
			case 'R' : return CouleurConsole.ROUGE.getFont();
			case 'J' : return CouleurConsole.JAUNE.getFont ();
			case 'B' : return CouleurConsole.BLEU.getFont ();
			case 'M' : return CouleurConsole.MAUVE.getFont ();
			case 'C' : return CouleurConsole.CYAN.getFont ();
			case '#' : return CouleurConsole.BLANC.getFont ();
			default  : return "\033[0m"; //par defaut de l'utilisateur;
		}
	}

	private void entete()
	{
		this.clear();
		Console.println( "\t\t\t                                                                         "     + "\n" +
		                 "\t\t\t      ,--.  ,---.,--.   ,--.,---.       ,---.     ,--. ,--.,--.   ,--.,--.     "     + "\n" +
		                 "\t\t\t      |  | /  O  \\\\  `.'  //  O  \\     '.-.  \\    |  | |  ||   `.'   ||  |     " + "\n" + 
		                 "\t\t\t ,--. |  ||  .-.  |\\     /|  .-.  |     .-' .'    |  | |  ||  |'.'|  ||  |     "    + "\n" +
		                 "\t\t\t |  '-'  /|  | |  | \\   / |  | |  |    /   '-.    '  '-'  '|  |   |  ||  '--.  "    + "\n" +
		                 "\t\t\t  `-----' `--' `--'  `-'  `--' `--'    '-----'     `-----' `--'   `--'`-----'  "     + "\n" +
		                 "\t\t\t                                                                               "     + "© 2020 - InnovAction - IUT du Havre.\n");
	}
	
	private void clear()//nettoyer la console
	{
		Console.effacerEcran();
	}
}
