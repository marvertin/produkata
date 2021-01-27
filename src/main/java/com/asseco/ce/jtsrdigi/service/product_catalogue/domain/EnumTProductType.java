package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Číselník typu produktov
 */
@Entity
@Data
@Table(name = "ENUM_T_PRODUCT_TYPE")
@Accessors(chain = true)
public class EnumTProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Id
    @Column(name = "PRODUCT_TYPE_ID", nullable = false)
    private String productTypeId;

    /**
     * Defaultný - Slovenský názov meny
     */
    @Column(name = "TEXT")
    private String TEXT;

    /**
     * dátum platnosti záznamu
     */
    @Column(name = "VALID_FROM")
    private Date validFrom;

    /**
     * Platnosť do
     */
    @Column(name = "VALID_TO")
    private Date validTo;

}
