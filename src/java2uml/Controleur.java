package java2uml;

import iut.algo.Console;
import java2uml.IHM.GUI.IHMGUI;
import java2uml.IHM.CUI.IHMCUI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;


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
		
		if(this.ihmCUI.choixGraphique() == 'G')	{ this.ihmGUI = new IHMGUI(/*this*/); } 
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
}
