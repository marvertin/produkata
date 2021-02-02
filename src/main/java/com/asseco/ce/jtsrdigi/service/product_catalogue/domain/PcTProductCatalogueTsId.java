package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kompozitny klúč pre entitu PcTProductCatalogueTs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PcTProductCatalogueTsId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID produktu na ktorý sa vzťahuje údaj o časovom rade
     */
    @Column(name = "PRODUCTID", nullable = false, precision = 22, scale = 0)
    private BigInteger productid;

    /**
     * Dátum platnosti od
     */
    @Column(name = "DATE_FROM", nullable = false)
    private LocalDate dateFrom; //vygenerovane to bolo povodne s Date, len bol problem koly tomu ze z db chodil LocalDate

    /**
     * FK na ATTR_TAB.ATTR_ID
     */
    @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)
    private BigInteger attrId;

}


