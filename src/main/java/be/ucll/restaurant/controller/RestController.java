package be.ucll.restaurant.controller;


import be.ucll.restaurant.model.DagMenu;
import be.ucll.restaurant.model.WeekMenu;
import be.ucll.restaurant.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private Service service;

    @GetMapping("/weekmenu")
    public List <WeekMenu> getAllDagMenus() {
        return service.weekMenus();
    }

    @PutMapping("/dagmenu/add")
    public Object  addDagMenu(@RequestBody @Valid DagMenu dagMenu, BindingResult bindingResult) {
        List <FieldError> errors = new ArrayList <>();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error);
            }
        } else {
            service.addDagMenu(dagMenu);
        }
        if (!errors.isEmpty()) {
            return new ArrayList<>(errors);
        }
        return service.getAllDagmenus();
    }

    @PutMapping("/dagmenu/change/{datum}")
    public Optional <DagMenu> changeDagMenu(@PathVariable("datum") @Valid String datum, @RequestBody @Valid DagMenu dagMenu) {
        return service.updateDagmenu(datum, dagMenu);
    }
}
