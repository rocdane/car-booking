package com.carbooking.car;

public class Car {
	private Integer Id;
    private String Immatriculation;
    private String Marque;
    private String Modele;
    private String Description;
    private double PrixLocation;
    public String Image;
    public Boolean isRent;
    
    public Car() {
    	super();	
    }
    
    public Car(Integer id, String Immatriculation, String Marque, String Modele, String Description, double PrixLocation, String Image,Boolean isRent) {
        super();
        this.Id=id;
    	this.Immatriculation = Immatriculation;
        this.Marque = Marque;
        this.Modele = Modele;
        this.Description = Description;
        this.PrixLocation = PrixLocation;
        this.Image = Image;
        this.isRent = false;
    }

    public Integer getId() {
    	return this.Id;
    }
    
    public String getImmatriculation() {
        return Immatriculation;
    }

    public String getMarque() {
        return Marque;
    }

    public String getModele() {
        return Modele;
    }
    
    public String getDescription() {
        return Description;
    }
    
    public double getPrice() {
        return PrixLocation;
    }
    
    public void setPrixLocation(double PrixLocation){
        this.PrixLocation = PrixLocation;
    }
}