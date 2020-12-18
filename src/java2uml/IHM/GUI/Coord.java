package java2uml.IHM.GUI;

public class Coord {
	
	private int x;
	private int y;
	private boolean estPris;
	
	public Coord(int x , int y, boolean pris)
	{
		this.x = x;
		this.y = y;
		this.estPris = pris;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setPris(boolean pris) {
		this.estPris = pris;
	}
	
	public boolean estPris() {
		return this.estPris;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public String toString() {
		return "x : "+this.x+" y : "+this.y;
	}
}
