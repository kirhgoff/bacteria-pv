package com.db.bacteria;

public class Bacteria {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int diameter;

	// private static final Logger log = Logger.getLogger(Bacteria.class);

	/*
	 * public static Bacteria newRandomInstance() { return new Bacteria((int)
	 * Math.round(Math.random()500), (int) Math .round(Math.random()500), (int)
	 * Math .round(Math.random()10) - (int) Math.round(Math.random()10), (int)
	 * Math.round(Math .random()10) - (int) Math.round(Math.random()10), (int)
	 * Math.round(Math .random()100)); }
	 */

	public static Bacteria[] newRandomInstance(Bacteria[] bacterias) {
		// final Bacteria[] bacterias = new Bacteria[amountofBacterias];
		do {
			System.out.println("One more time");
			for (int i = 0; i < bacterias.length; i++) {
				bacterias[i] = new Bacteria((int) Math
						.round(Math.random() * 600), (int) Math.round(Math
						.random() * 600), (int) Math.round(Math.random() * 10)
						- (int) Math.round(Math.random() * 10), (int) Math
						.round(Math.random() * 10)
						- (int) Math.round(Math.random() * 10), (int) Math
						.round(Math.random() * 100));
			}
		} while (verification(bacterias) == false);

		return bacterias;
	}

	//TODO load bacteria configuration from XML file
	public static boolean verification(Bacteria[] bacterias) {
		double[][] centralDistancesIJ = new double[bacterias.length][bacterias.length];
		double[][] actualDistancesIJ = new double[bacterias.length][bacterias.length];
		double actualDistanceX;
		double actualDistanceY;
		int sum = 0;
		for (int i = 0; i <= (bacterias.length - 1); i++) {
			for (int j = i + 1; j < bacterias.length; j++) {
				centralDistancesIJ[i][j] = bacterias[i].getDiameter() / 2
						+ bacterias[j].getDiameter() / 2;
				actualDistanceX = (bacterias[j].getX() - bacterias[i].getX()
						+ bacterias[j].getDiameter() / 2 - bacterias[i]
						.getDiameter() / 2);
				actualDistanceY = bacterias[j].getY() - bacterias[i].getY()
						+ bacterias[j].getDiameter() / 2
						- bacterias[i].getDiameter() / 2;
				actualDistancesIJ[i][j] = Math.round(Math.sqrt(Math.pow(
						actualDistanceX, 2)
						+ Math.pow(actualDistanceY, 2)));

				if (actualDistancesIJ[i][j] >= centralDistancesIJ[i][j]) {
					sum = sum + 1;
				}
			}
		}
		System.out.println("sum: " + sum);
		if (sum == bacterias.length * (bacterias.length - 1) / 2) {
			return true;
		} else {
			return false;
		}
	}

	public Bacteria(int x, int y, int vx, int vy, int radius) {
		this.setX(x);
		this.setY(y);
		this.setVX(vx);
		this.setVY(vy);
		this.setRadius(radius);

	}

	public void changeDirection(Camera camera) {
		if (getX() <= camera.getMinX()) {
			setX(camera.getMinX());
			setVX(-getVX());
		} else if (getX() >= camera.getMaxX() - getDiameter()) {
			setVX(-getVX());
			setX(camera.getMaxX() - getDiameter());
		} else if (getY() <= camera.getMinY()) {
			setVY(-getVY());
			setY(camera.getMinY());
		} else if (getY() >= camera.getMaxY() - getDiameter()) {
			setVY(-getVY());
			setY(camera.getMaxY() - getDiameter());

		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.diameter = radius;
	}

	public int getY() {
		return y;
	}

	public void setVX(int vx) {
		this.vx = vx;
	}

	public int getVX() {
		return vx;
	}

	public void setVY(int vy) {
		this.vy = vy;
	}

	public int getVY() {
		return vy;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public String toString() {
		return super.toString() + " [" + x + ", " + y + "]";
	}

	public void moveBy(double dx, double dy) {
		setX((int) Math.round(getX() + dx));
		setY((int) Math.round(getY() + dy));
	}

	public void move() {
		moveBy(getVX(), getVY());
	}

}
