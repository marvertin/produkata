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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tabuľka ktorá obsahuje zoznam všetkých nespoločných atribútov pre jednotlivé domény produktového katalógu.
 * Tabuľka obsahuje väzbu sama na seba (ATTR_ATTR_ID), teda podporuje vytváranie kategórií atribútov.
 * Príklad:
 * - ATTR_ID = 50, ATTR_NAME = VÝKONNOSŤ -> kategória
 * - ATTR_ID = 51, ATTR_NAME = VÝKONNOSŤ_1m, ATTR_ATTR_ID = 50 -> referencia na kategóriu
 * - ATTR_ID = 52, ATTR_NAME = VÝKONNOSŤ_2m, ATTR_ATTR_ID = 50-> referencia na kategóriu
 * - ATTR_ID = 53, ATTR_NAME = VÝKONNOSŤ_3m, ATTR_ATTR_ID = 50-> referencia na kategóriu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUM_T_PRODCAT_ATTR")
public class EnumTProdcatAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID atribútu
     */
    @Id
    @Column(name = "ATTR_ID", unique = true, nullable = false, precision = 30, scale = 0)
    private BigInteger attrId;

    /**
     * Technický názov atribútu
     */
    @Column(name = "ATTR_NAME", nullable = false, length = 80)
    private String attrName;

    /**
     * Početnosť
     */
    @Column(name = "ATTR_MULTIPLICITY", precision = 22, scale = 0)
    private BigInteger attrMultiplicity;

    /**
     * Typ atribútu (STRING, DATE, NUMBER, ENUM_VAL, TIMESER,FILE,TEXT,...)
     */
    @Column(name = "ATTR_TYPE", length = 8)
    private String attrType;

    /**
     * Nadradený atribút
     * FK na ATTR_TAB.ATTR_ID
     */
    @Column(name = "ATTR_ATTR_ID", precision = 30, scale = 0)
    private BigInteger attrAttrId;

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
    @Column(name = "VALID_TO", length = 7)
    private Date validTo;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTProdcatAttr")
    private Set pcTProductCatalogueTses = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTProdcatAttr")
    private Set pcTProductCatalogues = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTProdcatAttr")
    private Set enuiTProdcatAttrs = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTProdcatAttr")
    private Set pcTProductCatalogueDocumentses = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enumTProdcatAttr")
    private Set enutTProdcatAttrs = new HashSet(0);*/

}


