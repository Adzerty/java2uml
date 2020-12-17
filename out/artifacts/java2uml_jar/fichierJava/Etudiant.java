package utilitaire;

public class Etudiant implements Comparable<Etudiant>
{
	/*-------------------------------*/
	/* Attributs                     */
	/*-------------------------------*/
	private String nom;
	private String prenom;

	private double moyenne;


	/*-------------------------------*/
	/* Constructeur                  */
	/*-------------------------------*/

	public Etudiant (String nom, String prenom, double moyenne)
	{
		this.nom     = nom;
		this.prenom  = prenom;
		this.moyenne = moyenne;
	}

	/*-------------------------------*/
	/* Accesseurs                    */
	/*-------------------------------*/
	public String getNom    (){ return nom;     }
	public String getPrenom (){ return prenom;  }
	public double getMoyenne(){ return moyenne; }


	/*-------------------------------*/
	/* MÃ©thodes Standards            */
	/*-------------------------------*/

	// Nous dÃ©finissions ici un "ordre naturel" de comparaison
	// selon la valeur de la moyenne
	public int compareTo ( Etudiant e )
	{
		if (moyenne > e.moyenne ) return  1;		//@1
		if (moyenne < e.moyenne ) return -1;
		return 0;
	}

	// La mÃ©thode Ã©quals doit respecter l' "ordre naturel".
	public boolean equals ( Etudiant e )
	{
		return e.compareTo(this) == 0;
	}

	public String toString()
	{
		return this.prenom + " " + this.nom + " (" + this.moyenne + ")";
	}

}


/*----*/
/* @1 */
/*----------------------------------------------------------------------------*/
/* Nous pourrions                                                             */
/* - pour des moyennes ne comportant qu'un chiffre aprÃ¨s la virgule Ã©crire    */
/*   l'unique instruction :                                                   */
/*       return (int) (moyenne*10 - e.moyenne*10);                            */
/*                                                                            */
/* - pour des moyennes ne comportant que deux chiffres aprÃ¨s la virgule Ã©crire*/
/*   l'unique instruction :                                                   */
/*       return (int) (moyenne*100 - e.moyenne*100);                          */
/*                                                                            */
/* Mais attention une diffÃ©rence au delÃ  de ces prÃ©cisions donnerait un       */
/* rÃ©sultat Ã©gal Ã  0,                                                         */
/* les deux notes seraient donc considÃ©rÃ©es comme Ã©gales                      */
/*----------------------------------------------------------------------------*/