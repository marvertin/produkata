package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Tabuľka obsahuje textové popisy pre jednotlivé typy produktov v rôznych jazykových mutáciách
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUT_T_PRODUCT_TYPE")
public class EnutTProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč EnutTProductTypeId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "productTypeId", column = @Column(name = "PRODUCT_TYPE_ID", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "langCode", column = @Column(name = "LANG_CODE", nullable = false, length = 2)),
            @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false, length = 7))})
    private EnutTProductTypeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LANG_CODE", nullable = false, insertable = false, updatable = false)
    private CfgTLang cfgTLang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_TYPE_ID", nullable = false, insertable = false, updatable = false)
    private EnumTProductType enumTProductType;

    /**
     * Dlhý názov
     */
    @Column(name = "TEXT")
    private String TEXT;

    /**
     * Stredný názov
     */
    @Column(name = "TEXT_MEDIUM")
    private String textMedium;

    /**
     * Krátky názov
     */
    @Column(name = "TEXT_SHORT")
    private String textShort;

    /**
     * Platnosť do
     */
    @Column(name = "VALID_TO")
    private Date validTo;

}
