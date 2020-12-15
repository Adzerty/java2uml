package java2uml.IHM.CUI;

import iut.algo.Console;
import iut.algo.CouleurConsole;

import java2uml.Controleur;

public class IHMCUI
{
	private char coul = '#'; //Couleur d'écriture du prog → BLANC (voir setCE)
	
	private Controleur ctrl;
	public IHMCUI(Controleur ctrl)
	{
		this.ctrl = ctrl;
	}
	
	//Demande en quel mode d'affichage le programme se lance →TODO Faire lancement avec args ?
	public char choixGraphique()
	{
		
		String ihm = "CUI";//par defaut
		
		do
		{
			Console.print( this.col("\n\tAffichage (", this.coul) + this.col("CUI", 'B') + "/" + this.col("GUI", 'B') + ")? : " + this.setCE('B') );
			ihm = getString().toUpperCase();
			Console.print(this.setCE('#'));
		}
		while( !(ihm.equals("GUI") || ihm.equals("CUI")));
		
		Console.print("\n\tDemarrage du Mode " + ihm +" ...");
		
		this.clear();
		this.afficherInnovAction();
		try {Thread.sleep(2000);} catch (Exception ex) {}
		this.entete();
		
		if( ihm.equals("GUI")){ return 'G';	}
		else                  {	return 'C'; }
		
	}
	
	//Lancement de l'IHM CUI
	public void start()
	{
		this.menu();
	}
	
	//Menu de l'application
	private void menu()
	{
		
		int choix;
		do
		{
			Console.println("\t " +this.col("0",'B')+ " : Quitter le programme" );
			Console.println("\t " +this.col("1",'B')+ " : Charger"              );
			Console.println("\t " +this.col("2",'B')+ " : Sauvegarder"          ); 
			Console.println("\t " +this.col("3",'B')+ " : Modifier"             );
			Console.print  ("\n saisie : " );
			Console.print(this.setCE('B'));
			choix = getInt() ;
			Console.print(this.setCE(this.coul));
			switch (choix)
			{
				case  0 : Console.print(this.setCE('*')    ); System.exit (0);
				case  1 : Console.print("\n\tChargement."  ); try{Thread.sleep(800);}catch (Exception ex){} Console.print("."); try {Thread.sleep(800);}catch(Exception ex){} Console.print(".\n"); break;
				case  2 : Console.print("\n\tSauvegarde."  ); try{Thread.sleep(800);}catch (Exception ex){} Console.print("."); try {Thread.sleep(800);}catch(Exception ex){} Console.print(".\n"); break;
				case  3 : Console.print("\n\tMofification."); try{Thread.sleep(800);}catch (Exception ex){} Console.print("."); try {Thread.sleep(800);}catch(Exception ex){} Console.print(".\n"); break;
				default : Console.println("\t Choix invalide (" +this.col("0",'B')+ "/" +this.col("1",'B')+ "/" +this.col("2",'B')+ "/" +this.col("3",'B')+ ")" );
			}
		}while(choix != 0 && choix != 1 && choix != 2 && choix != 3);
		Console.print(this.setCE('*'));
	}
	
	private void charger()
	{
		//charger un fichier config
	}
	
	private void sauvegarder()
	{
		//sauvegarder un fichier en config
	}
	
	private void modifier()
	{
		//modifier un .config
	}
	
	//Coloration d'un String s dans la console avec la couleur du caractere c avec la méthode setCe
	private String col(String s, char c)
	{
		return setCE(c) +s+  setCE(this.coul);
	}
	
	private String setCE(char c)//Renvoie un code en String pour changer la couleur d'ECRITURE dans la console
	{
		switch(c)
		{
			case 'B' : return CouleurConsole.BLEU.getFont ();
			case 'C' : return CouleurConsole.CYAN.getFont ();
			case 'J' : return CouleurConsole.JAUNE.getFont ();
			case 'M' : return CouleurConsole.MAUVE.getFont ();
			case 'N' : return CouleurConsole.NOIR.getFont ();
			case 'R' : return CouleurConsole.ROUGE.getFont();
			case '#' : return CouleurConsole.BLANC.getFont ();
			default  : return "\033[0m"; //par defaut de l'utilisateur;
		}
	}

	private void clear()//nettoyer la console
	{
		Console.effacerEcran();
	}
	
	//affiche la banniere du programme JAVA2UML
	private void entete()
	{
		this.clear();
		Console.print(this.setCE(this.coul));
		Console.println( "\t\t\t                                                                         "     + "\n" +
		                 "\t\t\t      ,--.  ,---.,--.   ,--.,---.       ,---.     ,--. ,--.,--.   ,--.,--.     "     + "\n" +
		                 "\t\t\t      |  | /  O  \\\\  `.'  //  O  \\     '.-.  \\    |  | |  ||   `.'   ||  |     " + "\n" + 
		                 "\t\t\t ,--. |  ||  .-.  |\\     /|  .-.  |     .-' .'    |  | |  ||  |'.'|  ||  |     "    + "\n" +
		                 "\t\t\t |  '-'  /|  | |  | \\   / |  | |  |    /   '-.    '  '-'  '|  |   |  ||  '--.  "    + "\n" +
		                 "\t\t\t  `-----' `--' `--'  `-'  `--' `--'    '-----'     `-----' `--'   `--'`-----'  "     + "\n" +
		                 "\t\t\t                                                                               "     + "© 2020 - InnovAction - IUT du Havre.\n");
		Console.print(this.setCE('*'));
	}
	
	//Affiche le logo de l'entreprise InnovAction
	private void afficherInnovAction()
	{
		Console.print("\n\n\n\n\n\n\n\n\n" +
		this.col("                                                           "       +          "              "       +          "                  "      +                            "                                                                            " , 'N') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("            *(((/.",'R') + this.col(                  "                                                                            " , 'N') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("          /((((/"  ,'R') + this.col(                "                                                                              " , 'N')  + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("        ,(((((,"   ,'R') + this.col(               "                                                                               " , 'N') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("       /((((("     ,'R') + this.col(             "                                                                                 " , 'N') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("     ,(((((*"      ,'R') + this.col(            "                                                                                  " , 'N') + "\n" +
		this.col("    *%%%.  *#%%(.   .%%%,  ,#%%#,    (%%(   *%&@@@@@@@%*   ", 'N') + this.col("./###,        ", 'B') + this.col("    /(((((."       ,'R') + this.col(           "  (@@@@@(       .%@@@@@@@@@ .@@@@@@@@@@@@% /@@@.  ,&@@@@@@@@@@&.   /@@@@@,   /@@@. " , 'N') + "\n" +
		this.col("    (@@@. &@@@@@@,  ,@@@* (@@@@@@#   %@@% *@@@@@%%%%@@@@@* ", 'N') + this.col(" (####/       ", 'B') + this.col("  .(((((*"         ,'R') + this.col(         "   *@@@%@@@*     &@@@#*******  ****%@@@****, /@@@. /@@@%*,,,,*%@@@( ,@@@%@@@.  /@@@. " , 'N') + "\n" +
		this.col("    (@@@. &@@#/@@@  ,@@@* (@@@*@@@,  %@@% %@@%        %@@& ", 'N') + this.col("  *#####.     ", 'B') + this.col(" *(((((."          ,'R') + this.col(        "   ,@@@( (@@@,   ,@@@*              (@@@.     /@@@. %@@%        #@@& ,@@@*(@@&  /@@@. " , 'N') + "\n" +
		this.col("    (@@@. &@@# %@@# ,@@@* (@@@./@@@  %@@% %@@%        #@@& ", 'N') + this.col("   .#####/    ", 'B') + this.col("/((((/"            ,'R') + this.col(      "    .&@@#   &@@&   ,@@@,              (@@@.     /@@@. %@@%        #@@& ,@@@* @@@/ /@@@. " , 'N') + "\n" +
		this.col("    (@@@. &@@# .@@@,,@@@* (@@@. %@@# %@@% %@@%        #@@& ", 'N') + this.col("     /####(.*(", 'B') + this.col("((((,"             ,'R') + this.col(     "     %@@@@@@@@@@@%  ,@@@,              (@@@.     /@@@. %@@%        #@@& ,@@@* .@@@,/@@@. " , 'N') + "\n" +
		this.col("    (@@@. &@@#  *@@@*@@@* (@@@. .@@@,%@@% %@@%        %@@& ", 'N') + this.col("      .#####((", 'B') + this.col("((/."              ,'R') + this.col(    "     (@@@/*****(@@@/ .@@@(              (@@@.     /@@@. %@@&        %@@% ,@@@*  (@@%/@@@. " , 'N') + "\n" +
		this.col("    (@@@. &@@#   &@@@@@@* (@@@.  *@@@@@@% *@@@@&%%%%&@@@@* ", 'N') + this.col("        (#####", 'B') + this.col("(*"                ,'R') + this.col(  "      ,@@@/       (@@@, .&@@@@@@@@@@      (@@@.     /@@@. .@@@@@@@@@@@@@@. ,@@@*   &@@@@@@  " , 'N') + "\n" +
		this.col("    /&&&. %&&(    ,&&&%.  (&&&.    %&&&*    /@@@@@@@@@@*   ", 'N') + this.col("         *####", 'B') + this.col("."                 ,'R') + this.col( "                                                              ,(######(,                     "        + "\n" +
		         "                                                           "       +          "              "       +          ""                        +          "                                                                                              " , 'N') + "\n" +
		setCE('*') + "\n");
	}
	
	private String getString() { return Console.lireString(); }
	private int    getInt   () { return Console.lireInt()   ; }
	//private char   getChar  () { return Console.lireChar()  ; }
}
