package java2uml;

import java.util.ArrayList;

public class Diagramme {

	private ArrayList<Classes> ensClasses;
	private ArrayList<Association> ensAssociations;
	
	public Diagramme(String[] nomsClasses)
	{
		this.ensClasses = new ArrayList<Classes>();
		
		if(nomsClasses.length > 1)
			this.ensAssociations = new ArrayList<Association>();
		else
			this.ensAssociations = null;
		
		
		
		for(int i = 0; i<nomsClasses.length;i++)
		{
			this.ensClasses.add(new Classes(nomsClasses[i]));	
		}
		
	}
	
	public Diagramme(String nomFichierConfig, boolean estFichierConfig)
	{
		this.ensClasses = new ArrayList<Classes>();
		this.ensAssociations = new ArrayList<Association>();
	}
	
	public static void main(String[] args) {
		
		String[] tabNoms = {"Coord"};
		Diagramme d = new Diagramme(tabNoms);
	}

	public ArrayList<Classes> getEnsClasses() 
	{
		return ensClasses;
	}


	public ArrayList<Association> getEnsAssociations() 
	{
		return ensAssociations;
	}

	

}
