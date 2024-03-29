package com.demo.springwebflux.service.impl;

import com.demo.springwebflux.dto.ProductDto;
import com.demo.springwebflux.dto.Request;
import com.demo.springwebflux.dto.Response;
import com.demo.springwebflux.exception.GenralException;
import com.demo.springwebflux.exception.ProductNotFoundException;
import com.demo.springwebflux.repository.ProductRepository;
import com.demo.springwebflux.service.ProductService;
import com.demo.springwebflux.util.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Mono<Response> getAllProduct() {
        Response response = new Response();
        return productRepository.findAll().switchIfEmpty(Mono.error(new ProductNotFoundException()))
                .map(AppUtil::entityToDto)
                .log()
                .collectList()
//                .map(AppUtil::dtoToRsponse).
                .map(productDtos -> {
                    response.setIsSuccess(true);
                    response.setProductDto(productDtos);
                    return response;
                })
                .onErrorMap(err -> err instanceof ProductNotFoundException ? err : new GenralException(err.getMessage())).log();
    }

    @Override
    public Mono<Response> saveProduct(Mono<Request> request) {
        Response response = new Response();
        return request.map(requestObj -> {
                    ProductDto productDto = requestObj.getProductDto().get(0);
                    productDto.setId(UUID.randomUUID().toString());
                    return productDto;
                }).map(AppUtil::dtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtil::entityToDto)
                .map(productDto -> {
                    response.setProductDto(Arrays.asList(productDto));
                    response.setIsSuccess(true);
                    return response;
                })
                .onErrorMap(err -> err instanceof ProductNotFoundException ? err : new GenralException(err.getMessage())).log();
    }

    @Override
    public Mono<Response> getproduct(String name) {
        return productRepository.findByName(name).switchIfEmpty(Mono.error(new ProductNotFoundException(name)))
                .map(AppUtil::entityToDto)
                .collectList()
                .map(AppUtil::dtoToRsponse)
                .onErrorMap(err -> err instanceof ProductNotFoundException ? err : new GenralException(err.getMessage())).log();
    }

    @Override
    public Mono<Response> deleteProduct(String id) {

        return productRepository.findById(id).switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .flatMap(existingProduct -> productRepository.delete(existingProduct)
                        .then(Mono.just(AppUtil.entityToDto(existingProduct))))
                .map(productDto -> {
                    return AppUtil.dtoToRsponse(Arrays.asList(productDto));
                })
                .onErrorMap(err -> err instanceof ProductNotFoundException ? err : new GenralException(err.getMessage())).log();
    }


    @Override
    public Mono<Response> updateProduct(Mono<Request> request) {
        return request.map(requestObj -> {
                    return requestObj.getProductDto().get(0);
                })
                .map(productDto -> {
                    return productRepository.findById(productDto.getId()).switchIfEmpty(Mono.error(new ProductNotFoundException(productDto.getId())))
                            .flatMap(existingProduct -> {
                                if (null != productDto.getName())
                                    existingProduct.setName(productDto.getName());
                                if (productDto.getPrice() > 0)
                                    existingProduct.setPrice(productDto.getPrice());

                                if (productDto.getQuantity() >= 0) {
                                    existingProduct.setQuantity(productDto.getQuantity());
                                }
                                return productRepository.save(existingProduct);
                            }).map(AppUtil::entityToDto)
                            .map(updatedProductDto -> {
                                return AppUtil.dtoToRsponse(Arrays.asList(updatedProductDto));
                            })
                            .onErrorMap(ex -> {
                                log.error("Exception Occured :" + ex);
                                throw new GenralException(ex.getMessage());
                            });
                }).flatMap(responseMono -> {
                    return responseMono;
                })
                .onErrorMap(err -> err instanceof ProductNotFoundException ? err : new GenralException(err.getMessage())).log();

    }
}
