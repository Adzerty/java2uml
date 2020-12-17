/** [Class Train]
 * TP4 exo3
 * @author Coatanhay Victor
 * @version 2 du 29/09/20
**/

	/*---------------*/
	/*    Import     */
	/*---------------*/
	
	import iut.algo.*;
	import java.util.ArrayList;
	import java.util.Iterator;

public class Train implements Iterable<Vehicule>
{
	/*------------------*/
	/*     Attributs    */
	/*------------------*/

	//Attributs de classe (static)
	

	//Attributs d'instance
	private final int  MAX_V = 100;
	private int        nbVehicule;
	private Vehicule[] ensVehicule;
	private ArrayList<Vehicule> alVehi;

	/*------------------*/
	/*   Constructeur   */
	/*------------------*/

	//Constructeur Principal
	public Train()
	{
		this.nbVehicule = 0;
		this.ensVehicule = new Vehicule[MAX_V];
	}

	/*------------------*/
	/*     Méthodes     */
	/*------------------*/
	public boolean ajouterVehicule(Vehicule v)
	{
		if(this.nbVehicule >= 100)
			return false;
		this.ensVehicule[this.nbVehicule++] = v;
		return true;
	}

	public Iterator<Vehicule> iterator() { return new TrainIterator(); }

	public String toString()
	{
		//Locomotive
		String show = "         * \n       *   __________\n";
		
		//Construction haut
		show += "   ___I___/" + this.setCF("ROUGE") + this.setCE('N') + " | |  \\" + this.setCE('B') + this.setCF("DEF") + " O |    ";
		for(int w = 0; w < nbVehicule; w++)
		{
			show +="________     ";
		}
		show = show.substring(0,show.length() -3) + "\n";
		
		//Construction milieu
		show += "  /" + this.setCF("ROUGE") + this.setCE('N') + "         | |:  #==" + this.setCE('B') + this.setCF("DEF") + "|   ";
		for(int w = 0; w < nbVehicule; w++)
		{
			show += "|" + this.colorier(this.ensVehicule[w].getCouleur()) + "|   ";
		}
		show = show.substring(0,show.length() -3) + "\n";
		
		//Construction base
		show += "<<==========#=#======:---";
		for(int w = 0; w < nbVehicule; w++)
		{
			show += ":========:---";
		}
		show = show.substring(0,show.length() -3) + "\n";
		
		//Construction roues
		show += "     00        00  00     ";
		for(int w = 0; w < nbVehicule; w++)
		{
			show += "00    00     ";
		}
		show = show.substring(0,show.length() -3) + "\n" + this.setCE('D');
		return show;
	}

	private String colorier (String coul)
	{
		return this.setCF(coul) + this.setCE('N') + String.format(" %-6s ", coul) + this.setCE('B') + this.setCF("DEF");
	}

	private String setCE(char c)//Renvoie un code en String pour changer la couleur d'ECRITURE
	{
		switch(c)
		{
			case 'B' : return CouleurConsole.BLANC.getFont();
			case 'N' : return CouleurConsole.NOIR.getFont();
			default  : return "\033[0m";
		}
	}

	private String setCF(String c)//Renvoie un code en String pour changer la couleur de FOND
	{
		switch(c)
		{
			case "ROUGE" : return CouleurConsole.ROUGE.getFond();
			case "BLEU"  : return CouleurConsole.BLEU.getFond ();
			case "VERT"  : return CouleurConsole.VERT.getFond ();
			case "BLANC" : return CouleurConsole.BLANC.getFond ();
			default      : return CouleurConsole.NOIR.getFond ();
		}
	}

	public static void main(String[] args)
	{
	
		/*------------------*/
		/*     Variables    */
		/*------------------*/
	
		Vehicule v1 = new Vehicule("ROUGE");
		Vehicule v2 = new Vehicule("VERT");
		Vehicule v3 = new Vehicule("BLEU");
		Vehicule v4 = new Vehicule("BLANC");
		Vehicule v5 = new Vehicule("ROUGE");
		
		Train    t1 = new Train();
		Train    t2 = new Train();
	
		/*------------------*/
		/*   Instructions   */
		/*------------------*/
		
		Console.println("\n-------------\nAjout Vehicules Train 1\n-------------\n");
		
		Console.println("Ajout v1=>t1 : " + t1.ajouterVehicule(v1));
		Console.println("Ajout v2=>t1 : " + t1.ajouterVehicule(v2));
		
		Console.println("\n-------------\nAjout Vehicules Train 2\n-------------\n");
		
		Console.println("Ajout v3=>t2 : " + t2.ajouterVehicule(v3));
		Console.println("Ajout v4=>t2 : " + t2.ajouterVehicule(v4));
		Console.println("Ajout v5=>t2 : " + t2.ajouterVehicule(v5));
		
		Console.println("\n-------------\nAffichage Train 1\n-------------\n");
		
		Console.println(CouleurConsole.BLANC.getFont());
		Console.println(t1);
		
		Console.println("\n-------------\nAffichage Train 2\n-------------\n");
		
		Console.println(CouleurConsole.BLANC.getFont());
		Console.println(t2);
		
		for(Vehicule v : t2)
			Console.println(v);

	}
	class TrainIterator implements Iterator<Vehicule>
	{
		/*------------------*/
		/*     Attributs    */
		/*------------------*/

		//Attributs d'instance
		private int curseur;

		/*------------------*/
		/*   Constructeur   */
		/*------------------*/

		//Constructeur Principal
		public TrainIterator()
		{
			this.curseur = 0;
		}

		/*------------------*/
		/*     Méthodes     */
		/*------------------*/
		public Vehicule next()
		{
			return Train.this.ensVehicule[this.curseur++];
		}

		public boolean hasNext()
		{
			return this.curseur < Train.this.nbVehicule;
		}
	}
}
