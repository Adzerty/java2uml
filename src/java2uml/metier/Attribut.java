package java2uml.metier;

public class Attribut
{
    private String nom;
    private char visibilite;
    private boolean estStatique;
    private boolean estFinale;
    private String valeurParDefault;
    private String type;

    public Attribut(String nom, char visibilite, boolean estStatique, boolean estFinale, String valeurParDefault,String type) {
        this.nom = nom;
        this.visibilite = visibilite;
        this.estStatique = estStatique;
        this.estFinale = estFinale;
        this.valeurParDefault = valeurParDefault;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getValeurParDefault() {
        return valeurParDefault;
    }

    public void setValeurParDefault(String valeurParDefault) {
        this.valeurParDefault = valeurParDefault;
    }
    
    public String toString() {
    	return this.visibilite + " " + this.type + " " + this.nom;
    }
}
