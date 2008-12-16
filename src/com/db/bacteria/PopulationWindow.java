package com.db.bacteria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class PopulationWindow extends JComponent{
	private Camera camera = null;
    private Image bacteriaImage = null;

	public PopulationWindow(Camera camera) {
		this.camera = camera;
	}
 
	public Dimension getPreferredSize() {
		return new Dimension(800, 800);
	}
	
    public void update(Graphics g) {
    	paint(g);
   	}
	
	public void paint(Graphics g) {
		Dimension size = getSize();
		bacteriaImage = createImage(size.width,size.height);
		Graphics imageGraphics = bacteriaImage.getGraphics();

		for (Bacteria bacteria : camera.getBacteria()) {
			int x = bacteria.getX();
			int y = bacteria.getY();
			int d = bacteria.getDiameter(); 
	 
			imageGraphics.setColor(new Color(200,50,0));
			imageGraphics.fillOval(x, y, d, d);
			imageGraphics.setColor(new Color(0,0,0));
			imageGraphics.drawOval(x, y, d, d);
					
		}
		
     	g.drawImage(bacteriaImage, 0, 0, null);
	}

}
