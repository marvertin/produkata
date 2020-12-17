package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kompozitny klúč pre entitu EnuiTCountry
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnuiTCountryId implements Serializable {

    /**
     * Primárny kľúč
     */
    @Column(name = "COUNTRY_ID", nullable = false, precision = 22, scale = 0)
    private BigInteger countryId;

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


