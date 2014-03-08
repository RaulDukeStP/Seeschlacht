package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import spielfeld.GUI;
import spielfeld.Spielfeld;

public class Shoot implements MouseMotionListener, MouseListener
{
	private Spielfeld sfSpieler;
	private Spielfeld sfGegner;
	private GUI gui;
	
	public Shoot(GUI gui, Spielfeld spieler, Spielfeld gegner)
	{
		gui.addMouseMotionListener(this);
		gui.addMouseListener(this);
		
		this.gui = gui;
		sfSpieler = spieler;
		sfGegner = gegner;			
	}
	
	public void mouseClicked(MouseEvent e)	
	{
		int x = sfGegner.getXCoord(e.getX());
        int y = sfGegner.getYCoord(e.getY());
        
        if( x >= 0 && x < 10 && y >= 0 && y < 10)        
        {
        	System.out.println(x + " " + y + " " + sfGegner.getFieldState(x, y));
        	
        	if(sfGegner.getFieldState(x, y) == Spielfeld.FELD_SCHIFF)
        	{
        		sfGegner.setFieldState(x, y, Spielfeld.FELD_HIT);
        		gegnerShootBack();
        		checkWinner();
        	}
        	
        	
        	if(sfGegner.getFieldState(x, y) == Spielfeld.FELD_LEER)
        	{
        		sfGegner.setFieldState(x, y, Spielfeld.FELD_MISS);
        		gegnerShootBack();
        		checkWinner();
        	}
        	
        	gui.zeichne();
        	
        	sfGegner.Output();
        }     
	}	
	
	private void gegnerShootBack()
	{	
		int x, y;
		do
		{
			x = new Random().nextInt(10);
			y = new Random().nextInt(10);
			if(sfSpieler.getFieldState(x, y) == Spielfeld.FELD_SCHIFF)
			{
        		sfSpieler.setFieldState(x, y, Spielfeld.FELD_HIT);
        		break;
			}
			if(sfSpieler.getFieldState(x, y) == Spielfeld.FELD_LEER)
			{
        		sfSpieler.setFieldState(x, y, Spielfeld.FELD_MISS);
        		break;
			}
		}
		while( true );
	}
	
	private void checkWinner()
	{
		if( sfGegner.BlackShipDown() )
			gui.setWinner("Spieler");
		
		if( sfSpieler.BlackShipDown() )
			gui.setWinner("PC");
	}
	
	public void mouseMoved(MouseEvent e){}
	public void mouseDragged (MouseEvent e) {}
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
    public void mousePressed (MouseEvent e) {}
    public void mouseReleased (MouseEvent e) {}

}
