package java2uml;

import java2uml.IHM.GUI.IHMGUI;
import java2uml.IHM.CUI.IHMCUI;


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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Controleur
{
	private Object metier;
	private IHMCUI ihmCUI;
	private IHMGUI ihmGUI;
	
	private String cheminExec = getClass().getProtectionDomain().getCodeSource().getLocation().getFile().substring(1).replaceAll("/", "\\\\"); // le repertoire courant de l'Exec
	
	public Controleur()
	{ 
		metier = new Object();
		this.ihmCUI = new IHMCUI (this);
		
		if(this.ihmCUI.choixGraphique() == 'G')	{ this.ihmGUI = new IHMGUI(this); } 
		else                                    { this.ihmCUI.start(); }
	}
	
	public static void main(String[] args)
	{
		new Controleur();
	}
	
	public String[] getConfig()
	{
		//InputStream stream = Controleur.class.getClassLoader().getResourceAsStream("\\config"); Solution pour le -jar a developper

		File repertoire = new File(this.cheminExec + "config");
		
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
					Path file = Paths.get(this.cheminExec + "config\\" + liste[i]);
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
	
	public int getTailleMaxConfig()
	{
		int max = 0;
		File repertoire = new File(this.cheminExec + "config");
		String liste[] = repertoire.list();
		
		for(String s : liste)
		{
			if(max < s.length())
				max = s.length();
		}
		return max;
	}
	
	public String getContenuConfig(String nomFichier)
	{
		String diagramme = "\n\n\n\n\n";
		String ligne;
		try
		{
			FileInputStream fis = new FileInputStream(this.cheminExec + "config\\" + nomFichier);
		    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		    BufferedReader lecteur = new BufferedReader(isr);
			while ((ligne = lecteur.readLine()) != null)
			{
				diagramme += "\t" + ligne + "\n";
			}
			lecteur.close();
		}
		catch(Exception exc) { System.out.println("Erreur d'ouverture"); }
		return diagramme;
	}
	
	public void ouvrirEnEdit(String nomFichier)
	{
		File file = new File(this.cheminExec + "config\\" + nomFichier);
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
}
