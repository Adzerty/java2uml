package java2uml.metier;

import java.util.ArrayList;

/**
 * <b>Parametre est la classe qui sert a stocker les informations des parametre d'une methode.</b>
 * <p>
 * Parametre possède les attributs suivant :
 * <ul>
 * <li>Une Chaine qui définit le type du parametre.</li>
 * <li>Un  Chaine  qui définit le nom du parametre.</li>
 * </ul>
 * </p>
 *
 * @author InnovAction
 * @version 1.0
 */
public class Parametre
{
    /**
     * Nom du parametre
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Parametre#Parametre(String, String)
     * @see Parametre#setNomVar(String)
     * @see Parametre#getNomVar()
     * @see Parametre#toString()
     *
     */
    private String nomVar;

    /**
     * Type du parametre
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Parametre#Parametre(String, String)
     * @see Parametre#setType(String)
     * @see Parametre#getType()
     * @see Parametre#toString()
     *
     */
    private String type;

    /**
     * <b>Constructeur Parametre.</b>
     * <p>
     * A l'instanciation d'un objet de type Parametre
     * </p>
     *
     * @param nomVar
     *            Nom de la parametre.
     * @param type
     *            Type du parametre
     *
     * @see ----- Utilise les attributs -----
     * @see Parametre#nomVar
     * @see Parametre#type
     */
    public Parametre(String nomVar, String type)
    {
        this.nomVar = nomVar;
        this.type = type;
    }

    public String getNomVar()   { return nomVar; }
    public String getType()     { return type;   }

    public void setNomVar(String nomVar) { this.nomVar = nomVar; }
    public void setType(String type)     { this.type = type;     }

    public String toString() { return  nomVar +  " : " + type; }
}

