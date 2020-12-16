package java2uml.metier;

public class Association
{
    private String classeGauche;
    private String classeDroite;
    private String multipliciteGauche;
    private String multipliciteDroite;
    private String contrainte;
    private String typeFleche;
    private String typeRelation;

    public Association(String classeGauche, String classeDroite, String multipliciteGauche, String multipliciteDroite,
                       String contrainte, String typeFleche, String typeRelation)
    {
        this.classeGauche = classeGauche;
        this.classeDroite = classeDroite;
        this.multipliciteGauche = multipliciteGauche;
        this.multipliciteDroite = multipliciteDroite;
        this.contrainte = contrainte;
        this.typeFleche = typeFleche;
        this.typeRelation = typeRelation;
    }

    public String getClasseGauche() {
        return classeGauche;
    }

    public void setClasseGauche(String classeGauche) {
        this.classeGauche = classeGauche;
    }

    public String getClasseDroite() {
        return classeDroite;
    }

    public void setClasseDroite(String classeDroite) {
        this.classeDroite = classeDroite;
    }

    public String getMultipliciteGauche() {
        return multipliciteGauche;
    }

    public void setMultipliciteGauche(String multipliciteGauche) {
        this.multipliciteGauche = multipliciteGauche;
    }

    public String getMultipliciteDroite() {
        return multipliciteDroite;
    }

    public void setMultipliciteDroite(String multipliciteDroite) {
        this.multipliciteDroite = multipliciteDroite;
    }

    public String getContrainte() {
        return contrainte;
    }

    public void setContrainte(String contrainte) {
        this.contrainte = contrainte;
    }

    public String getTypeFleche() {
        return typeFleche;
    }

    public void setTypeFleche(String typeFleche) {
        this.typeFleche = typeFleche;
    }

    public String getTypeRelation() {
        return typeRelation;
    }

    public void setTypeRelation(String typeRelation) {
        this.typeRelation = typeRelation;
    }

    public String toString(int compteur) {
    	String sRet="";
        sRet+="Association " + compteur + " :";

        if (typeFleche.contains("<") || typeFleche.contains(">")) sRet+= "unidirectionnelle";
        else if(typeFleche.contains("⬦")) sRet += "agrégation";
        else if(typeFleche.contains("◆")) sRet += "composition";
        else if (typeFleche.contains("▷")) sRet += "généralisation/spécialisation";

        sRet += "\n"+classeGauche +' ' +multipliciteGauche + ' ' + typeFleche + ' ' + multipliciteDroite + ' '+ classeDroite + ' ' + contrainte ;
        return  sRet;
    }
}
