package org.marcio.demospringboot.controller;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.vcard.VCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.marcio.demospringboot.dao.FormationRepository;
import org.marcio.demospringboot.model.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

@Controller
public class FormationController {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    public FormationController(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    @RequestMapping("/formation")
    public String home() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/formation/list")
    public String list(Map<String, Object> map) {

        map.put("listTheme", formationRepository.findAll());

        return "list";
    }

    @RequestMapping("/formation/new")
    public String form(ModelMap map) {

        Formation formation = new Formation();

        map.addAttribute("formation", formation);
        map.addAttribute("listTheme", formationRepository.findAll());

        return "createForm";
    }

    @RequestMapping(value = "formation/new.do", method = RequestMethod.POST)
    public String saveProduct(Formation formation){
        formationRepository.save(formation);
        return "redirect:list";
    }

   @RequestMapping("formation/show/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("showTheme", formationRepository.findOne(id));
       System.out.println("Objecto Long: " + formationRepository.findOne(id).getTheme());
        return "showForm";
    }

    @RequestMapping("formation/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("formation", formationRepository.findOne(id));
        return "createForm";
    }

    @RequestMapping("formation/{id}")
    public String delete(@PathVariable Long id){
        formationRepository.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "formation/api/form", method = RequestMethod.GET)
    public
    @ResponseBody
    String listFormationJson(ModelMap model) throws JSONException {
        JSONArray formationArray = new JSONArray();
        for (Formation formation : formationRepository.findAll()) {
            JSONObject formationJSON = new JSONObject();
            formationJSON.put("id", formation.getId());
            formationJSON.put("theme", formation.getTheme());
            formationJSON.put("description", formation.getDescription());
            formationArray.put(formationJSON);
        }
        return formationArray.toString();
    }

    @RequestMapping (value="/formation/qr/{id}", method = RequestMethod.GET)
    public HttpEntity<byte[]> qr(@PathVariable Long id) {

        String toEncode = "nº Theme: "+formationRepository.findOne(id).getId()+
                          "\nTheme: "+formationRepository.findOne(id).getTheme()+
                          "\nDescription: "+formationRepository.findOne(id).getDescription();

        byte[] bytes =  QRCode.from(toEncode).withSize(150, 150).stream().toByteArray();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(bytes.length);

        return new ResponseEntity<byte[]> (bytes, headers, HttpStatus.CREATED);

    }

}
