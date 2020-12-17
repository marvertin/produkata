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
 * Kompozitny klúč pre entitu PcTProductCatalogueTs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PcTProductCatalogueTsId implements Serializable {

    /**
     * ID produktu na ktorý sa vzťahuje údaj o časovom rade
     */
    @Column(name = "PRODUCTID", nullable = false, precision = 22, scale = 0)
    private BigInteger productid;

    /**
     * Dátum platnosti od
     */
    @Column(name = "DATE_FROM", nullable = false, length = 7)
    private Date dateFrom;

    /**
     * FK na ATTR_TAB.ATTR_ID
     */
    @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)
    private BigInteger attrId;

}


