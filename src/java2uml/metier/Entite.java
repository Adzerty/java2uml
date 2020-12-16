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

    public int getTailleMax()
    {
        int maxTaille=0;

        //ATTIBUTS
        int tailleAttNoType=0;
        int tailleAttType=0;

        for (Attribut a: ensAttribut )
        {
            if(tailleAttNoType<a.toStringNoType().length())tailleAttNoType=a.toStringNoType().length();
            if(tailleAttType<a.toString().length())tailleAttType=a.toString().length();
        }
        for (Attribut a: ensAttribut )
        {
            String temp = "";
            temp+=String.format("%-"+String.valueOf(tailleAttNoType)+ 's',a.toStringNoType());
            temp+=String.format("%-"+String.valueOf(tailleAttType-tailleAttNoType)+ 's'," : "+ a.getType());
            if(temp.length()>maxTaille)maxTaille=temp.length();
        }

        //Methodes
        int tailleMetNoType=0;
        int tailleMetType=0;

        for (Methode m: ensMethode )
        {
            if(tailleMetNoType<m.toStringNoReturnType().length())tailleMetNoType=m.toStringNoReturnType().length();
            if(tailleMetType<m.toString().length())tailleMetType=m.toString().length();
        }
        for (Methode m: ensMethode )
        {
            String temp="";
            temp+=String.format("%-"+String.valueOf(tailleMetNoType)+ 's',m.toStringNoReturnType());
            temp+=String.format("%-"+String.valueOf(tailleMetType-tailleMetNoType)+ 's'," : "+ m.getTypeDeRetour());

            if(temp.length()>maxTaille)maxTaille=temp.length();
            temp+="\n";
        }

        String ligneNom="\n│";
        for (int i = 0; i < (int) (maxTaille - nom.length()- type.length()-3) /2 ; i++)
            ligneNom+=" ";
        ligneNom+= type + " : ";
        ligneNom+= nom;
        if(ligneNom.length()>maxTaille) maxTaille = ligneNom.length();

        return  maxTaille;
    }
    @Override
    public String toString()
    {
        String sRet="";

        int maxTaille = getTailleMax();

        //ATTIBUTS
        int tailleAttNoType=0;
        int tailleAttType=0;

        for (Attribut a: ensAttribut )
        {
            if(tailleAttNoType<a.toStringNoType().length())tailleAttNoType=a.toStringNoType().length();
            if(tailleAttType<a.toString().length())tailleAttType=a.toString().length();
        }
        String attribut="\n";
        for (Attribut a: ensAttribut )
        {
            String temp = "│";
            temp+=String.format("%-"+String.valueOf(tailleAttNoType)+ 's',a.toStringNoType());
            temp+=String.format("%-"+String.valueOf(tailleAttType-tailleAttNoType)+ 's'," : "+ a.getType());
            for (int i = temp.length(); i<=maxTaille;i++) temp+=" ";
            temp+="│";
            if(a.isEstStatique())
            {
                String underline="";
                for (int i = 0; i <maxTaille; i++)
                    if(i<a.toStringNoType().length())
                        underline+="¯";
                    else
                        underline+=" ";
                temp+="\n│"+underline+"│";
            }

            attribut+=temp+"\n";
        }

        //Methodes
        int tailleMetNoType=0;
        int tailleMetType=0;

        for (Methode m: ensMethode )
        {
            if(tailleMetNoType<m.toStringNoReturnType().length())tailleMetNoType=m.toStringNoReturnType().length();
            if(tailleMetType<m.toString().length())tailleMetType=m.toString().length();
        }

        String methode="\n";
        for (Methode m: ensMethode )
        {
            String temp="│";
            temp+=String.format("%-"+String.valueOf(tailleMetNoType)+ 's',m.toStringNoReturnType());
            temp+=String.format("%-"+String.valueOf(tailleMetType-tailleMetNoType)+ 's'," : "+ m.getTypeDeRetour());
            for (int i = temp.length(); i<=maxTaille;i++) temp+=" ";
            temp+="│";
            if (m.isEstAbstraite()) temp = "│\033[3m"+temp.substring(1,temp.length()-1)+"\033[0m│";
            if(m.isEstStatique())
            {
                String underline="";
                for (int i = 0; i <maxTaille; i++)
                    if(i<m.toStringNoReturnType().length())
                        underline+="¯";
                    else
                        underline+=" ";
                temp+="\n│"+underline+"│";
            }
            methode+=temp+"\n";
        }

        //MISE EN FORME
        String separation="";
        for (int i = 0; i < maxTaille ; i++)
            separation+="─";

        String ligneNom="\n│";
        for (int i = 0; i < (int) (maxTaille - nom.length()- type.length()-3) /2 ; i++)
            ligneNom+=" ";
        ligneNom+= type + " : ";
        ligneNom+= nom;
        for (int i = ligneNom.length(); i <=maxTaille+1; i++)
            ligneNom+=" ";

        ligneNom+="│";
        if (estAbstraite) ligneNom = "\n│\033[3m"+ligneNom.substring(2,ligneNom.length()-1)+"\033[0m│";
        ligneNom+="\n";

        sRet += "┌" + separation + "┐";
        sRet += ligneNom;
        sRet += "├"+separation+"┤";
        sRet += attribut;
        sRet += "├"+separation+"┤";
        sRet += methode;
        sRet += "└" + separation + "┘\n\n\n";

        return sRet;
    }
}
