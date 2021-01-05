package java2uml.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigReader
{
    private ArrayList<Entite> ensEntite;
    private int compteurLigne=0;
    private String repConfig="../config/";
    public String globalContrainte="";

    public ConfigReader(String fichier)
{
    	Association.compteur = 1;
        ensEntite = new ArrayList<>();
        try
        {
            Scanner sc = new Scanner(new File(repConfig+fichier));
            while(sc.hasNextLine()) {
                String nomEntite = "";
                ArrayList<Attribut> ensAttribut = new ArrayList<>();
                ArrayList<Methode> ensMethode = new ArrayList<>();
                ArrayList<Association> ensAssociation = new ArrayList<>();
                String typeEntite = "";
                boolean entiteEstAbstraite;
                boolean entiteEstFinale;

                //nom Entite
                String temp = sc.nextLine();
                compteurLigne ++;
                while (sc.hasNextLine() && !temp.contains("Entité :"))
                {
                    temp = sc.nextLine();
                    compteurLigne ++;
                }
                if (!temp.contains("//")) {
                    if (!sc.hasNextLine()) break;

                    for (int i = temp.indexOf(':') + 1; i < temp.length(); i++)
                        typeEntite += temp.charAt(i);

                    temp = sc.nextLine();
                    compteurLigne ++;

                    int cpt = 0;
                    while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                        nomEntite += temp.charAt(cpt);
                        cpt++;
                    }
                    while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                        typeEntite += temp.charAt(cpt);
                        cpt++;
                    }

                    temp=temp.substring(cpt,temp.length());
                    entiteEstFinale = temp.contains("final");
                    entiteEstAbstraite = temp.contains("abstract");

                    String [] entiteContraintes= null;
                    if(temp.contains("{") && temp.contains("}"))
                    {
                        String contrainte = temp.substring(temp.indexOf("{")+1,temp.indexOf("}"));
                        entiteContraintes = contrainte.split(",");
                    }

                    //ATTRIBUTS
                    while (sc.hasNextLine() && !temp.contains("Attribut(s) :"))
                    {
                        compteurLigne ++;
                        temp = sc.nextLine();
                    }

                    if (!temp.contains("//"))
                    {
                        temp = sc.nextLine();
                        compteurLigne ++;

                        while (sc.hasNextLine() && temp.length() > 1) {
                            if (temp.charAt(0) != '/' && temp.charAt(1) != '/') {
                                String nom = "";
                                String multiplicite="";
                                char visibilite = temp.charAt(0);

                                String valeurParDefault = "";
                                String type = "";

                                cpt = 2;
                                while (temp.charAt(cpt) != ' ') {
                                    type += temp.charAt(cpt);
                                    cpt++;
                                }
                                cpt++;
                                while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                                    nom += temp.charAt(cpt);
                                    cpt++;
                                }
                                if(temp.contains("[")&& temp.contains("["))
                                {
                                    cpt++;
                                    while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                                        multiplicite += temp.charAt(cpt);
                                        cpt++;
                                    }
                                }
                                temp=temp.substring(cpt,temp.length());

                                boolean estStatique = temp.contains("static");
                                boolean estFinale = temp.contains("final");

                                String [] tabContraintes= null;
                                if(temp.contains("{") && temp.contains("}"))
                                {
                                    String contrainte = temp.substring(temp.indexOf("{")+1,temp.indexOf("}"));
                                    tabContraintes = contrainte.split(",");
                                }

                                if (temp.contains("default ="))
                                    for (int i = temp.indexOf("default =") + 9; i < temp.length(); i++)
                                        valeurParDefault += temp.charAt(i);

                                ensAttribut.add(new Attribut(nom, visibilite, estStatique, estFinale, valeurParDefault, type,tabContraintes,multiplicite));
                            }
                            temp = sc.nextLine();
                            compteurLigne ++;
                        }
                    }
                    //METHODE
                    while (sc.hasNextLine() && !temp.contains("Méthode(s) :"))
                    {
                        compteurLigne ++;
                        temp = sc.nextLine();
                    }

                    if (!temp.contains("//")) {
                        temp = sc.nextLine();
                        compteurLigne ++;

                        while (sc.hasNextLine() && temp.length() > 1) {
                            if (temp.charAt(0) != '/' && temp.charAt(1) != '/') {
                                String nom = "";
                                char visibilite = temp.charAt(0);
                                String typeDeRetour = "";

                                ArrayList<Parametre> ensParametre = new ArrayList<>();


                                cpt = 2;
                                while (temp.charAt(cpt) != ' ') {
                                    typeDeRetour += temp.charAt(cpt);
                                    cpt++;
                                }
                                cpt++;
                                while (cpt < temp.length() && temp.charAt(cpt) != '(') {
                                    nom += temp.charAt(cpt);
                                    cpt++;
                                }
                                //ensemblme de parametre
                                String parametres = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
                                if (parametres.length() > 1) {
                                    String[] tabParam;
                                    if (parametres.contains(";")) {
                                        tabParam = parametres.split("; ");
                                    } else {
                                        tabParam = new String[1];
                                        tabParam[0] = parametres;
                                    }

                                    for (String str : tabParam)
                                    {
                                        String nomVar = "";
                                        String typeParam = "";
                                        cpt = 0;

                                        while (str.charAt(cpt) != ' ') {
                                            typeParam += str.charAt(cpt);
                                            cpt++;
                                        }
                                        cpt++;
                                        while (cpt < str.length()) {
                                            nomVar += str.charAt(cpt);
                                            cpt++;
                                        }
                                        Parametre param = new Parametre(nomVar, typeParam);
                                        ensParametre.add(param);
                                    }
                                }
                                temp=temp.substring(temp.indexOf(")"),temp.length());
                                boolean estStatique = temp.contains("static");
                                boolean estFinale = temp.contains("final");
                                boolean estAbstraite = temp.contains("abstract");
                                Methode met = new Methode(nom, visibilite, typeDeRetour, ensParametre, estStatique, estFinale, estAbstraite);
                                ensMethode.add(met);
                            }
                            temp = sc.nextLine();
                            compteurLigne ++;

                        }
                    }

                    //ASSOCIATIONS
                    while (sc.hasNextLine() && !temp.contains("Association(s) :"))
                    {
                        temp = sc.nextLine();
                        compteurLigne ++;
                    }

                    if(!temp.contains("//")) {

                        temp = sc.nextLine();
                        compteurLigne++;
                        while (sc.hasNextLine() && temp.length() > 1) {
                            String classeGauche = "";
                            String classeDroite = "";
                            String multipliciteGauche = "";
                            String multipliciteDroite = "";
                            String typeFleche = "";

                            cpt = 0;
                            if (temp.charAt(0) != '/' && temp.charAt(1) != '/')
                            {

                                while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                                    classeGauche += temp.charAt(cpt);
                                    cpt++;
                                }
                                cpt++;
                                if (temp.charAt(cpt) == '[') {
                                    while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                                        multipliciteGauche += temp.charAt(cpt);
                                        cpt++;
                                    }
                                    cpt++;
                                }
                                while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                                    typeFleche += temp.charAt(cpt);
                                    cpt++;
                                }
                                cpt++;
                                if (temp.charAt(cpt) == '[') {
                                    while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                                        multipliciteDroite += temp.charAt(cpt);
                                        cpt++;
                                    }
                                    cpt++;
                                }
                                while (cpt < temp.length() && temp.charAt(cpt) != ' ') {
                                    classeDroite += temp.charAt(cpt);
                                    cpt++;
                                }

                                Association a = new Association(classeGauche, classeDroite, multipliciteGauche,
                                        multipliciteDroite, typeFleche);
                                ensAssociation.add(a);
                            }
                            temp = sc.nextLine();
                            compteurLigne++;
                        }
                        while (sc.hasNextLine() && !temp.contains("Contrainte(s)"))
                        {
                            temp = sc.nextLine();
                            compteurLigne++;
                        }
                        if(!temp.contains("//")) {
                            while (sc.hasNextLine() && temp.length() > 1)
                            {
                                temp = sc.nextLine();
                                compteurLigne++;
                                if(!temp.contains("//")&& temp.length()>1) globalContrainte += "\t\t"+temp + '\n';
                            }
                        }
                    }
                    Entite e = new Entite(ensMethode, ensAttribut, nomEntite, typeEntite,
                            entiteEstAbstraite, entiteEstFinale, ensAssociation,entiteContraintes);
                    ensEntite.add(e);
                    }
            }
            sc.close();
        }
        catch(Exception e)
        {
            System.err.println("erreur fichier de config ligne :" + compteurLigne);
        }
    }
    public ArrayList<Entite> getEnsEntite() {
        return ensEntite;
    }

    public String toString() {
        String sRet="";
        for (Entite ent : ensEntite)
            sRet+=ent.toString();

        for (Entite ent : ensEntite)
        {
            ArrayList<Association> temp = ent.getEnsAssociations();
            for (Association a : temp) sRet += a.toString()+"\n";
        }
        sRet+=globalContrainte;
        return sRet;
    }

}
