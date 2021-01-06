package java2uml.metier;

import java.util.ArrayList;

public class Diagramme {

	private ArrayList<JavaReader> ensFile;
	private ArrayList<Association> ensAssociations;
	
	public Diagramme(String[] nomsClasses)
	{
		this.ensFile = new ArrayList<JavaReader>();
		
		if(nomsClasses.length > 1) this.ensAssociations = new ArrayList<Association>();
		else this.ensAssociations = null;

		for(int i = 0; i<nomsClasses.length;i++) this.ensFile.add(new JavaReader(nomsClasses[i]));

	}
	public ArrayList<JavaReader> getEnsFile() { return ensFile; }
	public ArrayList<Association> getEnsAssociations() { return ensAssociations; }
}
