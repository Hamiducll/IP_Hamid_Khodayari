package be.ucll.restaurant.controller;

import be.ucll.restaurant.model.Gerecht;
import be.ucll.restaurant.model.GerechtType;
import be.ucll.restaurant.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@org.springframework.stereotype.Controller
public class GerechtController implements WebMvcConfigurer {
    @Autowired
    private Service service;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/gerechten")
    public String gerechten(Model model) {
        model.addAttribute("gerechten", service.getAllGerechts());
        return "gerechten";
    }
    @GetMapping("/gerechten/change")
    public String changeGerechten(Model model) {
        model.addAttribute("gerechten", service.getAllGerechts());
        return "changeGerechten";
    }
    @GetMapping("/gerechten/add")
    public String addGerecht(Model model) {
        model.addAttribute("types", GerechtType.values());
        return "addGerecht";
    }
    @PostMapping("/gerechten/add")
    public String addGerecht(@Valid Gerecht gerecht, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("types", GerechtType.values());
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "addGerecht";
        }
        else {
            service.addGerecht(gerecht);
            return "redirect:/gerechten";
        }
    }

    @GetMapping("/gerechten/delete")
    public String getGerechtById(int id, Model model) {
        Gerecht g = service.findGerechtById(id);
        model.addAttribute("ToBeDeletedGerecht", g);
        return "deleteGerecht";
    }

    @PostMapping("/gerechten/delete")
    public String deleteGerecht( int id, Model model) {
        Gerecht g = service.findGerechtById(id);
        service.deleteGerecht(g);
        model.addAttribute("gerechten", service.getAllGerechts());
        return "gerechten";
    }

    @GetMapping("/gerechten/update")
    public String getUpdatePagina(int id, Model model) {
        Gerecht g = service.findGerechtById(id);
        model.addAttribute("types", GerechtType.values());
        model.addAttribute("gerecht", g);
        return "updateGerecht";
    }

    @PostMapping("/gerechten/update/{id}")
    public String updateGerecht(@PathVariable("id") int gId, @Valid Gerecht gerecht, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("types", GerechtType.values());
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "updateGerecht";
        }
        else {
            service.updateGerecht(gerecht, gId);
            return "redirect:/gerechten";
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Requested ID not found!")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void badIdExceptionHandler(){}
}
