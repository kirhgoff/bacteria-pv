package com.db.bacteria;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		final Socket socket = new Socket("localhost", 6666);
		new Thread(new Runnable() {
			@Override
			public void run() {
				ObjectInputStream is = null;
				try {
					while (true) {
						is = new ObjectInputStream(socket.getInputStream());
						Camera camera = (Camera) is.readObject();
						System.out.println(camera);
						is.close();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}
}
