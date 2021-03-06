package java2uml.metier;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import java2uml.Controleur;
import java2uml.IHM.CUI.IHMCUI;


/**
 * <b>ConfigGenerator est la classe qui sert, à partir d'un Diagramme, à générer un fichier de configuration de diagramme.</b>
 * <p>
 * ConfigGenerator possède les attributs suivant :
 * <ul>
 * <li>Un Diagramme qui permet de générer le fichier de configuration.</li>
 * <li>Une chaine qui définit le nom du fichier.</li>
 * <li>Une chaine qui définit le nom d'auteur.</li>
 * <li>Une chaine qui définit le bandeau des classes.</li>
 * <li>Une liste de chaines qui stock l'ensemble des entites du diagrammes pour trouver les associations.</li>
 * <li>Une chaine qui définit le chemin où enregistrer le fichier.</li>
 * </ul>
 * </p>
 * 
 * 
 * @author InnovAction
 * @version 1.0
 */
public class ConfigGenerator {

	/**
	 * Le diagramme utilisé pour généré le fichier.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see ConfigGenerator#genererClasses()
	 */
	private Diagramme diag;
	
	/**
	 * Le nom du fichier à enregister.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see ConfigGenerator#genererBanniere()
	 */
	private String nomFic;
	
	/**
	 * Le nom de l'auteur du fichier.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see ConfigGenerator#genererBanniere()
	 */
	private String nomAuteur;
	
	/**
	 * Le bandeau +------+ Entité(s) +------+.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see ConfigGenerator#genererClasses()
	 */
	private String bandeauClasse = "";
	
	/**
	 * La liste des entités du diagramme.
	 * 
	 * @see ----- Utilisée dans les méthodes -----
	 * @see ConfigGenerator#genererClasses()
	 */
	private ArrayList<String> ensEntite = new ArrayList<String>();
	
	/**
	 * Le chemin où enregistré le fichier.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see ConfigGenerator#ConfigGenerator(Diagramme diag, String nomFic, String auteur)
	 */
	private static final String CHEMIN = "../config/";
	
	
	/**
     * <b>Constructeur ConfigGenerator.</b>
     * <p>
     * A l'instanciation d'un objet de type ConfigGenerator on crée un fichier de configuration du diagramme passé en paramètre
     * </p>
     * 
     * @param diag
     *            Le diagramme.
     * @param nomFic
     *            Le nom du fichier.
     * @param auteur
     *            Le nom de l'auteur du fichier.
     * 
     * @see ----- Utilise les attributs -----
     * @see ConfigGenerator#diag
     * @see ConfigGenerator#nomFic
     * @see ConfigGenerator#nomAuteur
     * @see ConfigGenerator#bandeauClasse
     */
	public ConfigGenerator(Diagramme diag, String nomFic, String auteur) 
	{
		this.diag = diag;
		this.nomFic = nomFic;
		this.nomAuteur = auteur;
		
		this.bandeauClasse = diag.getEnsFile().size() > 1 ?
				"+-------+\n Entités \n+-------+\n" :
				"+------+\n Entité \n+------+\n";
		
		
		//On génére le fichier avec la bannière / les consignes / les entites
		String banniere = genererBanniere();
		String consignes = genererConsignes();
		String classes  = genererClasses();
		
		PrintWriter writer;//On écrit le fichier au chemin indiqué
		try
		{
			File f = new File(CHEMIN,this.nomFic+".txt");
			writer = new PrintWriter(f, "UTF-8");
			writer.println(banniere);
			writer.println(consignes);
			writer.println(classes);
			writer.println("------Fin");
			writer.close();
		}
		catch (Exception e) {e.printStackTrace();}
		
	}
	
	
	/**
     * Permet de générer la bannière du fichier (infos, rappels etc.)
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see ConfigGenerator#ConfigGenerator(Diagramme diag, String nomFic, String auteur)
     * 
     */
	private String genererBanniere() 
	{
		
		String sRet = "";
		
		sRet += "                                                                               "     + "\n" +
		         "      ,--.  ,---.,--.   ,--.,---.       ,---.     ,--. ,--.,--.   ,--.,--.     "     + "\n" +
		         "      |  | /  O  \\\\  `.'  //  O  \\     '.-.  \\    |  | |  ||   `.'   ||  |     " + "\n" +
		         " ,--. |  ||  .-.  |\\     /|  .-.  |     .-' .'    |  | |  ||  |'.'|  ||  |     "    + "\n" +
		         " |  '-'  /|  | |  | \\   / |  | |  |    /   '-.    '  '-'  '|  |   |  ||  '--.  "    + "\n" +
		         "  `-----' `--' `--'  `-'  `--' `--'    '-----'     `-----' `--'   `--'`-----'  "     + "\n" +
		         "                                                                               "     + "\n" ;
		
		 //CREATION SEPARATION TYPE : "---------------------"
		String separation = "+";
        for (int i = 0; i < this.nomFic.length(); i++) separation+="-";
        separation += "+\n";
        
        sRet += separation;
        sRet += ' ' + this.nomFic+'\n';
        sRet += separation+'\n';

        //Récupère la date du jour
        Date dateDuJour = new Date();

        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);

        sRet += "------Date de création : " + shortDateFormat.format(dateDuJour) + '\n';
        sRet += "------Auteur : " + this.nomAuteur + '\n';
        sRet += "------Proposé par : InnovAction\n\n";
        sRet += "\nListe de caractères utilisés : <> (agrégation), <//> (composition), |> (héritage), (+) (classe interne) \n";
        
        return sRet;
	}
	
	
	/**
     * Permet de générer quelques rappels sur les consignes de modification des fichiers
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see ConfigGenerator#ConfigGenerator(Diagramme diag, String nomFic, String auteur)
     * 
     */
	private String genererConsignes() 
	{
		
		String sRet = "";
		
		sRet += "+-----------+\n Rappels \n+-----------+\n";
		
        sRet += "Un commentaire s'écrit de cette manière : \"//\"\n\n";
        
        sRet += "Pour masquer un élément (attribut, méthode...), commentez le."+ '\n';
        sRet += "\tExemple : //- int entierA <-- pour masquer UN attribut\n\n";
        sRet += "\tExemple : //-----Attributs <-- pour masquer TOUS les attributs\n\n";
        
        sRet += "Pour ajouter une contrainte sur les associations :"+ '\n';
        sRet += "\tAssociation1 Association2 {contrainte}\n\n";
        
        sRet += "Pour ajouter des multiplicités sur une association :"+ '\n';
        sRet += "\tClasseA [0..1] ------> [1..*] ClasseB\n\n";
        
        sRet += "Pour modifier une valeur par défault d'un attribut :"+ '\n';
        sRet += "\tExemple : - int entierA static final default = 10 \n\n";
        
        sRet += "Pour d'autres renseignements, consultez la documentation\n\n";
        
        sRet+="\n";
        return sRet;
	}
	
	/**
     * Permet de générer le corps du fichier (les classes et leur(s) attributs/méthodes/associations etc.)
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see ConfigGenerator#ConfigGenerator(Diagramme diag, String nomFic, String auteur)
     * 
     * @see ----- Utilise les méthodes -----
     * @see ConfigGenerator#getFormattedType(Object o)
     * 
     */
	private String genererClasses()
	{
		String sRet = this.bandeauClasse;
		
		//On initialise toutes les entites du diagramme
		for(JavaReader c : this.diag.getEnsFile())
			if(! ensEntite.contains(c.getNomClasse()))
				ensEntite.add(c.getNomClasse());
		
		//Pour chaque entite du diagramme
		for(JavaReader c : this.diag.getEnsFile())
		{
			sRet += "------ Entité : " + c.getTypeEntite()+'\n';				
				
			//On regarde si l'entite est abstraite et finale
			sRet += c.getNomClasse();
			if(c.isAbstraite()) sRet += " abstract";
			if(c.isFinal()) sRet += " final";

			sRet += "\n----Attribut(s) :\n";
			for(Field f : c.getTabAttribut()) //Pour chaque attribut de l'entite
			{
				if(f.getName().contains("$"))continue;
				
				//On regarde la visibilité de l'attribut
				char visibilite = 0;
				if(Modifier.isPrivate(f.getModifiers())) visibilite='-';
	            if(Modifier.isPublic(f.getModifiers())) visibilite='+';
	            if(Modifier.isProtected(f.getModifiers())) visibilite='#';
	            
	            //On regarde la staticite de l'attribut
	            String staticite = "";
	            if(Modifier.isStatic(f.getModifiers())) staticite="static ";
	            
	            //On regarde la finalite de l'attribut
	            String finalite = "";
	            if(Modifier.isFinal(f.getModifiers())) finalite="final ";
	            
	            String multiplicite = "";

	            //On récupère le type de l'attribut
	            String type = getFormattedType(f);
	            
	            //On récupère les multiplicités de l'attribut
	            if(type.contains("[")) multiplicite="[0..*] ";
	            
	            if( (! sRet.contains("\\$")))//On regarde si on doit afficher l'attribut ou non
	            {
	            	boolean bOk = true;
	            	for(String s : ensEntite) // Permet de ne pas afficher les attributs traduits par les associations
	            		if(type.contains(s))
	            			bOk = false;
	            	
	            	if(bOk)
	            		sRet+="" + visibilite + ' ' + type + ' ' +f.getName()+ ' ' + multiplicite + staticite + finalite + '\n';
	            }
			}
			
			sRet += "\n----Méthode(s) :\n";
			
			//Pour chaque constructeur de l'entite
			for(Constructor co : c.getTabConstruct())
			{
				//On regarde la visibilité du constructeur
				char visibilite = 0;
				if(Modifier.isPrivate(co.getModifiers())) visibilite='-';
	            if(Modifier.isPublic(co.getModifiers())) visibilite='+';
	            if(Modifier.isProtected(co.getModifiers())) visibilite='#';

				sRet+="" + visibilite + ' ' +  "{constructeur}" + ' ' + co.getName() +"(";
				
				//Pour chaque paramètres du constructeur
				Parameter[] params = co.getParameters();
				for(int i = 0; i<params.length; i++)
				{
					Parameter p = params[i];
					
					//On récupère leur type formatté au format UML
					String typeParam = getFormattedType(p);
					
					sRet+=typeParam + ' ' +p.getName();
					if( i != params.length-1)
						sRet += "; ";
				}
				sRet += ")";
				
				sRet+='\n';	
			}
			
			//Pour chaque méthode de l'entite
			for(Method m : c.getTabMeth())
			{
				//On regarde la visibilité de la méthode
				char visibilite = 0;
				if(Modifier.isPrivate(m.getModifiers())) visibilite='-';
	            if(Modifier.isPublic(m.getModifiers())) visibilite='+';
	            if(Modifier.isProtected(m.getModifiers())) visibilite='#';
	            
	            //On regarde la staticite de la méthode
	            String staticite = "";
	            if(Modifier.isStatic(m.getModifiers())) staticite="static ";
	            
	            //On récupère le type de retour de la méthode au format UML
				String type = getFormattedType(m);
				
				sRet+="" + visibilite + ' ' + type + ' ' +m.getName()+"(";
				
				//Pour chaque paramètres de la méthode
				Parameter[] params = m.getParameters();
				for(int i = 0; i<params.length; i++)
				{
					Parameter p = params[i];
					
					//On récupère leur type formatté au format UML
					String typeParam = getFormattedType(p);

					sRet+=typeParam + ' ' +p.getName();
					if( i != params.length-1)
						sRet += "; ";
				}
				sRet += ")";
				//Si la méthode est abstraite
				sRet += Modifier.isAbstract(m.getModifiers()) ? " abstract " : "";
				sRet += ' ' +staticite;
				
				sRet+='\n';
			}
			sRet += "\n----Association(s) :\n";
			//On génère les associations qu'on peut déduire du fichier Java
			if(c.aMere()) sRet += c.getNomClasse() + " -------|> "+ c.getMere() +'\n';  //Association de généralisation / spécialisation

			if(c.aClasseGlobale()) sRet += c.getNomClasse() + " -------(+) "+ c.getClasseGlobale() +'\n'; //Classe interne

			//Pour chaque attribut de l'entite on regarde si le type fait parti des classe du diagramme
			for(Field f : c.getTabAttribut())
			{
				if(f.getName().contains("$"))continue;
				for(String s : ensEntite)
					if(getFormattedType(f).contains(s))
						sRet += c.getNomClasse() + " ------- " + s + '\n';
			}

			for(Class inter : c.getInterface())// Implémentation d'interface
			{
				for(String s : ensEntite)
					if(inter.getName().contains(s))
						sRet += c.getNomClasse() + " -.-.-.-|> " + inter.getName() + '\n';
			}
			
			sRet += "\n----Contrainte(s) :\n";
			sRet += "\n\n";
		}
		return sRet;
	}

	/**
     * Permet de retourner le type au format UML de l'objet fourni en paramètre
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see ConfigGenerator#genererClasses()
     * 
     */
	private String getFormattedType(Object o) {
		Type type = null;
		String sRet = "";
		
		//On récupère le type de l'object passé en paramètres
		if(o instanceof Field) type = ((Field) o).getGenericType();
        if(o instanceof Method) type = ((Method) o).getGenericReturnType();
        if(o instanceof Parameter) type = ((Parameter) o).getParameterizedType();
        
        //Si on a des <> (ParameterizedType) on recupère le type et le type entre chevron
        if (type instanceof ParameterizedType) 
        {
        	String sTypeName = type.getTypeName();
        	Scanner scChevron = new Scanner(sTypeName);
        	scChevron.useDelimiter("\\<");
        	
        	while(scChevron.hasNext())
        	{
        		String scChevronNext = scChevron.next();
        		
        		if(scChevronNext.contains(","))
        		{
        			Scanner scVirgule = new Scanner(scChevronNext);
        			scVirgule.useDelimiter(",");
        			
        			String scVirguleNext = "";
        			while(scVirgule.hasNext())
	        		{
	        			scVirguleNext = scVirgule.next();
	        			
	        			Scanner scPoint = new Scanner(scVirguleNext);
	        			scPoint.useDelimiter("\\.");
	        			String scPointNext = "";
	            		while(scPoint.hasNext())scPointNext = scPoint.next();
	            		
	        			sRet+=scPointNext +",";
	        		}
        			
        			sRet = sRet.substring(0, sRet.length()-1);
        		}
        		else 
        		{
        			Scanner sc2 = new Scanner(scChevronNext);
            		sc2.useDelimiter("\\.");
            		String sc2Next = "";
            		while(sc2.hasNext())sc2Next = sc2.next();
            		//System.err.println(sc2Next);
            		sRet+=sc2Next;
            		sRet += sc2Next.contains(">") ? "" : '<'; 	
        		}
        		
        	}
        } //Si non on affiche normalement
        else 
    	{
	    	String sTypeName = type.getTypeName();
	    	Scanner sc = new Scanner(sTypeName);
	    	sc.useDelimiter("\\.");
			String scNext = "";
			while(sc.hasNext())scNext = sc.next();
			
			sRet+=scNext;
    	}

        //On traduit tous les types primitifs (+ String ) en français
        sRet = sRet.replaceAll("int"	,"entier"		);
        sRet = sRet.replaceAll("long"	,"entier"		);
        
        sRet = sRet.replaceAll("char"	,"charactère"	);
        
        sRet = sRet.replaceAll("float"	,"réel"			);
        sRet = sRet.replaceAll("double"	,"réel"			);
        
        sRet = sRet.replaceAll("boolean","booléen"		);
      
        sRet = sRet.replaceAll("String"	,"Chaine"		);
        
        return sRet;
    }


}
