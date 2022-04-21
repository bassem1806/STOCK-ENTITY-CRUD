package com.sip.ams.controllers;

import com.sip.ams.entities.Direction;
import com.sip.ams.entities.Directiong;
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
import org.springframework.web.bind.annotation.PathVariable;
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

                                   @RequestParam(name = "directionId", required = true) Long d,
                                   @RequestParam(name = "directiongId", required = true) Long a)
    {

        Direction direction= directionRepository.findById(d).orElseThrow(()-> new IllegalArgumentException
                ("Invalid direction Id:" +d));
        sousdirection.setDirection(direction);

        Directiong directiong = directiongRepository.findById(a).orElseThrow(()-> new IllegalArgumentException
                ("Invalid directiong Id:" +a));
        // System.out.println("libille artile" +article.getLabel());
        sousdirection.setDirectiong(directiong);
        System.out.println( "dg :" + a);

        sousdirectionRepository.save(sousdirection);
        return "redirect:list";

    }


    @GetMapping("delete/{id}")
    public String deleteSousdirection(@PathVariable("id") long id, Model model) {
        Sousdirection sousdirection = sousdirectionRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid sousdirection Id:" + id));
        sousdirectionRepository.delete(sousdirection);
        model.addAttribute("sousdirection", sousdirectionRepository.findAll());
        return "redirect:../list";

    }
    @GetMapping("edit/{id}")
    public String showsousdirecctionFormToUpdate(@PathVariable("id") long id, Model model) {
        Sousdirection sousdirection = sousdirectionRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Sousdirection Id:" + id));

        model.addAttribute("sousdirection", sousdirection);

        model.addAttribute("directions", directionRepository.findAll());
        model.addAttribute("idDirection", sousdirection.getDirection().getId());

        model.addAttribute("directiongs", directiongRepository.findAll());
        model.addAttribute("idDirectiong", sousdirection.getDirectiong().getId());


    return "sousdirection/updateSousdirection";
    }

    @PostMapping("edit")
    public String updateSousdirection(@Valid Sousdirection sousdirection, BindingResult result, Model model, @RequestParam(name = "directiongId", required = false) Long d,  @RequestParam(name = "directionId", required = true) Long a) {
        if (result.hasErrors()) {
            return "sousdirection/updatesousdirection";
        }



        Directiong directiong = directiongRepository.findById(d)

                .orElseThrow(()-> new IllegalArgumentException("Invalid Directiong General Id:" + d));

        sousdirection.setDirectiong(directiong);




        Direction direction = directionRepository.findById(a)
        .orElseThrow(()-> new IllegalArgumentException("Invalid Direction Id:" + a));


        sousdirection.setDirection(direction);



     sousdirectionRepository.save(sousdirection);
        return "redirect:../list";

    }



}
