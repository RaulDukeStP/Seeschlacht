package control;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import java.util.Random;

import spielfeld.GUI;
import spielfeld.Spielfeld;
import schiffe.Schiff;
import schiffe.SchiffShadow;

public class PlaceSchiff implements MouseMotionListener, MouseListener
{
	private GUI gui;
	private Spielfeld feld;
	
	private List<Schiff> schiffe;
	private int nSchiffCounter;
	
	private SchiffShadow shadow;
	
	private boolean bReady;	
	
	public PlaceSchiff(GUI gui, int x, int y)
	{
		gui.addMouseMotionListener(this);
		gui.addMouseListener(this);
		
		feld = new Spielfeld(x, y, false);		
		this.gui = gui;
		
		schiffe = new LinkedList<Schiff>();
		gui.addSpielfeld(feld);
		
		nSchiffCounter = 0;	
		bReady = false;		
		
		shadow = new SchiffShadow(0, 0, 5, true);
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
					bReady = true;
					gui.removeMouseListener(this);
	                gui.removeMouseMotionListener(this);
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
	
	public void mouseMoved(MouseEvent e)
	{		
		for (Schiff schiff : schiffe)
        {
            schiff.setSchifftoSpielfeld(feld);
        }
				
		int x = feld.getXCoord(e.getX());
        int y = feld.getYCoord(e.getY());      
        
        if( x >= 0 && x < 10 && y >= 0 && y < 10 && nSchiffCounter < 10)        
        	shadow.move(feld, x, y);
        gui.zeichne();
        
	}
	
	public void mouseClicked(MouseEvent e)
	{	
		
		if(e.getButton() != MouseEvent.BUTTON1 && nSchiffCounter < 10)
        {
			shadow.setzeHorizontal(!shadow.getHorizontal());
			gui.zeichne();
        }
		
		if(e.getButton() == MouseEvent.BUTTON1 && shadow.positionGueltig(feld) && nSchiffCounter < 10)
		{			 
			schiffe.add(new Schiff(shadow.getX(), shadow.getY(), shadow.getGroesse(), shadow.getHorizontal()));
			nSchiffCounter++;
			
			switch(nSchiffCounter)
			{
				case 1:
				case 2:
					shadow = new SchiffShadow(0, 0, 4, true);
					break;
				case 3:
				case 4:
				case 5:
					shadow = new SchiffShadow(0, 0, 3, true);
					break;
				case 6:
				case 7:
				case 8:
				case 9:
					shadow = new SchiffShadow(0, 0, 2, true);
					break;
				default:
					bReady = true;
                    gui.removeMouseListener(this);
                    gui.removeMouseMotionListener(this);
                    break;
			}
		}
	}
	
	public void mouseDragged (MouseEvent e) {}
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
    public void mousePressed (MouseEvent e) {}
    public void mouseReleased (MouseEvent e) {}


}
