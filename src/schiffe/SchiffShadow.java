package schiffe;

import spielfeld.Spielfeld;

public class SchiffShadow extends Schiff 
{
	private boolean fix;
	
	public SchiffShadow(int x, int y, int groesse, boolean horizontal)
	{
		super(x, y, groesse, horizontal);
		fix = false;
	}
	
	public void setSchifftoSpielfeld(Spielfeld feld)
    {
        for (int i = 0; i < nGroesse; i++)
        {
            if (getHorizontal())
            {
                if (nCoordx + i < 10)
                    feld.setFieldState((nCoordx + i), nCoordy, Spielfeld.FELD_SHADOW);
            }
            else
            {
                if (nCoordy + i < 10)
                	feld.setFieldState(nCoordx, (nCoordy + i), Spielfeld.FELD_SHADOW);
            }
        }
    }
   
    public boolean positionGueltig(Spielfeld feld)
    {
            if (getHorizontal())
                    if (nCoordx + nGroesse > 10)
                            return false;
            if (!getHorizontal())
                    if (nCoordy + nGroesse > 10)
                            return false;
           
            int iterX = nCoordx;
            int iterY = nCoordy;
           
            if (getHorizontal())
                    for (iterX = nCoordx; iterX < nCoordx + nGroesse; iterX++)
                    {
                            if (feld.getFieldState(iterX, iterY) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX + 1, iterY) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX - 1, iterY) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX, iterY + 1) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX, iterY - 1) == Spielfeld.FELD_SCHIFF)
                                    return false;
                    }
           
            if (!getHorizontal())
                    for (iterY = nCoordy; iterY < nCoordy + nGroesse; iterY++)
                    {
                            if (feld.getFieldState(iterX, iterY) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX + 1, iterY) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX - 1, iterY) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX, iterY + 1) == Spielfeld.FELD_SCHIFF ||
                                    feld.getFieldState(iterX, iterY - 1) == Spielfeld.FELD_SCHIFF)
                                    return false;
                    }
            return true;
    }
   
    public void setzeHorizontal (boolean wert)
    {
    	setHorizontal(wert);
    }
   
    public void setzePhantom()
    {
        fix = true;
    }

    public boolean getGesetzt() 
    { 
    	return fix; 
    }

}
