package java2uml;

//package
import java2uml.IHM.GUI.IHMGUI;
import java2uml.IHM.CUI.IHMCUI;

import java2uml.metier.Java2uml;

import java.io.File;

/**
 * <b>Controleur est la classe qui permet de faire le lien entre les parties IHM et Métier.</b>
 * <p>
 * Controleur possède les attributs suivant :
 * <ul>
 * <li>Un IHMCUI qui permet de gérer la console et le mode CUI.</li>
 * <li>Un Java2uml qui est le métier regroupant les principales méthodes nécessaires aux IHMs.</li>
 * </ul>
 * De plus, Le controleur permer d'effectuer la compilation des fichiers Java du repertoire associé.
 * </p>
 * 
 * @see IHMCUI
 * @see IHMGUI
 * @see Java2uml
 * 
 * @author InnovAction
 * @version 1.0
 */
public class Controleur
{
	/**
	 * l'interface utilisateur en mode console.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Controleur#Controleur(String type)
	 * @see Controleur#supprimerFichiers(String[] tabFichierSup)
	 */
	private IHMCUI    ihmCUI;
	
	/**
	 * le metier qui contient les accès à toutes les méthodes nécessaires a l'application.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Controleur#Controleur(String type)
	 * @see ...
	 */
	private Java2uml  metier;
	
	public String repConfig    		= "../config/";
	public String repJava      		= "../fichierJava/";
	public String repCompile   		= "../fichierCompile/";
	public String repDiagramme	 	= "../diagrammes/";
	public String repDiagrammeTxt 	= "../diagrammes/txt/";
	public String repDiagrammePdf 	= "../diagrammes/pdf/";
	
	/**
     * <b>Constructeur Controleur.</b>
     * <p>
     * A l'instanciation d'un objet Controleur, l'IHMCUI est initialisée ainsi que le metier. Les éxécutables permettent,
     * en fonction de l'argument placé en paramètre d'initialiser le mode GUI ou de lancer le CUI.
     * </p>
     * 
     * @param type 
     * 		Chaine passée en paramètre au lancement de l'application qui dénit le type d'IHM qui sera utilisée.
     * 
     * @see Controleur#ihmCUI
     * @see Controleur#metier
     */
	public Controleur(String type)
	{
		this.metier = new Java2uml();
		this.ihmCUI = new IHMCUI(this);
		
		//Création des dossiers
		File file = new File(repConfig); 	if (!file.exists()) file.mkdir();
		file = new File(repJava); 			if (!file.exists()) file.mkdir();
		file = new File(repCompile); 		if (!file.exists()) file.mkdir();
		file = new File(repDiagramme); 		if (!file.exists()) file.mkdir();
		file = new File(repDiagrammeTxt); 	if (!file.exists()) file.mkdir();
		file = new File(repDiagrammePdf); 	if (!file.exists()) file.mkdir();

		if(type.equals("CUI")) { this.ihmCUI.start(); } //lancer CUI
		else                   { new IHMGUI(this);    } //lancer GUI

	}
	
	public static void main(String[] args)
	{
		new Controleur(args[0]);
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
		boolean optn0 = getOptions()[0];
		String sRet="";
		if(optn0) sRet+= this.metier.genNouvConfig(nomFichier, nomAuteur);
		else this.metier.genNouvConfig(nomFichier, nomAuteur);

		return sRet;
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
			this.ihmCUI.confirmSup(s, tabSup[0], tabSup[1], tabSup[2], tabSup[3]); //suppression des 2 fichiers
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
		         new ProcessBuilder("cmd", "/c", commande).start().waitFor();
		     else
		    	 new ProcessBuilder("/bin/bash", "-c", commande).start().waitFor();

		} catch (Exception t)
		{
			t.printStackTrace();
		}
    }
    public boolean[] getOptions() { return this.metier.getOptions(); }
}
