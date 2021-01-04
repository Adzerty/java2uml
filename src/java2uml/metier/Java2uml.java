package java2uml.metier;

//lecture
import java.awt.Desktop;

//recuperer fichier dans un rep avec dates
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java2uml.Controleur;

public class Java2uml
{
	public String repConfig    		= "../config/";
	public String repJava      		= "../fichierJava/";
	public String repCompile   		= "../fichierCompile/";
	public String repDiagramme	 	= "../diagrammes/";
	public String repDiagrammeTxt 	= "../diagrammes/txt/";
	public String repDiagrammePdf 	= "../diagrammes/pdf/";
	
	private Diagramme diagTemp;
	private boolean[] options;
	
	public Java2uml()
	{
		//lecture du fichier java2uml.ini
		this.options = new boolean[4]; //{affichage,fichierTxt,fichierPdf,suppression};
		
		try
		{
			Scanner sc = new Scanner(new File("java2uml.ini"));

			String conf = sc.nextLine();
			while(!conf.contains("Afficher diagramme après création"))    					  	  conf = sc.nextLine(); options[0] =conf.contains("true");
			while(!conf.contains("Créer fichier diagramme au format txt"))  					  conf = sc.nextLine(); options[1] =conf.contains("true");
			while(!conf.contains("Créer fichier diagramme au format pdf")) 						  conf = sc.nextLine(); options[2] =conf.contains("true");
			while(!conf.contains("Supprimer les fichiers diagrammes associés au fichier config")) conf = sc.nextLine(); options[3] =conf.contains("true");
		}
		catch (Exception e){}
	}
	
	public String[] recupFichConfig()//renvoie sous forme de tableau de String l'ensemble des fichiers de config
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
	
	public String[] recupFichClasse()
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
	
	public int      recupTailleMaxFichier(String rep)
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
	
	public String   recupContenuConfig(String nomFichier)
    {
        String diagramme = "\n\n\n";
        ConfigReader temp = new ConfigReader(nomFichier);
		if(options[0]) diagramme+= temp.toString();
        CreateFile(temp,nomFichier.replace(".txt",""),options[1],options[2]);
        return diagramme;
    }
	
	public void    genNouvDiagramme(String[] tabNomFichier)
	{
		for(int f = 0; f < tabNomFichier.length; f++)
			tabNomFichier[f] = tabNomFichier[f].replace(".java","");
		this.diagTemp = new Diagramme(tabNomFichier);
	}
	
	public String   genNouvConfig(String nomFichier, String nomAuteur)
	{
		new ConfigGenerator(this.diagTemp, nomFichier, nomAuteur);
		return recupContenuConfig(nomFichier+".txt");
	}
	
	public void     modifierConfig(String nomFichier)
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
	
	public boolean[] supprimerConfig(String fichierSup)
	{
		boolean[] tabRetSup = new boolean[3];
		try
		{
			File tmpC = new File(repConfig + fichierSup); //fichier config à sup
			tabRetSup[0] = tmpC.delete();
			
			if(options[3])
			{
				File tmpT = new File(repDiagrammeTxt + fichierSup                        ); //fichier diagramme à sup en txt
				File tmpP = new File(repDiagrammePdf + fichierSup.replace(".txt", ".pdf")); //fichier diagramme à sup en pdf

				
				//if(options[1]/*txt*/ && options[2]/*pdf*/)
				//{
					tabRetSup[1] = tmpT.delete();
					tabRetSup[2] = tmpP.delete();
				//}
				//else if(options[1]) supression = tmpT.delete();
				//else supression = tmpP.delete();
			}
			else { tabRetSup[1] = true; tabRetSup[2] = true; }
		}
		catch(Exception e) { e.printStackTrace(); }
		
		return tabRetSup;
	}
	
	public void modifierFichierIni(boolean[] options)
	{
		this.options = options;
		String sRet = "";
	
		sRet += "                                                                               "     + "\n" +
				"      ,--.  ,---.,--.   ,--.,---.       ,---.     ,--. ,--.,--.   ,--.,--.     "     + "\n" +
				"      |  | /  O  \\\\  `.'  //  O  \\     '.-.  \\    |  | |  ||   `.'   ||  |     " + "\n" +
				" ,--. |  ||  .-.  |\\     /|  .-.  |     .-' .'    |  | |  ||  |'.'|  ||  |     "    + "\n" +
				" |  '-'  /|  | |  | \\   / |  | |  |    /   '-.    '  '-'  '|  |   |  ||  '--.  "    + "\n" +
				"  `-----' `--' `--'  `-'  `--' `--'    '-----'     `-----' `--'   `--'`-----'  "     + "\n" +
				"                                                                               "     + "\n" ;
		sRet+="------Auteur : InnovAction\n";
	
		sRet+="Afficher diagramme après création = "						   		+options[0]+"\n";
		sRet+="Créer fichier diagramme au format txt = "					   		+options[1]+"\n";
		sRet+="Créer fichier diagramme au format pdf = "					   		+options[2]+"\n";
		sRet+="Supprimer les fichiers diagrammes associés au fichier config = " 	+options[3]+"\n";
	
		PrintWriter writer;
		try
		{
			File f = new File("./java2uml.ini");
			writer = new PrintWriter(f, "UTF-8");
			writer.println(sRet);
			writer.close();
		}catch (Exception e) {e.printStackTrace();}

	}
	public boolean[] getOptions(){return this.options;}

	public void CreateFile(ConfigReader conf,String nomFichier,boolean txt,boolean pdf)
	{
		try {
			if (pdf){
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(new File("../diagrammes/pdf/", nomFichier + ".pdf")));

				document.open();
				String sRet = conf.toString().replaceAll("│", "|");
				sRet = sRet.replaceAll("─", "-");
				sRet = sRet.replaceAll("┌", "+");
				sRet = sRet.replaceAll("├", "+");
				sRet = sRet.replaceAll("└", "+");
				sRet = sRet.replaceAll("┤", "+");
				sRet = sRet.replaceAll("┐", "+");
				sRet = sRet.replaceAll("┘", "+");

				Font font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
				Paragraph p = new Paragraph(sRet, font);

				document.add(p);

				//close
				document.close();
			}
			if(txt) {
				PrintWriter writer;
				File f = new File("../diagrammes/txt", nomFichier + ".txt");
				writer = new PrintWriter(f, "UTF-8");
				writer.println(this.toString());
				writer.close();
			}
		} catch (Exception e) {e.printStackTrace();}


	}

}


