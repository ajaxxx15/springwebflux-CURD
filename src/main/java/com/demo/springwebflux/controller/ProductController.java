package com.demo.springwebflux.controller;

import com.demo.springwebflux.dto.ProductDto;
import com.demo.springwebflux.dto.Request;
import com.demo.springwebflux.dto.Response;
import com.demo.springwebflux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getallproduct")
    public Mono<ResponseEntity<Response>> getAllProduct(){
        return productService.getAllProduct()
                .map(obj->{
                    return new ResponseEntity<Response>(obj,HttpStatus.OK);
                });
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<Response>> saveProduct(@RequestBody Mono<Request> request){
        return productService.saveProduct(request)
                .map(obj->{
                    return new ResponseEntity<Response>(obj,HttpStatus.CREATED);
                });
    }

    @GetMapping("/get/{name}")
    public Mono<ResponseEntity<Response>> getProduct(@PathVariable("name") String name){
        return productService.getproduct(name)
                .map(obj->{
                    return new ResponseEntity<Response>(obj,HttpStatus.OK);
                });
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Response>> deleteProduct(@PathVariable("id") String id){
        return productService.deleteProduct(id)
                .map(obj->{
                    return new ResponseEntity<Response>(obj,HttpStatus.OK);
                });
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Response>> updateProduct(@RequestBody Mono<Request> request){
        return productService.updateProduct(request)
                .map(obj->{
                    return new ResponseEntity<Response>(obj,HttpStatus.OK);
                });
    }
}
