package com.carbooking.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carbooking.tp.DataSource;

@Repository
public class CustomerRepositories {
	DataSource source = DataSource.getDataSource();//instance unique de la source de données
	
	@Autowired
	JdbcTemplate jdbcTemplate = new JdbcTemplate(source);
	
	private static CustomerRepositories customerRepositories;
	
	private CustomerRepositories() {
		
	}
	
	public static CustomerRepositories getInstance() {
		if(customerRepositories==null) {
			customerRepositories = new CustomerRepositories();
		}
		return customerRepositories;
	}
	
	public int addAdmin(String u, String p) {
		return jdbcTemplate.update("INSERT INTO ADMIN VALUES(?,?,?)",new Object[] {null,u,p});
	}
	
	public Integer login(String u, String p){
		return jdbcTemplate.queryForObject("SELECT * FROM ADMIN WHERE USERNAME=? AND PASS=?",new Object[] {u,p},(rs,i)->{
			return rs.getInt("AdminId");
		});
	}
	
	public int addCustomer(Customer customer) {
		return jdbcTemplate.update("INSERT INTO CUSTOMER VALUES(?,?,?,?,?)",new Object[] {customer.getId(),customer.getCNI(),customer.getNom(),customer.getPrenom(),customer.getPhone()});
	}
	
	public int addHistory(History newRent) {
		return jdbcTemplate.update("INSERT INTO HISTORY VALUES(?,?,?,?,?)",new Object[] {newRent.getHistoryId(),newRent.DateOf,newRent.DateTo,newRent.Car,newRent.Customer});
	}
	
	public List<CustomerCar> getHistory(){
		return jdbcTemplate.query("SELECT * FROM CUSTOMER,CAR,HISTORY WHERE CUSTOMER.CUSTOMERID=HISTORY.CUSTOMER AND CAR.CARID=HISTORY.CAR", new CustomerCarRowMapper());
	}
	
	public int returnCar(Integer id){
		return jdbcTemplate.update("UPDATE CAR SET isRent=FALSE WHERE CarId=?", new Object[] {id});
	}
	
	public Integer LastId(){
		return jdbcTemplate.queryForObject("SELECT * FROM CUSTOMER ORDER BY CUSTOMERID DESC LIMIT 1",(rs,i)->{
			return rs.getInt("CustomerId");
		});
	}
}