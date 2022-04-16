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
import org.springframework.web.bind.annotation.RequestParam;

import com.sip.ams.entities.Direction;
import com.sip.ams.entities.Directiong;

import com.sip.ams.repositories.DirectionRepository;
import com.sip.ams.repositories.DirectiongRepository;

@Controller
@RequestMapping("/direction/")
public class DirectionController {
	
	private final DirectionRepository directionRepository;
	private final DirectiongRepository directiongRepository;
	

	@Autowired
	 public DirectionController(DirectionRepository directionRepository, DirectiongRepository directiongRepository) {
        this.directionRepository = directionRepository ;
        this.directiongRepository = directiongRepository;
    }
	
	
	@GetMapping("list")
    //@ResponseBody
    public String listDirections(Model model) {
    	
    	List<Direction> lp = (List<Direction>)directionRepository.findAll();
    	
    	if(lp.size()==0)
    		lp = null;
        model.addAttribute("directions", lp);
        
        return "direction/listDirections";

    }
	
	 @GetMapping("add")
	    public String showAddDirectionForm(Direction direction, Model model) {
	  	    	model.addAttribute("directiongs", directiongRepository.findAll());
	  	    	
	          	model.addAttribute("direction", new Direction());
	          
	        return "direction/addDirection";
	    }
	 
	
		
		 
		

	@PostMapping("addSave")
		
		 public String addDirection(@Valid Direction direction, BindingResult result, 
				 @RequestParam(name = "directiongId", required = true) Long a)
		      
		    		
		    		 {
	//	System.out.println("init methode addDirection ");
		    	
			 Directiong directiong = directiongRepository.findById(a).orElseThrow(()-> new IllegalArgumentException
					 ("Invalid directiong Id:" +a));
			// System.out.println("libille artile" +article.getLabel());
		    	direction.setDirectiong(directiong);
		    	
		    	
		    			   		    		   		    	
		   	directionRepository.save(direction);
	 	 return "redirect:list";
		
		    	 }

    @GetMapping("delete/{id}")
    public String deleteDirection(@PathVariable("id") long id, Model model) {
        Direction direction = directionRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid Direction Id:" + id));
        directionRepository.delete(direction);
        model.addAttribute("directions", directionRepository.findAll());
   	
        return "direction/listDirections";
    }
    
    
    @GetMapping("edit/{id}")
    public String showDirecctionFormToUpdate(@PathVariable("id") long id, Model model) {
    	Direction direction = directionRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid Direction Id:" + id));
    	
        model.addAttribute("direction", direction);
        model.addAttribute("directiongs", directiongRepository.findAll());
        model.addAttribute("idDirectiong", direction.getDirectiong().getId());
      //  System.out.println(" idProvider :" +article.getProvider().getId());
        return "direction/updateDirection";
    }
    @PostMapping("edit")
    public String updateDirection(@Valid Direction direction, BindingResult result, Model model, @RequestParam(name = "directiongId", required = false) Long dg) {
        if (result.hasErrors()) {
            return "direction/updateDirection";
        }
        Directiong directiong = directiongRepository.findById(dg)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Directiong eneral Id:" + dg));
    	direction.setDirectiong(directiong);
    	
        directionRepository.save(direction);
        return "redirect:../list";
      
    }
	

}
