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

    public MainController() {
    }

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        User user = userService.getByEmail(principal.getName());
        model.addAttribute("userRoles",roleService.getRoleNames(user.getRoles()));
        model.addAttribute("user",user );
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        User user = userService.getByEmail(principal.getName());
        model.addAttribute("userRoles",roleService.getRoleNames(user.getRoles()));
        model.addAttribute("thisUser",user );
        model.addAttribute("newUser", new User());
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }


    @PutMapping("/admin/add")
    public String addExecute(@ModelAttribute("user") User user) {
        roleService.setExistingRoles(user);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/edit")
    public String editExecute(@ModelAttribute("user") User user) {
        System.out.println(user);
        roleService.setExistingRoles(user);
        System.out.println(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete")
    public String deleteExecute(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
