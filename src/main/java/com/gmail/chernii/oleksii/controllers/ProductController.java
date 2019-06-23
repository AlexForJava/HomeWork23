package com.gmail.chernii.oleksii.controllers;

import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundProductException;
import com.gmail.chernii.oleksii.service.producer.ProducerService;
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
    private final ProductService productService;
    private final ProducerService producerService;

    @GetMapping
    public ModelAndView showAll(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("products", "newProduct", new ProductDto());
        modelAndView.addObject("productList", productService.findAll());
        modelAndView.addObject("producerList", producerService.findAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView delete(@PathVariable String id){
        productService.deleteById(Long.valueOf(id));
        ModelAndView modelAndView = new ModelAndView("products", "newProduct", new ProductDto());
        modelAndView.addObject("producerList", producerService.findAll());
        modelAndView.addObject("productList", productService.findAll());
        return modelAndView;
    }


    @PostMapping
    public ModelAndView create(@ModelAttribute("newProduct") ProductDto productDto) {
        productService.create(productDto);
        ModelAndView modelAndView = new ModelAndView("products", "newProduct", new ProductDto());
        modelAndView.addObject("producerList", producerService.findAll());
        modelAndView.addObject("productList", productService.findAll());
        return modelAndView;
    }

    @PutMapping
    public ModelAndView update(@RequestBody ProductDto productDto) {
        try {
            productService.update(productDto);
        } catch (NotFoundProductException e) {
            return new ModelAndView("error", "message", "product not found");
        }
        ModelAndView modelAndView = new ModelAndView("products", "newProduct", new ProductDto());
        modelAndView.addObject("producerList", producerService.findAll());
        modelAndView.addObject("productList", productService.findAll());
        return modelAndView;
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteProduct(@PathVariable String id) {
        productService.deleteById(Long.valueOf(id));
        ModelAndView modelAndView = new ModelAndView("products", "newProduct", new ProductDto());
        modelAndView.addObject("producerList", producerService.findAll());
        modelAndView.addObject("productList", productService.findAll());
        return modelAndView;
    }
}
