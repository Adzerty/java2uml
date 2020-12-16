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
		
		if( ihm.equals("GUI")){ this.entete(); return 'G'; }
		else                  {	               return 'C'; }
		
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
			this.entete();
			
			Console.println("\t " +this.col("0",'B')+ " : Quitter le programme" );
			Console.println("\t " +this.col("1",'B')+ " : Creer un diagramme"    );
			Console.println("\t " +this.col("2",'B')+ " : Charger un diagramme" ); 
			Console.println("\t " +this.col("3",'B')+ " : Modifier une config"  );
			Console.print  ("\n saisie : " );
			Console.print(this.setCE('B'));
			choix = getInt() ;
			Console.print(this.setCE(this.coul));
			switch (choix)
			{
				case  0 : break;
				case  1 : Console.print("\n\tCreation."    ); try{Thread.sleep(800);}catch (Exception ex){} Console.print("."); try {Thread.sleep(800);}catch(Exception ex){} Console.print(".\n"); break;
				case  2 : Console.print("\n\tchargement."  ); try{Thread.sleep(800);}catch (Exception ex){} Console.print("."); try {Thread.sleep(800);}catch(Exception ex){} Console.print(".\n"); break;
				case  3 : this.modifier(0); break;
				default : Console.println("\t Choix invalide (" +this.col("0",'B')+ "/" +this.col("1",'B')+ "/" +this.col("2",'B')+ "/" +this.col("3",'B')+ ")" );
			}
		}while(choix != 0 && choix != 1);
		Console.print(this.setCE('*'));
		System.exit (0);
	}
	
	private void charger()
	{
		//charger un fichier config
	}
	
	private void sauvegarder()
	{
		//sauvegarder un fichier en config
	}
	
	private void modifier(int selec)
	{
		//Affichage de la selection
		this.entete();
		
		String[] listeC = this.ctrl.getConfig();          //chargement des fichiers
		
		//affichage des fichiers
		if(listeC != null)
		{
			int tMaxConfig  = this.ctrl.getTailleMaxConfig(); //recuperation de la taille max dans les fichiers
			this.enteteTab(tMaxConfig); // création bordure de tableau
			for (int f = 0; f < listeC.length; f++)
			{
				if(f == selec)
				{
					Console.print(this.col("\t---->", 'B'));
					this.afficherConfig(listeC[f], tMaxConfig);
				}
				else
				{
					Console.print(this.col("\t     ", 'B'));
					this.afficherConfig(listeC[f], tMaxConfig);
				}
			}
			Console.print("\t     +" + nSep( tMaxConfig +2, "-") + "+" + nSep(18, "-") + "+" + nSep(18, "-") + "+\n");
			
			String menu = "\n\t\t (" +this.col("+", 'B')+ ") : monter\n"
			              + "\t\t (" +this.col("-", 'B')+ ") : descendre\n"
			              + "\t\t (" +this.col("*", 'B')+ ") : valider\n"
			              + "\t\t (" +this.col("/", 'B')+ ") : annuler\n"
			              + "\tsaisie : ";
		
			Console.print(menu);
			
			Console.print(this.setCE('B'));
			char saisie = Character.toUpperCase(this.getChar());
			Console.print(this.setCE(this.coul));
			
			if(saisie== '/') { this.menu(); }
			if(saisie== '*')
			{
				Console.print("\tOuverture du fichier : " + this.col(listeC[selec].substring(0, listeC[selec].split("\\|")[0].length()), 'B'));
				try{Thread.sleep(3000);}catch (Exception ex){} }
			
			int newSel = selec;
			if(saisie== '+')
			{
				newSel--;
				if(newSel < 0) { this.modifier(listeC.length -1); } //torique haut
				else           { this.modifier(newSel);           } //on monte
			}
			if(saisie== '-')
			{
				newSel++;
				if(newSel > listeC.length -1) { this.modifier(0);     }//torique bas
				else                          { this.modifier(newSel); }//on descend
			}
		}
		else { Console.print(this.col("\tAucun fichier de config sauvegarde", 'R')); try {Thread.sleep(3000);} catch (Exception ex) {} }
	}
	

	private void enteteTab(int tMAxConfig)
	{
		Console.print(setCE(this.coul));
		Console.print("\t     +" + nSep(tMAxConfig +2, "-") + "+" + nSep(18, "-") + "+" + nSep(18, "-") + "+\n");
		Console.print("\t     "  + String.format("| %-" + tMAxConfig + "s | %-16s | %-16s |\n" , "  NomConfig  ", "  DateCrea  ", "  DateModif  "));
		Console.print("\t     +" + nSep(tMAxConfig +2, "-") + "+" + nSep(18, "-") + "+" + nSep(18, "-") + "+\n");
		Console.print(setCE(this.coul));
	}

	private void afficherConfig(String dataConf, int tMAxConfig)
	{
		String[] decConfig = dataConf.split("\\|");
		Console.print(String.format("| %-" + tMAxConfig + "s | %16s | %16s |\n", decConfig[0],decConfig[1], decConfig[2]));
	}
	
	private String nSep(int n, String s)
	{
		String sSep = "";
		for(int i =0; i < n; i++)
			sSep += s;
		
		return sSep;
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
		try {Thread.sleep(2000);} catch (Exception ex) {}
	}
	
	private String getString() { return Console.lireString(); }
	private int    getInt   () { return Console.lireInt()   ; }
	private char   getChar  () { return Console.lireChar()  ; }
}
