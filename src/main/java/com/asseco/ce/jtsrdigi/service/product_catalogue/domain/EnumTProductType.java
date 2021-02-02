package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Číselník typu produktov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUM_T_PRODUCT_TYPE"
, uniqueConstraints = @UniqueConstraint(columnNames = {"PRODUCT_CODE", "VALID_FROM"})
)
public class EnumTProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Id
    @Column(name = "PRODUCT_TYPE_ID", nullable = false)
    private String productTypeId;

    /**
     * Kód produktu
     */
    @Column(name = "PRODUCT_CODE")
    private String productCode;

    /**
     * Defaultný - Slovenský názov meny
     */
    @Column(name = "TEXT")
    private String text;

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
