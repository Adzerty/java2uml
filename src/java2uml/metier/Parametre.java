package java2uml.metier;
public class Parametre
{
    private String nomVar;
    private String type;

    public Parametre(String nomVar, String type) {
        this.nomVar = nomVar;
        this.type = type;
    }

    public String getNomVar() {
        return nomVar;
    }

    public void setNomVar(String nomVar) {
        this.nomVar = nomVar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

