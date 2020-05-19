package CRUD.controller;

import CRUD.model.Role;
import CRUD.model.User;
import CRUD.service.RoleService;
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

    RoleService roleService;

    SecurityService secService;

    @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setSecurityService(SecurityService secService) {
        this.secService = secService;
    }

    @GetMapping("/admin/adminPage")
    public void getAdminPage(ModelMap modelMap){
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
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getByRole("user"));
        user.setRoles(roleSet);
        if(userService.addUser(user)) secService.autoLogin(user.getUsername(), password);
        return "redirect:/user";
    }

    @GetMapping("/user")
    public ModelAndView getUserPage(ModelMap modelMap) {
        User user = userService.getByName(secService.findLoggedInUsername());
        modelMap.addAttribute("user", user);
        return new ModelAndView("userPage");
    }

    @GetMapping("/admin/add")
    public ModelAndView addUser() {
        return new ModelAndView("admin/add");
    }

    @PostMapping("/admin/add")
    public String addUser(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if(roles != null && roles.length != 0){
            for(String roleName : roles) roleSet.add(roleService.getByRole(roleName));
        } else {
            roleSet.add(roleService.getByRole("user"));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin/adminPage";
    }

    @GetMapping("/admin/update")
    public String updateUser(ModelMap modelMap, @RequestParam("id")Long id) {
        User user = userService.getById(id);
        if(user != null) {
            modelMap.addAttribute("user", user);
            return null;
        } else {
            return "redirect:/admin/adminPage";
        }
    }

    @PostMapping("/admin/update")
    public String updateUser(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if(roles != null && roles.length != 0){
            for(String roleName : roles) roleSet.add(roleService.getByRole(roleName));
            user.setRoles(roleSet);
        }
        userService.updateUser(user);
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
        roleService.dropTable();
        return "redirect:/admin/adminPage";
    }
}
