/** [Algo Boxing et unBoxing]
 * TP5 exo1
 * @author Coatanhay Victor
 * @version 1 du 15/02/19
**/

	/*---------------*/
	/*  Données      */
	/*---------------*/
	
	import iut.algo.*;

public class Exo1
{
	public static void main(String[] args)
	{
		/*------------------*/
		/*     Variables    */
		/*------------------*/

		Double unDouble;
		double un_double;
		String s;
		float un_float;
		
		/*---------------*/
		/* Instructions  */
		/*---------------*/
		
		un_double = 12.4;
		unDouble  = 12.4;
		un_float  = 12.4577515724582572f;
		
		//1.1
		un_double = unDouble;
		Console.println("\n1.1 : " + un_double);
		
		//1.2
		unDouble = un_double;
		Console.println("1.2 : " + unDouble);
		
		//1.3
		un_double = Double.parseDouble("12.4");
		Console.println("1.3 : " + un_double);
		
		//1.4
		unDouble = new Double("12.4");
		Console.println("1.4 : " + unDouble);
		
		//1.5
		s = String.valueOf(un_double); //String.valueOf(un_double);
		Console.println("1.5 : " + s);
		
		//1.6
		s = unDouble.toString();
		Console.println("1.6 : " + s);
		
		//1.7
		unDouble = (double)un_float;
		Console.println("1.7 : " + unDouble);
		
		//1.8
		un_float = unDouble.floatValue();
		Console.println("1.8 : " + un_float);
		
		Clavier.lireString();
		Console.effacerEcran();
		
	}
}
