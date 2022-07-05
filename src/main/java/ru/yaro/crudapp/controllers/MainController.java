package ru.yaro.crudapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yaro.crudapp.models.User;
import ru.yaro.crudapp.service.AppService;

@Controller
public class MainController {
    private AppService appService;
    
    public MainController(){}
    
    @Autowired
    public MainController(AppService appService){
    this.appService = appService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/fill")
    public String fill() {
        appService.fillRoles("ADMIN","USER");
        return "redirect:/";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", appService.getAllUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("listRoles", appService.getAllRoles());
        model.addAttribute("user", new User());
        return "add";
    }

    @PutMapping ("/add")
    public String addExecute(@ModelAttribute("user") User user) {
        appService.setExistingRoles(user);
        appService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("listRoles", appService.getAllRoles());
        model.addAttribute("user", appService.getUserById(id));
        return "edit";
    }

    @PostMapping("/delete")
    public String delete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("listRoles", appService.getAllRoles());
        model.addAttribute("user", appService.getUserById(id));
        return "delete";
    }
    @PatchMapping("/edit")
    public String editExecute(@ModelAttribute("user") User user) {
        appService.setExistingRoles(user);
        appService.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/delete")
    public String deleteExecute(@RequestParam("id") long id) {
        appService.deleteUserById(id);
        return "redirect:/admin";
    }

}
