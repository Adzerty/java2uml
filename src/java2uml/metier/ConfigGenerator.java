package java2uml.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConfigGenerator {

	private Diagramme diag;
	private String nomFic;
	private String nomAuteur;
	
	private String bandeauClasse = "";
	
	private static final String CHEMIN = "./config";
	
	public ConfigGenerator(Diagramme diag, String nomFic, String auteur) 
	{
		this.diag = diag;
		this.nomFic = nomFic;
		this.nomAuteur = auteur;
		
		this.bandeauClasse = diag.getEnsFile().size() > 1 ?
				"+-------+\n Entités \n+-------+\n" :
				"+------+\n Entité \n+------+\n";
		
		
		String banniere = genererBanniere();
		String classes  = genererClasses();
		
		PrintWriter writer;
		try {
			File f = new File("config",this.nomFic+".config");
			writer = new PrintWriter(f, "UTF-8");
			writer.println(banniere);
			writer.println(classes);
			writer.close();
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
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
        
        Date dateDuJour = new Date();

        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);

        sRet += "------Date de création : " + shortDateFormat.format(dateDuJour) + '\n';
        sRet += "------Auteur : " + this.nomAuteur + '\n';
        sRet += "------Proposé par : InnovAction\n";
        sRet += "------Liste de caractères spéciaux : ⬦ ◆ \n";
        
        return sRet;
	}
	
	//Méthode permettant de générer les classes dans le fichier config
	private String genererClasses()
	{
		String sRet = this.bandeauClasse;
		
		for(JavaReader c : this.diag.getEnsFile())
		{
			sRet += "------ Entité : " + c.getTypeEntite()+'\n';
			
			/*
			System.err.println();
			if(c.getClass().isEnum()) sRet += "Enum\n";
			if(c.getClass().isInterface())sRet += "Interface\n";
			else sRet += "Classe\n";*/
				
			
			sRet += c.getNomClasse();
			if(c.isAbstraite()) sRet += " abstact";
			if(c.isFinal()) sRet += " final";
			if(c.aMere()) sRet += " Classe mère : " + c.getMere();
			sRet += "\n----Attributs :\n";
			for(Field f : c.getTabAttribut())
			{
				//On regarde la visibilité de l'attribut
				char visibilite = 0;
				if(Modifier.isPrivate(f.getModifiers())) visibilite='-';
	            if(Modifier.isPublic(f.getModifiers())) visibilite='+';
	            if(Modifier.isProtected(f.getModifiers())) visibilite='#';
	            
	            String staticite = "";
	            if(Modifier.isStatic(f.getModifiers())) staticite="_ ";
	            
	            
	            //On récupère le type de l'attribut
				String[] typeSplited = f.getType().toString().split("\\.");
				String type = typeSplited[typeSplited.length-1];
				
				sRet+="" + visibilite + ' ' + type + ' ' +f.getName()+ ' ' +staticite +'\n';
			}
			
			sRet += "\n----Méthodes :\n";
			for(Method m : c.getTabMeth())
			{
				//On regarde la visibilité de la méthode
				char visibilite = 0;
				if(Modifier.isPrivate(m.getModifiers())) visibilite='-';
	            if(Modifier.isPublic(m.getModifiers())) visibilite='+';
	            if(Modifier.isProtected(m.getModifiers())) visibilite='#';
	            
	            String staticite = "";
	            if(Modifier.isStatic(m.getModifiers())) staticite="_ ";
	            
	            //On récupère le type de retour de la méthode
				String type = getFormattedType(m);
				
				sRet+="" + visibilite + ' ' + type + ' ' +m.getName()+"(";
				
				//Pour chaque paramètres de la méthode
				Parameter[] params = m.getParameters();
				for(int i = 0; i<params.length; i++)
				{
					Parameter p = params[i];
					
					String typeParam = getFormattedType(p);
					//String[] typeParamSplitted = t.getTypeName().split("\\.");
					//String typeParam = typeParamSplitted[typeParamSplitted.length-1];
					
					sRet+=typeParam + ' ' +p.getName();
					if( i != params.length-1)
						sRet += "; ";
				}
				sRet += ")";
				sRet += Modifier.isAbstract(m.getModifiers()) ? " abstract " : "";
				sRet += ' ' +staticite;
				
				sRet+='\n';
			}
			sRet += "\n----Associations :\n";
			sRet += "\n\n";
		}
		return sRet;
	}
	
	
	//Méthode qui permet de retourner le type au format uml (sans java.*** etc et avec les
	//types génériques
	private String getFormattedType(Object o) {
		Type type = null;
		String sRet = "";
		
		//On récupère le type de l'object passé en paramètres
        if(o instanceof Method) type = ((Method) o).getGenericReturnType();
        if(o instanceof Parameter) type = ((Parameter) o).getParameterizedType();
        
        //Si on des <> (ParameterizedType)
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
        }
        else 
    	{
	    	String sTypeName = type.getTypeName();
	    	Scanner sc = new Scanner(sTypeName);
	    	sc.useDelimiter("\\.");
			String scNext = "";
			while(sc.hasNext())scNext = sc.next();
			sRet+=scNext;
    	}
        return sRet;
    }
	

	
	public static void main(String[] args) {
		String[] tabNoms = {"Coord","test1","Actions"};
		Diagramme d = new Diagramme(tabNoms);
		
		ConfigGenerator cGen = new ConfigGenerator(d, "FichierTest", "Adrien PESTEL");
	}

}
