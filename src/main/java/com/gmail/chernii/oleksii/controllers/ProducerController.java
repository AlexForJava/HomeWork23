package com.gmail.chernii.oleksii.controllers;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundProducerException;
import com.gmail.chernii.oleksii.service.producer.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/producer")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerController {
    private final ProducerService service;

    @GetMapping
    public ModelAndView showAll(WebRequest request) {
        return new ModelAndView("producers", "producers", service.findAll());
    }

    @PostMapping
    public ModelAndView create(@RequestBody ProducerDto producerDto) {
        service.create(producerDto);
        return new ModelAndView("producers", "producers", service.findAll());
    }

    @PutMapping
    public ModelAndView update(@RequestBody ProducerDto producerDto) {
        try {
            service.update(producerDto);
        } catch (NotFoundProducerException e) {
            return new ModelAndView("error", "message", "producer not found");
        }
        return new ModelAndView("producers", "producers", service.findAll());
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable String id) {
        service.deleteById(Long.valueOf(id));
        return new ModelAndView("producers", "producers", service.findAll());
    }
}
