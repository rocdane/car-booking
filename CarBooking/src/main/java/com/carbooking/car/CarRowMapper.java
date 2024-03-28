package com.carbooking.car;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CarRowMapper implements RowMapper<Car>{
	
	public Car mapRow(ResultSet rs, int rowNum) throws SQLException{
		Car car = new Car(rs.getInt("CarId"),rs.getString("Immatriculation"),rs.getString("Marque"),rs.getString("Modele"),rs.getString("Description"),rs.getDouble("Price"),rs.getString("Image"),rs.getBoolean("isRent"));
		return car;
	}
}