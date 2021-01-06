package java2uml.metier;

import java.util.ArrayList;

    /**
     * <b>Methode est la classe qui sert a stocker les informations d'une methode.</b>
     * <p>
     * Methode possède les attributs suivant :
     * <ul>
     * <li>Une Chaine qui définit le type de retour.</li>
     * <li>Une Liste de chaine qui stocke l'ensemble de parametres de la methode</li>
     * <li>Un  booléen qui définit si la methode est Statique.</li>
     * <li>Un  booléen qui définit si la methode est Finale.</li>
     * <li>Un  booléen qui définit si la methode est Abstraite.</li>
     * <li>Un  Chaine  qui définit le nom de la methode.</li>
     * </ul>
     * </p>
     *
     * @author InnovAction
     * @version 1.0
     */

public class Methode
{
    /**
     * Nom de la methode
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#getNom()
     * @see Methode#setNom(String)
     * @see Methode#toString()
     */
    private String nom;

    /**
     * Visibilité de la methode
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#getVisibilite() 
     * @see Methode#setVisibilite(char) 
     * @see Methode#toString()
     */
    private char visibilite;
    
    /**
     * Type de retour de la methode
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#getTypeDeRetour() 
     * @see Methode#setTypeDeRetour(String)
     * @see Methode#toString()
     */
    private String typeDeRetour;

    /**
     * Ensemenble des parametres de la methode
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#getEnsParametre()
     * @see Methode#setEnsParametre(ArrayList)
     * @see Methode#toString()
     */
    private ArrayList<Parametre> ensParametre;

    /**
     * Verifie si la methode est statique ou non
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#isEstStatique() 
     * @see Methode#setEstAbstraite(boolean) 
     * @see Methode#toString()
     */
    private boolean estStatique;

    /**
     * Verifie si la methode est Finale ou non
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#isEstFinale()
     * @see Methode#setEstFinale(boolean) 
     * @see Methode#toString()
     */
    private boolean estFinale;

    /**
     * Verifie si la methode est Abstraite ou non
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#isEstAbstraite()
     * @see Methode#setEstAbstraite(boolean)
     * @see Methode#toString()
     */
    private boolean estAbstraite;

    /**
     * <b>Constructeur ConfigGenerator.</b>
     * <p>
     * A l'instanciation d'un objet de type Methode
     * </p>
     *
     * @param nom
     *            Nom de la methode.
     * @param visibilite
     *            visibilite de la methode.
     * @param typeDeRetour
     *            Le type de retour de la methode.
     * @param ensParametre
     *            Ensemble des parametres de la methode.
     * @param estAbstraite
     *            Verifie si la methode est abstraite
     * @param estFinale
     *            Verifie si la methode est Finale
     * @param estStatique
     *            Verifie si la methode est Statique
     *
     * @see ----- Utilise les attributs -----
     * @see Methode#visibilite
     * @see Methode#typeDeRetour
     * @see Methode#ensParametre
     * @see Methode#estStatique
     * @see Methode#estFinale
     * @see Methode#estAbstraite
     * @see Methode#nom
     */
    public Methode(String nom, char visibilite, String typeDeRetour, ArrayList<Parametre> ensParametre,
                   boolean estStatique, boolean estFinale, boolean estAbstraite)
    {
        this.visibilite = visibilite;
        this.typeDeRetour = typeDeRetour;
        this.ensParametre = ensParametre;
        this.estStatique = estStatique;
        this.estFinale = estFinale;
        this.estAbstraite = estAbstraite;
        this.nom = nom;
    }

    public void setVisibilite   (char visibilite)                   { this.visibilite = visibilite;     }
    public void setTypeDeRetour (String typeDeRetour)               { this.typeDeRetour = typeDeRetour; }
    public void setEnsParametre (ArrayList<Parametre> ensParametre) { this.ensParametre = ensParametre; }
    public void setEstStatique  (boolean estStatique)               { this.estStatique = estStatique;   }
    public void setEstFinale    (boolean estFinale)                 { this.estFinale = estFinale;       }
    public void setEstAbstraite (boolean estAbstraite)              { this.estAbstraite = estAbstraite; }
    public void setNom          (String nom)                        { this.nom = nom;                   }

    public ArrayList<Parametre> getEnsParametre()   { return ensParametre;  }
    public boolean              isEstFinale()       { return estFinale;     }
    public boolean              isEstStatique()     { return estStatique;   }
    public boolean              isEstAbstraite()    { return estAbstraite;  }
    public String               getTypeDeRetour()   { return typeDeRetour;  }
    public String               getNom()            { return nom;           }
    public char                 getVisibilite()     { return visibilite;    }

    @Override
    public String toString() {

        String sRet = "";
        sRet += visibilite + " ";
        sRet += nom;
        sRet += "(";
        if (ensParametre.size() > 0)
        {
            for (Parametre p : ensParametre)
                sRet += p.toString() + ", ";
            sRet = sRet.substring(0, sRet.length() - 2);
        }
        sRet += ")";

        if (estFinale) sRet += "{gelée} ";
        if (estAbstraite) sRet += "{abstract} ";

        if(!typeDeRetour.contains("{constructeur}") && !typeDeRetour.contains("void")) sRet += ": " + typeDeRetour;

        if (estStatique) {
            String underline = "";
            for (int i = 0; i < sRet.length(); i++) underline += "¯";
            sRet += "\n" + underline;
        }

        sRet += "\n";
        return sRet;
    }

    /**
     * Permet de renvoyer au format texte l'attribut sans son type de retour
     *
     * @see ----- Utilise les attributs -----
     * @see Methode#visibilite
     * @see Methode#ensParametre
     * @see Methode#estStatique
     * @see Methode#estFinale
     * @see Methode#estAbstraite
     * @see Methode#nom
     */
    public String toStringNoReturnType()
    {
        String sRet = "";
        sRet += visibilite + " ";
        sRet += nom;
        sRet += "(";
        if (ensParametre.size() > 0)
        {
            for (Parametre p : ensParametre) sRet += p.toString() + ", ";
            sRet = sRet.substring(0, sRet.length() - 2);
        }
        sRet += ")";

        if (estFinale) sRet += "{gelée} ";
        if (estAbstraite) sRet += "{abstract} ";

        return sRet;

    }
}
