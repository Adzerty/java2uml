package java2uml.metier;

import java.util.ArrayList;

    /**
     * <b>Entite est la classe qui sert a stocker les information d'une entite.</b>
     * <p>
     * entite possède les attributs suivant :
     * <ul>
     * <li>Une Liste de methodes qui stock toutes les methodes d'une entite</li>
     * <li>Une Liste d'attributs qui stock tous les attributs d'une entite</li>
     * <li>Une Chaine qui définit le nom de la methode.</li>
     * <li>Une Chaine qui définit le type de la methode.</li>
     * <li>Un  Boolean qui définit si l'entite est abstraite</li>
     * <li>Un  Boolean qui définit si l'entite est finale</li>
     * <li>Une Liste d'associations qui stock toutes les associations d'une entite</li> 
     * <li>Une Liste de contrainte qui stock toutes les contraintes d'une entite</li> 
     * </ul>
     * </p>
     *
     * @author InnovAction
     * @version 1.0
     */
    
public class Entite
{
    /**
     * Liste des methodes de l'entité
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[]) 
     * @see Entite#getEnsMethode() 
     * @see Entite#setEnsMethode(ArrayList) 
     * @see Entite#toString() 
     */
    private ArrayList<Methode> ensMethode;
    
    /**
     * Liste des attribut de l'entité
     *
     * @see ----- Utilisé dans les attribut -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[])
     * @see Entite#getEnsAttribut() 
     * @see Entite#setEnsAttribut(ArrayList) 
     * @see Entite#toString()
     */
    private ArrayList<Attribut> ensAttribut;

    /**
     * Le nom de l'entité.
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[])
     * @see Entite#getNom()
     * @see Entite#setNom(String)
     * @see Entite#toString()
     */
    private String nom;

    /**
     * Le type de l'entité.
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[])
     * @see Entite#getType() 
     * @see Entite#setType(String) 
     * @see Entite#toString()
     */
    private String type;
    /**
     * Si l'entité est abstraite.
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[]) 
     * @see Entite#isEstAbstraite()
     * @see Entite#setEstAbstraite(boolean)
     * @see Entite#toString()
     */
    private boolean estAbstraite;

    /**
     * Si l'entité est Finale.
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[])
     * @see Entite#isEstFinale()
     * @see Entite#setEstFinale(boolean)
     * @see Entite#toString()
     */
    private boolean estFinale;

    /**
     * Liste des associations de l'entité
     *
     * @see ----- Utilisé dans les attribut -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[])
     * @see Entite#getEnsAssociations()
     * @see Entite#setEnsAssociations(ArrayList)
     * @see Entite#toString()
     */
    private ArrayList<Association> ensAssociations;

    /**
     * Tableau de contraintes de l'entité
     *
     * @see ----- Utilisé dans les attribut -----
     * @see Entite#Entite(ArrayList, ArrayList, String, String, boolean, boolean, ArrayList, String[])
     * @see Entite#getContraintes()
     * @see Entite#setContraintes(String[])
     * @see Entite#toString()
     */
    private String [] contraintes;


    /**
     * <b>Constructeur Entite.</b>
     * <p>
     * On crée une Entite pour l'affichage du diagramme
     * </p>
     *
     * @param ensMethode
     *            Ensemble des methodes de l'entité
     * @param ensAttribut
     *            Ensemble de attributs de l'entité
     * @param nom
     *            Nom de l'entité
     * @param type
     *            Type de l'entité
     * @param estAbstraite
     *            Verifie si l'entité est abstraite.
     * @param estFinale
     *            La finalité de l'entité
     * @param ensAssociations
     *             L'ensemble des associations de l'entité
     * @param contraintes
     *             Les contraintes de l'entité
     *
     * @see ----- Utilise les attributs ----
     * @see Entite#ensMethode
     * @see Entite#ensAttribut
     * @see Entite#nom
     * @see Entite#type
     * @see Entite#estAbstraite
     * @see Entite#estFinale
     * @see Entite#ensAssociations
     * @see Entite#contraintes
     *
     */
    public Entite(ArrayList<Methode> ensMethode, ArrayList<Attribut> ensAttribut, String nom, String type, boolean estAbstraite,
                  boolean estFinale, ArrayList<Association> ensAssociations,String[] contraintes)
    {
        this.ensMethode = ensMethode;
        this.ensAttribut = ensAttribut;
        this.nom = nom;
        this.type = type;
        this.estAbstraite = estAbstraite;
        this.estFinale = estFinale;
        this.ensAssociations = ensAssociations;
        this.contraintes = contraintes;
    }
    public ArrayList<Methode>   getEnsMethode           (){ return ensMethode;      }
    public ArrayList<Attribut>  getEnsAttribut          (){ return ensAttribut;     }
    public String               getNom                  (){ return nom;             }
    public String               getType                 (){ return type;            }
    public boolean              isEstAbstraite          (){ return estAbstraite;    }
    public boolean              isEstFinale             (){ return estFinale;       }
    public String[]             getContraintes          (){ return contraintes;     }
    public ArrayList<Association> getEnsAssociations    (){ return ensAssociations; }

    public void setEnsMethode      (ArrayList<Methode> ensMethode)          { this.ensMethode = ensMethode;          }
    public void setEnsAttribut     (ArrayList<Attribut> ensAttribut)        { this.ensAttribut = ensAttribut;        }
    public void setNom             (String nom)                             { this.nom = nom;                        }
    public void setType            (String type)                            { this.type = type;                      }
    public void setEstAbstraite    (boolean estAbstraite)                   { this.estAbstraite = estAbstraite;      }
    public void setEstFinale       (boolean estFinale)                      { this.estFinale = estFinale;            }
    public void setEnsAssociations (ArrayList<Association> ensAssociations) { this.ensAssociations = ensAssociations;}
    public void setContraintes     (String[] contraintes)                   { this.contraintes = contraintes;        }


    /**
     * Permet de connaitre la taille de la ligne la plus longue.
     *
     * @see ----- Utilise les attributs ----
     * @see Entite#ensMethode
     * @see Entite#ensAttribut
     * @see Entite#nom
     * @see Entite#type
     * @see Entite#estAbstraite
     * @see Entite#estFinale
     * @see Entite#ensAssociations
     * @see Entite#contraintes
     *
     */
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
            if(!m.getTypeDeRetour().contains("{constructeur}") && !m.getTypeDeRetour().contains("void"))
                temp+=String.format("%-"+String.valueOf(tailleMetType-tailleMetNoType)+ 's'," : "+ m.getTypeDeRetour());

            if(temp.length()>maxTaille)maxTaille=temp.length();
            temp+="\n";
        }
        String contrainte = nom+" ";
        if(this.contraintes!=null) for (String cont: this.contraintes) contrainte +="{"+cont+"} ";
        if (estAbstraite) contrainte = nom + "{abstract}";
        if(contrainte.length()>maxTaille) maxTaille = contrainte.length();

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
                temp+="\n\t\t│"+underline+"│";
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
            if(!m.getTypeDeRetour().contains("{constructeur}") && !m.getTypeDeRetour().contains("void"))
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
                temp+="\n\t\t│"+underline+"│";
            }
            methode+=temp+"\n";
        }

        //MISE EN FORME
        String separation="";
        for (int i = 0; i < maxTaille ; i++) separation+="─";

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

        String temp = "\t\t│";
        String contrainte=" ";
        if(this.contraintes!=null) for (String cont: this.contraintes) contrainte +="{"+cont+"} ";
        if (estAbstraite)contrainte+="{abstract}";

        for (int i = 0; i < (int) (maxTaille - nom.length()- contrainte.length()) /2 ; i++) temp+= ' ';
        temp+= nom + contrainte;
        for (int i = temp.length(); i <= maxTaille; i++)  temp+=" ";
        ligneNom+=temp;

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
