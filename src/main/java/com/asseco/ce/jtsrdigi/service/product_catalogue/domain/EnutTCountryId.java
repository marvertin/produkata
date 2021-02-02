package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kompozitny klúč pre entitu EnutTCountry
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnutTCountryId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Column(name = "COUNTRY_ID", nullable = false, precision = 22, scale = 0)
    private BigInteger countryId;

    /**
     * kód jazyka (CFG_T_LANG)
     */
    @Column(name = "LANG_CODE", nullable = false, length = 2)
    private String langCode;

    /**
     * dátum platnosti záznamu
     */
    @Column(name = "VALID_FROM", nullable = false, length = 7)
    private Date validFrom;

}


