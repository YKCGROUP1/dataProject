package com.newproduct.springboot.entity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "xwhistory")
public class XwHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer dpid;
    private String department;
    private String name;
    private String xingwei;
    private String originfilename;
    private String time;
}
