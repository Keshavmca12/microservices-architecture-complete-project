package com.marktek.controller;

import com.marktek.entity.Customers;
import com.marktek.model.RegistrationForm;
import com.marktek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<Customers> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Customers create(@RequestBody Customers user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(@RequestBody RegistrationForm form){
        userService.sendOtp(form);
        return "success";
    }

}
