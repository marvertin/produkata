package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kompozitny klúč pre entitu EnutTProdcatAttr
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnutTProdcatAttrId implements Serializable {

    /**
     * ID Atribútu
     */
    @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)
    private BigInteger attrId;

    /**
     * ID jazykovej mutácie (FK na LANG.LANG_CODE)
     */
    @Column(name = "LANG_CODE", nullable = false, length = 2)
    private String langCode;

    /**
     * Platnosť od
     */
    @Column(name = "VALID_FROM", nullable = false, length = 7)
    private Date validFrom;

}


