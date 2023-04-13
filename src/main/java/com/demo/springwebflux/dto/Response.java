package com.demo.springwebflux.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private List<ProductDto> productDto;
    private String errorMessage;
    private Boolean isSuccess;

}
