package CRUD.controller;

import CRUD.model.Role;
import CRUD.model.User;
import CRUD.service.SecurityService;
import CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
@RequestMapping("/")
public class UserController {

    UserService userService;

    SecurityService secService;

    @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }

    @Autowired
    public void setSecurityService(SecurityService secService) {
        this.secService = secService;
    }

    @GetMapping("/admin/adminPage")
    public void getMain(ModelMap modelMap){
        modelMap.addAttribute("userData", userService.getAllUsers());
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user) {
        String password = user.getPassword();
        if(userService.addUser(user, false)) secService.autoLogin(user.getUsername(), password);
        return "redirect:/user";
    }

    @GetMapping("/user")
    public ModelAndView showUserPage(ModelMap modelMap) {
        User user = userService.getByName(secService.findLoggedInUsername());
        System.out.println(user.getUsername());
        modelMap.addAttribute("user", user);
        return new ModelAndView("userPage");
    }

    @GetMapping("/admin/add")
    public ModelAndView addUser() {
        return new ModelAndView("admin/add");
    }

    @PostMapping("/admin/add")
    public String addUser(User user, String role) {
        boolean admin = false;
        if(role != null) admin = true;
        userService.addUser(user, admin);
        return "redirect:/admin/adminPage";
    }

    @GetMapping("/admin/update")
    public String updateUser(ModelMap modelMap, @RequestParam("id")Long id) {
        User user = userService.getById(id);
        if(user != null) {
            Set<Role> rolesSet = user.getRoles();
            boolean admin = false;
            for (Role role : rolesSet) {
                if (role.getRole().equals("ROLE_admin")) {
                    admin = true;
                    break;
                }
            }
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("admin", admin);
            return null;
        } else {
            return "redirect:/admin/adminPage";
        }
    }

    @PostMapping("/admin/update")
    public String updateUser(User user, String role) {
        Boolean admin = null;
        if(role != null) {
            if(role.equals("admin")) admin = true;
            else admin = false;
        }
        userService.updateUser(user, admin);
        return "redirect:/admin/adminPage";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("id")Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/adminPage";
    }

    @GetMapping("/admin/drop")
    public String dropTable() {
        userService.dropTable();
        return "redirect:/admin/adminPage";
    }
}
