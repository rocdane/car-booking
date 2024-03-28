package com.carbooking.customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer>{
	
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
		Customer customer = new Customer(rs.getInt("CustomerId"),rs.getInt("CNI"),rs.getString("Nom"),rs.getString("Prenom"),rs.getInt("Phone"));
		return customer;
	}
}