package com.db.bacteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Camera {
	private int minX = 0;
	private int minY = 0;
	private int maxX = 800;
	private int maxY = 800;
	// TODO List<Bacteria> bacterias = new ArrayList<Bacteria> ();
	private List<Bacteria> bacterias = Collections
			.synchronizedList(new ArrayList<Bacteria>());

	// private static Logger log = Logger.getLogger(Camera.class);

	public Camera(int minX, int minY, int maxX, int maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public static Bacteria[] createBacteria(int size) {
		Bacteria[] bacterias = new Bacteria[size];
		// TODO create random instances one by one
		Bacteria.newRandomInstance(bacterias);
		return bacterias;
	}

	public void recalculate() {
		for (Bacteria bacteria : bacterias)
			bacteria.move();

		for (Bacteria bacteria : bacterias) {
			bacteria.changeDirection(Camera.this);

		}
		processCollided(bacterias);
	}

	public static void processCollided(List<Bacteria> bacterias) {
		for (Bacteria bacteriaI : bacterias) {
			for (Bacteria bacteriaJ : bacterias) {
				if (!movingInDifferentDirections(bacteriaJ, bacteriaI)) {
					if (areColliding(bacteriaJ, bacteriaI)) {

						assignNewSpeeds(bacteriaJ, bacteriaI);
						stepAside(bacteriaJ, bacteriaI);
					}
				}
			}
		}

	}

	private static void stepAside(Bacteria bacteriaJ, Bacteria bacteriaI) {
		double actualDistanceX = actualDistanceX(bacteriaJ, bacteriaI);
		double actualDistanceY = actualDistanceY(bacteriaJ, bacteriaI);
		double actualDistance = actualDistance(bacteriaJ, bacteriaI);
		double summarizedRadius = bacteriaI.getDiameter() / 2
				+ bacteriaJ.getDiameter() / 2;
		double cosAlfa = actualDistanceX / actualDistance;
		double sinAlfa = actualDistanceY / actualDistance;

		bacteriaJ.moveBy(cosAlfa * (summarizedRadius - actualDistance)
				* Math.signum(bacteriaJ.getVX()), sinAlfa
				* (summarizedRadius - actualDistance)
				* Math.signum(bacteriaJ.getVY()));
	}

	private static long actualDistance(Bacteria bacteriaJ, Bacteria bacteriaI) {
		return Math.round(Math.sqrt(Math.pow(actualDistanceX(bacteriaJ,
				bacteriaI), 2)
				+ Math.pow(actualDistanceY(bacteriaJ, bacteriaI), 2)));
	}

	private static boolean areColliding(Bacteria bacteriaJ, Bacteria bacteriaI) {
		double summarizedRadius = bacteriaI.getDiameter() / 2
				+ bacteriaJ.getDiameter() / 2;
		double actualDistance = actualDistance(bacteriaJ, bacteriaI);
		return summarizedRadius - actualDistance >= 0;
	}

	// TODO use mass to calculate new speeds
	private static void assignNewSpeeds(Bacteria bacteriaJ, Bacteria bacteriaI) {
		int m = bacteriaJ.getVX();
		int n = bacteriaJ.getVY();
		bacteriaJ.setVX(bacteriaI.getVX());
		bacteriaJ.setVY(bacteriaI.getVY());
		bacteriaI.setVX(m);
		bacteriaI.setVY(n);
	}

	private static boolean movingInDifferentDirections(Bacteria bacteriaJ,
			Bacteria bacteriaI) {
		int actualDistanceX = actualDistanceX(bacteriaJ, bacteriaI);
		int actualDistanceY = actualDistanceY(bacteriaJ, bacteriaI);

		return (bacteriaI.getVX() * actualDistanceX + bacteriaI.getVY()
				* actualDistanceY <= 0)
				&& (-bacteriaJ.getVX() * actualDistanceX - bacteriaJ.getVY()
						* actualDistanceY <= 0);
	}

	private static int actualDistanceY(Bacteria bacteriaJ, Bacteria bacteriaI) {
		return bacteriaJ.getY() - bacteriaI.getY() + bacteriaJ.getDiameter()
				/ 2 - bacteriaI.getDiameter() / 2;
	}

	private static int actualDistanceX(Bacteria bacteriaJ, Bacteria bacteriaI) {
		return (bacteriaJ.getX() - bacteriaI.getX() + bacteriaJ.getDiameter()
				/ 2 - bacteriaI.getDiameter() / 2);
	}

	public void setBacteria(Bacteria[] theBacterias) {
		for (int i = 0; i < theBacterias.length; i++) {
			bacterias.add(theBacterias [i]);
		}

	}

	public Bacteria[] getBacteria() {
		Bacteria[] array = new Bacteria[bacterias.size()];
		int i = 0;
		for (Bacteria bacteria : bacterias) {
			array[i] = bacteria;
			i++;
		}
		return array;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void killBacteriaAt(int x, int y) {
		System.out.println("Killed at x=" + x + ", y=" + y);
		for (Iterator<Bacteria> iterator = bacterias.iterator();iterator.hasNext();) {
			Bacteria bacteria = iterator.next();
			if (bacteria.includes (x, y)) {
				iterator.remove();
				System.err.println("removed " + bacteria);
				break;
			}
		}
	}

}
