package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tabuľka! kurzový lístok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXR_T_EXCHANGE_RATES"
        , uniqueConstraints = @UniqueConstraint(columnNames = {"DATE_ER", "CURRENCY_ID", "COUNTRY_ID", "SEQUENCE_NUMBER"})
)
public class ExrTExchangeRates implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Id
    @Column(name = "EXRRATES_ID", unique = true, nullable = false, precision = 22, scale = 0)
    private BigInteger exrratesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private EnumTCountry enumTCountry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID", nullable = false)
    private EnumTCurrency enumTCurrency;

    /**
     * Dátum platnosti kurzov
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_ER", nullable = false, length = 7)
    private Date dateEr;

    /**
     * Poradové číslo kurzového lístku vrámci dňa
     */
    @Column(name = "SEQUENCE_NUMBER", nullable = false, precision = 22, scale = 0)
    private BigInteger sequenceNumber;

    /**
     * Počet jednotiek meny, pre ktorý je definovaný kurz
     */
    @Column(name = "NOMINAL", precision = 15, scale = 0)
    private Long nominal;

    /**
     * Kurz pre nákup devíz
     */
    @Column(name = "FCY_BUY", precision = 19, scale = 6)
    private BigDecimal fcyBuy;

    /**
     * Kurz pre predaj devíz
     */
    @Column(name = "FCY_SELL", precision = 19, scale = 6)
    private BigDecimal fcySell;

    /**
     * Stredný kurz devíz
     */
    @Column(name = "FCY_MIDDLE", precision = 19, scale = 6)
    private BigDecimal fcyMiddle;

    /**
     * Kurz pre nákup valuty
     */
    @Column(name = "BANKNOTES_BUY", precision = 19, scale = 6)
    private BigDecimal banknotesBuy;

    /**
     * Kurz pre predaj valuty
     */
    @Column(name = "BANKNOTES_SELL", precision = 19, scale = 6)
    private BigDecimal banknotesSell;

    /**
     * Stredný kurz valuty
     */
    @Column(name = "BANKNOTES_MIDDLE", precision = 19, scale = 6)
    private BigDecimal banknotesMiddle;

}


