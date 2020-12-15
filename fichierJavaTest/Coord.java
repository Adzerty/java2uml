import java.awt.Point;

public abstract class Coord extends Point
{
	private int  col;
	private int  lig;

	public Coord (int col, int lig)
	{
		this.col = col;
		this.lig = lig;
	}

	protected static int getCol(){ return col; }
	public int getLig(){ return this.lig; }

}