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
 * Číselník krajín podla ISO 3166.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUM_T_COUNTRY"
        , uniqueConstraints = @UniqueConstraint(columnNames = {"ALFA_CODE", "VALID_FROM"})
)
public class EnumTCountry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Id
    @Column(name = "COUNTRY_ID", unique = true, nullable = false, precision = 22, scale = 0)
    private BigInteger countryId;

    /**
     * Numerický kód štátu
     */
    @Column(name = "COUNTRY_CODE", nullable = false, length = 3)
    private String countryCode;

    /**
     * Alfanumerický kód štátu (3 znaky)
     */
    @Column(name = "ALFA_CODE", length = 3)
    private String alfaCode;

    /**
     * Alfanumerický kód štátu (2 znaky)
     */
    @Column(name = "ALFA2_CODE", length = 2)
    private String alfa2Code;

    /**
     * Defaultný - Slovenský názov štátu
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

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTCountry")
    private Set enutTCountries = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTCountry")
    private Set enuiTCountries = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTCountry")
    private Set exrTExchangeRateses = new HashSet(0);*/

}


