package java2uml.metier;

    /**
     * <b>Association est la classe qui sert a stocker les informations d'une association.</b>
     * <p>
     * Association possède les attributs suivant :
     * <ul>
     * <li>Une Chaine qui définit le nom de la classe de droite.</li>
     * <li>Une Chaine qui définit le nom de la classe de gauche.</li>
     * <li>Une Chaine qui définit la multiplicite de l'association.</li>
     * <li>Une chaine qui définit le type de fleche de l'association.</li>
     * <li>Un entier qui définit le numero de l'association</li>
     * </ul>
     * </p>
     *
     * @author InnovAction
     * @version 1.0
     */

public class Association
{
    /**
     * Classe a gauche de l'association
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Association#Association(String, String, String, String, String)
     * @see Association#getClasseGauche()
     * @see Association#setClasseGauche(String)
     * @see Association#toString()
     */
    private String classeGauche;

    /**
     * Classe a droite de l'association
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Association#Association(String, String, String, String, String)
     * @see Association#getClasseDroite()
     * @see Association#setClasseDroite(String)
     * @see Association#toString()
     */
    private String classeDroite;

    /**
     * Multiplicite de la classe à gauche de l'association
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Association#Association(String, String, String, String, String)
     * @see Association#getMultipliciteGauche()
     * @see Association#setMultipliciteGauche(String)
     * @see Association#toString()
     */
    private String multipliciteGauche;
    /**
     * Multiplicite de la classe à gauche de l'association
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Association#Association(String, String, String, String, String)
     * @see Association#getMultipliciteDroite()
     * @see Association#setMultipliciteDroite(String)
     * @see Association#toString()
     */
    private String multipliciteDroite;

    /**
     * Type/style de fleche utilisé pour l'association
     *  <> (agrégation), <//> (composition), |> (héritage), (+) (classe interne)
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Association#Association(String, String, String, String, String)
     * @see Association#getTypeFleche()
     * @see Association#setTypeFleche(String)
     * @see Association#toString()
     */
    private String typeFleche;

    /**
     * Numero de l'association
     *
     * @see ----- Utilisé dans les méthodes -----
     * @see Association#Association(String, String, String, String, String)
     * @see Association#getNum()
     * @see Association#setNum(int)
     * @see Association#toString()
     */
    private int num;

    /**
     * Compteur du nombre d'assiciation
     * @see Association#Association(String, String, String, String, String)
     */
    public static int compteur = 1;

    /**
     * <b>Constructeur Association.</b>
     * <p>
     * A l'instanciation d'un objet de type Association
     * </p>
     *
     * @param classeGauche
     *            Classe a Gauche de l'association
     * @param classeDroite
     *            Classe a droite de l'association
     * @param multipliciteGauche
     *           Multiplicite de la classe à gauche de l'association
     * @param multipliciteDroite
     *           Multiplicite de la classe à droite de l'association
     * @param typeFleche
     *          Type/style de fleche utilisé pour l'association
     *
     *
     * @see ----- Utilise les attributs -----
     * @see Association#classeGauche
     * @see Association#classeDroite
     * @see Association#multipliciteGauche
     * @see Association#multipliciteDroite
     * @see Association#typeFleche
     * @see Association#num
     * @see Association#compteur
     */
    public Association(String classeGauche, String classeDroite, String multipliciteGauche, String multipliciteDroite, String typeFleche)
    {
        this.classeGauche = classeGauche;
        this.classeDroite = classeDroite;
        this.multipliciteGauche = multipliciteGauche;
        this.multipliciteDroite = multipliciteDroite;
        this.typeFleche = typeFleche;
        this.num = compteur++;
    }

    public String   getClasseGauche      () { return classeGauche;          }
    public String   getClasseDroite      () { return classeDroite;          }
    public String   getMultipliciteGauche() { return multipliciteGauche;    }
    public String   getMultipliciteDroite() { return multipliciteDroite;    }
    public String   getTypeFleche        () { return typeFleche;            }
    public int      getNum               () { return num;                   }

    public void     setClasseGauche         (String classeGauche)       { this.classeGauche = classeGauche;                     }
    public void     setClasseDroite         (String classeDroite)       {  this.classeDroite = classeDroite;                    }
    public void     setMultipliciteGauche   (String multipliciteGauche) {        this.multipliciteGauche = multipliciteGauche;  }
    public void     setMultipliciteDroite   (String multipliciteDroite) { this.multipliciteDroite = multipliciteDroite;         }
    public void     setTypeFleche           (String typeFleche)         { this.typeFleche = typeFleche;                         }
    public void     setNum                  (int num)                   { this.num = num;                                       }

    /**
     * Permet de renvoyer au format texte le type de l'association en fonction du type de fleche utilisé.
     * @see ----- Utilise les attributs -----
     * @see Association#typeFleche
     *
     */
    public String getTypeAssociation()
    {
        String sRet;
        if(typeFleche.contains(".")) sRet = "implémentation";
        else
	        if(typeFleche.contains("|>")) sRet = "généralisation/spécialisation";
	        else
	            if(typeFleche.contains("<//>")) sRet = "composition";
	            else
	                if(typeFleche.contains("<>")) sRet = "agrégation";
	                else
	                    if((typeFleche.contains("<") && !typeFleche.contains(">")) || (typeFleche.contains(">") && !typeFleche.contains("<"))) sRet= "unidirectionnelle";
	                    else
	                        if(typeFleche.contains("(+)")) sRet = "Classe interne";
	                        else sRet= "bidirectionnelle";

        return sRet;
    }

    public String toString() {
        String sRet="\t\t";
        sRet+="Association " + num + " : "+ getTypeAssociation() + '\n';

        sRet += "\t\t\t"+classeGauche +' ' +multipliciteGauche + ' ';
        sRet += typeFleche.replaceAll("\\.", " ");;
        
        sRet += ' ' + multipliciteDroite + ' '+ classeDroite + " \n" ;
        return  sRet;
    }
}
