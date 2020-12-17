package java2uml;

import java2uml.IHM.GUI.IHMGUI;
import java2uml.IHM.CUI.IHMCUI;
import java2uml.metier.ConfigGenerator;
import java2uml.metier.ConfigReader;
import java2uml.metier.Diagramme;


//recuperer fichier dans un rep avec dates
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
//lecture
import java.awt.Desktop;

//lecture d'un fichier
import java.io.File;
import java.io.IOException;

public class Controleur
{
	private IHMCUI    ihmCUI;
	private Diagramme diagTemp;

	public Controleur()
	{
		//compilation();
		this.ihmCUI = new IHMCUI (this);
		
		if(this.ihmCUI.choixGraphique() == 'G')	{ new IHMGUI(this); } 
		else                                    { this.ihmCUI.start(); }
	}
	
	public static void main(String[] args)
	{
		new Controleur();
	}
	
	public String[] getConfig()
	{

		File repertoire = new File("./config");
		
		String liste[] = repertoire.list();
		
		String tabF [] = new String[liste.length];
		
		String dateC = "????-??-??T??:??:??";
		String dateM = "????-??-??T??:??:??";

		if (liste.length != 0)//si il existe des anciennes config
		{
			for (int i = 0; i < liste.length; i++)
			{
				tabF[i] = liste[i];
				try
				{
					Path file = Paths.get("./config/" + liste[i]);
					BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
					dateC = attr.creationTime().toString();
					dateM = attr.lastModifiedTime().toString();
				}
				catch (IOException e) { e.printStackTrace(); System.out.println("Erreur lors de la recuperation des dates du fichier");}
				
				tabF[i] += "|" + dateC.substring(0,10) + " " + dateC.toString().substring(11,16) + "|" + dateM.substring(0,10) + " " + dateM.toString().substring(11,16) ;
			}
			return tabF;
		}
		else
		{
			//Aucune sauvegarde de .config
			return null;
		}
	}
	
	public String[] getClasse()
	{

		File repertoire = new File("./fichierJava");

		String liste[] = repertoire.list();

		String tabF [] = new String[liste.length];

		String dateC = "????-??-??T??:??:??";
		String dateM = "????-??-??T??:??:??";

		if (liste.length != 0)//si il existe des classe
		{
			for (int i = 0; i < liste.length; i++)
			{
				tabF[i] = liste[i];
				try
				{
					Path file = Paths.get("./fichierJava/" + liste[i]);
					BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
					dateC = attr.creationTime().toString();
					dateM = attr.lastModifiedTime().toString();
				}
				catch (IOException e) { e.printStackTrace(); System.out.println("Erreur lors de la recuperation des dates du fichier");}

				tabF[i] += "|" + dateC.substring(0,10) + " " + dateC.toString().substring(11,16) + "|" + dateM.substring(0,10) + " " + dateM.toString().substring(11,16) ;
			}
			return tabF;
		}
		else
		{
			//Aucune sauvegarde de classe
			return null;
		}
	}
	public int      getTailleMaxFichier(String rep)
	{
		int max = 0;
		File repertoire = new File(rep);
		String liste[] = repertoire.list();
		
		for(String s : liste)
		{
			if(max < s.length())
				max = s.length();
		}
		return max;
	}
	
	public String   getContenuConfig(String nomFichier)
    {
        String diagramme = "\n\n\n\n\n";
        ConfigReader temp = new ConfigReader(nomFichier);
        diagramme+= temp.toString();
        temp.CreateFile(nomFichier);
        return diagramme;
    }

	public void     CreateNewDiagramme(String[] tabNomFichier)
	{
		for(int f = 0; f < tabNomFichier.length; f++)
		{
			tabNomFichier[f] = tabNomFichier[f].replace(".java","");
		}
		this.diagTemp = new Diagramme(tabNomFichier);
	}
	
	public String   CreateConfigFile(String nomFichier, String nomAuteur)
	{
		new ConfigGenerator(this.diagTemp, nomFichier, nomAuteur);
		return getContenuConfig(nomFichier+".txt");
	}
	
	public void     ouvrirEnEdit(String nomFichier)
	{
		File file = new File("./config/" + nomFichier);
        if (!file.exists() && file.length() < 0)
        {
            System.out.println("Le fichier n'existe pas!");
            return;
        }
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            desktop.edit(file);
        } catch (IOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
		
	public static void compilation()
	{
        String repDest = "./fichierCompile/";
        File rep = new File("./fichierJava/");
		String listeJava[] = rep.list();

		String commande = "javac -d "+ repDest + " ./fichierJava/*.java";
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(commande);
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
    }
}
