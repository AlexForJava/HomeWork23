package com.gmail.chernii.oleksii.controllers;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.enities.Role;
import com.gmail.chernii.oleksii.exceptions.NotFoundUserException;
import com.gmail.chernii.oleksii.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;


@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {
    private final UserService service;

    @GetMapping
    public ModelAndView showAllUsers(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("users", "userList", service.findAll());
        modelAndView.addObject("newUser", new UserDto());
        modelAndView.addObject("roleSet", new HashSet(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ModelAndView createUser(@ModelAttribute("newUser") UserDto userDto) {
        service.create(userDto);
        ModelAndView modelAndView = new ModelAndView("users", "userList", service.findAll());
        modelAndView.addObject("newUser", new UserDto());
        modelAndView.addObject("roleSet", new HashSet(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ModelAndView updateUser(@ModelAttribute("newUser")  UserDto userDto) {
        try {
            service.update(userDto);
        } catch (NotFoundUserException e) {
            return new ModelAndView("error", "message", "user not found");
        }
        ModelAndView modelAndView = new ModelAndView("users", "userList", service.findAll());
        modelAndView.addObject("newUser", new UserDto());
        modelAndView.addObject("roleSet", new HashSet(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable String id) {
        service.deleteById(Long.valueOf(id));
        ModelAndView modelAndView = new ModelAndView("users", "userList", service.findAll());
        modelAndView.addObject("roleSet", new HashSet(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        modelAndView.addObject("newUser", new UserDto());
        return modelAndView;
    }

}
