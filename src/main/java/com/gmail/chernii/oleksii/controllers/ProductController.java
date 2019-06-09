package com.gmail.chernii.oleksii.controllers;

import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundProductException;
import com.gmail.chernii.oleksii.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/product")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ModelAndView showAll(WebRequest request) {
        return new ModelAndView("products", "products", service.findAll());
    }

    @PostMapping
    public ModelAndView create(@RequestBody ProductDto productDto) {
        service.create(productDto);
        return new ModelAndView("products", "products", service.findAll());
    }

    @PutMapping
    public ModelAndView update(@RequestBody ProductDto productDto) {
        try {
            service.update(productDto);
        } catch (NotFoundProductException e) {
            return new ModelAndView("error", "message", "product not found");
        }
        return new ModelAndView("products", "products", service.findAll());
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable String id) {
        service.deleteById(Long.valueOf(id));
        return new ModelAndView("products", "products", service.findAll());
    }
}
