package java2uml.metier;

import java.util.ArrayList;

import java2uml.IHM.CUI.IHMCUI;

/**
 * <b>Diagramme est la classe qui sert a stocker les information d'un diagramme.</b>
 * <p>
 * Diagramme possède les attributs suivant :
 * <ul>
 * <li>Une Liste de JavaReader qui stock toutes les entites du Diagramme</li>
 * <li>Une Liste d'associations</li>
 * </ul>
 * </p>
 *
 * @author InnovAction
 * @version 1.0
 */
public class Diagramme {

	/**
	 * L'ensemble des JavaReader (entites) du diagrammes.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Diagramme#Diagramme
	 * @see Diagramme#getEnsFile()
	 */
	private ArrayList<JavaReader> ensFile;
	
	/**
	 * L'ensemble des Association du diagrammes.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Diagramme#Diagramme
	 * @see Diagramme#getAssociation()
	 */
	private ArrayList<Association> ensAssociations;
	
	/**
     * <b>Constructeur ConfigReader.</b>
     * <p>
     * Instancie un Diagramme
     * </p>
     *
     * @param nomsClasses
     *            Les noms de toutes les classes du fichier.
     */
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
