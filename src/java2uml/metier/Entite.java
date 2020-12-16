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
    private ArrayList<Association> ensAssociations;

    public Entite(ArrayList<Methode> ensMethode, ArrayList<Attribut> ensAttribut, String nom, String type, boolean estAbstraite,
                  boolean estFinale, ArrayList<Association> ensAssociations)
    {
        this.ensMethode = ensMethode;
        this.ensAttribut = ensAttribut;
        this.nom = nom;
        this.type = type;
        this.estAbstraite = estAbstraite;
        this.estFinale = estFinale;
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

    public ArrayList<Association> getEnsAssociations() {
        return ensAssociations;
    }

    public void setEnsAssociations(ArrayList<Association> ensAssociations) {
        this.ensAssociations = ensAssociations;
    }

    @Override
    public String toString()
    {
        String sRet="";
        String separation = "\n";
        int taille = tailleSeparation();
        for (int i = 0; i < taille ; i++)
            separation+="-";

        separation += "\n";
        for (int i = 0; i < (int) (taille - nom.length()- type.length()-3) /2 ; i++)
            sRet+=" ";

        sRet+= type + " : ";
        sRet+= nom;
        if (estAbstraite) sRet = "\033[3m"+sRet+"\033[0m";
        sRet += separation;


        //ATTIBUTS
        int tailleAtt=0;

        for (Attribut a: ensAttribut )
           if(tailleAtt<a.toStringNoType().length())tailleAtt=a.toStringNoType().length();

        for (Attribut a: ensAttribut )
        {
            String attribut="";
            attribut+=String.format("%-"+String.valueOf(tailleAtt)+ 's',a.toStringNoType());
            attribut+=String.format("%-"+String.valueOf(taille-tailleAtt)+ 's'," : "+ a.getType());
            if(a.isEstStatique())
            {
                String underline="";
                for (int i = 0; i <a.toStringNoType().length(); i++) underline+="¯";
                attribut+="\n"+underline;
            }
            sRet+="\n"+attribut;
        }

        sRet+= separation;


        //Methodes
        for (Methode m: ensMethode )
            sRet+=m.toString();

        sRet += separation;
        int compteurAssociation=0;
        if(ensAssociations.size()>0)
            for (Association a: ensAssociations )
            {
                sRet+=a.toString(compteurAssociation);
                compteurAssociation++;
            }


        return sRet;
    }
    public int tailleSeparation()
    {
        int taille = nom.length();

        for (Attribut a: ensAttribut )
            if(a.toString().length()>taille) taille = a.toString().length();

        for (Methode m: ensMethode )
            if(m.toString().length()>taille) taille = m.toString().length();

        if(ensAssociations.size()>0)
            for (Association a: ensAssociations )
                if(a.toString().length()>taille) taille = a.toString().length();

        return taille;
    }
}
