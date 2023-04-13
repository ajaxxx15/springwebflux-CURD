package com.demo.springwebflux.dto;

import com.demo.springwebflux.model.Product;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private List<ProductDto> productDto;
}
