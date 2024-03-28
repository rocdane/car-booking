package com.carbooking.tp;

public class Session {
	
	private static Session session;
	
	private String Username;
	private String Password;
	
	private Session() {
		this.Username="";
		this.Password="";
	}
	
	public static Session getSession() {
		if(session==null) {
			session = new Session();
		}
		return session;
	}
	
	public void setUsername(String username) {
		session.Username = username;
	}
	
	public String getUsername() {
		return session.Username;
	}
	
	public void setPassword(String password) {
		session.Password=password;
	}
	
	public String getPassword() {
		return session.Password;
	}
	
	public void Login(String u, String p) {
		session.setPassword(p);
		session.setUsername(u);
		Compteur.compteur.start();
	}
	
	public void sessionDestroy() {
		session.setUsername("");
		session.setPassword("");
		Compteur.compteur.interrupt();
	}
}