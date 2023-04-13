package com.demo.springwebflux.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Product:" + id +" is not found.");
    }
    public ProductNotFoundException() {
        super("No Product found.");
    }
}