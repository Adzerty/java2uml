package java2uml.metier;

public class Attribut
{
    private String nom;
    private char visibilite;
    private boolean estStatique;
    private boolean estFinale;
    private String valeurParDefault;
    private String type;
    private String [] contrainte;
    private String multiplicite;
    public Attribut(String nom, char visibilite, boolean estStatique, boolean estFinale, String valeurParDefault,String type,String[] contrainte,String multiplicite) {
        this.nom = nom;
        this.visibilite = visibilite;
        this.estStatique = estStatique;
        this.estFinale = estFinale;
        this.valeurParDefault = valeurParDefault;
        this.type=type;
        this.contrainte = contrainte;
        this.multiplicite = multiplicite;
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
