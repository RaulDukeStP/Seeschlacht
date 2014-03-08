package main;

import spielfeld.Spielfeld;
import spielfeld.GUI;
import schiffe.Schiff;

import java.util.List;

import control.*;

public class Seeschlacht {

	public static void main(String[] args) {
		GUI gui = new GUI();
		
		PlaceSchiff placeSpieler = new PlaceSchiff(gui, 10, 10);		
		placeSpieler.Random();		
		//while( !placeSpieler.isReady() )
		//	gui.zeichne();		
		Spielfeld spieler = placeSpieler.getSpielfeld();
		List<Schiff> schiffeMe = placeSpieler.getSchiffe();
		
		PlaceGegner placeGegner = new PlaceGegner(gui, 300, 10);		
		placeGegner.Random();
		Spielfeld gegner = placeGegner.getSpielfeld();
		List<Schiff> schiffeGegner = placeGegner.getSchiffe();
			
		gui.addSpielfeld(spieler);
		gui.addSpielfeld(gegner);
		
		spieler.setBuildSpielfeld(schiffeMe);
		gegner.setBuildSpielfeld(schiffeGegner);
		
		Shoot shoot = new Shoot(gui, spieler, gegner);
		
		gui.zeichne();                
	}

}
