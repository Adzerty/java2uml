import java.lang.reflect.*;

public class Classes
{
    private String nomClasse;

    private Field[] tabAttribut;

    private Constructor[] tabConstruct;
    private Parameter[][] tabParamConstruct;
    private Method[] tabMeth;
    private Parameter[][] tabParamMethod;


    public Classes(String nomClasse, Field[] tabAttribut, Constructor[] tabConstruct,
                   Parameter[][] tabParamConstruct, Method[] tabMeth, Parameter[][] tabParamMethod) {
        this.nomClasse = nomClasse;
        this.tabAttribut = tabAttribut;
        this.tabConstruct = tabConstruct;
        this.tabParamConstruct = tabParamConstruct;
        this.tabMeth = tabMeth;
        this.tabParamMethod = tabParamMethod;
    }

    public String toString()
    {
        int taille = size();

        String separation ="";
        String sRet="";
        String nomClasseFormat;

        //CREATION SEPARATION TYPE : "---------------------"
        for (int i = 0; i < taille; i++) separation+="-";
        separation += "\n";

        //SEPARATION TYPE : "---------------------"
        sRet+=separation;

        //NOM CLASSES
        for (int i = 0; i < (int)(taille-nomClasse/2) ; i++) sRet+="";
        sRet+=nomClasse+"\n";

        //SEPARATION TYPE : "---------------------"
        sRet+=separation;

        //AJOUT DES ATTRIBUTS TYPE : -int x
        sRet+=attribut_toString();

        //SEPARATION TYPE : "---------------------"
        sRet+=separation;

        //AJOUT DES METHODE TYPE : + getX () : int
        sRet+=attribut_toString();

        return  sRet;

    }

    private String methode_toString(int taille)
    {
        String sRet="";

        for(int i = 0; i<tabMet.length; i++)
        {

            sRet += String.format("%-"+String.valueOf(taille), tabMeth[i].toString()) ;
            sRet+="\n";
        }
        return sRet;
    }

    private String attribut_toString()
    {
        String sRet"";
        for(int i = 0; i<tabAttribut.length; i++)
        {
            String att = "";
            boolean bStatic;

            if(Modifier.isPrivate(tabAttribut[i].getModifiers())) att+='-';
            if(Modifier.isPublic(tabAttribut[i].getModifiers())) att+='+';
            if(Modifier.isProtected(tabAttribut[i].getModifiers())) att+='#';
            bStatic = Modifier.isStatic(tabAttribut[i].getModifiers());

            String typeAtt = tabAttribut[i].getType().toString();
            //Si l'attribut contient '.' (ex : java.lang.String;) on récupère juste String
            if(typeAtt.contains("."))
            {
                String type[] = typeAtt.split("\\.");
                typeAtt = type[type.length-1].substring(0,type[type.length-1].length()-1);
            }

            //Si l'attribut contient ' ' (ex : Class ClasseA) on récupère juste ClasseA
            if(typeAtt.contains(" "))
            {
                String type[] = typeAtt.split(" ");
                typeAtt = type[type.length-1];
            }

            att+=tabAttribut[i].getName();
            att+=" : " + typeAtt;
            if(Modifier.isFinal(tabAtt[i].getModifiers())) att+=" {gelé}";

            if(bStatic)
            {
                att+="\n";
                for(int i = 0 ; i< sRet.length()-1;i++)
                    att+="¯";
            }
            sRet += String.format("%-"+String.valueOf(taille),att);
            sRet+="\n";
        }
        return sRet;
    }



    public int size()
    {
        int taille = 0 ;

        //ATTRIBUTS
        for(int i = 0; i<tabAttribut.length; i++)
        {
            String att = "";
            boolean bStatic;

            if (Modifier.isPrivate(tabAttribut[i].getModifiers())) att += '-';
            if (Modifier.isPublic(tabAttribut[i].getModifiers())) att += '+';
            if (Modifier.isProtected(tabAttribut[i].getModifiers())) att += '#';
            bStatic = Modifier.isStatic(tabAttribut[i].getModifiers());

            String typeAtt = tabAttribut[i].getType().toString();
            //Si l'attribut contient '.' (ex : java.lang.String;) on récupère juste String
            if (typeAtt.contains(".")) {
                String type[] = typeAtt.split("\\.");
                typeAtt = type[type.length - 1].substring(0, type[type.length - 1].length() - 1);
            }

            //Si l'attribut contient ' ' (ex : Class ClasseA) on récupère juste ClasseA
            if (typeAtt.contains(" ")) {
                String type[] = typeAtt.split(" ");
                typeAtt = type[type.length - 1];
            }

            att += tabAttribut[i].getName();
            att += " : " + typeAtt;
            if (Modifier.isFinal(tabAtt[i].getModifiers())) att += " {gelé}";

            if(att.length()>taille)taille>att.length();
        }

        //METHODES

        for(int i = 0; i<tabMet.length; i++)
             if(tabMeth[i].toString().length()>size)size=tabMeth[i].toString().length();

        return  taille;
    }

    public static void main(String[] args) {

    }
}