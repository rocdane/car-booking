package com.carbooking.customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerCarRowMapper implements RowMapper<CustomerCar>{
	public CustomerCar mapRow(ResultSet rs, int rowNum) throws SQLException{
		CustomerCar customer = new CustomerCar();
		customer.Surname = rs.getString("Nom");
		customer.Name =rs.getString("PreNom");
		customer.Marque = rs.getString("Marque");
		customer.Model = rs.getString("Modele");
		customer.From= rs.getDate("DateOf");
		customer.To= rs.getDate("DateTo");
		return customer;
	}
}
