package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Číselník mien.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUM_T_CURRENCY"
        , uniqueConstraints = @UniqueConstraint(columnNames = {"ALFA_CODE", "VALID_FROM"})
)
public class EnumTCurrency implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Id
    @Column(name = "CURRENCY_ID", unique = true, nullable = false, precision = 22, scale = 0)
    private BigInteger currencyId;

    /**
     * Numerický kód meny
     */
    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String currencyCode;

    /**
     * Alfanumerický kód meny
     */
    @Column(name = "ALFA_CODE", length = 3)
    private String alfaCode;

    /**
     * Defaultný - Slovenský názov meny
     */
    @Column(name = "TEXT", length = 200)
    private String text;

    /**
     * dátum platnosti záznamu
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_FROM", length = 7)
    private Date validFrom;

    /**
     * Platnosť do
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO", length = 7)
    private Date validTo;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTCurrency")
    private Set enutTCurrencies = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTCurrency")
    private Set exrTExchangeRateses = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTCurrency")
    private Set pcTProductCatalogueTses = new HashSet(0);*/

}


