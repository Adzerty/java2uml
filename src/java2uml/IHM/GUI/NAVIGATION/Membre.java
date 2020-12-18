package java2uml.IHM.GUI.NAVIGATION;

public class Membre {

	private String nom;
	private String prenom;
	private String image;
	private String titre;
	
	public Membre(String nom , String prenom , String image ) {
		this.nom = nom;
		this.prenom = prenom;
		this.image = image;
		
	}
	
	public Membre(String titre ) {
		this.titre = titre;
	}
	
	public String getNom() {
		return this.nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getImage() {
		return this.image;
	}
	public String getTitre() {
		return this.titre;
	}

	
}
