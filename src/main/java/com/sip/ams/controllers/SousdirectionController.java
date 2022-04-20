package com.sip.ams.controllers;

import com.sip.ams.entities.Direction;
import com.sip.ams.entities.Sousdirection;
import com.sip.ams.repositories.DirectionRepository;
import com.sip.ams.repositories.DirectiongRepository;
import com.sip.ams.repositories.SousdirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/sousdirection/")
public class SousdirectionController {


    private final SousdirectionRepository sousdirectionRepository;
    private final DirectionRepository directionRepository;
    private final DirectiongRepository directiongRepository;

    @Autowired
    public SousdirectionController(SousdirectionRepository sousdirectionRepository,DirectionRepository directionRepository, DirectiongRepository directiongRepository) {

        this.sousdirectionRepository = sousdirectionRepository;
        this.directionRepository = directionRepository ;
        this.directiongRepository = directiongRepository;
    }

    @GetMapping("list")
    //@ResponseBody
    public String listSousdirections(Model model) {

        List<Sousdirection> lp = (List<Sousdirection>)sousdirectionRepository.findAll();

        if(lp.size()==0)
            lp = null;
        model.addAttribute("sousdirections", lp);

        return "sousdirection/listSousdirections";

    }

    @GetMapping("add")
    public String showAddSousdirectionForm(Sousdirection sousdirection, Model model) {



        model.addAttribute("directiongs", directiongRepository.findAll());
        model.addAttribute("directions", directionRepository.findAll());
        model.addAttribute("sousdirection", new Sousdirection());

        return "sousdirection/addSousdirection";
    }


    @PostMapping("addSave")

    public String addSousdirection(@Valid Sousdirection sousdirection, BindingResult result,

                                   @RequestParam(name = "directionId", required = true) Long d)

    {

        Direction direction= directionRepository.findById(d).orElseThrow(()-> new IllegalArgumentException
                ("Invalid direction Id:" +d));
        sousdirection.setDirection(direction);


        sousdirectionRepository.save(sousdirection);
        return "redirect:list";

    }


}
