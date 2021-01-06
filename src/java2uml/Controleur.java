package java2uml;

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
	 * @see Controleur#Controleur(String type)
	 * @see Controleur#supprimerFichiers(String[] tabFichierSup)
	 */
	private IHMCUI    ihmCUI;
	
	/**
	 * le metier qui contient les accès à toutes les méthodes nécessaires a l'application.
	 * 
	 * @see Controleur#Controleur(String type)
	 */
	private Java2uml  metier;
	
	/**
	 * le chemin vers le répertoire contenant les fichiers de configurations à partir du répertoire de lancement de l'application.
	 * 
	 * @see Java2uml#recupFich(String rep)
	 */
	public String repConfig    		= "../config/";
	
	/**
	 * le chemin vers le répertoire contenant les fichiers java qui seront compilés à partir du répertoire de lancement de l'application.
	 * 
	 * @see Controleur#compilation()
	 */
	public String repJava      		= "../fichierJava/";
	
	/**
	 * le chemin vers le répertoire contenant les fichiers compilés à partir du répertoire de lancement de l'application.
	 * 
	 * @see Java2uml#recupFich(String rep)
	 */
	public String repCompile   		= "../fichierCompile/";
	
	/**
	 * le chemin vers le répertoire contenant les repertoires des diagrammes à partir du répertoire de lancement de l'application.
	 */
	public String repDiagramme	 	= "../diagrammes/";
	
	/**
	 * le chemin vers le répertoire contenant les fichiers des diagrammes au format .txt à partir du répertoire de lancement de l'application.
	 * 
	 * @see
	 */
	public String repDiagrammeTxt 	= "../diagrammes/txt/";
	
	/**
	 * le chemin vers le répertoire contenant les fichiers des diagrammes au format .pdf à partir du répertoire de lancement de l'application.
	 * 
	 * @see
	 */
	public String repDiagrammePdf 	= "../diagrammes/pdf/";
	
	/**
     * <b>Constructeur Controleur.</b>
     * <p>
     * A l'instanciation d'un objet Controleur, l'IHMCUI est initialisée ainsi que le metier. Les éxécutables permettent,
     * en fonction de l'argument placé en paramètre d'initialiser le mode GUI ou de lancer le CUI.
     * </p>
     * 
     *  <p>
     * De plus le constructeur créé les différents répertoires de l'application.
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

	/**
     * Lance l'instanciation d'un Controleur avec le mode graphique voulu en paramètre et démmare l'application.
     * 
     * @param args
     * 		une Chaine placée en paramètre dans la ligne de commande pour lancer l'application qui prend les valeurs "CUI" ou "GUI".
     * 
     * @see IHMCUI
     * @see IHMGUI
     */
	public static void main(String[] args) { new Controleur(args[0]); }

	/**
     * Récupère tous les fichiers de configuration dans le repertoire associé en utilisant la méthode du métier recupFich(String rep).
     * 
     * @return un tableau de Chaine avec tous les noms des fichiers de configurations.
     * 
     * @see Controleur#repConfig
     */
	public String[] getConfig			() 						{ return this.metier.recupFich(repConfig); 			}
	
	/**
     * Récupère tous les fichiers compilés dans le repertoire associé en utilisant la méthode du métier recupFich(String rep).
     * 
     * @return un tableau de Chaine avec tous les noms des fichiers compilés.
     * 
     * @see Controleur#repJava
     */
	public String[] getClasse			() 						{ return this.metier.recupFich(repCompile); 		}
	
	/**
     * Récupère le plus grand nombre de caractère parmi les noms des fichiers d'un repertoire donné en utilisant la méthode du métier recupTailleMaxFichier(String rep).
     * 
     * @param rep
     * 		une Chaine qui contient le chemin vers le repertoire ou l'on veut faire le traitement.
     * 
     * @return un entier qui correspont à la taille de la plus grande chaine parmi les noms des fichiers.
     * 
     * @see Java2uml#recupTailleMaxFichier(String rep)
     */
	public int 		getTailleMaxFichier	(String rep)			{ return this.metier.recupTailleMaxFichier(rep); 	}
	
	/**
     * Récupère un diagramme existant en utilisant la méthode du métier recupContenuConfig(String nomFichier).
     * 
     * @param nomFichier
     * 		une Chaine qui contient le chemin vers un fichier de diagramme.
     * 
     * @return une Chaine avec le diagramme demandé.
     * 
     * @see Java2uml#recupContenuConfig(String nomFichier)
     */
	public String   getContenuConfig	(String nomFichier)		{ return this.metier.recupContenuConfig(nomFichier);}
	
	/**
     * Créée un diagramme en utilisant la méthode du métier genNouvDiagramme(String[] tabNomFichier).
     * 
     * @param tabNomFichier
     * 		un tableau de Chaines qui contient tous les noms des fichiers associés à ce diagramme.
     * 
     * @see Java2uml#genNouvDiagramme(String[] tabNomFichier)
     */
	public void     creerNouvDiagramme	(String[] tabNomFichier){ this.metier.genNouvDiagramme(tabNomFichier); 		}
	
	/**
     * Ouvre le fichier demandé dans léediteur de texte par défaut en utilisant la méthode du métier modifierConfig(String nomFichier).
     * 
     * @param nomFichier
     * 		une Chaine qui contient le nom du fichier que l'on veut modifier.
     * 
     * @see Java2uml#modifierConfig(String nomFichier)
     */
	public void     ouvrirEnEdit		(String nomFichier) 	{ this.metier.modifierConfig(nomFichier); 			}
	
	/**
     * Met à jour les options du programme en utilisant la méthode du métier modifierFichierIni(boolean[] options).
     * 
     * @param options
     * 		un tableau de booléens qui contient les états de toutes les options.
     * 
     * @see Java2uml#modifierFichierIni(boolean[] options)
     */
	public void 	majOptions			(boolean[] options) 	{ this.metier.modifierFichierIni(options); 			}
	
	/**
	 * Renvoie un tableau de booléens contenant les états des diffférentes options du programme en utilisant la méthode du métier getOptions().
	 * 
	 * @return le tableau des états des options du programme.
	 * 
	 * @see Java2uml#getOptions()
	 */
	public boolean[] getOptions			() 						{ return this.metier.getOptions(); 					}
	
	/**
     * Créée une nouvelle configuration en utilisant la méthode du métier genNouvConfig(String nomFichier, String nomAuteur).
     * 
     * @param nomFichier
     * 		une Chaine qui contient le nom du fichier de configuration.
     * @param nomAuteur
     * 		une Chaine qui contient le nom de l'auteur de ce fichier de configuration.
     * 
     * @return une Chaine avec le(s) diagramme(s) demandé(s) si l'option "Afficher diagramme après création" est activée.
     * 
     * @see Java2uml#genNouvConfig(String nomFichier, String nomAuteur)
     */
	public String   creerNouvConfig(String nomFichier, String nomAuteur)
	{
		boolean optn0 = getOptions()[0];
		String sRet="";
		if(optn0) sRet+= this.metier.genNouvConfig(nomFichier, nomAuteur);
		else this.metier.genNouvConfig(nomFichier, nomAuteur);

		return sRet;
	}

	/**
     * Supprime les fichiers de configuration en utilisant la méthode du métier supprimerConfig(String fichierSup) et affiche dans la console un message de confirmation.
     * 
     * @param tabFichierSup
     * 		une Chaine qui contient le nom du fichier à supprimer.
     * 
     * @see Java2uml#supprimerConfig(String fichierSup)
     * @see IHMCUI#confirmSup(String, boolean, boolean, boolean, boolean)
     */
	public void supprimerFichiers(String[] tabFichierSup)
	{
		for(String s : tabFichierSup)
		{
			boolean[] tabSup = this.metier.supprimerConfig(s);
			this.ihmCUI.confirmSup(s, tabSup[0], tabSup[1], tabSup[2], tabSup[3]); //suppression des 2 fichiers
		}
	}

	/**
     * Compile tous les fichiers .java présent dans le répertoire associé et place les .class dans leur propre répertoire.
     * 
     * @see java.lang.ProcessBuilder
     * @see IHMCUI#creer(int selec ,boolean[] tabSelec)
     */
	public void compilation()
	{
		String commande = "javac -parameters -d "+ repCompile +' ' +repJava+"*.java";
		try
		{
		     if (System.getProperty("os.name").contains("Windows"))  new ProcessBuilder("cmd", "/c", commande).start().waitFor();
		     else new ProcessBuilder("/bin/bash", "-c", commande).start().waitFor();
		}
		catch (Exception t) { t.printStackTrace(); }
    }

}
