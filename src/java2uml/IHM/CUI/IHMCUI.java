package java2uml.IHM.CUI;

import iut.algo.Console;
import iut.algo.CouleurConsole;

import java2uml.Controleur;

public class IHMCUI
{
	private char coul = '#'; //Couleur d'écriture du prog → BLANC (voir setCE).
	private int  tpsDebug = 1500;
	private int  tpsAjust = 2000;
	
	private Controleur ctrl;
	public IHMCUI(Controleur ctrl)
	{
		this.ctrl = ctrl;
	}

	public void start()//lancement de l'IHM CUI
	{
		this.afficherInnovAction();
		this.menu();
	}
	
	public void confirmSup(String fichierSup, boolean supConfig, boolean supDiagTxt, boolean supDiagPdf, boolean optionSup)//gestion des types d'erreurs lors de la suppression.
	{
		if (supConfig) //si le fichier de confiuration a pu être supprimé
		{
			if (supDiagTxt && supDiagPdf) //si les diagrammes associés au fichier de configuration ont été supprimés.
			{
				if(optionSup)// si l'option de suppression des fichiers associés est acivée.
				{
					Console.println("\t\t" + this.col("# ", 'V') + "Le fichier de configuration " + this.col(fichierSup, 'B')
					               +" à été supprimé sans ses fichiers associés avec succès.");
				}
				else
				{
					Console.println("\t\t" + this.col("# ", 'V') + "Les fichiers associés à " + this.col(fichierSup, 'B')
					               +" ont été supprimé avec succès.");
				}
			}
			else
			{
				Console.println("\t\t" + this.col("# ", 'J') + "Le fichier " + this.col(fichierSup, 'B') 
				               +" a été supprimé avec succès, aucun diagramme " + ((supDiagTxt)? "pdf associé" : ((supDiagPdf)? "txt associé" : "txt et pdf associés")) + ".");
			}
		}
		else
		{
			Console.println("\t\t" + this.col("# ", 'R') + "Erreur lors de la suppression de " + this.col(fichierSup, 'B')
			               +", le fichier de configuration n'existe plus.");
		}
		try {Thread.sleep(tpsDebug);}catch(Exception ex){};
	}
	
	private void menu()//menu principal de l'application
	{
		int choix;
		do
		{
			this.entete();
			
			Console.println("\t\t " +this.col("0",'B')+ " : Quitter   le  programme." );
			Console.println("\t\t " +this.col("1",'B')+ " : Créer     un  diagramme." );
			Console.println("\t\t " +this.col("2",'B')+ " : Charger   un  diagramme." ); 
			Console.println("\t\t " +this.col("3",'B')+ " : Modifier  une configuration." );
			Console.println("\t\t " +this.col("4",'B')+ " : Supprimer une configuration." );
			Console.println("\t\t " +this.col("5",'B')+ " : Modifier  les parametres." );
			Console.print  ("\n\t saisie : " );
			
			Console.print(this.setCE('B'));
			choix = getInt() ;
			Console.print(this.setCE(this.coul));
			
			switch (choix)
			{
				case  0 : break;
				case  1 : Console.print("\n\t\tCompilation des fichiers " +this.col("JAVA",'B')+ " en cours...");this.ctrl.compilation(); this.clear(); this.creer(0, null); break;
				case  2 : this.charger  (0      ); break;
				case  3 : this.modifier (0      ); break;
				case  4 : this.supprimer(0, null); break;
				case  5 : this.parametres(0,this.ctrl.getOptions());break;
				default : Console.println(this.col("\n\tErreur",'R') + " : saisir  (" +this.col("0",'B')+ "/" +this.col("1",'B')+ "/" +this.col("2",'B')+ "/" +this.col("3",'B')+ "/" +this.col("4",'B')+ "/" +this.col("5",'B')+ ")" ); try {Thread.sleep(tpsDebug);}catch(Exception ex){}; break;
			}
		}while(choix != 0);
		Console.print(this.setCE('*'));
		this.clear();
		System.exit (0);
	}

	private void creer(int selec ,boolean[] tabSelec)//permet de créer une configuration et de générer son diagramme
	{
		this.entete();
		
		Console.println("\t\t" +this.col("1",'B')+ " : CREER UN DIAGRAMME DE CLASSE\n" );
		
		String[] listeS = this.ctrl.getClasse(); //chargement des fichiers

		//affichage des fichiers
		if(listeS != null)
		{
			if(tabSelec == null) { tabSelec = new boolean[listeS.length]; }
			
			int tMAxFichier  = this.ctrl.getTailleMaxFichier(this.ctrl.repCompile) -6; //recuperation de la taille max dans les fichiers sans les ".class"
			if(tMAxFichier < 16) tMAxFichier = 16;//Pour en-tete stable
			this.debTab(tMAxFichier); // création bordure de tableau
			for (int f = 0; f < listeS.length; f++)
			{
				if(f == selec) { Console.print(this.col("\t────>", 'B')); }
				else           { Console.print(this.col("\t     ", 'B')); }
				this.afficherFichier(listeS[f], tabSelec[f], tMAxFichier);
			}
			this.finTab(tMAxFichier);
		}
		else { Console.print(this.col("\tAucune classe java dans le repertoire fichierJava", 'R')); try {Thread.sleep(tpsDebug);} catch (Exception ex) {} }

		char saisie = this.menuSelection(true);
		if(!this.verifSaisie(saisie, true)) { this.creer(selec, tabSelec); } //si mauvaise saisie true = menuMulti
		
		int newSel = selec;
		
		if(saisie== '/') { this.menu(); }
		if(saisie== '.')
		{
			
			if(!tabSelec[selec]) { newSel++; }
			tabSelec[selec] = !tabSelec[selec];
			if(newSel > listeS.length -1) { this.creer(0      , tabSelec);  }//torique bas
			else                          { this.creer(newSel , tabSelec); 	}//on descend
		}
		if(saisie == '*')
        {
			boolean all = true;//acc
            for(int i = 0; i<tabSelec.length; i++)
            {
                if(!tabSelec[i]) { all = false; }//on regarde si tout est selectionné
            }
            //si tout est selectionné on désélectionne tout sinon on selectionne tout
            if(all) { for(int v = 0; v < tabSelec.length; v++) tabSelec[v] = false; }
            else    { for(int f = 0; f < tabSelec.length; f++) tabSelec[f] = true;  }
            
            this.creer(0      , tabSelec);
        }
		if(saisie== '=')
		{
			int cptTrue = 0;
			for(boolean b : tabSelec)
				if(b)
					cptTrue++;//compter le nombre de fichiers choisis
			
			if(cptTrue == 0)//pas de marque
			{
				String[] tabFichierJava = {listeS[selec].substring(0, listeS[selec].split("\\|")[0].length())}; //tableau avec la selection courante
				this.ctrl.creerNouvDiagramme(tabFichierJava); 
			}
			else
			{
				String[] tabFichierJava = new String[cptTrue];//contient le nom de tous les fichiers choisis
				
				int cptElt = 0;
				for(int b = 0; b < tabSelec.length; b++)
					if(tabSelec[b])
						tabFichierJava[cptElt++] = listeS[b].substring(0, listeS[b].split("\\|")[0].length());//recupere le nom sans les dates de la ligne
				
				this.ctrl.creerNouvDiagramme(tabFichierJava);//envoyé un tabString
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
			
			this.entete();
			Console.println("\t\t" +this.col("1",'B')+ " : CREER UN DIAGRAMME DE CLASSE" );
			
			Console.print(this.ctrl.creerNouvConfig(nomFichierConfig, auteur));
			
			Console.print("\t\tAppuyer sur " + this.col("Entrée", 'B') + " pour continuer ...");
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

	}
	
	private void charger(int selec)//permet de charger un diagramme, d'ont la configuration est sauvegardée.
	{
		this.entete();
		
		Console.println("\t\t" +this.col("2",'B')+ " : CHARGER UN DIAGRAMME DE CLASSE\n" );
		
		String[] listeC = this.ctrl.getConfig();//chargement des fichiers
		
		//affichage des fichiers
		if(listeC != null)
		{
			int tMAxFichier  = this.ctrl.getTailleMaxFichier(this.ctrl.repConfig); //recuperation de la taille max dans les fichiers
			if(tMAxFichier < 16) tMAxFichier = 16;//Pour en-tete stable
			this.debTab(tMAxFichier); // création bordure de tableau
			for (int f = 0; f < listeC.length; f++)
			{
				if(f == selec) { Console.print(this.col("\t────>", 'B')); }
				else           { Console.print(this.col("\t     ", 'B')); }
				this.afficherFichier(listeC[f],false, tMAxFichier);
			}
			this.finTab(tMAxFichier);//Fin du tableau
			
			char saisie = this.menuSelection(false);
			if(!this.verifSaisie(saisie, false)) { this.charger(selec); } //si mauvaise saisie false = non menuMulti
			
			if(saisie== '/') { this.menu(); }
			if(saisie== '=')
			{
				String nomFichier = listeC[selec].substring(0, listeC[selec].split("\\|")[0].length());
				Console.print("\n\t\tLecture du fichier : " + this.col(nomFichier, 'B'));
				
				this.entete();
				Console.println("\t\t" +this.col("2",'B')+ " : CHARGER UN DIAGRAMME DE CLASSE" );
				
				Console.print(this.ctrl.getContenuConfig(nomFichier));
				
				Console.print("\t\tAppuyer sur " + this.col("Entrée", 'B') + " pour continuer ...");
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
		else { Console.print(this.col("\tAucune configuration sauvegardée dans le dossier config", 'R')); try {Thread.sleep(tpsDebug);} catch (Exception ex) {} }
	}
	
	private void modifier(int selec)//permet de modifier un fichir de configuration.
	{
		//Affichage de la selection
		this.entete();
		
		Console.println("\t\t" +this.col("3",'B')+ " : MODIFIER UNE CONFIGURATION\n" );
		
		String[] listeC = this.ctrl.getConfig(); //chargement des fichiers
		
		//affichage des fichiers
		if(listeC != null)
		{
			int tMaxConfig  = this.ctrl.getTailleMaxFichier(this.ctrl.repConfig); //recuperation de la taille max dans les fichiers
			if(tMaxConfig < 16) tMaxConfig = 16;//Pour en-tete stable
			this.debTab(tMaxConfig); // création bordure de tableau
			for (int f = 0; f < listeC.length; f++)
			{
				if(f == selec) { Console.print(this.col("\t────>", 'B')); }
				else           { Console.print(this.col("\t     ", 'B')); }
				this.afficherFichier(listeC[f], false, tMaxConfig);
			}
			this.finTab(tMaxConfig);
			
			char saisie = this.menuSelection(false);
			if(!this.verifSaisie(saisie, false)) { this.modifier(selec); } //si mauvaise saisie false = non menuMulti
			
			if(saisie== '/') { this.menu(); }//Annuler
			if(saisie== '=') //Valider
			{
				String nomFichier = listeC[selec].substring(0, listeC[selec].split("\\|")[0].length());
				Console.print("\tOuverture du fichier : " + this.col(nomFichier, 'B'));
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
		else { Console.print(this.col("\tAucune configuration sauvegardée dans le dossier config", 'R')); try {Thread.sleep(tpsDebug);} catch (Exception ex) {} }
	}
	
	private void supprimer(int selec, boolean[] tabSelecSup)//permet de supprimer des fichiers de configuration.
	{
		this.entete();
		
		Console.println("\t\t" +this.col("4",'B')+ " : SUPPRIMER UNE CONFIGURATION\n" );
		
		String[] listeS = this.ctrl.getConfig(); //chargement des fichiers config

		//affichage des fichiers
		if(listeS != null)
		{
			if(tabSelecSup == null)	{ tabSelecSup = new boolean[listeS.length];	}
			
			int tMAxFichier  = this.ctrl.getTailleMaxFichier(this.ctrl.repConfig); //recuperation de la taille max dans les fichiers
			if(tMAxFichier < 16) tMAxFichier = 16;//Pour en-tete stable
			this.debTab(tMAxFichier); // création bordure de tableau
			for (int f = 0; f < listeS.length; f++)
			{
				if(f == selec) { Console.print(this.col("\t────>", 'B')); }
				else           { Console.print(this.col("\t     ", 'B')); }
				this.afficherFichier(listeS[f], tabSelecSup[f], tMAxFichier);
			}
			this.finTab(tMAxFichier);
			
			char saisie = this.menuSelection(true);
			if(!this.verifSaisie(saisie, true)) { this.supprimer(selec, tabSelecSup); } //si mauvaise saisie true = menuMulti
			
			int newSel = selec;
			
			if(saisie== '/') { this.menu(); }
			if(saisie== '.')
			{
				if(!tabSelecSup[selec])
				{
					newSel++;
				}
				tabSelecSup[selec] = !tabSelecSup[selec];
				if(newSel > listeS.length -1) { this.supprimer(0      , tabSelecSup);  }//torique bas
				else                          { this.supprimer(newSel , tabSelecSup); 	}//on descend
			}
			if(saisie == '*')
			{
				for(int i=0;i<tabSelecSup.length; i++)
				{
					if (!tabSelecSup[i]) newSel++;
					tabSelecSup[i] = !tabSelecSup[i];
				}
				this.supprimer(0      , tabSelecSup);
			}
			if(saisie== '=')
			{
				Console.println();
				int cptTrue = 0;
				for(boolean b : tabSelecSup)
					if(b)
						cptTrue++;//compter le nombre de fichiers choisis
				
				if(cptTrue == 0)//pas de marque
				{
					String[] tabFichierConfig = {listeS[selec].substring(0, listeS[selec].split("\\|")[0].length())}; //tableau avec la selection courante
					this.ctrl.supprimerFichiers(tabFichierConfig);
				}
				else
				{
					String[] tabFichierConfig = new String[cptTrue];//contient le nom de tous les fichiers choisis
					
					int cptElt = 0;
					for(int b = 0; b < tabSelecSup.length; b++)
					{
						if(tabSelecSup[b])
						{
							tabFichierConfig[cptElt++] = listeS[b].substring(0, listeS[b].split("\\|")[0].length());//recupere le nom sans les dates de la ligne
						}
					}
					this.ctrl.supprimerFichiers(tabFichierConfig);
				}
			}
			if(saisie== '-')
			{
				newSel--;
				if(newSel < 0) { this.supprimer(listeS.length -1, tabSelecSup);  } //torique haut
				else           { this.supprimer(newSel          , tabSelecSup);  } //on monte
			}
			if(saisie== '+')
			{
				newSel++;
				if(newSel > listeS.length -1) { this.supprimer(0     , tabSelecSup);  }//torique bas
				else                          { this.supprimer(newSel, tabSelecSup);  }//on descend
			}
		}
		else { Console.print(this.col("\tAucune configuration sauvegardée dans le dossier config", 'R')); try {Thread.sleep(tpsDebug);} catch (Exception ex) {} }
	}

	private void parametres(int selec,boolean[] listeP)
	{
		this.entete();
		String[] parametres = {"Afficher diagramme après création","Créer fichier diagramme au format txt",
				"Créer fichier diagramme au format pdf","Supprimer les fichiers diagrammes associés au fichier config"};
		int taille = 14; //Pour en-tete stable
		for (String s : parametres)
		{
			if(s.length()>taille) { taille=s.length(); }
		}

		Console.println("\t\t" +this.col("5",'B')+ " : MODIFIER LES PARAMETRES PROGRAMME\n" );

		Console.println("\t     ┌" + nSep(taille +2, "─")              + "┬" + nSep(13, "─") + "┐");
		Console.println("\t     │" + String.format(" %-"+ taille+ "s " + "│" + " %-11s "     + "│", nSep(taille/2 -"Paramètres".length()/2, " ") + "Paramètres","   État    "));
		Console.println("\t     ├" + nSep(taille +2, "─")              + "┼" + nSep(13, "─") + "┤");

		for (int f = 0; f < listeP.length; f++)
		{
			if(f == selec) { Console.print(this.col("\t────>", 'B')); }
			else           { Console.print(this.col("\t     ", 'B')); }

			if(listeP[f]) Console.println(String.format("│ %-"+taille+"s │ ", parametres[f]) + this.col(" Activé"   ,'V') + nSep(5, " ") + "│");
			else		  Console.println(String.format("│ %-"+taille+"s │ ", parametres[f]) + this.col(" Désactivé",'R') + nSep(2, " ") + "│");

		}
		Console.println("\t     └" + nSep(taille +2, "─")              + "┴" + nSep(13, "─") + "┘");

		char saisie;
		Console.print( "\n\t\t (" +this.col("-", 'B')+ ") : ^ monter\n"
				     +   "\t\t (" +this.col("+", 'B')+ ") : v descendre\n"
				     +   "\t\t (" +this.col(".", 'B')+ ") : . activer/désactiver\n"
		             +   "\t\t (" +this.col("*", 'B')+ ") : * activer/désactiver tout\n"
				     +   "\t\t (" +this.col("=", 'B')+ ") : > valider\n"
				     +   "\t\t (" +this.col("/", 'B')+ ") : < annuler\n"
				     +   "\n\tsaisie :  ");
		Console.print(this.setCE('B'));
		saisie = Character.toUpperCase(this.getChar());
		Console.print(this.setCE(this.coul));
		
		if(!this.verifSaisie(saisie, true)) { this.parametres(selec, listeP); } //si mauvaise saisie true = menuMulti

		int newSel = selec;

		if(saisie== '-')
		{
			newSel--;
			if(newSel < 0) { this.parametres(listeP.length -1, listeP);  } //torique haut
			else           { this.parametres(newSel          , listeP);  } //on monte
		}
		if(saisie== '+')
		{
			newSel++;
			if(newSel > listeP.length -1) { this.parametres(0     , listeP);  }//torique bas
			else                          { this.parametres(newSel, listeP);  }//on descend
		}
		if(saisie== '*')
		{
			int cpt=0;
			for (int f = 0; f < listeP.length; f++) if(listeP[f])cpt++;
			for (int f = 0; f < listeP.length; f++) listeP[f]=(cpt!=listeP.length);

			this.parametres(0,listeP);
		}
		if(saisie== '.')
		{
			listeP[selec]=!listeP[selec];
			newSel++;
			if(newSel > listeP.length -1) { this.parametres(0      , listeP);  }//torique bas
			else                          { this.parametres(newSel , listeP); 	}//on descend
		}
		if(saisie == '=')
		{
			this.ctrl.majOptions(listeP);
			Console.print("\t\tAppuyer sur " + this.col("Entrée", 'B') + " pour continuer ...");
			this.getString();
		}
		if(saisie== '/') { this.menu(); }

	}
	
	private char menuSelection(boolean multi) //affiche un menu de gestion de fichiers et renvoit le choix de l'utilisateur sous forme d'un caractere.
	{
		char choix;
		Console.print( "\n\t\t (" +this.col("-", 'B')+ ") : ^ monter\n"
	                 +   "\t\t (" +this.col("+", 'B')+ ") : v descendre\n" + ((multi)?
	                 (   "\t\t (" +this.col(".", 'B')+ ") : x selectionner/déselectionner\n"
	                 +   "\t\t (" +this.col("*", 'B')+ ") : * selectionner/déselectionner tout\n"):"")
	                 +   "\t\t (" +this.col("=", 'B')+ ") : > valider\n"
	                 +   "\t\t (" +this.col("/", 'B')+ ") : < annuler\n"
	                 +   "\n\tsaisie :  ");
		Console.print(this.setCE('B'));
		choix = Character.toUpperCase(this.getChar());
		Console.print(this.setCE(this.coul));
		return choix;
	}

	private void debTab(int tMAxFichier)//renvoie une Chaine adapté pour l'en-tête des tableaux
	{
		Console.print("\t     ┌" + nSep(tMAxFichier +2, "─")                 + "┬" + nSep(18, "─") + "┬" + nSep(18, "─") + "┐\n");
		Console.print("\t     │" + String.format(" %-" + tMAxFichier  + "s " + "│ %-16s "          + "│ %-16s "          + "│\n", "  NomFichier  ", "    DateCrea", "    DateModif"));
		Console.print("\t     ├" + nSep(tMAxFichier +2, "─")                 + "┼" + nSep(18, "─") + "┼" + nSep(18, "─") + "┤\n");
	}

	private void afficherFichier(String dataConf, boolean selec, int tMAxFichier)//renvoie une Chaine adapté pour la lecture des tableaux
	{
		
		String[] decConfig = dataConf.split("\\|");
		Console.print(String.format("│" + ((selec)? (this.col("x", 'B')):" ") + "%-" + tMAxFichier + "s │ %16s │ %16s │\n", decConfig[0].substring(0,decConfig[0].indexOf(".")),decConfig[1], decConfig[2]));
	}
	
	private void finTab(int tMAxFichier)//renvoie une Chaine adapté pour la fermeture des tableaux
	{
		Console.print("\t     └─" + nSep( tMAxFichier, "─") + "─┴─" + nSep(16, "─") + "─┴─" + nSep(16, "─") + "─┘\n");
	}
	
	private boolean verifSaisie(char saisie, boolean multi)//renvoie un booléen pour savoir si la saisie l'utilisateur dans les menus est bonne.
	{
		if(saisie != '-' && saisie != '+' && saisie != '=' && saisie != '/' && ((multi)? saisie != '.' && saisie != '*' : true))//la saisie ne correspond pas a ces caractères
		{
			Console.println(this.col("\n\tErreur",'R') + " : saisir  (" + this.col("-",'B') + "/" 
		                                                                      + this.col("+",'B') + "/" + ((multi)?
					                                                          ( this.col(".",'B') + "/"
		                                                                      + this.col("*",'B') + "/"):"")
		                                                                      + this.col("=",'B') + "/"
					                                                          + this.col("/",'B') + ")" );
			try {Thread.sleep(tpsDebug);} catch (Exception ex) {}
			return false;//la saisie est fausse                                                               
		}
		return true;// la saisie est bonne
	}
	
	private String nSep(int n, String s)//renvoie une chaine avec le caractere s répété n fois
	{
		String sSep = "";
		for(int i =0; i < n; i++)
			sSep += s;
		
		return sSep;
	}
	
	private String col(String s, char c)//renvoie la coloration d'une Chaine s dans la console avec la couleur du caractère c avec la méthode setCe(char c)
	{
		return setCE(c) +s+  setCE(this.coul);
	}
	
	private String setCE(char c)//renvoie un code en Chaine pour changer la couleur d'ECRITURE dans la console
	{
		switch(c)
		{
			case 'B' : return CouleurConsole.BLEU.getFont ();
			case 'C' : return CouleurConsole.CYAN.getFont ();
			case 'J' : return CouleurConsole.JAUNE.getFont ();
			case 'M' : return CouleurConsole.MAUVE.getFont ();
			case 'N' : return CouleurConsole.NOIR.getFont ();
			case 'R' : return CouleurConsole.ROUGE.getFont();
			case 'V' : return CouleurConsole.VERT.getFont();
			case '#' : return CouleurConsole.BLANC.getFont ();
			default  : return "\033[0m"; //par defaut de l'utilisateur;
		}
	}
	
	private void clear()//nettoyer la console
	{
		try
		{
		     if (System.getProperty("os.name").contains("Windows"))
		         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		     else
		     {
		    	 new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
		     }
		    	 
		}
		catch (Exception ex) {}
	}
	
	private void entete()//affiche la banniere du programme JAVA2UML
	{
		this.clear();
		Console.print(this.setCE(this.coul));
		Console.println( "\t\t\t\t\t                 "  +"                  "+ "         " +"                              " + "\n" +
		                 "\t\t\t\t\t      ,--.  ,---."  +",--.   ,--.,---.  "+ "   ,---. " +"  ,--. ,--.,--.   ,--.,--.    " + "\n" +
		                 "\t\t\t\t\t      |  | /  O  \\"+"\\  `.'  //  O  \\"+"   '.-.  \\"+"  |  | |  ||   `.'   ||  |    " + "\n" + 
		                 "\t\t\t\t\t ,--. |  ||  .-.  |"+ "\\     /|  .-.  |"+ "   .-' .'" +"  |  | |  ||  |'.'|  ||  |    " + "\n" +
		                 "\t\t\t\t\t |  '-'  /|  | |  |"+ " \\   / |  | |  |"+ "  /   '-." +"  '  '-'  '|  |   |  ||  '--. " + "\n" +
		                 "\t\t\t\t\t  `-----' `--' `--'"+  "  `-'  `--' `--'"+ "  '-----'" +"   `-----' `--'   `--'`-----' " + "\n" +
		                 "\t\t\t\t\t                           © 2020 - " + this.col("Innov", 'B') + this.col("Action", 'R') + " - IUT du Havre.\n");
	}
	
	private void afficherInnovAction()//Affiche le logo de l'entreprise InnovAction
	{
		Console.print("\n\n\n\n\n" +
		this.col("                                                           "       +          "              "       +          "                  "      +                            "                                                                            " , '#') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("            *(((/.",'R') + this.col(                  "                                                                            " , '#') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("          /((((/"  ,'R') + this.col(                "                                                                              " , '#')  + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("        ,(((((,"   ,'R') + this.col(               "                                                                               " , '#') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("       /((((("     ,'R') + this.col(             "                                                                                 " , '#') + "\n" +
		this.col("                                                           "       +          "              ", 'N') + this.col("     ,(((((*"      ,'R') + this.col(            "                                                                                  " , '#') + "\n" +
		this.col("    *%%%.  *#%%(.   .%%%,  ,#%%#,    (%%(   *%&@@@@@@@%*   ", '#') + this.col("./###,        ", 'B') + this.col("    /(((((."       ,'R') + this.col(           "  (@@@@@(       .%@@@@@@@@@ .@@@@@@@@@@@@% /@@@.  ,&@@@@@@@@@@&.   /@@@@@,   /@@@. " , '#') + "\n" +
		this.col("    (@@@. &@@@@@@,  ,@@@* (@@@@@@#   %@@% *@@@@@%%%%@@@@@* ", '#') + this.col(" (####/       ", 'B') + this.col("  .(((((*"         ,'R') + this.col(         "   *@@@%@@@*     &@@@#*******  ****%@@@****, /@@@. /@@@%*,,,,*%@@@( ,@@@%@@@.  /@@@. " , '#') + "\n" +
		this.col("    (@@@. &@@#/@@@  ,@@@* (@@@*@@@,  %@@% %@@%        %@@& ", '#') + this.col("  *#####.     ", 'B') + this.col(" *(((((."          ,'R') + this.col(        "   ,@@@( (@@@,   ,@@@*              (@@@.     /@@@. %@@%        #@@& ,@@@*(@@&  /@@@. " , '#') + "\n" +
		this.col("    (@@@. &@@# %@@# ,@@@* (@@@./@@@  %@@% %@@%        #@@& ", '#') + this.col("   .#####/    ", 'B') + this.col("/((((/"            ,'R') + this.col(      "    .&@@#   &@@&   ,@@@,              (@@@.     /@@@. %@@%        #@@& ,@@@* @@@/ /@@@. " , '#') + "\n" +
		this.col("    (@@@. &@@# .@@@,,@@@* (@@@. %@@# %@@% %@@%        #@@& ", '#') + this.col("     /####(.*(", 'B') + this.col("((((,"             ,'R') + this.col(     "     %@@@@@@@@@@@%  ,@@@,              (@@@.     /@@@. %@@%        #@@& ,@@@* .@@@,/@@@. " , '#') + "\n" +
		this.col("    (@@@. &@@#  *@@@*@@@* (@@@. .@@@,%@@% %@@%        %@@& ", '#') + this.col("      .#####((", 'B') + this.col("((/."              ,'R') + this.col(    "     (@@@/*****(@@@/ .@@@(              (@@@.     /@@@. %@@&        %@@% ,@@@*  (@@%/@@@. " , '#') + "\n" +
		this.col("    (@@@. &@@#   &@@@@@@* (@@@.  *@@@@@@% *@@@@&%%%%&@@@@* ", '#') + this.col("        (#####", 'B') + this.col("(*"                ,'R') + this.col(  "      ,@@@/       (@@@, .&@@@@@@@@@@      (@@@.     /@@@. .@@@@@@@@@@@@@@. ,@@@*   &@@@@@@  " , '#') + "\n" +
		this.col("    /&&&. %&&(    ,&&&%.  (&&&.    %&&&*    /@@@@@@@@@@*   ", '#') + this.col("         *####", 'B') + this.col("."                 ,'R') + this.col( "                                                              ,(######(,                     "        + "\n" +
		         "                                                           "       +          "              "       +          ""                        +          "                                                                                              " , '#') + "\n" +
		setCE('*') + "\n\n\n\n\n");
		try {Thread.sleep(tpsAjust);} catch (Exception ex) {}//ce temps pourra être utiliser pour adapter la fenêtre de la console
	}
	
	//Divers méthode de saisie utilisateur avec l'utilisation de la classe Clavier
	private String getString() { return Console.lireString(); }
	private int    getInt   () { return Console.lireInt()   ; }
	private char   getChar  () { return Console.lireChar()  ; }
}
