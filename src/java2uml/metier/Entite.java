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
            if(!m.getTypeDeRetour().contains("{constructeur}"))
                temp+=String.format("%-"+String.valueOf(tailleMetType-tailleMetNoType)+ 's'," : "+ m.getTypeDeRetour());

            if(temp.length()>maxTaille)maxTaille=temp.length();
            temp+="\n";
        }

        if (estAbstraite)
        {
            String temp = nom + " {abstract}";
            if(temp.length()>maxTaille) maxTaille = temp.length();
        }
        else
            if(nom.length()>maxTaille) maxTaille = nom.length();

        if(type.contains("Enum"))   if("<<enumeration>>".length()>maxTaille) maxTaille = "<<enumeration>>".length();
        else if(type.contains("Interface")) if("Interface".length()>maxTaille) maxTaille = "Interface".length();
        return  maxTaille+2;
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
            String temp = "\t\t│";
            temp+=String.format("%-"+String.valueOf(tailleAttNoType)+ 's',a.toStringNoType());
            temp+=String.format("%-"+String.valueOf(tailleAttType-tailleAttNoType)+ 's'," : "+ a.getType());
            for (int i = temp.length(); i<=maxTaille;i++) temp+=" ";
            temp+="  │";
            if(a.isEstStatique())
            {
                String underline="";
                for (int i = 0; i <maxTaille; i++)
                    if(i<tailleAttType)
                        underline+="¯";
                    else
                        underline+=" ";
                temp+="\n\t\t│"+underline+"  │";
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
            String temp="\t\t│";
            String underline="";
            temp+=String.format("%-"+String.valueOf(tailleMetNoType)+ 's',m.toStringNoReturnType());
            if(!m.getTypeDeRetour().contains("{constructeur}"))
                temp+=String.format("%-"+String.valueOf(tailleMetType-tailleMetNoType)+ 's'," : "+ m.getTypeDeRetour());

            for (int i = temp.length(); i<=maxTaille;i++) temp+=" ";
            temp+="  │";
            if(m.isEstStatique())
            {
                for (int i = 0; i <maxTaille; i++)
                    if(i<tailleMetType)
                        underline+="¯";
                    else
                        underline+=" ";
                temp+="\n\t\t│"+underline+"  │";
            }


            methode+=temp+"\n";
        }

        //MISE EN FORME
        String separation="";
        for (int i = 0; i < maxTaille ; i++)
            separation+="─";

        String ligneNom="\n\t\t│";
        if(type.contains("Enum"))
        {
            for (int i = 0; i < (int) (maxTaille - "<<enumeration>>".length()) /2 ; i++)  ligneNom+=" ";
            ligneNom+= "<<enumeration>>";
        }
        else if(type.contains("Interface"))
        {
            for (int i = 0; i < (int) (maxTaille - "<<interface>>".length()) /2 ; i++)  ligneNom+=" ";
            ligneNom+= "<<interface>>";
        }
        for (int i = ligneNom.length()-1; i <= maxTaille; i++)  ligneNom+=" ";
        ligneNom+="  │\n";

        if (estAbstraite)
        {
            String temp = "\t\t│";
            for (int i = 0; i < (int) (maxTaille - nom.length()- " {abstract}".length()) /2 ; i++) temp+= ' ';
            temp+= nom + " {abstract}";
            for (int i = temp.length(); i <= maxTaille; i++)  temp+=" ";
            ligneNom+=temp;
        }
        else
        {
            String temp = "\t\t│";
            for (int i = 0; i < (int) (maxTaille - nom.length()) /2 ; i++) temp+= ' ';
            temp+=nom;
            for (int i = temp.length(); i <= maxTaille; i++)  temp+=" ";
            ligneNom+=temp;
        }

        ligneNom+="  │\n";

        sRet += "\t\t┌" + separation + "┐";
        sRet += ligneNom;
        sRet += "\t\t├"+separation+"┤";
        sRet += attribut;
        sRet += "\t\t├"+separation+"┤";
        sRet += methode;
        sRet += "\t\t└" + separation + "┘\n\n\n";

        return sRet;
    }
}
