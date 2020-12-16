package java2uml.metier;

import java.util.ArrayList;

import java2uml.metier.JavaReader;

public class Diagramme {

	private ArrayList<JavaReader> ensFile;
	private ArrayList<Association> ensAssociations;
	
	public Diagramme(String[] nomsClasses)
	{
		this.ensFile = new ArrayList<JavaReader>();
		
		if(nomsClasses.length > 1)
			this.ensAssociations = new ArrayList<Association>();
		else
			this.ensAssociations = null;
		
		
		
		for(int i = 0; i<nomsClasses.length;i++)
		{
			this.ensFile.add(new JavaReader(nomsClasses[i]));	
		}
		
	}
	
	public Diagramme(String nomFichierConfig, boolean estFichierConfig)
	{
		this.ensFile = new ArrayList<JavaReader>();
		this.ensAssociations = new ArrayList<Association>();
	}
	
	public static void main(String[] args) {
		
		String[] tabNoms = {"Coord"};
		Diagramme d = new Diagramme(tabNoms);
	}

	public ArrayList<JavaReader> getEnsFile() 
	{
		return ensFile;
	}


	public ArrayList<Association> getEnsAssociations() 
	{
		return ensAssociations;
	}

	

}
