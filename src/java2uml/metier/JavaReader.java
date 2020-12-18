package java2uml.metier;

import java.lang.reflect.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class JavaReader
{
	
	private String cheminExec = getClass().getProtectionDomain().getCodeSource().getLocation().getFile().substring(1).replaceAll("/", "\\\\"); // le repertoire courant de l'Exec
	
    private String nomClasse;

    private Field[] tabAttribut;

    @SuppressWarnings("rawtypes")
	private Constructor[] tabConstruct;
    private Parameter[][] tabParamConstruct;
    private Method[] tabMeth;
    private Parameter[][] tabParamMethod;

    private boolean afficheMethode;
    private boolean afficheAttributs;
    private boolean estAbstraite;
    private boolean estFinal;
    private String mere;
    private String typeEntite;
    private String classeGlobale;
<<<<<<< Updated upstream

=======
    private String repCompile="../fichierCompile/";
>>>>>>> Stashed changes
    public JavaReader(String nomClasse)
    {
        // CONSTRUCTEUR
    	
    	
    	try
		{
			/* Permet d'accéder à un .class dans un autre dossier ici : ./fichierJava/ */
<<<<<<< Updated upstream
			File f = new File("./fichierCompile/");
=======
			File f = new File(repCompile);
>>>>>>> Stashed changes
			URL[] cp = {f.toURI().toURL()};
			URLClassLoader urlcl = new URLClassLoader(cp);
			Class c = urlcl.loadClass(nomClasse);

			// Récupère le nom de la classe
			this.nomClasse = c.getName();
			
			if(this.nomClasse.contains("$"))
			{
				int indexDollar = this.nomClasse.indexOf('$');
				this.nomClasse = this.getNomClasse().substring(indexDollar+1);
			}

			//Récupère les attributs de la classe
			this.tabAttribut = c.getDeclaredFields();

			//Récupère les constructeurs de la classe
			this.tabConstruct = c.getConstructors();

			this.tabParamConstruct = new Parameter[tabConstruct.length][];
			for(int i = 0; i<tabConstruct.length; i++)
			{ 
				tabParamConstruct[i] = tabConstruct[i].getParameters();
			}
			

			//Récupère les méthodes de la classe
			this.tabMeth = c.getDeclaredMethods();

			this.tabParamMethod = new Parameter[tabMeth.length][];
			/*for(int i = 0; i<tabMeth.length; i++)
			{
				tabParamMethod[i] = tabMeth[i].getParameters();
			}*/	

			this.afficheAttributs = this.afficheMethode =  true;
			this.estAbstraite = Modifier.isAbstract(c.getModifiers());
			this.estFinal = Modifier.isFinal(c.getModifiers());
			
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
			
			try {
				this.classeGlobale = c.getEnclosingClass().getName();
				}catch(Exception e) {}
			this.typeEntite = "";
			if(c.isEnum())typeEntite = "Enum";
			else
				if(c.isInterface()) typeEntite = "Interface";
				else typeEntite = "Classe";
			
			

		}catch(Exception e)
		{
			e.printStackTrace();
		}
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

    public static void main(String[] args)
    {
    	JavaReader c = new JavaReader("Train");
    	System.out.println(c.toString());
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
}