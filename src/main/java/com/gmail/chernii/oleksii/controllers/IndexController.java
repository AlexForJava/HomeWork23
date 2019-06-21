package com.gmail.chernii.oleksii.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping({"/", "/index"})
public class IndexController {

    @GetMapping
    public ModelAndView index(WebRequest request) {
        return new ModelAndView("index", "", null);
    }
}
