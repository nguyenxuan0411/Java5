package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "UserID")
    public Account accounts;
    @Column(name = "CreateDate")
    private Date createdate;
    @Column(name = "Address")
    private String address;
    @Column(name = "TotalAmount")
    private BigDecimal totalamount;


}