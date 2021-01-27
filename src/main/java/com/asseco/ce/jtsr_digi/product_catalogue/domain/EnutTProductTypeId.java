package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Kompozitny klúč pre entitu EnutTProductType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnutTProductTypeId implements Serializable {

    /**
     * Primárny kľúč
     */
    @Column(name = "PRODUCT_TYPE_ID", nullable = false, precision = 22, scale = 0)
    private BigInteger productTypeId;

    /**
     * 2 znakový Kód jazyka podľa ISO 639
     */
    @Column(name = "LANG_CODE", nullable = false, length = 2)
    private String langCode;

    /**
     * dátum platnosti záznamu
     */
    @Column(name = "VALID_FROM", nullable = false, length = 7)
    private Date validFrom;

}


