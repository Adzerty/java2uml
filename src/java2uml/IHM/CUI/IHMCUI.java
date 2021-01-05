package java2uml.IHM.CUI;

import iut.algo.Console;
import iut.algo.CouleurConsole;

import java2uml.Controleur;

/**
 * <b>IHMCUI est la classe qui sert d'intermédiaire entre l'utilisateur et l'application.</b>
 * <p>
 * IHMCUI possède les attributs suivant :
 * <ul>
 * <li>Un caractère qui permet de changer l'écriture du texte de l'application.</li>
 * <li>Un entier qui définit le temps d'affichage des messages d'erreur.</li>
 * <li>Un entier qui définit le temps d'apparition du logo de l'entreprise InnovAction pour régler la taille de la fenêtre.</li>
 * </ul>
 * De plus, IHMCUI possède de nombreux menus intuitifs avec des choix multiples et simples d'utilisation.
 * </p>
 * 
 * @see IHMGUI
 * @see Controleur
 * 
 * @author InnovAction
 * @version 1.0
 */
public class IHMCUI
{
	/**
	 * La couleur d'écriture du programme, définit par défault à blanc avec le caractère '#'.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see IHMCUI#setCE(char c)
	 * @see IHMCUI#col(String s, char c)
	 */
	private char coul;
	
	/**
	 * Le temps écoulé pour lire le message d'erreur, définit par défault à 1500ms soit 1.5s.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see IHMCUI#confirmSup(String fichierSup, boolean supConfig, boolean supDiagTxt, boolean supDiagPdf, boolean optionSup)
	 * @see IHMCUI#menu()
	 * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
	 * @see IHMCUI#charger(int selec)
	 * @see IHMCUI#modifier(int selec)
	 * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
	 * @see IHMCUI#verifSaisie(char saisie, boolean multi)
	 * 
	 */
	private int  tpsDebug;
	
	/**
	 * Le temps écoulé pour afficher le logo d'InnovAction, définit par défault à 2000ms soit 2s.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see IHMCUI#afficherInnovAction()
	 */
	private int  tpsAjust;
	
	/**
	 * Le controleur de l'application qui fait le lien entre la partie métier et la partie IHM.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see IHMCUI#menu()
	 * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
	 * @see IHMCUI#charger(int selec)
	 * @see IHMCUI#modifier(int selec)
	 * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
	 * @see IHMCUI#parametres(int selec,boolean[] listeP)
	 */
	private Controleur ctrl;
	
	/**
     * <b>Constructeur IHMCUI.</b>
     * <p>
     * A l'instanciation d'un objet IHMCUI, le controleur est initialisé. Un Controleur, qui
     * permet d'avoir un acces complet aux différentes méthodes du métier et de faire le lien.
     * </p>
     * 
     * @param ctrl
     *            Le controleur de l'application qui fait le lien entre la partie métier et la partie IHM.
     * 
     * @see ----- Utilise les attributs -----
     * @see IHMCUI#coul
     * @see IHMCUI#tpsDebug
     * @see IHMCUI#tpsAjust
     * @see IHMCUI#ctrl
     */
	public IHMCUI(Controleur ctrl)
	{
		this.coul     = '#';
		
		this.tpsDebug = 1500;
		this.tpsAjust = 2000;
		
		this.ctrl = ctrl;
		
		this.afficherInnovAction();
	}

	/**
     * Lance le menu du mode CUI à partir du Controleur.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see Controleur#Controleur(String type)
     * 
     * @see ----- Utilise les méthodes -----
     * @see IHMCUI#menu()
     * 
     */
	public void start()
	{
		this.menu();
	}
	
	/**
     * Gère les types d'erreurs lors de la suppression et affiche dans la console les différents messages.
     * 
     * @param fichierSup
     * 		Le fichier concerné par la suppression.
     * @param supConfig
     * 		boolean qui définit l'état de la suppression du fichier de configuration.
     * @param supDiagTxt
     * 		boolean qui définit l'état de la suppression du fichier du diagramme au format txt.
     * @param supDiagPdf
     * 		boolean qui définit l'état de la suppression du fichier du diagramme au format pdf.
     * @param optionSup
     * 		boolean qui définit l'état de l'option "Supprimer les fichiers diagrammes associés au fichier configuration".
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see Controleur#supprimerFichiers(String[] tabFichierSup)
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console
     * @see IHMCUI#col(String s, char c)
     * @see Thread
     */
	public void confirmSup(String fichierSup, boolean supConfig, boolean supDiagTxt, boolean supDiagPdf, boolean optionSup)//gestion des types d'erreurs lors de la suppression.
	{
		if (supConfig) //si le fichier de confiuration a pu être supprimé
		{
			if (supDiagTxt && supDiagPdf) //si les diagrammes associés au fichier de configuration ont été supprimés.
			{
				if(optionSup)// si l'option de suppression des fichiers associés est acivée.
				{
					Console.println("\t\t" + this.col("# ", 'V') + "Les fichiers associés à " + this.col(fichierSup, 'B')
		                           +" ont été supprimé avec succès.");
				}
				else
				{
					Console.println("\t\t" + this.col("# ", 'V') + "Le fichier de configuration " + this.col(fichierSup, 'B')
		                           +" à été supprimé sans ses fichiers associés avec succès.");
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
	
	/**
     * Affiche dans la console le menu principal avec les différentes fonctionnalités de l'application
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#start()
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console
     * @see Controleur#compilation()
     * @see Controleur#getOptions()
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * @see IHMCUI#clear()
     * @see IHMCUI#entete()
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#setCE(char c)
     * @see IHMCUI#getInt()
     * @see Thread
     * @see System#exit(int status)
     */
	private void menu()
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

	/**
     * Créé une configuration et génère les diagrammes associés via un menu interactif, avec des déplacements toriques grâce à des appels récursifs.
     * 
     * @param selec
     * 		un entier qui définit la position du fichier courant nécessaire pour le menu récursif.
     * @param tabSelec
     * 		un tableau de booléen qui est nécessaire pour la selection multiple, car il permet de savoir si un fichier a été préalablement sélectionné, pour la création du fichier de configuration.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#menu()
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console
     * @see Controleur#getClasse()
     * @see Controleur#getTailleMaxFichier(String rep)
     * @see Controleur#creerNouvDiagramme(String[] tabNomFichier)
     * @see Controleur#creerNouvConfig(String nomFichier, String nomAuteur)
     * @see IHMCUI#entete()
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#debTab(int tMAxFichier)
     * @see IHMCUI#afficherFichier(String dataConf, boolean selec, int tMAxFichier)
     * @see IHMCUI#finTab(int tMAxFichier)
     * @see IHMCUI#menuSelection(boolean multi)
     * @see IHMCUI#menu()
     * @see IHMCUI#verifSaisie(char saisie, boolean multi)
     * @see IHMCUI#getString()
     * @see String
     * @see Thread
     */
	private void creer(int selec ,boolean[] tabSelec)
	{
		this.entete();
		
		Console.println("\t\t" +this.col("1",'B')+ " : CREER UN DIAGRAMME DE CLASSE\n" );
		
		String[] listeS = this.ctrl.getClasse(); //chargement des fichiers

		//affichage des fichiers
		if(listeS != null)
		{
			if(tabSelec == null) { tabSelec = new boolean[listeS.length]; }
			
			int tMAxFichier  = this.ctrl.getTailleMaxFichier(this.ctrl.repCompile) -6; //recuperation de la taille max dans les fichiers sans le ".class"
			if(tMAxFichier < 16) tMAxFichier = 16;//Pour en-tete stable
			this.debTab(tMAxFichier); // création bordure de tableau
			for (int f = 0; f < listeS.length; f++)
			{
				if(f == selec) { Console.print(this.col("\t────>", 'B')); }
				else           { Console.print(this.col("\t     ", 'B')); }
				this.afficherFichier(listeS[f], tabSelec[f], tMAxFichier);
			}
			this.finTab(tMAxFichier);
		

			char saisie = this.menuSelection(true);
			if(!this.verifSaisie(saisie, true)) { this.creer(selec, tabSelec); } //si mauvaise saisie true = menuMulti
			
			int newSel = selec;
			
			if(saisie== '/') {}
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
					
					this.ctrl.creerNouvDiagramme(tabFichierJava);
				}
	
				Console.print("\n\t\tAuteur      : ");
				Console.print(this.setCE('B'));
				String auteur = getString() ;
				Console.print(this.setCE(this.coul));
				
				if(auteur.equals("")) { auteur = "?"; }//pour éviter null
				
				Console.print("\t\tNom Fichier : ");
				Console.print(this.setCE('B'));
				String nomFichierConfig = getString() ;
				Console.print(this.setCE(this.coul));
				
				if(nomFichierConfig.equals("")) { nomFichierConfig = "nouveau"; }//pour éviter null
				
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
		else { Console.print(this.col("\tAucune classe java dans le repertoire fichierJava", 'R')); try {Thread.sleep(tpsDebug);} catch (Exception ex) {} }

	}
	
	/**
     * Charge un diagramme, dont la configuration est sauvegardée via un menu interactif, avec des déplacements toriques grâce à des appels récursifs.
     * 
     * @param selec
     * 		un entier qui définit la position du fichier courant nécessaire pour le menu récursif.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#menu()
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console
     * @see Controleur#getConfig()
     * @see Controleur#getTailleMaxFichier(String rep)
     * @see Controleur#getContenuConfig(String nomFichier)
     * @see IHMCUI#entete()
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#debTab(int tMAxFichier)
     * @see IHMCUI#afficherFichier(String dataConf, boolean selec, int tMAxFichier)
     * @see IHMCUI#finTab(int tMAxFichier)
     * @see IHMCUI#menuSelection(boolean multi)
     * @see IHMCUI#menu()
     * @see IHMCUI#verifSaisie(char saisie, boolean multi)
     * @see IHMCUI#getString()
     * @see String
     * @see Thread
     */
	private void charger(int selec)
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
			
			if(saisie== '/') {}
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
	
	/**
     * Ouvre un fichier de configuration dans l'éditeur de texte par défaut via un menu interactif, avec des déplacements toriques grâce à des appels récursifs.
     * 
     * @param selec
     * 		un entier qui définit la position du fichier courant nécessaire pour le menu récursif.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#menu()
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console
     * @see Controleur#getConfig()
     * @see Controleur#getTailleMaxFichier(String rep)
     * @see Controleur#ouvrirEnEdit(String nomFichier)
     * @see IHMCUI#entete()
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#debTab(int tMAxFichier)
     * @see IHMCUI#afficherFichier(String dataConf, boolean selec, int tMAxFichier)
     * @see IHMCUI#finTab(int tMAxFichier)
     * @see IHMCUI#menuSelection(boolean multi)
     * @see IHMCUI#menu()
     * @see IHMCUI#verifSaisie(char saisie, boolean multi)
     * @see IHMCUI#getString()
     * @see String
     * @see Thread
     */
	private void modifier(int selec)
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
			
			if(saisie== '/') {}//Annuler
			if(saisie== '=') //Valider
			{
				String nomFichier = listeC[selec].substring(0, listeC[selec].split("\\|")[0].length());
				Console.println("\n\t\tOuverture du fichier : " + this.col(nomFichier, 'B'));
				this.ctrl.ouvrirEnEdit(nomFichier);
				
				Console.print("\n\t\tAppuyer sur " + this.col("Entrée", 'B') + " pour continuer ...");
				this.getString();
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
	
	/**
     * Permet de supprimer des fichiers de configuration et ses diagrammes via un menu interactif, avec des déplacements toriques grâce à des appels récursifs.
     * 
     * @param selec
     * 		un entier qui définit la position du fichier courant nécessaire pour le menu récursif.
     * @param tabSelecSup
     * 		un tableau de booléen qui est nécessaire pour la selection multiple, car il permet de savoir si un fichier a été préalablement sélectionné, pour être supprimé.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * @see IHMCUI#menu()
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console
     * @see Controleur#getConfig()
     * @see Controleur#getTailleMaxFichier(String rep)
     * @see Controleur#supprimerFichiers(String[] tabFichierSup)
     * @see IHMCUI#entete()
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#debTab(int tMAxFichier)
     * @see IHMCUI#afficherFichier(String dataConf, boolean selec, int tMAxFichier)
     * @see IHMCUI#finTab(int tMAxFichier)
     * @see IHMCUI#menuSelection(boolean multi)
     * @see IHMCUI#menu()
     * @see IHMCUI#verifSaisie(char saisie, boolean multi)
     * @see String
     * @see Thread
     */
	private void supprimer(int selec, boolean[] tabSelecSup)
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
			
			if(saisie== '/') {}
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

	/**
     * Permet de modifier les options du programme via un menu interactif, avec des déplacements toriques grâce à des appels récursifs.
     * 
     * @param selec
     * 		un entier qui définit la position du fichier courant nécessaire pour le menu récursif.
     * @param listeP
     * 		un tableau de booléen associé aux différentes options du programme.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#parametres(int selec,boolean[] listeP)
     * @see IHMCUI#menu()
     * 
     * @see ----- Utilise les méthodes -----
     * @see Character#toUpperCase(char ch)
     * @see Console
     * @see Controleur#majOptions(boolean[] options)
     * @see IHMCUI#entete()
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#nSep(int n, String s)
     * @see IHMCUI#getChar()
     * @see IHMCUI#menu()
     * @see IHMCUI#verifSaisie(char saisie, boolean multi)
     * @see String
     */
	private void parametres(int selec,boolean[] listeP)
	{
		this.entete();
		String[] parametres = {"Afficher diagramme après création","Créer fichier diagramme au format txt",
				"Créer fichier diagramme au format pdf","Supprimer les fichiers diagrammes associés au fichier configuration"};
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
		}
		if(saisie== '/') {}

	}
	 
	/**
     * Affiche un menu de gestion de fichiers avec les différentes actions possibles et renvoie le choix de l'utilisateur par son caractère.
     * Un menu pour des selections multiples est disponible avec le paramètre booléen multi.
     * 
     * @param multi
     * 		Une condition booléenne qui active l'option des menus à choix multiples pour des raisons ergonomiques
     * 
     * @return l'action de l'utilisateur, sous la forme d'un caractère.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * 
     * @see ----- Utilise les méthodes -----
     * @see Character#toUpperCase(char ch)
     * @see Console#print()
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#getChar()
     * @see IHMCUI#setCE(char c)
     */
	private char menuSelection(boolean multi)
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

	/**
     * Affiche une chaine dans la console, d'une en-tête de tableau,
     *  avec une largeur définit en fonction du plus grand fichier que contiendra le tableau.
     * 
     * @param tMAxFichier
     * 		un entier qui définie la taille de la chaine du plus grand nom de fichier dans le tableau.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * 
     * @see ----- Utilise les méthodes -----
     * @see String
     * @see Console#println()
     */
	private void debTab(int tMAxFichier)
	{
		Console.print("\t     ┌" + nSep(tMAxFichier +2, "─")                 + "┬" + nSep(18, "─") + "┬" + nSep(18, "─") + "┐\n");
		Console.print("\t     │" + String.format(" %-" + tMAxFichier  + "s " + "│ %-16s "          + "│ %-16s "          + "│\n", "  NomFichier  ", "    DateCrea", "    DateModif"));
		Console.print("\t     ├" + nSep(tMAxFichier +2, "─")                 + "┼" + nSep(18, "─") + "┼" + nSep(18, "─") + "┤\n");
	}

	/**
     * Affiche une chaine dans la console, adapté pour la lecture des tableaux,
     *  avec une largeur définit en fonction du plus grand fichier que contiendra le tableau.
     * 
     * @param dataConf
     * 		une chaine sous la forme : | Nom de la Configuration | Date de Création | Date de Modification |
     * @param selec
     * 		un booléen qui permet de savoir si la ligne actuelle est sélectionnée.
     * @param tMAxFichier
     * 		un entier qui définie la taille de la chaine du plus grand nom de fichier dans le tableau.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * 
     * @see ----- Utilise les méthodes -----
     * @see String
     * @see Console#println()
     */
	private void afficherFichier(String dataConf, boolean selec, int tMAxFichier)
	{
		String[] decConfig = dataConf.split("\\|");
		Console.println(String.format("│" + ((selec)? (this.col("x", 'B')):" ") + "%-" + tMAxFichier + "s │ %16s │ %16s │", decConfig[0].substring(0,decConfig[0].indexOf(".")),decConfig[1], decConfig[2]));
	}
	
	/**
     * Affiche une chaine dans la console, adapté pour la fermeture des tableaux,
     *  avec une largeur définit en fonction du plus grand fichier que contiendra le tableau.
     * 
     * @param tMAxFichier
     * 		un entier qui définie la taille de la chaine du plus grand nom de fichier dans le tableau.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * 
     * @see ----- Utilise les méthodes -----
     * @see IHMCUI#nSep(int n, String s)
     * @see Console#println()
     */
	private void finTab(int tMAxFichier)
	{
		Console.println("\t     └─" + nSep( tMAxFichier, "─") + "─┴─" + nSep(16, "─") + "─┴─" + nSep(16, "─") + "─┘");
	}
	
	/**
     * Renvoie un booléen pour savoir si la saisie l'utilisateur dans les menus est bonne.
     * 
     * @param saisie
     * 		le caractère que l'utilisateur à tappé.
     * @param multi
     * 		le booleen qui permet de savoir si la saisie pour des menus de multi selections est à vérifier.
     * 
     * @return un boolean :
     * <ul>
     * <li>false → la saisie est fausse;</li>
     * <li>true  → la saisie est bonne;</li> 
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * @see IHMCUI#parametres(int selec,boolean[] listeP)
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console#println()
     * @see IHMCUI#col(String s, char c)
     * @see Thread
     */
	private boolean verifSaisie(char saisie, boolean multi)
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
	
	/**
     * Renvoie une chaine avec la chaine s répété n fois
     * 
     * @param n
     * 		l'entier qui définit le nombre de fois que la chaine s sera répété.
     * @param s
     * 		la chaine qui sera répété n fois
     * 
     * @return la chaine qui a été répété n fois a la suite
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#debTab(int tMAxFichier)
     * @see IHMCUI#finTab(int tMAxFichier)
     * @see IHMCUI#parametres(int selec,boolean[] listeP)
     */
	private String nSep(int n, String s)
	{
		String sSep = "";
		for(int i =0; i < n; i++)
			sSep += s;
		
		return sSep;
	}
	
	/**
     * Renvoie la coloration d'une Chaine s dans la console avec la couleur du caractère c via la méthode setCE(char c)
     * 
     * @param c
     * 		caractère qui definit quel code couleur on souhaite récupérer
     * 
     * @return le code couleur choisie pour la console, sous la forme d'une Chaine.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#confirmSup(String fichierSup, boolean supConfig, boolean supDiagTxt, boolean supDiagPdf, boolean optionSup)
     * @see IHMCUI#menu()
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * @see IHMCUI#parametres(int selec,boolean[] listeP)
     * @see IHMCUI#menuSelection(boolean multi)
     * @see IHMCUI#afficherFichier(String dataConf, boolean selec, int tMAxFichier)
     * @see IHMCUI#verifSaisie(char saisie, boolean multi)
     * @see IHMCUI#entete()
     * @see IHMCUI#afficherInnovAction()
     * 
     * @see ----- Utilise les méthodes -----
     * @see IHMCUI#setCE(char c)
     */
	private String col(String s, char c)
	{
		return setCE(c) +s+  setCE(this.coul);
	}
	
	/**
     * Renvoie sous forme de Chaine des codes couleurs pour changer la couleur d'écriture dans la console via la classe CouleurConsole de l'archive iut.algo.
     * 
     * @param c
     * 		caractère qui definit quel code couleur on souhaite récupérer
     * 
     * @return le code couleur choisie pour la console, sous la forme d'une Chaine.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#menu()
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#parametres(int selec,boolean[] listeP)
     * @see IHMCUI#menuSelection(boolean multi)
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#entete()
     * @see IHMCUI#afficherInnovAction()
     * 
     * @see ----- Utilise les méthodes -----
     * @see CouleurConsole
     */
	private String setCE(char c)
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
	
	/**
     * Execute une commande dans la console en fonction du système d'exploitation qui efface la console.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#menu()
     * @see IHMCUI#entete()
     * @see IHMCUI#afficherInnovAction()
     * 
     * @see ----- Utilise les méthodes -----
     * @see System
     * @see ProcessBuilder
     */
	private void clear()
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
	
	/**
     * Affiche sous forme d'une chaine dans la console, la banniere du programme JAVA2UML
     *  sous forme d'en-tête.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#menu()
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#modifier(int selec)
     * @see IHMCUI#supprimer(int selec, boolean[] tabSelecSup)
     * @see IHMCUI#parametres(int selec,boolean[] listeP)
     * 
     * @see ----- Utilise les méthodes -----
     * @see IHMCUI#clear()
     * @see IHMCUI#col(String s, char c)
     * @see Console
     */
	private void entete()
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
	
	/**
     * Affiche sous forme d'une chaine dans la console, le logo d'InnovAction
     *  sous sa charte graphique.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#IHMCUI(Controleur ctrl)
     * 
     * @see ----- Utilise les méthodes -----
     * @see IHMCUI#col(String s, char c)
     * @see IHMCUI#clear()
     * @see IHMCUI#setCE(char c)
     * @see Thread
     */
	private void afficherInnovAction()
	{
		this.clear();
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
	
	/**
     * Demande à l'utilisateur une Chaine de caractère via la classe Console de l'archive iut.algo.
     * 
     * @return la Chaine saisie par l'utilisateur au clavier.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     * @see IHMCUI#charger(int selec)
     * @see IHMCUI#modifier(int selec)
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console#lireString()
     */
	private String getString() { return Console.lireString(); }
	
	/**
     * Demande à l'utilisateur un entier via la classe Console de l'archive iut.algo.
     * 
     * @return l'entier saisie par l'utilisateur au clavier.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#menu()
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console#lireInt()
     */
	private int    getInt   () { return Console.lireInt()   ; }
	
	/**
     * Demande à l'utilisateur un caractère via la classe Console de l'archive iut.algo.
     * 
     * @return le caractère saisie par l'utilisateur au clavier.
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see IHMCUI#menuSelection(boolean multi)
     * @see IHMCUI#parametres(int selec,boolean[] listeP)
     * 
     * @see ----- Utilise les méthodes -----
     * @see Console#lireChar()
     */
	private char   getChar  () { return Console.lireChar()  ; }
}
