/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbooking.controllers;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.carbooking.car.Car;
import com.carbooking.car.CarRepositories;
import com.carbooking.customer.Customer;
import com.carbooking.customer.CustomerRepositories;
import com.carbooking.customer.History;
import com.carbooking.tp.Session;

@RestController
public class CarsController {
	//private static String FOLDER1 = "static\\carPictures";
    File resourcesDirectory = new File("src/main/resources/static/carPictures");
    @RequestMapping("add")
    public ModelAndView addVoirure(){    	
    	if(Session.getSession().getUsername().equals("") && Session.getSession().getPassword().equals("")){
    		return new ModelAndView("redirect:/login");
    	}else{
    		return new ModelAndView("add");
    	}
    }
    
    @RequestMapping(value="save", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView register(@RequestParam("immatriculation") String immatriculation, @RequestParam("marque") String marque, @RequestParam("modele") String modele, @RequestParam("description") String description,@RequestParam("price") String price,@RequestParam("image") MultipartFile image){
    	StringBuilder fileName = new StringBuilder();
        fileName.append(immatriculation); //nom du fichier à mettre dans la base de données
        fileName.append(image.getOriginalFilename());
        
        Path imagePath = Paths.get(resourcesDirectory.getAbsolutePath()+"/"+fileName);//chemin de la destination
          try {
			Files.write(imagePath,image.getBytes());
			 
		} catch (IOException e) {
			e.printStackTrace();
		}        
    	Map<String,Car> data = new HashMap<>();
        Car model = new Car(null,immatriculation,marque,modele,description,Double.parseDouble(price),"carPictures/"+fileName.toString(),false);
        CarRepositories.getInstance().addCar(model);
        data.put("done", model);
        return new ModelAndView("show",data);
    }
    
    @GetMapping("car/{idVoiture}")
    public ModelAndView viewCar(@PathVariable("idVoiture")Integer id){
    	Map<String,Car> data = new HashMap<>();
    	//CarRepositories.getInstance().rentCar(id);
    	Car resultat = CarRepositories.getInstance().findById(id);
    	data.put("car", resultat);
    	return new ModelAndView("car",data);
    }
    
    @RequestMapping("carList")
    public ModelAndView getAllCars(){
    	List<Car> resultat = new ArrayList<>();
    	Map<String,List<Car>> data = new HashMap<>();
    	resultat=CarRepositories.getInstance().findAll();
    	data.put("list",resultat);
    	return new ModelAndView("carList",data);
    }
   
    @RequestMapping(value="car/toRent", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView rent(@RequestParam("car") String car,@RequestParam("cni") String cni, @RequestParam("prenom") String name, @RequestParam("nom") String surname, @RequestParam("phone") String phone,@RequestParam("dateOf") Date dateOf,@RequestParam("dateTo") Date dateTo){
        Session.getSession().sessionDestroy();
    	
    	Integer carId=Integer.parseInt(car);
        
    	Customer client = new Customer(null,Integer.parseInt(cni),surname,name,Integer.parseInt(phone));
        CustomerRepositories.getInstance().addCustomer(client);// Enregistrement du client
        
        Integer lastCustomerId=CustomerRepositories.getInstance().LastId();// récupération de ID du dernier client ajouté
        
        System.out.println(lastCustomerId);
        History newRent = new History(null,dateOf,dateTo,carId,lastCustomerId);
        CustomerRepositories.getInstance().addHistory(newRent);// Enregistrement de la location
        
        CarRepositories.getInstance().rentCar(carId); // Placer la voiture comme loué
        
        
        return new RedirectView("/");
    }
}