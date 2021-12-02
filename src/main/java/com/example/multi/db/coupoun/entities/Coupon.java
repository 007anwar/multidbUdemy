package com.example.multi.db.coupoun.entities;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private BigDecimal discount;
    @Column(name = "exp_date")
    private String expDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Coupon(String code, BigDecimal discount, String expDate) {
        this.code = code;
        this.discount = discount;
        this.expDate = expDate;
    }
}
