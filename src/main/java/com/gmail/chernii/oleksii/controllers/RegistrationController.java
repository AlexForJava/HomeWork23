package com.gmail.chernii.oleksii.controllers;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.enities.UserEntity;
import com.gmail.chernii.oleksii.exceptions.EmailExistsException;
import com.gmail.chernii.oleksii.service.user.UserServiceRegistration;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/registration")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {
    private final UserServiceRegistration registration;

    @GetMapping
    public ModelAndView showRegistrationForm(WebRequest request, ModelAndView model) {
        UserDto userDto = new UserDto();
        model.setViewName("registration");
        model.addObject("user", userDto);
        return model;
    }

    @PostMapping
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto,
                                            BindingResult result, WebRequest request, Errors errors) {
        UserEntity registered = new UserEntity();
        if (!result.hasErrors()) {
            try {
                registered = registration.registeredNewAccount(accountDto);
            } catch (EmailExistsException e) {
                registered = null;
            }
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        } else {
            return new ModelAndView("success", "user", accountDto);
        }
    }
}
