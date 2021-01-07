/**
  Classe : Coord
  @Author : InnovAction
  @version : 1.0
  @since : 2021
*/

package java2uml.IHM.GUI;


public class Coord {
	
	/*////////////////////////////////////////////////////////////////////////////
	//                        déclaration des attributs                        //
	///////////////////////////////////////////////////////////////////////////*/
	private int x;
	private int y;
	
	
	//Cette classe permet de récuperer les coord X et Y et elle est utiliser pour la liaisons des fléches
	public Coord(int x , int y)
	{
		this.x = x;
		this.y = y;
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
	
	
	public void setY(int y) {
		this.y = y;
	}
	public String toString() {
		return "x : "+this.x+" y : "+this.y;
	}
}
