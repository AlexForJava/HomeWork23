package com.gmail.chernii.oleksii.controllers;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundProducerException;
import com.gmail.chernii.oleksii.repository.ProducerRepository;
import com.gmail.chernii.oleksii.repository.ProductRepository;
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
    private final ProducerRepository producerRepository;
    private final ProductRepository productRepository;

    @GetMapping
    public ModelAndView showAll(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("producers", "newProducer", new ProducerDto());
        modelAndView.addObject("producersList", service.findAll());
        return modelAndView;
    }

    @GetMapping("/clear")
    public ModelAndView clear(){
        productRepository.deleteAll();
        ModelAndView modelAndView = new ModelAndView("producers", "newProducer", new ProducerDto());
        modelAndView.addObject("producersList", service.findAll());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("newProducer") ProducerDto producerDto) {
        service.create(producerDto);
        return new ModelAndView("producers", "producersList", service.findAll());
    }

    @PutMapping
    public ModelAndView update(@ModelAttribute("newProducer") ProducerDto producerDto) {
        try {
            service.update(producerDto);
        } catch (NotFoundProducerException e) {
            return new ModelAndView("error", "message", "producer not found");
        }
        return new ModelAndView("producers", "producersList", service.findAll());
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable String id) {
        service.deleteById(Long.valueOf(id));
        ModelAndView modelAndView = new ModelAndView("producers", "newProducer", new ProducerDto());
        modelAndView.addObject("producersList", service.findAll());
        return modelAndView;
    }
}
