package java2uml.metier;

public class Association
{
    private Entite classeGauche;
    private Entite classeDroite;
    private String multipliciteGauche;
    private String multipliciteDroite;
    private String contrainte;

    public Association(Entite classeGauche, Entite classeDroite, String multipliciteGauche,
                       String multipliciteDroite, String contrainte)
    {
        this.classeGauche = classeGauche;
        this.classeDroite = classeDroite;
        this.multipliciteGauche = multipliciteGauche;
        this.multipliciteDroite = multipliciteDroite;
        this.contrainte = contrainte;
    }

    public Entite getClasseGauche() {
        return classeGauche;
    }

    public void setClasseGauche(Entite classeGauche) {
        this.classeGauche = classeGauche;
    }

    public Entite getClasseDroite() {
        return classeDroite;
    }

    public void setClasseDroite(Entite classeDroite) {
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
}
