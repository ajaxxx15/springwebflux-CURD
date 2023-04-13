package com.demo.springwebflux.repository;

import com.demo.springwebflux.model.Product;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ProductRepository extends ReactiveCassandraRepository<Product,String> {
    @AllowFiltering
    Flux<Product> findByName(String name);

}
