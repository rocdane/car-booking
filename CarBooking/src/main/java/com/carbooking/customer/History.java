package com.carbooking.customer;

import java.sql.Date;

public class History {
	private Integer historyId;
	public Date DateOf;
	public Date DateTo;
	public Integer Customer;
	public Integer Car;
	
	public History(Integer id, Date dateOf,Date dateTo,Integer carId,Integer custId) {
		this.historyId=id;
		this.DateOf = dateOf;
		this.DateTo = dateTo;
		this.Customer = custId;
		this.Car = carId;
	}
	
	public Integer getHistoryId() {
		return this.historyId;
	}
}