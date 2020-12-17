/** [Class Vehicule]
 * TP4 exo3
 * @author Coatanhay Victor
 * @version 1 du 23/09/20
**/

	/*---------------*/
	/*    Import     */
	/*---------------*/
	
	import iut.algo.*;

public class Vehicule
{
	/*------------------*/
	/*     Attributs    */
	/*------------------*/

	//Attributs de classe (static)
	private static int nbVehicule;

	//Attributs d'instance
	private String couleur;
	private int    numV;

	/*------------------*/
	/*   Constructeur   */
	/*------------------*/

	//Constructeur Principal
	public Vehicule(String couleur)
	{
		this.numV    = ++Vehicule.nbVehicule;
		this.couleur = couleur;
	}

	/*------------------*/
	/*     Méthodes     */
	/*------------------*/
	public String getCouleur()
	{
		return this.couleur;
	}

	public String toString()
	{
		return "Wagon n" + this.numV +" de couleur : " + this.couleur;
	}
}