package java2uml.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigReader
{
    private ArrayList<Entite> ensEntite;

    public ConfigReader(String fichier)
    {
        ensEntite = new ArrayList<>();
        try
        {
            Scanner sc = new Scanner(new File("./config/"+fichier));
            while(sc.hasNext())
            {
                String nomEntite="";
                ArrayList<Attribut> ensAttribut = new ArrayList<>();
                ArrayList<Methode> ensMethode = new ArrayList<>();
                ArrayList<Association> ensAssociation = new ArrayList<>();
                String typeEntite="";
                boolean entiteEstAbstraite;
                boolean entiteEstFinale;
                String mere="";

                //nom classe
                String temp = sc.nextLine();
                while(sc.hasNext() && !temp.contains("Classe :"))
                    temp = sc.nextLine();

                temp = sc.nextLine();

                int cpt = 0;
                while (cpt<temp.length() && temp.charAt(cpt)!=' ' )
                {
                    nomEntite+= temp.charAt(cpt);
                    cpt++;
                }
                while (cpt<temp.length() && temp.charAt(cpt)!=' ')
                {
                    typeEntite+= temp.charAt(cpt);
                    cpt++;
                }
                entiteEstFinale = temp.contains("final");
                entiteEstAbstraite = temp.contains("abstract");

                if(temp.contains("Classe mere :"))
                    for(int i = temp.indexOf("Classe mere :")+13;i<temp.length();i++)
                        mere+=temp.charAt(i);

                //ATTRIBUTS
                while(sc.hasNext() && !temp.contains("Attributs :"))
                    temp = sc.nextLine();
                temp = sc.nextLine();

                while(sc.hasNext() && temp.length()>1)
                {
                    if(temp.charAt(0)!='/' && temp.charAt(1)!='/')
                    {
                        String  nom="";
                        char    visibilite = temp.charAt(0);
                        boolean estStatique=temp.contains("_");
                        boolean estFinale=temp.contains("final");;
                        String  valeurParDefault="";
                        String  type="";

                        cpt = 3 ;
                        while(temp.charAt(cpt)!=' ')
                        {
                            type+=temp.charAt(cpt);
                            cpt++;
                        }
                        while(temp.charAt(cpt)!=' ')
                        {
                            nom+=temp.charAt(cpt);
                            cpt++;
                        }
                        if(temp.contains("default ="))
                            for(int i=temp.indexOf("default =")+9;i<temp.length();i++)
                                valeurParDefault+=temp.charAt(i);

                        ensAttribut.add(new Attribut(nom,visibilite,estStatique,estFinale,valeurParDefault,type));
                    }
                    temp = sc.nextLine();
                }
                //METHODE
                while(sc.hasNext() && !temp.contains("Méthodes :"))
                    temp = sc.nextLine();
                temp = sc.nextLine();

                while(sc.hasNext() && temp.length()>1)
                {
                    if(temp.charAt(0)!='/' && temp.charAt(1)!='/')
                    {
                        String nom="";
                        char visibilite= temp.charAt(0);
                        String typeDeRetour="";

                        ArrayList<Parametre> ensParametre = new ArrayList<>();

                        boolean estStatique = temp.contains("static");
                        boolean estFinale= temp.contains("final");
                        boolean estAbstraite= temp.contains("abstract");

                        cpt = 3 ;
                        while(temp.charAt(cpt)!=' ')
                        {
                            typeDeRetour+=temp.charAt(cpt);
                            cpt++;
                        }
                        while(temp.charAt(cpt)!=' ')
                        {
                            nom+=temp.charAt(cpt);
                            cpt++;
                        }

                        //ensemblme de parametre
                        String parametres = temp.substring(temp.indexOf('('),temp.indexOf(')'));
                        if(parametres.length()>1)
                        {
                            String[] tabParam = parametres.split(",");
                            String nomVar="";
                            String typeParam="";

                            cpt = 0 ;
                            for (String str: tabParam )
                            {
                                while(str.charAt(cpt)!=' ')
                                {
                                    typeParam+=str.charAt(cpt);
                                    cpt++;
                                }
                                while(str.charAt(cpt)!=' ')
                                {
                                    nomVar+=str.charAt(cpt);
                                    cpt++;
                                }
                                Parametre param = new Parametre(nomVar,typeParam);
                                ensParametre.add(param);
                            }
                        }
                        Methode met = new Methode(nom,visibilite,typeDeRetour,ensParametre,estStatique,estFinale,estAbstraite);
                        ensMethode.add(met);
                    }
                    temp = sc.nextLine();

                }

                //ASSOCIATIONS
                while(sc.hasNext() && !temp.contains("Associations :"))
                    temp = sc.nextLine();
                temp = sc.nextLine();

                while(sc.hasNext() && temp.length()>1)
                {
                    String classeGauche="";
                    String classeDroite="";
                    String multipliciteGauche="";
                    String multipliciteDroite="";
                    String contrainte="";
                    String typeFleche="";
                    String typeRelation="";

                    cpt = 0;

                    while(temp.charAt(cpt)!=' ')
                    {
                        typeRelation+=temp.charAt(cpt);
                        cpt++;
                    }
                    while(temp.charAt(cpt)!=' ')
                    {
                        classeGauche+=temp.charAt(cpt);
                        cpt++;
                    }
                    while(temp.charAt(cpt)!=' ')
                    {
                        multipliciteGauche+=temp.charAt(cpt);
                        cpt++;
                    }
                    while(temp.charAt(cpt)!=' ')
                    {
                        typeFleche+=temp.charAt(cpt);
                        cpt++;
                    }
                    while(temp.charAt(cpt)!=' ')
                    {
                        multipliciteDroite+=temp.charAt(cpt);
                        cpt++;
                    }
                    while(temp.charAt(cpt)!=' ')
                    {
                        classeDroite+=temp.charAt(cpt);
                        cpt++;
                    }
                    if(temp.contains("{"))
                        while(temp.charAt(cpt)!=' ')
                        {
                            contrainte+=temp.charAt(cpt);
                            cpt++;
                        }
                    Association a = new Association(classeGauche,classeDroite,multipliciteGauche,
                            multipliciteDroite,contrainte,typeFleche,typeRelation);

                    ensAssociation.add(a);
                }
                Entite e = new Entite(ensMethode,ensAttribut,nomEntite,typeEntite,
                        entiteEstAbstraite,entiteEstFinale,mere,ensAssociation);
                ensEntite.add(e);
                System.out.println(e);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Entite> getEnsEntite() {
        return ensEntite;
    }

    public static void main(String[] args) {
       ConfigReader conf = new ConfigReader("test.config");

    }
}
