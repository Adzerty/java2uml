package java2uml.metier;

/**
 * <b>Attribut est la classe qui permet de stocker un Attribut avec ses infos</b>
 * <p>
 * Attribut possède les attributs suivant :
 * <ul>
 * <li>Une chaine qui va définir le nom de l'attribut</li>
 * <li>Un caractère qui définit sa visibilité</li>
 * <li>Un booléen qui définit si l'attribut est static</li>
 * <li>Un booléen qui définit si l'attribut est final</li>
 * <li>Une chaine qui donne sa valeur par defaut </li>
 * <li>Une chaine qui donne son type </li>
 * <li>Un tableau de chaine qui va stocker les propriétés de cet attribut</li>
 * <li>Une chaine qui permet de définir la multiplicite de l'attribut</li>
 * </ul>
 * </p>
 * 
 * 
 * @author InnovAction
 * @version 1.0
 */
public class Attribut
{
	/**
	 * Le nom de l'attribut.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String)
	 * @see Attribut#getNom()
	 * @see Attribut#setNom(String)
	 * @see Attribut#toString()
	 * @see Attribut#toStringNoType()
	 */
    private String nom;
    
    /**
	 * La visibilité de l'attribut.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String)
	 * @see Attribut#getVisibilite()
	 * @see Attribut#setVisibilite(char)
	 * @see Attribut#toString()
	 * @see Attribut#toStringNoType()
	 */
    private char visibilite;
    
    /**
	 * Si l'attribut est static.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String)
	 * @see Attribut#isEstStatique()
	 * @see Attribut#setEstStatique(boolean)
	 * @see Attribut#toString()
	 */
    private boolean estStatique;
    
    /**
	 * Si l'attribut est final.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String)
	 * @see Attribut#isEstFinale()
	 * @see Attribut#setEstFinale(boolean)
	 * @see Attribut#toString()
	 * @see Attribut#toStringNoType()
	 */
    private boolean estFinale;
    
    /**
	 * La valeur par défaut de l'attribut.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String)
	 * @see Attribut#getValeurParDefault()
	 * @see Attribut#setValeurParDefault(String)
	 * @see Attribut#toString()
	 * @see Attribut#toStringNoType()
	 */
    private String valeurParDefault;
    
    /**
	 * La type de l'attribut.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String)
	 * @see Attribut#getType()
	 * @see Attribut#setType(String)
	 * @see Attribut#toString()
	 */
    private String type;
    
    /**
	 * Le tableau de propriétes de l'attribut.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String) ()
	 * @see Attribut#getContrainte()
	 * @see Attribut#setContrainte(String[])
	 * @see Attribut#toString()
	 * @see Attribut#toStringNoType()
	 */
    private String [] contrainte;
    
    /**
	 * La multiplicité de l'attribut.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Attribut#Attribut(String, char, boolean, boolean, String, String, String[], String) ()
	 * @see Attribut#getMultiplicite()
	 * @see Attribut#setMultiplicite(String)
	 * @see Attribut#toString()
	 * @see Attribut#toStringNoType()
	 */
    private String multiplicite;

    
    /**
     * <b>Constructeur Attribut.</b>
     * <p>
     * On crée un Attribut pour l'affichage du diagramme
     * </p>
     * 
     * @param nom
     *            Le nom de l'attribut.
     * @param visibilite
     *            La visibilite de l'attribut.
     * @param estStatique
     *            La staticité de l'attribut.
     * @param estFinale
     *            La Finalité de l'attribut.
     * @param contrainte
     *            Les contraintes de l'attribut
     * @param multiplicite
     *            Les multiplicité de l'attribut
     * @param type
     *            Le type de l'attribut
     * @param valeurParDefault
     *            La valeur par defaut de l'attribut
     * 
     * @see ----- Utilise les attributs ----
     * @see Attribut#nom
     * @see Attribut#visibilite
     * @see Attribut#estStatique
     * @see Attribut#estFinale
     * @see Attribut#valeurParDefault
     * @see Attribut#type
     * @see Attribut#contrainte
     * @see Attribut#multiplicite
     *
     */
    public Attribut(String nom, char visibilite, boolean estStatique, boolean estFinale, String valeurParDefault,String type,String[] contrainte,String multiplicite)
    {
        this.nom = nom;
        this.visibilite = visibilite;
        this.estStatique = estStatique;
        this.estFinale = estFinale;
        this.valeurParDefault = valeurParDefault;
        this.type=type;
        this.contrainte = contrainte;
        this.multiplicite = multiplicite;
    }

    public String   getNom() {
        return nom;
    }
    public void     setNom(String nom) {
        this.nom = nom;
    }
    public char     getVisibilite() {
        return visibilite;
    }
    public void     setVisibilite(char visibilite) {
        this.visibilite = visibilite;
    }
    public boolean  isEstStatique() {
        return estStatique;
    }
    public void     setEstStatique(boolean estStatique) {
        this.estStatique = estStatique;
    }
    public boolean  isEstFinale() {
        return estFinale;
    }
    public void     setEstFinale(boolean estFinale) {
        this.estFinale = estFinale;
    }
    public String   getValeurParDefault() { return valeurParDefault; }
    public void     setValeurParDefault(String valeurParDefault) {
        this.valeurParDefault = valeurParDefault;
    }
    public String   getType() { return type; }
    public void     setType(String type) { this.type = type; }
    public String[] getContrainte() { return contrainte; }
    public void     setContrainte(String[] contrainte) {
        this.contrainte = contrainte;
    }
    public String   getMultiplicite() {
        return multiplicite;
    }
    public void     setMultiplicite(String multiplicite) {
        this.multiplicite = multiplicite;
    }

    @Override
    public String toString()
    {
        String sRet="";
        sRet+=visibilite+" ";
        sRet+=nom + " " + multiplicite;

        if(valeurParDefault.length()>0)
            sRet+= " = " + valeurParDefault + " ";

        if(estFinale)sRet+="{gelée} ";
        if(contrainte!=null) for (String cont: contrainte) sRet+="{"+cont+"} ";
        sRet+= ": " + type;
        if(estStatique)
        {
            String underline="";
            for (int i = 0; i <sRet.length(); i++) underline+="¯";
            sRet+="\n"+underline;
        }
        sRet+="\n";
       return sRet;

    }
    /**
     * Permet de renvoyer au format texte l'attribut sans son type de retour
     *
     * @see ----- Utilise les attributs -----
     * @see Attribut#nom
     * @see Attribut#visibilite
     * @see Attribut#estStatique
     * @see Attribut#estFinale
     * @see Attribut#valeurParDefault
     * @see Attribut#contrainte
     * @see Attribut#multiplicite
     */
    public String toStringNoType()
    {
        String sRet="";
        sRet+=visibilite+" ";
        sRet+=nom + " " + multiplicite;

        if(valeurParDefault.length()>0)
            sRet+= " = " + valeurParDefault + " ";

        if(estFinale)sRet+="{gelée} ";
        if(contrainte!=null) for (String cont: contrainte) sRet+="{"+cont+"} ";

        return  sRet;
    }
}
