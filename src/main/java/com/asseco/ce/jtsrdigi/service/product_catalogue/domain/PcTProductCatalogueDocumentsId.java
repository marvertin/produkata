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
 * Kompozitny klúč pre entitu PcTProductCatalogueDocuments
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PcTProductCatalogueDocumentsId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * FK na PRODUCT_CATALOGUE.ID
     */
    @Column(name = "PRODUCTID", nullable = false, precision = 22, scale = 0)
    private BigInteger productid;

    /**
     * FK na ATTR_TAB.ATTR_ID
     */
    @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)
    private BigInteger attrId;

    /**
     * FK na LANG.LANG_CODE
     */
    @Column(name = "LANG_CODE", nullable = false, length = 2)
    private String langCode;

    @Column(name = "ORDER_VALUE", nullable = false, precision = 22, scale = 0)
    private BigInteger orderValue;

    /**
     * Dátum platnosti od
     */
    @Column(name = "VALID_FROM", nullable = false, length = 7)
    private LocalDate validFrom; //vygenerovane to bolo povodne s Date, len bol problem koly tomu ze z db chodil LocalDate

}


