/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbooking.customer;

public class Customer {
	private Integer CustomerId;
    private Integer CNI;
    private String Nom;
    private String Prenom;
    private Integer Phone;
    

    public Customer() {
    	super();
    }
    
    public Customer(Integer CustomerId,Integer CNI, String Nom, String Prenom, Integer Phone) {
        super();
    	this.CNI = CNI;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Phone = Phone;
    }
    
    public Integer getId(){
        return CustomerId;
    }
    
    public Integer getCNI(){
        return CNI;
    }

    public String getNom(){
        return Nom;
    }

    public String getPrenom(){
        return Prenom;
    }

    public Integer getPhone(){
        return Phone;
    }
}