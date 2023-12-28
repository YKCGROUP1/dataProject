package com.newproduct.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "datereport")
public class DateReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String settlementid;
    private String type;
    private String orderid;
    private String sku;
    private String description;
    private String quantity;
    private String marketplace;
    private String accounttype;
    private String fulfillment;
    private String ordercity;
    private String orderstate;
    private String orderpostal;
    private String taxcollectionmodel;
    private String productsales;
    private String productsalestax;
    private String shippingcredits;
    private String shippingcreditstax;
    private String giftwrapcredits;
    private String giftwrapcreditstax;
    private String regulatoryfee;
    private String taxonregulatoryfee;
    private String promotionalrebates;
    private String promotionalrebatestax;
    private String marketplacewithheldtax;
    private String sellingfees;
    private String fbafees;
    private String othertransactionfees;
    private String other;
    private String total;
}
