package java2uml;

//package
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
	public String repConfig    = "../config/";
	public String repJava      = "../fichierJava/";
	public String repCompile   = "../fichierCompile/";
	public String repDiagramme = "../diagrammes/";

	public Controleur()
	{
		//Création des dossiers
		File file = new File(repConfig); 	if (!file.exists()) file.mkdir();
		file = new File(repJava); 			if (!file.exists()) file.mkdir();
		file = new File(repCompile); 		if (!file.exists()) file.mkdir();
		file = new File(repDiagramme); 		if (!file.exists()) file.mkdir();

		//compilation();
		this.ihmCUI = new IHMCUI (this);
		
		if(this.ihmCUI.choixGraphique() == 'G')	{ new IHMGUI(this); } 
		else                                    { this.ihmCUI.start(); }


	}
	
	public static void main(String[] args)
	{
		new Controleur();
	}
	
	public String[] getConfig()//renvoie sous forme de tableau de String l'ensemble des fichiers de config
	{

		File repertoire = new File(repConfig);
		
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
					Path file = Paths.get(repConfig + liste[i]);
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

		File repertoire = new File(repCompile);

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
					Path file = Paths.get(repCompile + liste[i]);
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
		int tailleMax = 80;//pour eviter de dépasser et de casser l'affichage en cas de nom de classe improbable
		
		int max = 0;
		File repertoire = new File(rep);
		String liste[] = repertoire.list();
		
		for(String s : liste)
		{
			if(max < s.length())
				max = s.length();
		}
		if(max > tailleMax) return tailleMax;
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

	public void     createNewDiagramme(String[] tabNomFichier)
	{
		for(int f = 0; f < tabNomFichier.length; f++)
			tabNomFichier[f] = tabNomFichier[f].replace(".java","");
		this.diagTemp = new Diagramme(tabNomFichier);
	}
	
	public String   createConfigFile(String nomFichier, String nomAuteur)
	{
		new ConfigGenerator(this.diagTemp, nomFichier, nomAuteur);
		return getContenuConfig(nomFichier+".txt");
	}
	
	public void     ouvrirEnEdit(String nomFichier)
	{
		File file = new File(repConfig + nomFichier);
        if (!file.exists() && file.length() < 0)
        {
            System.out.println("Le fichier n'existe pas!");
            return;
        }
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try
        {
            desktop.edit(file);
        }
        catch (IOException ex) { Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex); }
	}
	
	public void supprimerConfig(String[] tabFichierSup)
	{
		try
		{
			for(String s : tabFichierSup)
			{
				File tmpC = new File(repConfig     + s); //fichier config    a sup
				File tmpD = new File(repDiagramme  + s); //fichier diagramme a sup

				this.ihmCUI.confirmSup(tmpC.getName(), tmpC.delete(), tmpD.delete()); //suppression des 2 fichiers
			}
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	public void compilation()
	{
		String commande = "javac -parameters -d "+ repCompile +' ' +repJava+"*.java";
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(commande);
			int exitVal = proc.waitFor();
			if(exitVal!=0) System.out.println("erreur compilation sur un fichier");
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
    }
	
}
