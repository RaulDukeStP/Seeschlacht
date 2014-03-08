package spielfeld;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.LinkedList;
import java.util.List;


public class GUI extends JPanel 
{
	private static final long serialVersionUID = 1L;
	 
	private List<Spielfeld> felder;
	private JFrame mainFrame;
	private String winner = null;
	
	Graphics g;
	
	public static final int GROESSE_PRO_FELD = 20;
	
	public GUI()
	{
		felder = new LinkedList<Spielfeld>();
		
		mainFrame = new JFrame("Seeschlacht");         
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize (800, 600);
		mainFrame.setLocationRelativeTo (null);
		mainFrame.setVisible (true);
		mainFrame.add (this);

	}
	
	public void addSpielfeld(Spielfeld feld)
	{
		felder.add(feld);
	}
	
	public void setWinner(String sWho)
	{
		this.winner = sWho;
	}
		
	private void paintFeld(Graphics g, Spielfeld feld)
	{
		 int nFieldStatus;
		 
		 g.setColor(new Color (0.0f, 0.0f, 0.0f));
         for (int i = 0; i <= 10; i++)
         {
                 g.drawLine(feld.getX() + (i * GROESSE_PRO_FELD + i), feld.getY(), feld.getX() + (i * GROESSE_PRO_FELD + i),
                                 feld.getY() + (feld.GROESSE * GROESSE_PRO_FELD + feld.GROESSE));
                
                 g.drawLine(feld.getX() , feld.getY() + (i * GROESSE_PRO_FELD + i), feld.getX() + (feld.GROESSE * GROESSE_PRO_FELD + feld.GROESSE),
                		 feld.getY() + (i * GROESSE_PRO_FELD + i));
         }
         
         for (int x = 0; x < 10; x++)
         {
                 for (int y = 0; y < 10; y++)
                 {
                	 nFieldStatus = feld.getFieldState(x, y);
                	 switch(nFieldStatus)
                	 {
                	 	case Spielfeld.FELD_LEER:
                	 		 g.setColor(new Color (1.0f, 1.0f, 1.0f));
                             g.fillRect(feld.getX() + (1 + x * GROESSE_PRO_FELD + x), feld.getY() + (1 + y * GROESSE_PRO_FELD + y), GROESSE_PRO_FELD, GROESSE_PRO_FELD);
                             break;
                        
                	 	case Spielfeld.FELD_SCHIFF:
                	 		if( feld.isHidden() )
                	 			g.setColor(new Color (1.0f, 1.0f, 1.0f));
                	 		else
                	 			g.setColor(new Color (1.0f, 0.0f, 0.0f));
                            g.fillRect(feld.getX() + (1 + x * GROESSE_PRO_FELD + x), feld.getY() + (1 + y * GROESSE_PRO_FELD + y), GROESSE_PRO_FELD, GROESSE_PRO_FELD);
                            break;
                         
                	 	case Spielfeld.FELD_SHADOW:
                             g.setColor(new Color (0.0f, 1.0f, 0.0f));
                             g.fillRect(feld.getX() + (1 + x * GROESSE_PRO_FELD + x), feld.getY() + (1 + y * GROESSE_PRO_FELD + y), GROESSE_PRO_FELD, GROESSE_PRO_FELD);
                             break;
                        
                	 	case Spielfeld.FELD_MISS:
                            g.setColor(new Color (1.0f, 1.0f, 1.0f));
                            g.fillRect(feld.getX() + (1 + x * GROESSE_PRO_FELD + x), feld.getY() + (1 + y * GROESSE_PRO_FELD + y), GROESSE_PRO_FELD, GROESSE_PRO_FELD);
                            g.setColor(new Color (0.0f, 0.0f, 0.0f));
                            g.drawOval(2 + feld.getX() + (1 + x * GROESSE_PRO_FELD + x), 2 + feld.getY() + (1 + y * GROESSE_PRO_FELD + y), GROESSE_PRO_FELD - 4, GROESSE_PRO_FELD - 4);
                            break;
                            
                        case Spielfeld.FELD_HIT:
                            g.setColor(new Color (1.0f, 0.1f, 0.1f));
                            g.fillRect(feld.getX() + (1 + x * GROESSE_PRO_FELD + x), feld.getY() + (1 + y * GROESSE_PRO_FELD + y), GROESSE_PRO_FELD, GROESSE_PRO_FELD);
                            g.setColor(new Color (0.0f, 0.0f, 0.0f));
                            g.drawLine(feld.getX() + (1 + x * GROESSE_PRO_FELD + x), feld.getY() + (1 + y * GROESSE_PRO_FELD + y), feld.getX() + (1 + x * GROESSE_PRO_FELD + x) + GROESSE_PRO_FELD, feld.getY() + (1 + y * GROESSE_PRO_FELD + y) + GROESSE_PRO_FELD);
                            g.drawLine(feld.getX() + (1 + x * GROESSE_PRO_FELD + x), feld.getY() + (1 + y * GROESSE_PRO_FELD + y) + GROESSE_PRO_FELD,
                            feld.getX() + (1 + x * GROESSE_PRO_FELD + x) + GROESSE_PRO_FELD, feld.getY() + (1 + y * GROESSE_PRO_FELD + y));
                            break;
                	 }
                 }
         }
         
         if(winner != null)
        	 g.drawString(winner + " WINS!", 400, 400);


	}
	
	public void paintComponent (Graphics g)
	{
	     this.g = g;
	        
	     super.paintComponents(g);
	     for (Spielfeld feld : felder)
         {
	    	 paintFeld (g, feld);	    	 
         }
	         
	}
	
	public void zeichne()
    {
         this.repaint();
    }

}
