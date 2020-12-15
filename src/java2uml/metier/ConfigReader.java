package java2uml.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigReader
{
    private ArrayList<Entite> ensEntite;

    public ConfigReader(String fichier)
    {
        String nomEntite;

        ensEntite = new ArrayList<>();
        try
        {
            Scanner sc = new Scanner(new File(fichier));
            while(sc.hasNext())
            {
                String temp = sc.next();                 //ligne commentée
                while(!temp.contains("Classe")|| (temp.charAt(0)=='/' && temp.charAt(1)=='/'))
                    temp = sc.next();
               // nomEntite= temp

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
