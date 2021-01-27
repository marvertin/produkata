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
 * Číselníková/konfiguračná tabuľka pre kódy externých systémov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CFG_T_EXTERNAL_SYSTEM")
public class CfgTExternalSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * kód externého systému
     */
    @Id
    @Column(name = "EXT_SYSTEM_ID", unique = true, nullable = false, length = 30)
    private String extSystemId;

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

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTExternalSystem")
    private Set enuiTProdcatAttrs = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTExternalSystem")
    private Set enuiTCurrencies = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cfgTExternalSystem")
    private Set enuiTCountries = new HashSet(0);*/

}


