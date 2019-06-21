package com.gmail.chernii.oleksii.controllers;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundUserException;
import com.gmail.chernii.oleksii.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {
    private final UserService service;

    @GetMapping
    public ModelAndView showAllUsers(WebRequest request) {
        return new ModelAndView("users", "userList", service.findAll());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ModelAndView createUser(@RequestBody UserDto userDto) {
        service.create(userDto);
        return new ModelAndView("users", "userList", service.findAll());
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ModelAndView updateUser(@RequestBody UserDto userDto) {
        try {
            service.update(userDto);
        } catch (NotFoundUserException e) {
            return new ModelAndView("error", "message", "user not found");
        }
        return new ModelAndView("users", "userList", service.findAll());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable String id) {
        service.deleteById(Long.valueOf(id));
        return new ModelAndView("users", "userList", service.findAll());
    }

}
