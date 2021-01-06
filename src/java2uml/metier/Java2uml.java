package java2uml.metier;

//lecture
import java.awt.Desktop;

//recuperer fichier dans un rep avec dates
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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

/**
 * <b>Java2uml est la classe qui permet de faire tout le traitement algorithmique de l'applications (Metier).</b>
 * <p>
 * Java2uml possède les attributs suivant :
 * <ul>
 * <li>Un Diagramme  ??? .</li>
 * <li>Un tableau de booléens ??? .</li>
 * <li>Un constante booléenne ??? .</li>
 * </ul>
 * De plus, Le métier permet l'acces à l'ensemble des classes Metier en une seule.
 * </p>
 * 
 * @see Controleur
 * @see ...
 * 
 * @author InnovAction
 * @version 1.0
 */
public class Java2uml
{
	public String repConfig    		= "../config/";
	public String repJava      		= "../fichierJava/";
	public String repCompile   		= "../fichierCompile/";
	public String repDiagramme	 	= "../diagrammes/";
	public String repDiagrammeTxt 	= "../diagrammes/txt/";
	public String repDiagrammePdf 	= "../diagrammes/pdf/";
	
	private Diagramme diagTemp;
	
	/**
	 * Un tableau de booléens regroupant les différentes options du programmes et modiable dans l'application.
	 * 
	 * @see ----- Utilisé dans les méthodes -----
	 * @see Java2uml#Java2uml()
	 * @see ...
	 */
	private boolean[] options;
	
	private final boolean H_HIVER = true;
	
	public Java2uml()
	{
		//lecture du fichier java2uml.ini
		this.options = new boolean[4]; //{affichage,fichierTxt,fichierPdf,suppression};
		
		try
		{
			Scanner sc = new Scanner(new File("java2uml.ini"));

			String conf = sc.nextLine();

			while(!conf.contains("Afficher diagramme après création"))    					  	         conf = sc.nextLine(); this.options[0] = conf.contains("true");
			while(!conf.contains("Créer fichier diagramme au format txt"))  					         conf = sc.nextLine(); this.options[1] = conf.contains("true");
			while(!conf.contains("Créer fichier diagramme au format pdf")) 						         conf = sc.nextLine(); this.options[2] = conf.contains("true");
			while(!conf.contains("Supprimer les fichiers diagrammes associés au fichier configuration")) conf = sc.nextLine(); this.options[3] = conf.contains("true");
		}
		catch (Exception ignored){}
	}
	public String[] recupFich(String rep)//renvoie sous forme de tableau de String l'ensemble des fichiers compilés
	{

		File repertoire = new File(rep);

		String[] liste = repertoire.list();

		assert liste != null;
		String[] tabF  = new String[liste.length];

		String dateC = "????-??-??T??:??:??";
		String dateM = "????-??-??T??:??:??";

		if (liste.length != 0)//si il existe des classe
		{
			for (int i = 0; i < liste.length; i++)
			{
				tabF[i] = liste[i];
				try
				{
					Path file = Paths.get(rep + liste[i]);
					BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
					dateC = attr.creationTime().toString();
					dateM = attr.lastModifiedTime().toString();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					System.out.println("Erreur lors de la recuperation des dates du fichier");
				}
				tabF[i] += "|" + dateC.substring(0,10) + " " + (Integer.parseInt(dateC.substring(11,13)) + ((this.H_HIVER)? 1 : 0)) + dateC.substring(13,16)
						+  "|" + dateM.substring(0,10) + " " + (Integer.parseInt(dateM.substring(11,13)) + ((this.H_HIVER)? 1 : 0)) + dateM.substring(13,16);
			}
			return tabF;
		}
		else { return null; } //Aucune sauvegarde de classe
	}
	
	public int      recupTailleMaxFichier(String rep)
	{
		int tailleMax = 80;//pour eviter de dépasser et de casser l'affichage en cas de nom de classe improbable
		
		int max = 0;
		File repertoire = new File(rep);
		String[] liste = repertoire.list();

		assert liste != null;
		for(String s : liste) { if(max < s.length()) max = s.length(); }
		if(max > tailleMax) return tailleMax;
		return max;
	}
	
	public String   recupContenuConfig(String nomFichier)//recupre dans un String un diagramme déjà créé
    {
        String diagramme = "\n\n\n";
        ConfigReader temp = new ConfigReader(nomFichier);
		diagramme+= temp.toString();
        CreateFile(temp,nomFichier.replace(".txt",""), this.options[1], this.options[2]);
        return diagramme;
    }
	
	public void     genNouvDiagramme(String[] tabNomFichier)//génère les diagrammes des fichiers java en paramètres
	{
		for(int f = 0; f < tabNomFichier.length; f++) tabNomFichier[f] = tabNomFichier[f].replace(".java","");
		this.diagTemp = new Diagramme(tabNomFichier);
	}
	
	public String   genNouvConfig(String nomFichier, String nomAuteur)//génère une nouvelle configuration au format txt
	{
		new ConfigGenerator(this.diagTemp, nomFichier, nomAuteur);
		return recupContenuConfig(nomFichier+".txt");
	}
	
	public void     modifierConfig(String nomFichier)//permet de modifier en interne les fichiers de configuration
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
			assert desktop != null;
			desktop.edit(file);
        }
        catch (IOException ex) { Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex); }
	}
	
	public boolean[] supprimerConfig(String fichierSup)//permet de supprimer en interne les fichiers de configuration
	{
		boolean[] tabRetSup = new boolean[4];
		try
		{
			File tmpC = new File(repConfig + fichierSup); //fichier config à sup
			tabRetSup[0] = tmpC.delete();
			
			if(options[3])
			{
				File tmpT = new File(repDiagrammeTxt + fichierSup                        ); //fichier diagramme à sup en txt
				File tmpP = new File(repDiagrammePdf + fichierSup.replace(".txt", ".pdf")); //fichier diagramme à sup en pdf

				tabRetSup[1] = tmpT.delete();
				tabRetSup[2] = tmpP.delete();
			}
			else
			{
				tabRetSup[1] = true;
				tabRetSup[2] = true;
			}
		}
		catch(Exception e) { e.printStackTrace(); }
		tabRetSup[3] = this.options[3];
		return tabRetSup;
	}
	
	public void modifierFichierIni(boolean[] options)//permet de réécrire le fichier .ini en modifiant des options du programme
	{
		this.options = options;
		String sRet = "";
	
		sRet += ""
              + "\t                                                                              \n"
              + "\t      ,--.  ,---.,--.   ,--.,---.       ,---.     ,--. ,--.,--.   ,--.,--.    \n"
              + "\t      |  | /  O  \\\\  `.'  //  O  \\     '.-.  \\    |  | |  ||   `.'   ||  |    \n"
              + "\t ,--. |  ||  .-.  |\\     /|  .-.  |     .-' .'    |  | |  ||  |'.'|  ||  |    \n"
              + "\t |  '-'  /|  | |  | \\   / |  | |  |    /   '-.    '  '-'  '|  |   |  ||  '--. \n"
              + "\t  `-----' `--' `--'  `-'  `--' `--'    '-----'     `-----' `--'   `--'`-----' \n"
              + "\t                                                                              \n"
			  + "";
		sRet+="Développé par ©InnovAction\n\n";
	
		sRet+="Afficher diagramme après création = "						   	       + this.options[0] + "\n";
		sRet+="Créer fichier diagramme au format txt = "					   	       + this.options[1] + "\n";
		sRet+="Créer fichier diagramme au format pdf = "					   		   + this.options[2] + "\n";
		sRet+="Supprimer les fichiers diagrammes associés au fichier configuration = " + this.options[3] + "\n";
	
		PrintWriter writer;
		try
		{
			File f = new File("./java2uml.ini");
			writer = new PrintWriter(f, StandardCharsets.UTF_8);
			writer.println(sRet);
			writer.close();
		}catch (Exception e) {e.printStackTrace();}

	}

	public boolean[] getOptions(){return this.options;}

	public void CreateFile(ConfigReader conf,String nomFichier,boolean txt,boolean pdf)
	{
		try
		{
			if (pdf)
			{
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
			if(txt)
			{
				PrintWriter writer;
				File f = new File("../diagrammes/txt", nomFichier + ".txt");
				writer = new PrintWriter(f, StandardCharsets.UTF_8);
				writer.println(conf.toString());
				writer.close();
			}
		}
		catch (Exception e) {e.printStackTrace();}
	}
}


