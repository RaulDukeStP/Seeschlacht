package control;

import java.util.LinkedList;
import java.util.List;

import java.util.Random;

import spielfeld.GUI;
import spielfeld.Spielfeld;
import schiffe.Schiff;
import schiffe.SchiffShadow;

public class PlaceGegner 
{
	private GUI gui;
	private Spielfeld feld;
	
	private List<Schiff> schiffe;
	private int nSchiffCounter;
	
	private SchiffShadow shadow;
	
	private boolean bReady;	
	
	public PlaceGegner(GUI gui, int x, int y)
	{
		feld = new Spielfeld(x, y, false);		
		this.gui = gui;
		
		schiffe = new LinkedList<Schiff>();
		gui.addSpielfeld(feld);
		
		nSchiffCounter = 0;	
		bReady = false;
	}
	
	public void Random()
	{
		
		SchiffShadow shRandom = new SchiffShadow(new Random().nextInt(10), new Random().nextInt(10), 5, new Random().nextBoolean());
		nSchiffCounter = 0;
		
		while(nSchiffCounter < 10)
		{		
			switch(nSchiffCounter)
			{
				case 0:
					shRandom = new SchiffShadow(new Random().nextInt(10), new Random().nextInt(10), 5, new Random().nextBoolean());
					break;
				case 1:
				case 2:
					shRandom = new SchiffShadow(new Random().nextInt(10), new Random().nextInt(10), 4, new Random().nextBoolean());
					break;
				case 3:
				case 4:
				case 5:
					shRandom = new SchiffShadow(new Random().nextInt(10), new Random().nextInt(10), 3, new Random().nextBoolean());
					break;
				case 6:
				case 7:
				case 8:
				case 9:
					shRandom = new SchiffShadow(new Random().nextInt(10), new Random().nextInt(10), 2, new Random().nextBoolean());						
					break;
				default:					
					break;
			}
			
			if( shRandom.positionGueltig(feld) )
			{
				nSchiffCounter++;
				Schiff schiff = new Schiff(shRandom.getX(), shRandom.getY(), shRandom.getGroesse(), shRandom.getHorizontal());
				schiffe.add(schiff);
				schiff.setSchifftoSpielfeld(feld);				
			}
		}
		
	}
	
	public List<Schiff> getSchiffe()
	{
		return schiffe;
	}
	
	public Spielfeld getSpielfeld()
	{
		return feld;
	}
	
	public boolean isReady()
	{
		return bReady;
	}
}
