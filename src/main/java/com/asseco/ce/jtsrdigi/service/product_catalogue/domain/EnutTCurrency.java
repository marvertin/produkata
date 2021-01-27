package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Číselník jazykových mutácii popisu mien.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUT_T_CURRENCY")
public class EnutTCurrency implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč EnutTCurrencyId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "currencyId", column = @Column(name = "CURRENCY_ID", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "langCode", column = @Column(name = "LANG_CODE", nullable = false, length = 2)),
            @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false, length = 7))})
    private EnutTCurrencyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LANG_CODE", nullable = false, insertable = false, updatable = false)
    private CfgTLang cfgTLang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID", nullable = false, insertable = false, updatable = false)
    private EnumTCurrency enumTCurrency;

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
     * Krátky názov
     */
    @Column(name = "TEXT_SHORT", length = 30)
    private String textShort;

    /**
     * Platnosť do
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO", length = 7)
    private Date validTo;

}


