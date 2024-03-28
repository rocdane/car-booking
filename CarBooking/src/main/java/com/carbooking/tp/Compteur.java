package com.carbooking.tp;

public class Compteur extends Thread {
	public static Compteur compteur = new Compteur();
	@Override
	public void run() {
		for (int i = 0; i <= 300; i++) {
			Pause(1000);
			Compteur.yield();
		}
		Session.getSession().sessionDestroy();
	}

	public void Pause(long chrono) {
		try {
			Thread.sleep(chrono);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}