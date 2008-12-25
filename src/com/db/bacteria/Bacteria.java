package com.db.bacteria;

import java.io.Serializable;
import java.util.List;

public class Bacteria implements Serializable {
	private static final long serialVersionUID = -7465812087743365129L;
	
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
	public static List<Bacteria> newRandomInstance(List<Bacteria> bacterias,
			int quantityOfBacterias) {
		// final Bacteria[] bacterias = new Bacteria[amountofBacterias];
		for (int i = 0; i < quantityOfBacterias; i++) {
			bacterias.add(i, new Bacteria(
					(int) Math.round(Math.random() * 600), (int) Math
							.round(Math.random() * 600), (int) Math.round(Math
							.random() * 10)
							- (int) Math.round(Math.random() * 10), (int) Math
							.round(Math.random() * 10)
							- (int) Math.round(Math.random() * 10), (int) Math
							.round(Math.random() * 100)));
		}

		while (verification(bacterias) == false) {
			System.out.println("One more time");
			for (int i = 0; i < bacterias.size(); i++) {
				bacterias.set(i, new Bacteria((int) Math
						.round(Math.random() * 600), (int) Math.round(Math
						.random() * 600), (int) Math.round(Math.random() * 10)
						- (int) Math.round(Math.random() * 10), (int) Math
						.round(Math.random() * 10)
						- (int) Math.round(Math.random() * 10), (int) Math
						.round(Math.random() * 100)));
			}
		}
		return bacterias;
	}

	// TODO load bacteria configuration from XML file

	public static boolean verification(List<Bacteria> bacterias) {
		double[][] centralDistancesIJ = new double[bacterias.size()][bacterias
				.size()];
		double[][] actualDistancesIJ = new double[bacterias.size()][bacterias
				.size()];
		double actualDistanceX;
		double actualDistanceY;
		int sum = 0;
		for (int i = 0; i <= (bacterias.size() - 1); i++) {
			for (int j = i + 1; j < bacterias.size(); j++) {
				centralDistancesIJ[i][j] = bacterias.get(i).getDiameter() / 2
						+ bacterias.get(j).getDiameter() / 2;
				actualDistanceX = bacterias.get(j).getX()
						- bacterias.get(j).getX()
						+ bacterias.get(j).getDiameter() / 2
						- bacterias.get(i).getDiameter() / 2;
				actualDistanceY = bacterias.get(j).getY()
						- bacterias.get(i).getY()
						+ bacterias.get(j).getDiameter() / 2
						- bacterias.get(i).getDiameter() / 2;
				actualDistancesIJ[i][j] = Math.round(Math.sqrt(Math.pow(
						actualDistanceX, 2)
						+ Math.pow(actualDistanceY, 2)));

				if (actualDistancesIJ[i][j] >= centralDistancesIJ[i][j]) {
					sum = sum + 1;
				}
			}
		}
		System.out.println("sum: " + sum);
		if (sum == bacterias.size() * (bacterias.size() - 1) / 2) {
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

	public static void termination(List<Bacteria> bacterias,
			int ClickedX, int ClickedY) {
		for (int i = 0; i < bacterias.size(); i++) {
			int a = bacterias.get(i).getX() + bacterias.get(i).getDiameter()
					/ 2;
			int b = bacterias.get(i).getY() + bacterias.get(i).getDiameter()
					/ 2;
			if (bacterias.get(i).getDiameter() / 2 >= (int) Math
					.abs(Math.sqrt(Math.pow((bacterias.get(i).getX()
							+ bacterias.get(i).getDiameter() / 2
							- bacterias.get(i).getVX() - ClickedX), 2)
							+ Math.pow((bacterias.get(i).getY()
									+ bacterias.get(i).getDiameter() / 2
									- bacterias.get(i).getVY() - ClickedY), 2))))

			{

				System.out.println("����� �����: " + bacterias.get(i)
						+ "� ������� ������! ");
				bacterias.remove(i);

			} else {
				System.out.println("����� ������:  " + a + ";" + b
						+ "������ ������: " + bacterias.get(i).getDiameter()
						/ 2);
				System.out.println("�������� ����:  " + ClickedX + ";"
						+ ClickedY);
			}
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

	public boolean includes(int targetX, int targetY) {
		int toTheCenter = Math.abs(targetX - diameter/2- x)*Math.abs(targetX - diameter/2- x) +  
			Math.abs(targetY- diameter/2 - y)*Math.abs(targetY - diameter/2 - y);
		return toTheCenter < (diameter/2)*(diameter/2);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Bacteria)) return false;
		Bacteria other = (Bacteria) obj;
		
		if (x != other.x) return false;
		if (y != other.y) return false;
		if (vx != other.vx) return false;
		if (vy != other.vy) return false;
		if (diameter != other.diameter) return false;

		return true;
	}
}
