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
 * Kompozitny klúč pre entitu EnutTCurrency
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnutTCurrencyId implements Serializable {

    /**
     * Primárny kľúč
     */
    @Column(name = "CURRENCY_ID", nullable = false, precision = 22, scale = 0)
    private BigInteger currencyId;

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


