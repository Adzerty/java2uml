package java2uml.IHM.GUI;

import java2uml.metier.Association;

public class Coord {
	
	private int x;
	private int y;
	private boolean estPris;
	private Association a;
	
	public Coord(int x , int y, boolean pris, Association a)
	{
		this.x = x;
		this.y = y;
		this.estPris = pris;
		this.a = a;
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
	
	public Association getAssociation() {
		return this.a;
	}
	
	public void setAssociation( Association a) {
		this.a = a;
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
