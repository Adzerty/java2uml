/** [Class Hanoï]
 * TP2 exo4
 * @author Coatanhay Victor
 * @version 1 du 09/09/20
**/

	/*---------------*/
	/*    Import     */
	/*---------------*/
	
	import iut.algo.*;

//javac @compile.list -d %REDIR%

public class Hanoi
{
	/*------------------*/
	/*     Attributs    */
	/*------------------*/

	//Attributs de classe (static)

	//Attributs d'instance
	private int etape  = 0;

	/*------------------*/
	/*   Constructeur   */
	/*------------------*/

	//Constructeur Principal
	public Hanoi()
	{
		this.etape  =0;
		
	}

	/*------------------*/
	/*     Méthodes     */
	/*------------------*/
	public void hanoi(int nbDisque, int tourDeb, int tourFin)
	{
		int interm;
		if(nbDisque > 0)
		{
			interm = 6 - tourDeb - tourFin;
			hanoi( nbDisque-1, tourDeb, interm);
			System.out.println(++this.etape + (etape>9 ? " " : "  ") + ": " + tourDeb + " => " + tourFin);
			hanoi( nbDisque-1, interm, tourFin);
		}
	}

	public static void main(String[] args)
	{

		/*---------------*/
		/* Constantes    */
		/*---------------*/
	
		
	
		/*------------------*/
		/*     Variables    */
		/*------------------*/
	
		Hanoi jeu1 = new Hanoi();
		Hanoi jeu2 = new Hanoi();
	
		/*------------------*/
		/*   Instructions   */
		/*------------------*/
		
		System.out.println("\nJe veux déplacer 3 disques de la tour1 vers la tour3\n");
		jeu1.hanoi(3, 1, 3);
		
		System.out.println("\nJe veux déplacer 4 disques de la tour1 vers la tour3\n");
		jeu2.hanoi(4, 1, 3);


	}
}