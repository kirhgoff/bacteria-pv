package com.db.bacteria;

import java.util.Arrays;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

public class BacteriaTest {
	{
		BasicConfigurator.configure();
	}

	@Test
	public void testChangeDirectionForOne() throws Exception {
		Bacteria bacteria = new Bacteria(0, 0, 5, 10, 5);
		bacteria.changeDirection(new Camera(-100, -100, 10, 4));

		Assert.assertEquals(5, bacteria.getVX());
		Assert.assertEquals(-10, bacteria.getVY());
		Assert.assertEquals(0, bacteria.getX());
		Assert.assertEquals(-1, bacteria.getY());
	}

	// @Test
	// public void testCollision() throws Exception {
	// Bacteria bacteriaBig = new Bacteria(0, 0, 5, 0, 4);
	// Bacteria bacteriaSmall = new Bacteria(3, 0, -5, 0, 2);
	// Camera.processCollided(new Bacteria[] { bacteriaBig, bacteriaSmall });
	//
	// Assert.assertEquals(-5, bacteriaBig.getVX());
	// Assert.assertEquals(5, bacteriaSmall.getVX());
	// Assert.assertEquals(3, bacteriaSmall.getX());
	// Assert.assertEquals(1, bacteriaBig.getX());
	//
	// }

	// @Test
	// public void testVerification() throws Exception {
	// Bacteria bacteriaBig = new Bacteria(0, 0, 5, 0, 4);
	// Bacteria bacteriaSmall = new Bacteria(3, 0, -5, 0, 2);
	// Bacteria.verification(new Bacteria[] { bacteriaBig, bacteriaSmall });

	// Assert.assertEquals(-5, centralDistancesIJ[0][1]);
	// Assert.assertEquals(5, bacteriaSmall.getVX());
	// Assert.assertEquals(4, bacteriaSmall.getX());
	// Assert.assertEquals(0, bacteriaBig.getX());

	// }

	// @Test
	// public void testNewSize() throws Exception {
	// System.out.println("new size");
	// Camera camera = new Camera (0, 0, 100, 100);
	// Bacteria bacteria = new Bacteria (0, 0, 50, 50, 100);
	// camera.setBacteria(new Bacteria [] {bacteria});
	// camera.setNewSize(new Dimension (200, 200));
	// Assert.assertEquals (0, bacteria.getX ());
	// Assert.assertEquals (0, bacteria.getY ());
	// Assert.assertEquals (100, bacteria.getVX ());
	// Assert.assertEquals (100, bacteria.getVY ());
	// Assert.assertEquals (200, bacteria.getDiameter());
	// }
	//	
	// @Test
	// public void testNewSize2() throws Exception {
	// System.out.println("new size2");
	// Camera camera = new Camera (0, 0, 100, 100);
	// Bacteria bacteria = new Bacteria (0, 0, 50, 50, 100);
	// camera.setBacteria(new Bacteria [] {bacteria});
	// camera.setNewSize(new Dimension (100, 200));
	// Assert.assertEquals (0, bacteria.getX ());
	// Assert.assertEquals (0, bacteria.getY ());
	// Assert.assertEquals (50, bacteria.getVX ());
	// Assert.assertEquals (50, bacteria.getVY ());
	// Assert.assertEquals (100, bacteria.getDiameter());
	// }
	//	
	// @Test
	// public void testNewSize3() throws Exception {
	// System.out.println("new size3");
	// Camera camera = new Camera (0, 0, 100, 100);
	// Bacteria bacteria = new Bacteria (0, 0, 50, 50, 100);
	// camera.setBacteria(new Bacteria [] {bacteria});
	// camera.setNewSize(new Dimension (300, 200));
	// Assert.assertEquals (0, bacteria.getX ());
	// Assert.assertEquals (0, bacteria.getY ());
	// Assert.assertEquals (100, bacteria.getVX ());
	// Assert.assertEquals (100, bacteria.getVY ());
	// Assert.assertEquals (200, bacteria.getDiameter());
	// }

}
