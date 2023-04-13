package com.demo.springwebflux.util;

import com.demo.springwebflux.dto.ProductDto;
import com.demo.springwebflux.dto.Response;
import com.demo.springwebflux.model.Product;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class AppUtil {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static Response dtoToRsponse(List<ProductDto> productDtos) {
        Response response = new Response();
        response.setProductDto(productDtos);
        return response;
    }
}
