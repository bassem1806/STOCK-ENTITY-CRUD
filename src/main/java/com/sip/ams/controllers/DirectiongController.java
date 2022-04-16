package com.sip.ams.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sip.ams.entities.Directiong;
import com.sip.ams.repositories.DirectiongRepository;

@Controller
@RequestMapping("/directiong/")
public class DirectiongController {
	
	private final DirectiongRepository directiongRepository;
	
	  @Autowired
	    public DirectiongController(DirectiongRepository directiongRepository) {
	        this.directiongRepository = directiongRepository;
	        
	    }
	  
	  
	  @GetMapping("list")
	    //@ResponseBody
	    public String listDirectiongs(Model model) {
	    	
	    	List<Directiong> lp = (List<Directiong>)directiongRepository.findAll();
	    	
	    	if(lp.size()==0)
	    		lp = null;
	        model.addAttribute("directiongs", lp);
	        
	        return "directiong/listDirectiongs";

	    }
	  @GetMapping("add")
	    public String showAddDirectiongForm(Model model) {
	    	Directiong directiong = new Directiong();// object dont la valeur des attributs par defaut
	    	model.addAttribute("directiong", directiong);
	        return "directiong/addDirectiong";
	    }
	    
	    @PostMapping("addSave")
	    //@ResponseBody
	    public String addDirectiong(@Valid Directiong directiong, BindingResult result) {
	        if (result.hasErrors()) {
	            return "directiong/addDirectiong";
	        }
	        directiongRepository.save(directiong);
	       System.out.println("direction general :" +directiong); 
	        
	        return "redirect:list";
	    }


	    
	    @GetMapping("delete/{id}")
	    public String deleteDirectiong(@PathVariable("id") long id, Model model) {
	    	
	    	
	    	//long id2 = 100L;
	    	
	        Directiong directiong = directiongRepository.findById(id)
	            .orElseThrow(()-> new IllegalArgumentException("Invalid Direction Général Id:" + id));
	        
	       // System.out.println("suite du programme...");
	        
	        directiongRepository.delete(directiong);
	        
	        /*model.addAttribute("directiongs", directiongRepository.findAll());
	        return "directiong/listDirectiongs";*/
	        return "redirect:../list";
	    }
		  
	    @GetMapping("edit/{id}")
	    public String showDirectiongFormToUpdate(@PathVariable("id") long id, Model model) {
	        Directiong directiong = directiongRepository.findById(id)
	            .orElseThrow(()->new IllegalArgumentException("Invalid Direction Général Id:" + id));
	        
	     //   System.out.println(directiong);
	        model.addAttribute("directiong", directiong);
	        
	        return "directiong/updateDirectiong";
	    }

	    
	    
	    @PostMapping("update")
	    public String updateDirectiong(@Valid Directiong directiong, BindingResult result) {
	    	 if (result.hasErrors()) {
	             return "directiong/updateDirectiong";
	         }
	    	directiongRepository.save(directiong);
	    	return"redirect:list";
	    	
	    }

}
