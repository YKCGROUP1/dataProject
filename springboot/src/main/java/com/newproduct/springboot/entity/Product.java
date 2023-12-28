package com.newproduct.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String product;
    private String sku;
    private String department;
    private String dpid;
    private String yunying;
    private String date;

}
