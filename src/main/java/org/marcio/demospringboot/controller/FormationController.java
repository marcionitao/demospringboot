package org.marcio.demospringboot.controller;

import org.marcio.demospringboot.dao.FormationRepository;
import org.marcio.demospringboot.model.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FormationController {


    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    public FormationController(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String list(Map<String, Object> map) {

        map.put("listTheme", formationRepository.findAll());

        return "list";
    }

    @RequestMapping("/new")
    public String form(ModelMap map) {

        Formation formation = new Formation();

        map.addAttribute("formation", formation);
        map.addAttribute("listTheme", formationRepository.findAll());

        return "createForm";
    }

    @RequestMapping(value = "/new.do", method = RequestMethod.POST)
    public String addNewPost(@Valid Formation formation) {

        formationRepository.save(new Formation(formation.getTheme(), formation.getDescription()));

        return "redirect:list";
    }

    @RequestMapping("formation/{id}")
    public String showProduct(@PathVariable Long id, Model model){
        model.addAttribute("showTheme", formationRepository.findOne(id));
        return "showForm";
    }
/*
    @RequestMapping("formation/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "form";
    }
    */
}
