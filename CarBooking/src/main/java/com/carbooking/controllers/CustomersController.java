package com.carbooking.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.carbooking.customer.CustomerCar;
import com.carbooking.customer.CustomerRepositories;

@RestController
public class CustomersController {
	
    @RequestMapping("/customer")
    public ModelAndView History(){
    	Map<String,List<CustomerCar>> data = new HashMap<>();
    	List<CustomerCar> temp = CustomerRepositories.getInstance().getHistory();
    	data.put("list",temp);
        return new ModelAndView("customer",data);
    }
}