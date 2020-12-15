package java2uml.metier;

import java.util.ArrayList;

public class Methode
{
    private String nom;
    private char visibilite;
    private String typeDeRetour;
    private ArrayList<Parametre> ensParametre;
    private boolean estStatique;
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
    
    public String toString() {
    	return this.visibilite + " " + this.typeDeRetour + " " + this.nom;
    }
}
