package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tabuľka obsahuje spoločné atribúty pre jednotlivé domény produktového katalógu.
 * Očakávame definovanú plnú štruktúru produktových katalógov od JT banky.
 * V prípade, že sa jedná o abstraktný produkt, je to uvedené v atribúte ABSTRACT (yes/no). Produkty môžu mať väzbu na abstraktný produkt, ktorá je uvedená v atribúte PARENT_PRODUCT.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PC_T_PRODUCT")
public class PcTProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID produktu tecchnické 24/7
     */
    @Id
    @Column(name = "PRODUCTID", unique = true, nullable = false, precision = 22, scale = 0)
    private BigInteger productid;

    /**
     * ISIN je základným identifikátorom emisií cenných papierov (CP) a finančných nástrojov, prideľovaný v súlade s medzinárodnou normou ISO 6166 – Cenné papiere a príbuzné finančné nástroje – Systém medzinárodných identifikačných čísiel cenných papierov.
     * <p>
     * ISIN pozostáva z 12-miestneho alfanumerického kódu. Predpona (prefix) je dvojpísmenový kód krajiny (alebo XS v prípade medzinárodných cenných papierov). Základným číslom je deväťmiestny alfanumerický kód.  V prípade ISIN prideleného CDCP určuje prvá číslica v  ISIN za prefixom SK kategóriu (druh) CP/finančného nástroja v zmysle rozdelenia podľa ISO 10962
     */
    @Column(name = "ISIN", length = 12)
    private String isin;

    /**
     * Názov produktu
     */
    @Column(name = "PRODUCT_BUSINESS_NAME", nullable = false, length = 255)
    private String productBusinessName;

    /**
     * Businessové ID produktu
     */
    @Column(name = "PRODUCT_BUSINESS_ID", nullable = false, length = 50)
    private String productBusinessId;

    /**
     * Technické ID produktu
     */
    @Column(name = "PRODUCT_TECHNICAL_ID", nullable = false, length = 50)
    private String productTechnicalId;

    /**
     * Stav
     */
    @Column(name = "PRODUCT_STATE", nullable = false, length = 30)
    private String productState;

    @Column(name = "DESCRIPTION", length = 4000)
    private String description;

    /**
     * Iné zaistenie
     */
    @Column(name = "OTHER_COLLATERAL", length = 128)
    private String otherCollateral;

    /**
     * ID produktu
     */
    @Column(name = "PRODUCTID_EXT", nullable = false, length = 128)
    private String productidExt;

    /**
     * Platnosť od
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_FROM", nullable = false, length = 7)
    private Date validFrom;

    /**
     * Platnosť do
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO", nullable = false, length = 7)
    private Date validTo;

    /**
     * Typ - ENTITY_TYPE
     */
    @Column(name = "ENTITY_TYPE", length = 50)
    private String entityType;

    /**
     * Status (CCP)
     */
    @Column(name = "STATUS_INFO", length = 128)
    private String statusInfo;

    /**
     * Nadradený produkt
     */
    @Column(name = "PARENT_PRODUCT", length = 80)
    private String parentProduct;

    /**
     * Abstraktný
     */
    @Column(name = "IS_ABSTRACT", length = 1)
    private Character isAbstract;

    /**
     * Zdroj dynamických polí
     */
    @Column(name = "DYNAMIC_SOURCE", length = 128)
    private String dynamicSource;

    /**
     * Uzol
     */
    @Column(name = "NODE_TYPE", length = 30)
    private String nodeType;

    @Column(name = "SHORTNAME", length = 128)
    private String shortname;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "pcTProduct")
    private Set pcTProductCatalogueTses = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pcTProduct")
    private Set pcTProductCatalogueDocumentses = new HashSet(0);*/
    
}


