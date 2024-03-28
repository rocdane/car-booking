package com.carbooking.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carbooking.tp.DataSource;

@Repository
public class CarRepositories {
	DataSource source = DataSource.getDataSource();//instance unique de la source de données
	
	@Autowired
	JdbcTemplate jdbcTemplate = new JdbcTemplate(source);
	
	private static CarRepositories carRepositories;
	
	private CarRepositories() {
		
	}
	
	public static CarRepositories getInstance() {
		if(carRepositories==null) {
			carRepositories = new CarRepositories();
		}
		return carRepositories;
	}
	
	public Car findById(Integer id) {
		String query = "SELECT * FROM CAR WHERE CarId="+id+"";
		return jdbcTemplate.queryForObject(query,(rs,i)->{
			return new Car(rs.getInt("CarId"),rs.getString("Immatriculation"), rs.getString("Marque"),rs.getString("Modele"), rs.getString("Description"),rs.getDouble("Price"),rs.getString("Image"),rs.getBoolean("isRent"));
		});
	}
	
	public int addCar(Car car) {
		return jdbcTemplate.update("INSERT INTO CAR VALUES(?,?,?,?,?,?,?,?)",new Object[] {car.getId(),car.getImmatriculation(),car.getMarque(),car.getModele(),car.getDescription(),car.getPrice(),car.isRent,car.Image});
	}
	
	public List<Car> findAll(){
		return jdbcTemplate.query("SELECT * FROM CAR", new CarRowMapper());
	}
	
	public List<Car> notRent(){
		return jdbcTemplate.query("SELECT * FROM CAR WHERE isRent=FALSE", new CarRowMapper());
	}
	
	public int rentCar(Integer id){
		return jdbcTemplate.update("UPDATE CAR SET isRent=TRUE WHERE CarId=?", new Object[] {id});
	}
}