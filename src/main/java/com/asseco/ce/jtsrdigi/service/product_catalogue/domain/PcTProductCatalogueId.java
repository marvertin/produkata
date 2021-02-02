package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kompozitny klúč pre entitu PcTProductCatalogue
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class PcTProductCatalogueId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID produktu
     */
    @Column(name = "PRODUCTID", nullable = false, precision = 22, scale = 0)
    private BigInteger productid;

    /**
     * ID atribútu (FK na ATTR_TAB.ATTR_ID)
     */
    @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)
    private BigInteger attrId;

    /**
     * ID jazykovej mutácie (FK na LANG.LANG_CODE)
     */
    @Column(name = "LANG_CODE", nullable = false, length = 2)
    private String langCode;

    /**
     * Poradové číslo hodnoty atribútu
     */
    @Column(name = "ORDER_VALUE", nullable = false, precision = 22, scale = 0)
    private BigInteger orderValue;

    /**
     * Platnosť od
     */
    @Column(name = "VALID_FROM", nullable = false)
    private LocalDate validFrom; //vygenerovane to bolo povodne s Date, len bol problem koly tomu ze z db chodil LocalDate

}


