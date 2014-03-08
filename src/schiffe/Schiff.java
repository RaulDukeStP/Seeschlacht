package schiffe;

import spielfeld.Spielfeld;

public class Schiff {
	
	protected int nCoordx;
	protected int nCoordy;
	protected int nGroesse;
	private boolean bHorizontal;
	
	public Schiff(int x, int y, int groesse, boolean horizont)
	{
		nCoordx = x;
		nCoordy = y;
		
		nGroesse = groesse;
		bHorizontal = horizont;
	}
	
	public void setSchifftoSpielfeld(Spielfeld feld)
	{
		for(int i=0;i<nGroesse;i++)
		{
			if(bHorizontal)
			{
				if( (nCoordx + i) < 10 )
				{	
					if(feld.getFieldState((nCoordx + 1), nCoordy) != Spielfeld.FELD_HIT)
						feld.setFieldState((nCoordx + i), nCoordy, Spielfeld.FELD_SCHIFF);
				}
			}
			else
			{
				if( (nCoordy + i) < 10 )
				{
					if(feld.getFieldState((nCoordx + 1), nCoordy) != Spielfeld.FELD_HIT)
						feld.setFieldState(nCoordx, (nCoordy + i), Spielfeld.FELD_SCHIFF);
				}
			}			
		}		
	}
	
	public void move(Spielfeld feld, int xNeu, int yNeu)
    {
        for (int i = 0; i < 10; i++)
        {
            if (bHorizontal)
            {
                if (nCoordx + i < 10)
                	feld.setFieldState((nCoordx + i), nCoordy, Spielfeld.FELD_LEER);
            }
            else
            {
                if (nCoordy + i < 10)
                    feld.setFieldState(nCoordx, (nCoordy + i), Spielfeld.FELD_LEER);
            }
        }
       
        nCoordx = xNeu;
        nCoordy = yNeu;           
        setSchifftoSpielfeld (feld);
    }

	
	public boolean getHorizontal()
	{
		return bHorizontal;
	}
	
	public void setHorizontal(boolean value)
	{
		bHorizontal = value;
	}
	
	public int getGroesse() 
	{ 
		return nGroesse; 
	}
	
    public int getX() 
    { 
    	return nCoordx; 
    }
    
    public int getY() 
    { 
    	return nCoordy; 
    }

	
	
}
