package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tabuľka slúži pre uloženie dát, ktoré neskôr slúžia pre zobrazenie časového radu vývoja hodnoty daného produktu. (TS – time series)
 * Tabuľka obsahuje väzbu na ATTR_TAB, pretože predpokladáme možnosť sledovať vývoj časových radov až na úroveň atribútu daného produktu.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PC_T_PRODUCT_CATALOGUE_TS")
public class PcTProductCatalogueTs implements Serializable {

    /**
     * Kompozitny klúč PcTProductCatalogueTsId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "productid", column = @Column(name = "PRODUCTID", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "dateFrom", column = @Column(name = "DATE_FROM", nullable = false, length = 7)),
            @AttributeOverride(name = "attrId", column = @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0))})
    private PcTProductCatalogueTsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTID", nullable = false, insertable = false, updatable = false)
    private PcTProduct pcTProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTR_ID", nullable = false, insertable = false, updatable = false)
    private EnumTProdcatAttr enumTProdcatAttr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID")
    private EnumTCurrency enumTCurrency;

    /**
     * Hodnota
     */
    @Column(name = "VALUE", length = 50)
    private String value;

    /**
     * Dátum platnosti do
     */
    //@Temporal(TemporalType.DATE)
    @Column(name = "DATE_TO", nullable = false, length = 7)
    private LocalDate dateTo; //vygenerovane to bolo povodne s Date, len bol problem koly tomu ze z db chodil LocalDate

}


