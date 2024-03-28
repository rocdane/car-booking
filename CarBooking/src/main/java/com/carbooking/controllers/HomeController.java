/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbooking.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.carbooking.car.Car;
import com.carbooking.car.CarRepositories;
import com.carbooking.customer.CustomerRepositories;
import com.carbooking.tp.Session;

@RestController
public class HomeController {
    public static HomeController home;
	@RequestMapping("/")
    public ModelAndView accueil(){
		
    	List<Car> resultat = new ArrayList<>();
    	resultat=CarRepositories.getInstance().notRent();
    	Map<String,List<Car>> data = new HashMap<>();
    	data.put("list",resultat);
    	System.out.println(Session.getSession().getUsername());
    	System.out.println(Session.getSession().getPassword());
    	return new ModelAndView("index",data);
    }
    
    @RequestMapping("/login")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }
    
    @RequestMapping(value="/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView Login(@RequestParam("username") String u, @RequestParam("password") String p){
    	CustomerRepositories.getInstance().login(u,p);
    	if(CustomerRepositories.getInstance().login(u,p)!=null) {
    		System.out.println(Session.getSession().getUsername()+"#"+Session.getSession().getPassword());
    		Session.getSession().Login(u,p);
    		//Compteur.compteur.run();
    		return new ModelAndView("redirect:/add");
    	}else {
    		System.out.println(Session.getSession().getUsername()+"#"+Session.getSession().getPassword());
    		return new ModelAndView("redirect:/login");
    	}
    }
    
    @RequestMapping("/register")
    public ModelAndView SignIn(){
    	return new ModelAndView("register");
    }
    
    @RequestMapping(value="/registrate", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView Registrate(@RequestParam("username") String u, @RequestParam("password") String p){
    	CustomerRepositories.getInstance().addAdmin(u,p);
    	return new ModelAndView("redirect:/login");
    }
}