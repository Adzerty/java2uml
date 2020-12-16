/** Mystere n°3
  * date : 18/11/2019
  *	@author : P. Le Pivert + A.Pestel, A.Godefroy, C.Saint-Requier
  */

import iut.algo.Clavier;

public class Mystere
{
	public static void main(String[] a)
	{
		/*---------------*/
		/* Données       */
		/*---------------*/

		int[] tabTirage;

		int[] tirageJoueur1, tirageJoueur2;    // permet de stocker les 2 faces pour chaque joueur

		int cptLig = 0,cptCol = 0;

		int   premierJoueur = 1;

		int[][] grille;

		/*---------------*/
		/* Instructions  */
		/*---------------*/

		// initialisations
		// ---------------

		tirageJoueur1 = new int[2];
		tirageJoueur2 = new int[2];

		grille = Mystere.initGrille();

		// Lancement de la partie
		// ----------------------

		while ( true )
		{

			// tirage au sort d'une face pour chaque dé
			tabTirage = Mystere.lancerDes();

			// Verification de la fin de partie 

			// Choix des Joueurs
			Mystere.choixJoueurs ( premierJoueur, tabTirage, tirageJoueur1, tirageJoueur2 );

			// Affichage de la main de chaque Joueur
			
			System.out.println ( "Joueur 1 : " +  tirageJoueur1[0] + " " + tirageJoueur1[1] );
			System.out.println ( "Joueur 2 : " +  tirageJoueur2[0] + " " + tirageJoueur2[1] );

			// Positionnement des dés de chaque Joueur
			Mystere.positionnerDes ( premierJoueur, tirageJoueur1, tirageJoueur2, grille );
			
			if (premierJoueur == 1)
			{
				premierJoueur = 2;
			}
			else
			{
				if(premierJoueur == 2)
				{
					premierJoueur = 1;
				}
			}
		}
	}


	/** InitGrille
	  * initialise la grille sur laquelle les joueurs vont positionner
	  * leurs dés.
	  * une case à -1 signifiant qu'elle est est libre
	  * la case centrale est à zéro, on peut placer orthogonalement
	  * n'importe quelle valeur autour.
	  */
	private static int[][] initGrille ()
	{
		int[][] gr = new int[5][7];

		// cases non affectés
		for (int lig=0; lig<gr.length; lig++ )
			for (int col=0; col<gr[lig].length; col++ )
				gr[lig][col] = -1;

		// case du centre
		gr[2][3] = 0;

		return gr;
	}


	/** tabEnChaine
	  * retourne une chaine correspondant à une
	  * représentation sous forme de grille  du
	  * tableau tab à deux dimensions
	  * Les valeurs -1 sont afficher sous  forme de blanc
	  * La  valeur   0 est  remplacée par XX
	  */
	private static String tabEnChaine (int[][] tab)
	{
		String ligneHori = String.format ("%"+tab[0].length+"s","").replaceAll (" ","+--" ) + "+";
		String sRet = "   ";

		// enTete de colonne
		for ( int cpt=0; cpt<tab[0].length;cpt++)
			sRet += (char)(cpt+'A') + "  ";

		sRet += "\n  " + ligneHori + "\n";

		// traitement de chaque ligne
		for (int lig=0; lig<tab.length; lig++ )
		{
			sRet += (lig+1) + " |";

			for (int col=0; col<tab[lig].length; col++ )
				switch ( tab[lig][col] )
				{
					case -1 : sRet += "  |";   break;
					case  0 : sRet += "XX|";   break;
					default : sRet += tab[lig][col] + "|";
				}

			sRet += "\n  " + ligneHori + "\n";
		}

		return sRet;
	}


	/** tabEnChaine
	  * retourne une chaine correspondant à une
	  * représentation sous forme de grille  du
	  * tableau tab
	  * Les valeurs 0 sont afficher sous  forme
	  * de blanc
	  */
	private static String tabEnChaine (int[] tab)
	{
		String sRet;
		String sLigneHori;

		// construction de la ligne horizontale +--+--+...
		// en fonction de la taille du tableau
		sLigneHori = "";
		for (int cpt=0; cpt<tab.length; cpt++ )
			sLigneHori +=  "  +" + "--+";


		// Constrution de la chaine de retour
		sRet = "\n" + sLigneHori +  "\n";

		for (int cpt=0; cpt<tab.length; cpt++ )
			if ( tab[cpt] == 0 ) sRet += "  |" + "  |"          ;
			else                 sRet += "  |" + tab[cpt] + "|" ;

		sRet += "\n" + sLigneHori + "\n";

		for (int cpt=0; cpt<tab.length; cpt++ )
			sRet +=  "    " + cpt + " ";

		sRet += "\n";


		return sRet;
	}


	/** lancerDes
	  * retourne un tableau de 4 entiers, correspondant au lancer
	  * des 4 dés
	  */
	private static int[] lancerDes ()
	{
		int[] de1, de2, de3, de4;
		int[] tabRetour;

		de1 = new int[] { 10, 20, 60, 20, 41, 71 };
		de2 = new int[] { 20, 50, 60, 30, 41, 71 };
		de3 = new int[] { 10, 61, 20, 21, 40, 70 };
		de4 = new int[] { 20, 32, 60, 52, 40, 70 };

		tabRetour= new int[4];

		tabRetour[0] = de1 [ (int) ( Math.random() * 6 ) ];
		tabRetour[1] = de2 [ (int) ( Math.random() * 6 ) ];
		tabRetour[2] = de3 [ (int) ( Math.random() * 6 ) ];
		tabRetour[3] = de4 [ (int) ( Math.random() * 6 ) ];

		return tabRetour;
	}


	/** choixJoueurs
	  * Cette méthode permet de remplir le choix des deux joueurs.
	  * Ces choix seront placés dans les paramètres tirageJoueur1 et tirageJoueur2.
	  * Ils choisiront leur valeurs à partir de tabTirage, une fois choisie chaque
	  * case sera mise à 0
	  */
	private static void choixJoueurs ( int premierJoueur, int[] tabTirage, int[] tirageJoueur1, int[] tirageJoueur2 )
	{
		int indice, dernierIndice;
		int deuxiemeJoueur = 3-premierJoueur;

		int[] tempPremierJoueur, tempDeuxiemeJoueur;


		if ( premierJoueur == 1 )
		{
			tempPremierJoueur  = tirageJoueur1;
			tempDeuxiemeJoueur = tirageJoueur2;
			
		}
		else
		{
			tempPremierJoueur  = tirageJoueur2;
			tempDeuxiemeJoueur = tirageJoueur1;
		}

		
		dernierIndice = 6;

		// Premier choix
		System.out.println ( Mystere.tabEnChaine ( tabTirage ) );

		indice = Mystere.choix ( premierJoueur, tabTirage );
		tempPremierJoueur[0] = tabTirage[indice];
		tabTirage[indice] = 0;

		dernierIndice -= indice;

		// Deuxième choix
		System.out.println ( Mystere.tabEnChaine ( tabTirage ) );

		indice = Mystere.choix ( deuxiemeJoueur, tabTirage );
		tempDeuxiemeJoueur[0] = tabTirage[indice];
		tabTirage[indice] = 0;

		dernierIndice -= indice;

		// Troisième choix
		System.out.println ( Mystere.tabEnChaine ( tabTirage ) );

		indice = Mystere.choix ( deuxiemeJoueur, tabTirage );
		tempDeuxiemeJoueur[1] = tabTirage[indice];
		tabTirage[indice] = 0;

		dernierIndice -= indice;


		// Quatrième choix ==> Automatique

		System.out.println ( Mystere.tabEnChaine ( tabTirage ) );

		tempPremierJoueur[1] = tabTirage[dernierIndice];
		tabTirage[dernierIndice] = 0;
		
		if ( premierJoueur == 1 )
		{
			premierJoueur = 2;
			
		}
		else
		{
			premierJoueur = 1;
		}


	}




	/** choix
	  * demande au joueur numJoueur de choisir l'indice  d'une  case
	  * du tableau tab.
	  * une valeur 0 dans une case signifiant que celle-ci est vide,
	  * elle ne peut donc pas être sélectionnée.
	  * retourne l'indice choisi par l'utilisateur
	  */
	private static int choix ( int numJoueur, int[] tab)
	{
		int indice;

		System.out.print ( "Joueur " + numJoueur + " faites votre choix : " );

		indice = Clavier.lire_int();

		while ( indice < 0 || indice >= tab.length || tab[indice] == 0 )
		{
			System.out.print ( " choix invalide recommencez : " );
			indice = Clavier.lire_int();
		}

		return indice;
	}


	/** positionnerDes
         demande à chaque joueur de positionner leurs dés sur la grille
	  */
	public static void positionnerDes ( int premierJoueur, int[] tirageJoueur1, int[] tirageJoueur2, int[][] gr)
	{
		Coord coord1, coord2;

		int deuxiemeJoueur = 3-premierJoueur;

		int[] tempPremierJoueur, tempDeuxiemeJoueur;


		if ( premierJoueur == 1 )
		{
			tempPremierJoueur  = tirageJoueur1;
			tempDeuxiemeJoueur = tirageJoueur2;
		}
		else
		{
			tempPremierJoueur  = tirageJoueur2;
			tempDeuxiemeJoueur = tirageJoueur1;
		}

		System.out.println ( Mystere.tabEnChaine ( gr ) );


		// Saisie des coordonnées pour les dés du premier Joueur
		coord1 = Mystere.choixCoord ( premierJoueur, 1 );
		coord2 = Mystere.choixCoord ( premierJoueur, 2 );
		
		while(Mystere.testCdtsPlacement(coord1.getCol(), coord1.getLig(), coord2.getCol(), coord2.getLig(), gr, tempPremierJoueur) == false)
		{
			System.out.println("erreur ressaisissez");
			
			coord1 = Mystere.choixCoord ( premierJoueur, 1 );
			coord2 = Mystere.choixCoord ( premierJoueur, 2 );		
		}


		// positionnement des dés du premier joueur
		gr[coord1.getLig()][coord1.getCol()] = tempPremierJoueur[0];
		gr[coord2.getLig()][coord2.getCol()] = tempPremierJoueur[1];


		System.out.println ( Mystere.tabEnChaine ( gr ) );

		// Saisie des coordonnées pour les dés deuxième Joueur
		coord1 = Mystere.choixCoord ( deuxiemeJoueur, 1 );
		coord2 = Mystere.choixCoord ( deuxiemeJoueur, 2 );
		
		while(Mystere.testCdtsPlacement(coord1.getCol(), coord1.getLig(), coord2.getCol(), coord2.getLig(), gr, tempDeuxiemeJoueur) == false)
		{
			System.out.println("erreur ressaisissez");
			coord1 = Mystere.choixCoord ( deuxiemeJoueur, 1 );
			coord2 = Mystere.choixCoord ( deuxiemeJoueur, 2 );		
		}
		

		// positionnement des dés du deuxieme joueur
		gr[coord1.getLig()][coord1.getCol()] = tempDeuxiemeJoueur[0];
		gr[coord2.getLig()][coord2.getCol()] = tempDeuxiemeJoueur[1];
		
		System.out.println ( Mystere.tabEnChaine ( gr ) );
	}


	/** choixCoord
	  * place dans une objet unique Coord les deux coordonnées d'une case.
	  * Ces coordonnées sont des coordonnées informatique cad de 0 à taille-1
	  */
	public static Coord choixCoord (int numJoueur, int numDe)
	{
		int  colUser;
		int  ligUser;

		System.out.println ( "joueur " + numJoueur + " votre dé n° " + numDe );

		System.out.print   ( "     col : " );

		colUser = (int)(Clavier.lire_char()-'A');
		while(colUser<0 || colUser>=7)
		{
			System.out.println("ressaisissez la colonne");
			colUser = (int)(Clavier.lire_char()-'A');
		}

		System.out.print   ( "     lig : " );

		ligUser = Clavier.lire_int()-1;
		while(ligUser<0 || ligUser>=5)
		{
			System.out.println("ressaisissez la ligne");
			ligUser = Clavier.lire_int()-1;
		}

		return new Coord ( colUser, ligUser );
	}
	
	
	/** testCdtsPlacement
	  * verifie que toutes les conditions de placements soient respectée pour placer
	  * un dé
	  */
	private static boolean testCdtsPlacement (  int coord1Col, int coord1Lig, 
												int coord2Col, int coord2Lig, 
												int[][] grille, int[] tirageJoueur)
	{
		boolean boolTest;
		int correctionLig = 0, correctionCol = 0;
		
		
		boolTest = true;

		
		//VERIFIE QUE LE DE1 OU LE DE2 N'EST PAS SUR LA CASE DU CENTRE
		if( (coord1Col == 3 && coord1Lig == 2) || (coord2Col == 3 && coord2Lig == 2) )
		{
			boolTest = false;
		}

		//System.out.println("----boolTest1  "+boolTest);
		
		
		//VERIFIE QUE LES DEUX DES SONT PLACES DE FACON CONTIGUE 
		
		if( Mystere.sontContigues(coord1Col, coord1Lig, coord2Col, coord2Lig) == false)
		{
			boolTest = false;
		}

		
		//VERIFIE SI LES DES SONT PLACES DE MANIERE ORTHOGONALE A LA CASE DU CENTRE
		if( (coord1Col != 2 || coord1Lig != 2) && 
			(coord1Col != 3 || coord1Lig != 1) &&
			(coord1Col != 3 || coord1Lig != 3) && 
			(coord1Col != 4 || coord1Lig != 2) &&
			(coord2Col != 2 || coord2Lig != 2) && 
			(coord2Col != 3 || coord2Lig != 1) &&
			(coord2Col != 3 || coord2Lig != 3) && 
			(coord2Col != 4 || coord2Lig != 2) )
		{
		
			//VERIFIE SI AU MOINS UN DES DEUX DES A UN DE DE LA MEME VALEURE AUTOUR DE LUI
			if ((Mystere.desAutour(coord1Col, coord1Lig, grille, tirageJoueur[0]) == false) && 
				(Mystere.desAutour(coord2Col, coord2Lig, grille, tirageJoueur[1]) == false)) 
			{
				boolTest = false;
			}
		
		}
		
		
		//VERIFIE QUE LES DES SONT PLACES DANS DES CASES VIDES
		if((grille[coord1Lig][coord1Col] != -1 || grille[coord2Lig][coord2Col] != -1) ||
			((coord1Lig == coord2Lig) && (coord1Col == coord2Col)))
		{	
			boolTest = false;
		}
		
		
			
		
		
		return boolTest;
	}

	/** desAutour
	  * verifie qu'au moins un dé a un dé de la meme valeure placé autour
	  */
	private static boolean desAutour ( int coordCol, int coordLig, int[][] grille, int valeurTestee)
	{
		boolean boolTest = false;
		
		switch(coordLig)
		{
			case 0  :

				switch(coordCol)
				{
					case 0  :
						if( (grille[coordLig][coordCol+1] == valeurTestee) ||
							(grille[coordLig+1][coordCol] == valeurTestee))
						{
							boolTest = true;
						}
						break;

					case 6  :
						if( (grille[coordLig][coordCol-1] == valeurTestee) ||
							(grille[coordLig+1][coordCol] == valeurTestee))
						{
							boolTest = true;
						}
						break;

					default :
						if( (grille[coordLig][coordCol+1] == valeurTestee) ||
							(grille[coordLig+1][coordCol] == valeurTestee) ||
							(grille[coordLig][coordCol-1] == valeurTestee))
						{
							boolTest = true;
						}
						break;
				}
				break;

			case 4  :
				switch(coordCol)
				{
					case 0  :
						if( (grille[coordLig][coordCol+1] == valeurTestee) ||
							(grille[coordLig-1][coordCol] == valeurTestee))
						{
							boolTest = true;
						}
						break;

					case 6  :
						if( (grille[coordLig][coordCol-1] == valeurTestee) ||
							(grille[coordLig-1][coordCol] == valeurTestee))
						{
							boolTest = true;
						}
						break;

					default :
						if( (grille[coordLig][coordCol+1] == valeurTestee) ||
							(grille[coordLig-1][coordCol] == valeurTestee) ||
							(grille[coordLig][coordCol-1] == valeurTestee))
						{
							boolTest = true;
						}
						break;
				}
				break;

			default :
				switch(coordCol)
				{
					case 0  :
						if( (grille[coordLig][coordCol+1] == valeurTestee) ||
							(grille[coordLig+1][coordCol] == valeurTestee) ||
							(grille[coordLig-1][coordCol] == valeurTestee))
						{
							boolTest = true;
						}
						break;

					case 6  :
						if( (grille[coordLig][coordCol-1] == valeurTestee) ||
							(grille[coordLig+1][coordCol] == valeurTestee) ||
							(grille[coordLig-1][coordCol] == valeurTestee))
						{
							boolTest = true;
						}
						break;

					default :
						if( (grille[coordLig][coordCol+1] == valeurTestee) ||
							(grille[coordLig+1][coordCol] == valeurTestee) ||
							(grille[coordLig][coordCol-1] == valeurTestee) ||
							(grille[coordLig-1][coordCol] == valeurTestee))
						{
							boolTest = true;
						}
						break;
				}
				break;
		}

		return boolTest;
	}

	/** sontContigues
	  * verifie que les deux dés sont placés de manière contigue
	  */
	private static boolean sontContigues ( int coord1Col, int coord1Lig, int coord2Col, int coord2Lig)
	{
		boolean boolTest = false;
		
		if( (coord1Col == coord2Col && (coord1Lig == (coord2Lig - 1) || coord1Lig == (coord2Lig + 1))) ||
			(coord1Lig == coord2Lig && (coord1Col == (coord2Col - 1) || coord1Col == (coord2Col + 1))))
		{
			boolTest = true;
		}
		return boolTest;
	}
}


