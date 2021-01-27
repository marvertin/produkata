package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.sql.Blob;
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
 * Tabuľka slúži pre uloženie dát pre:
 * - URL dokumentov	- Kľúčové informácie pre investorov
 * - Prospekt
 * <p>
 * - 	-
 * <p>
 * - Popis fondu
 * - Komentár portfólio manažéra
 * - Jednotlivé popisy produktov (Napr. „Prečo práve dlhopisy“ z detailu produktov – dlhopisy)
 * Obsahuje prepojenie na produktový katalóg, kde je väzba 1:N.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PC_T_PRODUCT_CATALOGUE_DOCUMENTS")
public class PcTProductCatalogueDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč PcTProductCatalogueDocumentsId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "productid", column = @Column(name = "PRODUCTID", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "attrId", column = @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)),
            @AttributeOverride(name = "langCode", column = @Column(name = "LANG_CODE", nullable = false, length = 2)),
            @AttributeOverride(name = "orderValue", column = @Column(name = "ORDER_VALUE", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false))})
    private PcTProductCatalogueDocumentsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LANG_CODE", nullable = false, insertable = false, updatable = false)
    private CfgTLang cfgTLang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTID", nullable = false, insertable = false, updatable = false)
    private PcTProduct pcTProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTR_ID", nullable = false, insertable = false, updatable = false)
    private EnumTProdcatAttr enumTProdcatAttr;

    /**
     * Obsahové dáta pre Prospekt/Kľúčové informácie pre investorov,...
     */
    @Column(name = "DATA")
    private Blob data;

    /**
     * URL
     */
    @Column(name = "URL", length = 2000)
    private String url;

    /**
     * Textový popis
     */
    @Column(name = "DESCRIPTION", length = 160)
    private String description;

    /**
     * Dátum platnosti do
     */
    //@Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO")
    private LocalDate validTo; //vygenerovane to bolo povodne s Date, len bol problem koly tomu ze z db chodil LocalDate

}


