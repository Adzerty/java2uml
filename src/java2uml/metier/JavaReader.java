package java2uml.metier;

import java.lang.reflect.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

/**
 * <b>JavaReader est la classe qui permet de lire un fichier Java et d'en ressortir les infos nécessaires.</b>
 * <p>
 * JavaReader possède les attributs suivant :
 * <ul>
 * <li>Une chaine qui va définir le nom de la classe à analyser</li>
 * <li>Un tableau de Field qui va stocker les attributs</li>
 * <li>Un tableau de Constructor qui va stocker les constructeurs</li>
 * <li>Un tableau à deux dimensions de Parameter qui va stocker les paramètres des constructeurs</li>
 * <li>Un tableau de Method qui va stocker les méthodes</li>
 * <li>Un tableau à deux dimensions de Parameter qui va stocker les paramètres des méthodes</li>
 * <li>Un tableau de Classe qui va stocker les classes implémentées</li>
 * <li>Un booléen qui permet de définir si on affiche les méthodes</li>
 * <li>Un booléen qui permet de définir si on affiche les attributs</li>
 * <li>Un booléen qui permet de définir si la classe est abstraite</li>
 * <li>Un booléen qui permet de définir si on classe est finale</li>
 * <li>Une chaine qui permet de stocker le nom de la classe mere</li>
 * <li>Une chaine qui permet de stocker le type de l'entite (classe, interface, enum ...)</li>
 * <li>Une chaine qui permet de stocker le nom de la classe dans laquelle est cette classe interne</li>
 * <li>Une chaine qui permet de stocker le chemin des classes compiles</li>
 * </ul>
 * </p>
 * 
 * 
 * @author InnovAction
 * @version 1.0
 */
public class JavaReader
{
	
	/**
	 * Le nom de la classe.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#toString()
	 * @see JavaReader#getNomClasse()
	 */
    private String nomClasse;

    /**
	 * Le tableau stockant les attributs.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#attribut_toString()
	 * @see JavaReader#size()
	 */
    private Field[] tabAttribut;

    /**
	 * Le tableau stockant les constructeurs.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 */   
	private Constructor[] tabConstruct;
	
	/**
	 * Le tableau stockant les paramètres des constructeurs.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#getTabParamConstruct()
	 */
    private Parameter[][] tabParamConstruct;
    
    /**
	 * Le tableau stockant les méthodes.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#methode_toString()
	 * @see JavaReader#size()
	 * @see JavaReader#getTabMeth()
	 */
    private Method[] tabMeth;
    
    /**
	 * Le tableau stockant les paramètres des méthodes.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#getTabParamMethod()
	 */
    private Parameter[][] tabParamMethod;
    
    /**
	 * Le tableau stockant les noms des classes implémentées.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#getInterface()
	 */
    private Class[] tabInterface;

	/**
	 * Le booléen qui indique si on affiche ou non les méthodes.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#toString()
	 * @see JavaReader#isAfficheMethode()
	 */
    private boolean afficheMethode;
    
    /**
	 * Le booléen qui indique si on affiche ou non les attributs.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#toString()
	 * @see JavaReader#isAfficheAttributs()
	 */
    private boolean afficheAttributs;
    
    /**
	 * Le booléen qui indique si la classe est abstraite.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#isAbstraite()
	 */
    private boolean estAbstraite;
    
    /**
	 * Le booléen qui indique si la classe est finale.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#isFinal()
	 */
    private boolean estFinal;
    
    /**
	 * La chaine qui stocke le nom de la classe mere de cette classe.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#aMere()
	 * @see JavaReader#getMere()
	 */
    private String mere;
    
    /**
	 * La chaine qui stocke le type d'entité
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 */
    private String typeEntite;
    
    /**
	 * La chaine qui stocke le nom de la classe dans laquelle est la classe, si elle est interne
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 * @see JavaReader#aClasseGlobale()
	 * @see JavaReader#getClasseGlobale()
	 */
    private String classeGlobale;
    
    /**
	 * La chaine qui stocke le chemin où sont compilées les classes
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see JavaReader#JavaReader()
	 */
    private String repCompile="../fichierCompile/";
    
    
    /**
     * <b>Constructeur JavaReader.</b>
     * <p>
     * A l'instanciation d'un objet de type JavaReader, on lit un fichier java passé en paramètre
     * </p>
     * 
     * @param nomClasse
     *            Le nom de la classe.
     * 
     * @see ----- Utilise les attributs -----
     * @see JavaReader#nomClasse
     * @see JavaReader#tabAttribut
     * @see JavaReader#tabConstruct
     * @see JavaReader#tabParamConstruct
     * @see JavaReader#tabMeth
     * @see JavaReader#tabParamMethod
     * @see JavaReader#tabInterface
     * @see JavaReader#afficheMethode
     * @see JavaReader#afficheAttributs
     * @see JavaReader#estAbstraite
     * @see JavaReader#estFinal
     * @see JavaReader#mere
     * @see JavaReader#typeEntite
     * @see JavaReader#classeGlobale
     * @see JavaReader#repCompile
     */
    public JavaReader(String nomClasse)
    {
    	try
		{
			/* Permet d'accéder à un .class dans un autre dossier ici : ./fichierJava/ */
			File f = new File(repCompile);
			URL[] cp = {f.toURI().toURL()};
			nomClasse = nomClasse.replace(".class", "");
			URLClassLoader urlcl = new URLClassLoader(cp);
			Class c = urlcl.loadClass(nomClasse);

			// Récupère le nom de la classe
			this.nomClasse = c.getName();
			
			if(this.nomClasse.contains("$")) // On récupère le nom de la classe si elle est interne
			{
				int indexDollar = this.nomClasse.indexOf('$');
				this.nomClasse = this.getNomClasse().substring(indexDollar+1);
			}

			//Récupère les attributs de la classe
			this.tabAttribut = c.getDeclaredFields();

			//Récupère les constructeurs de la classe
			this.tabConstruct = c.getConstructors();

			//Récupère les params des constructeurs
			this.tabParamConstruct = new Parameter[tabConstruct.length][];
			for(int i = 0; i<tabConstruct.length; i++)
			{ 
				tabParamConstruct[i] = tabConstruct[i].getParameters();
			}
			

			//Récupère les méthodes de la classe
			this.tabMeth = c.getDeclaredMethods();

			//Recupère les params methodes
			this.tabParamMethod = new Parameter[tabMeth.length][];
			/*for(int i = 0; i<tabMeth.length; i++)
			{
				tabParamMethod[i] = tabMeth[i].getParameters();
			}*/	

			//On affecte les booléens
			this.afficheAttributs = this.afficheMethode =  true;
			this.estAbstraite = Modifier.isAbstract(c.getModifiers());
			this.estFinal = Modifier.isFinal(c.getModifiers());
			
			//On gère les classes mères
			try {
			this.mere = c.getSuperclass().getName();
			if(mere.contains("Object") || mere.contains("Enum"))
				mere=null;
			else
			{
				Scanner scPoint = new Scanner(mere);
				scPoint.useDelimiter("\\.");
			
				while(scPoint.hasNext())mere = scPoint.next();
			}
			}catch(Exception e) {}
			
			//On gère les classes globales si classe interne
			try {
				this.classeGlobale = c.getEnclosingClass().getName();
				}catch(Exception e) {}
			this.typeEntite = "";
			if(c.isEnum())typeEntite = "Enum";
			else
				if(c.isInterface()) typeEntite = "Interface";
				else typeEntite = "Classe";
			
			this.tabInterface = c.getInterfaces();

            urlcl.close();//on ferme la classe
			

		}catch(Exception e){e.printStackTrace();}
    }

	public boolean isAbstraite() {
		return estAbstraite;
	}

	public String toString()
    {
        int taille = size();

        String separation ="";
        String sRet="";
        String nomClasseFormat;

        //CREATION SEPARATION TYPE : "---------------------"
        for (int i = 0; i < taille; i++) separation+="-";
        separation += "\n";

        //SEPARATION TYPE : "---------------------"
        sRet+=separation;

        //NOM CLASSES
        for (int i = 0; i < (int)(taille-nomClasse.length())/2 ; i++) sRet+=" ";
        sRet+=nomClasse+"\n";

        if(afficheAttributs)
        {
            //SEPARATION TYPE : "---------------------"
            sRet+=separation;

            //AJOUT DES ATTRIBUTS TYPE : -int x
            sRet+=attribut_toString(taille);
        }
       if(afficheMethode)
       {
           //SEPARATION TYPE : "---------------------"
           sRet+=separation;

           //AJOUT DES METHODE TYPE : + getX () : int
           sRet+=methode_toString(taille);
       }

       //SEPARATION TYPE : "---------------------"
       sRet+=separation;

        return  sRet;
    }

	/* Permet de générer un toString pour les méthodes (pour aérer le toString global) */
    private String methode_toString(int taille)
    {
        String sRet="";

        for(int i = 0; i<tabMeth.length; i++)
        {

            sRet += String.format("%-"+String.valueOf(taille) + 's', tabMeth[i].toString()) ;
            sRet+="\n";
        }
        return sRet;
    }
    
	/* Permet de générer un toString pour les attributs (pour aérer le toString global) */
    private String attribut_toString(int taille)
    {
        String sRet="";
        for(int i = 0; i<tabAttribut.length; i++)
        {
            String att = "";
            boolean bStatic;

            if(Modifier.isPrivate(tabAttribut[i].getModifiers())) att+='-';
            if(Modifier.isPublic(tabAttribut[i].getModifiers())) att+='+';
            if(Modifier.isProtected(tabAttribut[i].getModifiers())) att+='#';
            bStatic = Modifier.isStatic(tabAttribut[i].getModifiers());

            String typeAtt = tabAttribut[i].getType().toString();
            //Si l'attribut contient '.' (ex : java.lang.String;) on récupère juste String
            if(typeAtt.contains("."))
            {
                String type[] = typeAtt.split("\\.");
                typeAtt = type[type.length-1].substring(0,type[type.length-1].length()-1);
            }

            //Si l'attribut contient ' ' (ex : Class ClasseA) on récupère juste ClasseA
            if(typeAtt.contains(" "))
            {
                String type[] = typeAtt.split(" ");
                typeAtt = type[type.length-1];
            }

            att+=tabAttribut[i].getName();
            att+=" : " + typeAtt;
            if(Modifier.isFinal(tabAttribut[i].getModifiers())) att+=" {gelé}";

            if(bStatic)
            {
                att+="\n";
                for(int l = 0 ; l< sRet.length()-1;l++)
                    att+="¯";
            }
            sRet += String.format("%-"+String.valueOf(taille) + 's',att);
            sRet+="\n";
        }
        return sRet;
    }

    /**
     * Permet de récupérer la taille de la ligne (pour l'affichage console)
     * 
     * @see ----- Utilisé par les méthodes -----
     * @see JavaReader#toString()
     * 
     */
    public int size()
    {
        int taille = 0 ;

        //ATTRIBUTS
        for(int i = 0; i<tabAttribut.length; i++)
        {
            String att = "";
            boolean bStatic;

            if (Modifier.isPrivate(tabAttribut[i].getModifiers())) att += '-';
            if (Modifier.isPublic(tabAttribut[i].getModifiers())) att += '+';
            if (Modifier.isProtected(tabAttribut[i].getModifiers())) att += '#';
            bStatic = Modifier.isStatic(tabAttribut[i].getModifiers());

            String typeAtt = tabAttribut[i].getType().toString();
            //Si l'attribut contient '.' (ex : java.lang.String;) on récupère juste String
            if (typeAtt.contains(".")) {
                String type[] = typeAtt.split("\\.");
                typeAtt = type[type.length - 1].substring(0, type[type.length - 1].length() - 1);
            }

            //Si l'attribut contient ' ' (ex : Class ClasseA) on récupère juste ClasseA
            if (typeAtt.contains(" ")) {
                String type[] = typeAtt.split(" ");
                typeAtt = type[type.length - 1];
            }

            att += tabAttribut[i].getName();
            att += " : " + typeAtt;
            if (Modifier.isFinal(tabAttribut[i].getModifiers())) att += " {gelé}";

            if(att.length()>taille)taille=att.length();
        }

        //METHODES

        for(int i = 0; i<tabMeth.length; i++)
             if(tabMeth[i].toString().length()>taille)taille=tabMeth[i].toString().length();

        return  taille;
    }
    
	public String getNomClasse() {
		return nomClasse;
	}

	public Field[] getTabAttribut() {
		return tabAttribut;
	}

	public Constructor[] getTabConstruct() {
		return tabConstruct;
	}

	public Parameter[][] getTabParamConstruct() {
		return tabParamConstruct;
	}

	public Method[] getTabMeth() {
		return tabMeth;
	}

	public Parameter[][] getTabParamMethod() {
		return tabParamMethod;
	}

	public boolean isAfficheMethode() {
		return afficheMethode;
	}

	public boolean isAfficheAttributs() {
		return afficheAttributs;
	}

	public boolean isFinal() {
		return estFinal;
	}
	
	public boolean aMere()
	{
		return mere != null;
	}
	
	public String getMere()
	{
		return this.mere;
	}
	
	public boolean aClasseGlobale()
	{
		return classeGlobale != null;
	}
	
	public String getClasseGlobale()
	{
		return this.classeGlobale;
	}
	
	public String getTypeEntite()
	{
		return this.typeEntite;
	}
	
	@SuppressWarnings("rawtypes")
	public Class[] getInterface()
	{
		return this.tabInterface;
	}
}