package com.demo.springwebflux.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("products")
@ToString
public class Product {
    @Id
    @Column("id")
    private String id;
    private String name;
    private int quantity;
    private double price;
}
