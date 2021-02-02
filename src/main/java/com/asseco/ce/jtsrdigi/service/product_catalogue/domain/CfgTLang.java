package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Číselníková/konfiguračná tabuľka pre kódy jazykov (podľa ISO 639).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CFG_T_LANG")
public class CfgTLang implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 2 znakový Kód jazyka podľa ISO 639
     */
    @Id
    @Column(name = "LANG_CODE", unique = true, nullable = false, length = 2)
    private String langCode;

    /**
     * Dlhý názov
     */
    @Column(name = "TEXT", length = 200)
    private String text;

    /**
     * Stredný názov
     */
    @Column(name = "TEXT_MEDIUM", length = 50)
    private String textMedium;

    /**
     * krátky názov
     */
    @Column(name = "TEXT_SHORT", length = 30)
    private String textShort;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTLang")
    private Set enutTCurrencies = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTLang")
    private Set enutTProdcatAttrs = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTLang")
    private Set pcTProductCatalogues = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTLang")
    private Set enutTCountries = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTLang")
    private Set pcTProductCatalogueDocumentses = new HashSet(0);*/

}


