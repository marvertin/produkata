package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Tabuľka obsahuje textové popisy pre jednotlivé typy produktov v rôznych jazykových mutáciách
 */
@Entity
@Table(name = "ENUT_T_PRODUCT_TYPE")
@Data
@Accessors(chain = true)
public class EnutTProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Id
    @Column(name = "PRODUCT_TYPE_ID", nullable = false)
    private String productTypeId;

    @Id
    @Column(name = "LANG_CODE", nullable = false)
    private String langCode;

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
     * dátum platnosti záznamu
     */
    @Id
    @Column(name = "VALID_FROM", nullable = false)
    private Date validFrom;

    /**
     * Platnosť do
     */
    @Column(name = "VALID_TO")
    private Date validTo;

}
