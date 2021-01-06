package java2uml.metier;

import java.util.ArrayList;

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
     * Verifie si la methode est statique ou non
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Methode#Methode(String, char, String, ArrayList, boolean, boolean, boolean)
     * @see Methode#isEstFinale() 
     * @see Methode#setEstFinale(boolean) 
     * @see Methode#toString()
     */
    private boolean estFinale;
    private boolean estAbstraite;

    public Methode(String nom, char visibilite, String typeDeRetour, ArrayList<Parametre> ensParametre,
                   boolean estStatique, boolean estFinale, boolean estAbstraite)
    {
        this.nom = nom;
        this.visibilite = visibilite;
        this.typeDeRetour = typeDeRetour;
        this.ensParametre = ensParametre;
        this.estStatique = estStatique;
        this.estFinale = estFinale;
        this.estAbstraite = estAbstraite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(char visibilite) {
        this.visibilite = visibilite;
    }

    public String getTypeDeRetour() {
        return typeDeRetour;
    }

    public void setTypeDeRetour(String typeDeRetour) {
        this.typeDeRetour = typeDeRetour;
    }

    public ArrayList<Parametre> getEnsParametre() {
        return ensParametre;
    }

    public void setEnsParametre(ArrayList<Parametre> ensParametre) {
        this.ensParametre = ensParametre;
    }

    public boolean isEstStatique() {
        return estStatique;
    }

    public void setEstStatique(boolean estStatique) {
        this.estStatique = estStatique;
    }

    public boolean isEstFinale() {
        return estFinale;
    }

    public void setEstFinale(boolean estFinale) {
        this.estFinale = estFinale;
    }

    public boolean isEstAbstraite() {
        return estAbstraite;
    }

    public void setEstAbstraite(boolean estAbstraite) {
        this.estAbstraite = estAbstraite;
    }


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
    public String toStringNoReturnType()
    {
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

        return sRet;

    }
}
