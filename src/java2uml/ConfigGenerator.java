package java2uml;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.util.Date;

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
		
		this.bandeauClasse = diag.getEnsClasses().size() > 1 ?
				"+-------+\n Classes \n+-------+\n" :
				"+------+\n Classe \n+------+\n";
		
		
		System.out.println(genererBanniere());
		System.out.println(genererClasses());
	}
	
	private String genererBanniere() 
	{
		String sRet = "";
		
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
        sRet += "------Proposé par : InnovAction";
        
        return sRet;
	}
	
	private String genererClasses()
	{
		String sRet = this.bandeauClasse;
		
		for(Classes c : this.diag.getEnsClasses())
		{
			sRet += "------ Classe : " + c.getNomClasse() +'\n';
			sRet += "----Attributs :\n";
			for(Field f : c.getTabAttribut())
			{
				char visibilite = 0;
				if(Modifier.isPrivate(f.getModifiers())) visibilite='-';
	            if(Modifier.isPublic(f.getModifiers())) visibilite='+';
	            if(Modifier.isProtected(f.getModifiers())) visibilite='#';
	            
				String[] typeSplited = f.getType().toString().split("\\.");
				String type = typeSplited[typeSplited.length-1];
				
				sRet+="" + visibilite + ' ' + type + ' ' +f.getName()+'\n';
			}
			
			sRet += "\n\n";
		}
		
		return sRet;
	}
	
	
	
	public static void main(String[] args) {
		String[] tabNoms = {"Coord", "test1"};
		Diagramme d = new Diagramme(tabNoms);
		
		ConfigGenerator cGen = new ConfigGenerator(d, "FichierTest", "Adrien PESTEL");
	}

}
