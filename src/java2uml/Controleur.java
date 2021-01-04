package java2uml;

//package
import java2uml.IHM.GUI.IHMGUI;
import java2uml.IHM.CUI.IHMCUI;

import java2uml.metier.Java2uml;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Controleur
{
	private IHMCUI    ihmCUI;
	private Java2uml  metier;

	public String repConfig    		= "../config/";
	public String repJava      		= "../fichierJava/";
	public String repCompile   		= "../fichierCompile/";
	public String repDiagramme	 	= "../diagrammes/";
	public String repDiagrammeTxt 	= "../diagrammes/txt/";
	public String repDiagrammePdf 	= "../diagrammes/pdf/";

	public boolean[] options;

	public Controleur()
	{
		//Création des dossiers
		File file = new File(repConfig); 	if (!file.exists()) file.mkdir();
		file = new File(repJava); 			if (!file.exists()) file.mkdir();
		file = new File(repCompile); 		if (!file.exists()) file.mkdir();
		file = new File(repDiagramme); 		if (!file.exists()) file.mkdir();
		file = new File(repDiagrammeTxt); 	if (!file.exists()) file.mkdir();
		file = new File(repDiagrammePdf); 	if (!file.exists()) file.mkdir();
		
		this.metier = new Java2uml();
		this.ihmCUI = new IHMCUI (this);
		
		if(this.ihmCUI.choixGraphique() == 'G')	{ new IHMGUI(this);    } 
		else                                    { this.ihmCUI.start(); }
	}
	
	public static void main(String[] args)
	{
		new Controleur();
	}
	
	public String[] getConfig()//renvoie sous forme de tableau de String l'ensemble des fichiers de config
	{
		return this.metier.recupFichConfig();
	}
	
	public String[] getClasse()//renvoie sous forme de tableau de String l'ensemble des fichiers class
	{
		return this.metier.recupFichClasse();
	}
	
	public int      getTailleMaxFichier(String rep)// renvoie la taille du fichier le plus grand dans un repertoire
	{
		return this.metier.recupTailleMaxFichier(rep);
	}
	
	public String   getContenuConfig(String nomFichier)
    {
        return this.metier.recupContenuConfig(nomFichier);
    }

	public void     creerNouvDiagramme(String[] tabNomFichier)
	{
		this.metier.genNouvDiagramme(tabNomFichier);
	}
	
	public String   creerNouvConfig(String nomFichier, String nomAuteur)
	{
		return this.metier.genNouvConfig(nomFichier, nomAuteur);
	}
	
	public void     ouvrirEnEdit(String nomFichier)
	{
		this.metier.modifierConfig(nomFichier);
	}
	
	public void supprimerFichiers(String[] tabFichierSup)
	{
		for(String s : tabFichierSup)
		{
			boolean[] tabSup = this.metier.supprimerConfig(s);
			this.ihmCUI.confirmSup(s, tabSup[0], tabSup[1], tabSup[2]); //suppression des 2 fichiers
		}
	}
	
	public void majOptions(boolean[] options)
	{
		this.metier.modifierFichierIni(options);
	}
	 
	public void compilation()
	{
		String commande = "javac -parameters -d "+ repCompile +' ' +repJava+"*.java";
		try {
		     if (System.getProperty("os.name").contains("Windows"))
		         new ProcessBuilder("cmd", "/c", commande).inheritIO().start().waitFor();
		     else
		    	 new ProcessBuilder("/bin/bash", "-c", commande).inheritIO().start().waitFor();

		} catch (Exception t)
		{
			t.printStackTrace();
		}
    }
    public boolean[] getOptions() { return this.metier.getOptions(); }
}
