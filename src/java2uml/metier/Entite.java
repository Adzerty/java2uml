package java2uml.metier;

import java.util.ArrayList;

public class Entite
{
    private ArrayList<Methode> ensMethode;
    private ArrayList<Attribut> ensAttribut;
    private String nom;
    private String type;
    private boolean estAbstraite;
    private boolean estFinale;
    private Entite mere;
    private ArrayList<Association> ensAssociations;

    public Entite(ArrayList<Methode> ensMethode, ArrayList<Attribut> ensAttribut, String nom, String type, boolean estAbstraite,
                  boolean estFinale, Entite mere, ArrayList<Association> ensAssociations)
    {
        this.ensMethode = ensMethode;
        this.ensAttribut = ensAttribut;
        this.nom = nom;
        this.type = type;
        this.estAbstraite = estAbstraite;
        this.estFinale = estFinale;
        this.mere = mere;
        this.ensAssociations = ensAssociations;
    }

    public ArrayList<Methode> getEnsMethode() {
        return ensMethode;
    }

    public void setEnsMethode(ArrayList<Methode> ensMethode) {
        this.ensMethode = ensMethode;
    }

    public ArrayList<Attribut> getEnsAttribut() {
        return ensAttribut;
    }

    public void setEnsAttribut(ArrayList<Attribut> ensAttribut) {
        this.ensAttribut = ensAttribut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEstAbstraite() {
        return estAbstraite;
    }

    public void setEstAbstraite(boolean estAbstraite) {
        this.estAbstraite = estAbstraite;
    }

    public boolean isEstFinale() {
        return estFinale;
    }

    public void setEstFinale(boolean estFinale) {
        this.estFinale = estFinale;
    }

    public Entite getMere() {
        return mere;
    }

    public void setMere(Entite mere) {
        this.mere = mere;
    }

    public ArrayList<Association> getEnsAssociations() {
        return ensAssociations;
    }

    public void setEnsAssociations(ArrayList<Association> ensAssociations) {
        this.ensAssociations = ensAssociations;
    }
}
