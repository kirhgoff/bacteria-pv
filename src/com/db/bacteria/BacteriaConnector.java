package com.db.bacteria;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BacteriaConnector implements Runnable {
	private ServerSocket serverSocket;
	private List<Socket> sockets = new ArrayList<Socket>();
	private Camera camera;

	public BacteriaConnector(Camera camera) {
		this.camera = camera;
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(6666);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 6666");
			System.exit(-1);
		}
		Socket clientSocket = null;
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				synchronized (camera) {
					sockets.add(clientSocket);
					System.out.println("Socket added");
				}
			} catch (IOException e) {
				System.err.println("Accept failed.");

			}
		}
	}

	public void startSendingUpdates() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (camera) {
						if (sockets.size() < 1) continue;
						for (Iterator<Socket> iterator = sockets.iterator(); iterator.hasNext();) {
							Socket socket = iterator.next();
							
							try {
								ObjectOutputStream os = new ObjectOutputStream(
										socket.getOutputStream());
								os.writeObject(camera);
								os.close();
								System.out.println("Written");
							} 
							catch (IOException e) {
								e.printStackTrace();
								iterator.remove();
							}
						}
					}
					try {
						Thread.sleep(100);
					} 
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			}
		}, "Update sender").start();

	}
}
