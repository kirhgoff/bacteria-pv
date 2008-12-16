package com.db.bacteria;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.log4j.BasicConfigurator;

public class Main {
	private static Camera camera;
	private static PopulationWindow populationWindow;

	public static void main(String[] args) throws InterruptedException {
		BasicConfigurator.configure();
	
		final JFrame frame = new JFrame("Bacteria is moving New");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		camera = new Camera(0, 0, 800, 800);		
		camera.setBacteria (Camera.createBacteria (25));
		populationWindow = new PopulationWindow(camera);
		populationWindow.addMouseListener(
				new MouseAdapter () {
					@Override
					public void mouseReleased(MouseEvent e) {
						camera.killBacteriaAt (e.getX(), e.getY());
					}
				}
		);
		frame.getContentPane ().add(populationWindow);
		frame.pack();

		startPaintingThread();
		startBacteriaThread();
		
		frame.setVisible(true);
	}

	private static void startPaintingThread() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					populationWindow.repaint();

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		thread.start();

	}
	
	public static void startBacteriaThread() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					camera.recalculate();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
	
		});
		thread.start();
	
	}	
}
