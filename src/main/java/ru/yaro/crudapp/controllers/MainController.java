package ru.yaro.crudapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yaro.crudapp.models.User;
import ru.yaro.crudapp.service.RoleService;
import ru.yaro.crudapp.service.UserService;

import java.security.Principal;

@Controller
public class MainController {
    private UserService userService;
    private RoleService roleService;
    
    public MainController(){}
    
    @Autowired
    public MainController(UserService userService, RoleService roleService){
    this.userService = userService;
    this.roleService=roleService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/admin/fill")
    public String fill() {
        roleService.fillRoles("ADMIN","USER");
        return "redirect:/";
    }

    @GetMapping("/user")
    public String user(Model model,Principal principal) {
        model.addAttribute("user", userService.getByEmail(principal.getName()));
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String add(Model model) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "add";
    }

    @PutMapping ("/admin/add")
    public String addExecute(@ModelAttribute("user") User user) {
        roleService.setExistingRoles(user);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String edit(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/admin/delete")
    public String delete(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "delete";
    }
    @PatchMapping("/admin/edit")
    public String editExecute(@ModelAttribute("user") User user) {
        roleService.setExistingRoles(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/admin/delete")
    public String deleteExecute(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
