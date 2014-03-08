package spielfeld;

import java.util.List;


import schiffe.Schiff;
import spielfeld.GUI;

public class Spielfeld {
	
	public static final int FELD_LEER = 0;	
	public static final int FELD_SCHIFF = 1;
	public static final int FELD_MISS = 2;
	public static final int FELD_HIT = 3;
	public static final int FELD_SHADOW = 4;
	public static final int GROESSE = 10;
	
	private int[][] feld;
	private int CoordX;
	private int CoordY;
	private boolean bHidden;
		
	public Spielfeld(int x, int y, boolean hide)
	{
		CoordX = x;
		CoordY = y;
		
		bHidden = hide;
		
		feld = new int[10][10];
		for(int i=0;i<10;i++)
			for(int k=0;k<10;k++)
				feld[i][k] = FELD_LEER;		
	}
	
	public void setBuildSpielfeld(List<Schiff> schiffe)
	{
		for (Schiff schiff : schiffe)
			schiff.setSchifftoSpielfeld(this);        
	}
	
	public void setFieldState(int x, int y, int status)
	{
		feld[x][y] = status;		
	}
	
	public int getFieldState(int x, int y)
	{
		if (x < 10 && y < 10 && x >= 0 && y >= 0)
            return feld[x][y];
		else
            return FELD_LEER;
	}	
	
	public int getX()
	{
		return CoordX;
	}
	
	public int getY()
	{
		return CoordY;
	}
	
	public boolean isHidden()
	{
		return bHidden;
	}
	
	public int getXCoord(int x)
    {
            if (x > this.CoordX + 10 * (GUI.GROESSE_PRO_FELD))
                    return 10 - 1;
            return (x - this.CoordX) / (GUI.GROESSE_PRO_FELD + 1);
    }
   
    public int getYCoord(int y)
    {
            if (y > this.CoordY + 10 * (GUI.GROESSE_PRO_FELD))
                    return 10 - 1;
             return (y - this.CoordY) / (GUI.GROESSE_PRO_FELD + 1);
    }
    
    public boolean BlackShipDown()
    {
    	for(int i=0;i<10;i++)
			for(int k=0;k<10;k++)
			{
				if(feld[i][k] == FELD_SCHIFF)
					return false;
			}
    	return true;
    }

	
	public void Output()
	{
		for (int x = 0; x < 10; x++)
		{
			for (int y = 0; y < 10; y++)
				System.out.print(feld[y][x]);
			System.out.print("\n");
		}		
	}
	

}
