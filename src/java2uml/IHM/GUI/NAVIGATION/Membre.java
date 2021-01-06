package java2uml.IHM.GUI.NAVIGATION;

public class Membre {

	private String nom;
	private String prenom;
	private String image;
	private String titre;
	private String description;
	
	public Membre(String nom , String prenom ,String description, String image ) {
		this.nom = nom;
		this.prenom = prenom;
		this.image = image;
		this.description = description;
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
	public String getDescription() {
		return this.description;
	}
	
}
