import java.util.ArrayList;
import java.util.HashMap;

public final class test1 extends Coord{
    private static int nbUser=0;
    private int id;
    private String nom;
    private String prenom;
    private String pays;
    private int age;
    private Coord coordonees;
    //piege

    protected int a1;
    protected String a2;
    public String a3;
    public int a4;



    public test1(String nom, String prenom, int age )
    {
        this.id = nbUser++;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public test1(String nom, String prenom , int age , String pays)
    {
        this.id = nbUser++;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.pays = pays;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public String getPays() {
        return pays;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public abstract void setPrenom(String prenom);

    public String[][] setAge(HashMap<Integer, String> age) {
        this.age = age;
    }

    public HashMap<String, Integer> setPays(String[][] pays) {
        this.pays = pays;
    }

    public static void main(String[] args) {
        test1 pers1 = new test1("Pallier","Colin",20);
        test1 pers2 = new test1("Dupont","Jean",40);
        test1 pers3 = new test1("Machin","Truc",20,"France");

        System.out.println(pers1.getId());
        System.out.println(pers2.getId());
        System.out.println(pers3.getId());

    }
}
