package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Kompozitny klúč pre entitu EnuiTProductType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnuiTProductTypeId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Column(name = "PRODUCT_TYPE_ID", nullable = false, precision = 22, scale = 0)
    private BigInteger productTypeId;

    /**
     * kód externého systému
     */
    @Column(name = "EXT_SYSTEM_ID", nullable = false, length = 30)
    private String extSystemId;

    /**
     * dátum platnosti záznamu
     */
    @Column(name = "VALID_FROM", nullable = false, length = 7)
    private Date validFrom;

}


