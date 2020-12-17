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
		/*
		do
		{
			Console.print( this.col("\n\tAffichage (", this.coul) + this.col("CUI", 'B') + "/" + this.col("GUI", 'B') + ")? : " + this.setCE('B') );
			ihm = getString().toUpperCase();
			Console.print(this.setCE('#'));
		}
		while( !(ihm.equals("GUI") || ihm.equals("CUI")));
		*/
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
			Console.println("\t " +this.col("1",'B')+ " : Créer   un diagramme" );
			Console.println("\t " +this.col("2",'B')+ " : Charger un diagramme" ); 
			Console.println("\t " +this.col("3",'B')+ " : Modifier  une config" );
			Console.println("\t " +this.col("4",'B')+ " : Supprimer une config" );
			Console.print  ("\n saisie : " );
			
			Console.print(this.setCE('B'));
			choix = getInt() ;
			Console.print(this.setCE(this.coul));
			
			switch (choix)
			{
				case  0 : break;
				case  1 : this.creer(0, null); break;
				case  2 : this.charger(0    ); break;
				case  3 : this.modifier(0   ); break;
				case  4 : this.supprimer(0  ); Console.println("\n\tSuppression des fichiers config ..."); try {Thread.sleep(800);}catch(Exception ex){}; break;
				default : Console.println("\t Choix invalide (" +this.col("0",'B')+ "/" +this.col("1",'B')+ "/" +this.col("2",'B')+ "/" +this.col("3",'B')+ "/" +this.col("4",'B')+ ")" ); try {Thread.sleep(1000);}catch(Exception ex){}; break;
			}
		}while(choix != 0);
		Console.print(this.setCE('*'));
		this.clear();
		System.exit (0);
	}
	
	private void creer(int selec ,boolean[] tabSelec)
	{
		Controleur.compilation();
		//creer un diagramme de la selection
		this.entete();
		
		Controleur.compilation();

		String[] listeS = this.ctrl.getClasse(); //chargement des fichiers

		//affichage des fichiers
		if(listeS != null)
		{
			if(tabSelec == null)
			{
				tabSelec = new boolean[listeS.length];
			}
			
			int tMAxFichier  = this.ctrl.getTailleMaxFichier("./fichierJava"); //recuperation de la taille max dans les fichiers
			if(tMAxFichier < 16) tMAxFichier = 16;//Pour en-tete stable
			this.debTab(tMAxFichier); // création bordure de tableau
			for (int f = 0; f < listeS.length; f++)
			{
				if(f == selec)
				{
					Console.print(this.col("\t────>", 'B'));
					this.afficherFichier(listeS[f], tabSelec[f], tMAxFichier);
				}
				else
				{
					Console.print(this.col("\t     ", 'B'));
					this.afficherFichier(listeS[f], tabSelec[f], tMAxFichier);
				}
			}
			this.finTab(tMAxFichier);
		}
		else { Console.print(this.col("\tAucune classe java dans le repertoire classe", 'R')); try {Thread.sleep(3000);} catch (Exception ex) {} }

		char saisie = this.menuSelection(true);
		
		int newSel = selec;
		
		if(saisie== '/') { this.menu(); }
		if(saisie== '.')
		{
			tabSelec[selec] = !tabSelec[selec];
			newSel++;
			if(newSel > listeS.length -1) { this.creer(0      , tabSelec);  }//torique bas
			else                          { this.creer(newSel , tabSelec); 	}//on descend
		}
		if(saisie== '*')
		{
			int cptTrue = 0;
			for(boolean b : tabSelec)
				if(b)
					cptTrue++;//compter le nombre de fichiers choisis
			
			if(cptTrue == 0)//pas de marque
			{
				String[] tabFichierJava = {listeS[selec].substring(0, listeS[selec].split("\\|")[0].length())}; //tableau avec la selection courante
				this.ctrl.CreateNewDiagramme(tabFichierJava);
			}
			else
			{
				String[] tabFichierJava = new String[cptTrue];//contient le nom de tous les fichiers choisis
				
				int cptElt = 0;
				for(int b = 0; b < tabSelec.length; b++)
					if(tabSelec[b])
						tabFichierJava[cptElt++] = listeS[b].substring(0, listeS[b].split("\\|")[0].length());//recupere le nom sans les dates de la ligne
				
				this.ctrl.CreateNewDiagramme(tabFichierJava);//envoyé un tabString
			}

			Console.print("\n\t\tAuteur      : ");
			Console.print(this.setCE('B'));
			String auteur = getString() ;
			Console.print(this.setCE(this.coul));
			
			if(auteur.equals("")) { auteur = "?"; }
			
			Console.print("\t\tNom Fichier : ");
			Console.print(this.setCE('B'));
			String nomFichierConfig = getString() ;
			Console.print(this.setCE(this.coul));
			
			if(nomFichierConfig.equals("")) { nomFichierConfig = "nouveau"; }
			
			Console.print(this.ctrl.CreateConfigFile(nomFichierConfig, auteur));
			this.getString();
		}
		
		if(saisie== '-')
		{
			newSel--;
			if(newSel < 0) { this.creer(listeS.length -1, tabSelec);  } //torique haut
			else           { this.creer(newSel          , tabSelec);  } //on monte
		}
		if(saisie== '+')
		{
			newSel++;
			if(newSel > listeS.length -1) { this.creer(0     , tabSelec);  }//torique bas
			else                          { this.creer(newSel, tabSelec);  }//on descend
		}

		this.menu();
	}
	
	private void charger(int selec)//permet de charger un diagramme (sous forme txt)
	{
		
		//Affichage de la selection
		this.entete();
		
		String[] listeC = this.ctrl.getConfig();//chargement des fichiers
		
		//affichage des fichiers
		if(listeC != null)
		{
			int tMAxFichier  = this.ctrl.getTailleMaxFichier("./config"); //recuperation de la taille max dans les fichiers
			if(tMAxFichier < 16) tMAxFichier = 16;//Pour en-tete stable
			this.debTab(tMAxFichier); // création bordure de tableau
			for (int f = 0; f < listeC.length; f++)
			{
				if(f == selec)
				{
					Console.print(this.col("\t────>", 'B'));
					this.afficherFichier(listeC[f], false, tMAxFichier);
				}
				else
				{
					Console.print(this.col("\t    ", 'B'));
					this.afficherFichier(listeC[f], tMAxFichier);
				}
			}
			this.finTab(tMAxFichier);
			
			char saisie = this.menuSelection(false);
			
			if(saisie== '/') { this.menu(); }
			if(saisie== '*')
			{
				String nomFichier = listeC[selec].substring(0, listeC[selec].split("\\|")[0].length());
				Console.print("\n\t\tLecture du fichier : " + this.col(nomFichier, 'B'));
				try{Thread.sleep(1500);}catch (Exception ex){}
				this.entete();
				Console.print(this.ctrl.getContenuConfig(nomFichier));
				this.getString();
			}
			
			
			int newSel = selec;
			if(saisie== '-')
			{
				newSel--;
				if(newSel < 0) { this.charger(listeC.length -1); } //torique haut
				else           { this.charger(newSel);           } //on monte
			}
			if(saisie== '+')
			{
				newSel++;
				if(newSel > listeC.length -1) { this.charger(0);     }//torique bas
				else                          { this.charger(newSel); }//on descend
			}
		}
		else { Console.print(this.col("\tAucun fichier sauvegardé dans le dossier config", 'R')); try {Thread.sleep(1500);} catch (Exception ex) {} }
	}
	
	private void modifier(int selec)
	{
		//Affichage de la selection
		this.entete();
		
		String[] listeC = this.ctrl.getConfig(); //chargement des fichiers
		
		//affichage des fichiers
		if(listeC != null)
		{
			int tMaxConfig  = this.ctrl.getTailleMaxFichier("./config"); //recuperation de la taille max dans les fichiers
			if(tMaxConfig < 16) tMaxConfig = 16;//Pour en-tete stable
			this.debTab(tMaxConfig); // création bordure de tableau
			for (int f = 0; f < listeC.length; f++)
			{
				if(f == selec)
				{
					Console.print(this.col("\t────>", 'B'));
					this.afficherFichier(listeC[f], false, tMaxConfig);
				}
				else
				{
					Console.print(this.col("\t     ", 'B'));
					this.afficherFichier(listeC[f], false, tMaxConfig);
				}
			}
			this.finTab(tMaxConfig);
			
			char saisie = this.menuSelection(false);
			
			if(saisie== '/') { this.menu(); }//Annuler
			if(saisie== '*') //Valider
			{
				String nomFichier = listeC[selec].substring(0, listeC[selec].split("\\|")[0].length());
				Console.print("\tOuverture du fichier : " + this.col(nomFichier, 'B'));
				try{Thread.sleep(1500);}catch (Exception ex){}
				this.ctrl.ouvrirEnEdit(nomFichier);
			}
			
			int newSel = selec;
			if(saisie== '-')//Monter
			{
				newSel--;
				if(newSel < 0) { this.modifier(listeC.length -1); } //torique haut
				else           { this.modifier(newSel);           } //on monte
			}
			if(saisie== '+')//Descendre
			{
				newSel++;
				if(newSel > listeC.length -1) { this.modifier(0);      }//torique bas
				else                          { this.modifier(newSel); }//on descend
			}
		}
		else { Console.print(this.col("\tAucun fichier sauvegardé dans le dossier config", 'R')); try {Thread.sleep(1500);} catch (Exception ex) {} }
	}
	
	private void supprimer(int selec)
	{
		return;
	}
	
	private char menuSelection(boolean multi)
	{
		Console.print( "\n\t\t (" +this.col("-", 'B')+ ") : ^ monter\n"
		               + "\t\t (" +this.col("+", 'B')+ ") : v descendre\n" + ((multi)?
		               ( "\t\t (" +this.col(".", 'B')+ ") :   selectionner\n"):"")
		               + "\t\t (" +this.col("*", 'B')+ ") :   valider\n"
		               + "\t\t (" +this.col("/", 'B')+ ") :   annuler\n"
		               + "\tsaisie : ");

		
		Console.print(this.setCE('B'));
		char choix = Character.toUpperCase(this.getChar());
		Console.print(this.setCE(this.coul));
		
		return choix;
	}

	private void debTab(int tMAxFichier)
	{
		Console.print("\t     ┌" + nSep(tMAxFichier +2, "─")          +   "┬" + nSep(18, "─") + "┬" + nSep(18, "─") + "┐\n");
		Console.print("\t     "  + String.format("│ %-" + tMAxFichier + "s │ %-16s │ %-16s │\n" , "  NomFichier  ", "  DateCrea  ", "  DateModif  "));
		Console.print("\t     ├" + nSep(tMAxFichier +2, "─")          +   "┼" + nSep(18, "─") + "┼" + nSep(18, "─") + "┤\n");
	}

	private void afficherFichier(String dataConf, boolean selec, int tMAxFichier)
	{
		String[] decConfig = dataConf.split("\\|");
		Console.print(String.format("│" + ((selec)? (this.col("x", 'B')):" ") + "%-" + tMAxFichier + "s │ %16s │ %16s │\n", decConfig[0],decConfig[1], decConfig[2]));
	}
	
	private void finTab(int tMAxFichier)
	{
		Console.print("\t     └" + nSep( tMAxFichier +2, "─") + "┴" + nSep(18, "─") + "┴" + nSep(18, "─") + "┘\n");
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
		//Console.print(this.setCE('*'));
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
		setCE('*') + "\n\n\n\n\n\n\n\n\n");
		try {Thread.sleep(1500);} catch (Exception ex) {}
	}
	
	private String getString() { return Console.lireString(); }
	private int    getInt   () { return Console.lireInt()   ; }
	private char   getChar  () { return Console.lireChar()  ; }
}
