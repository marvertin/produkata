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
 * Kompozitny klúč pre entitu EnuiTProdcatAttr
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnuiTProdcatAttrId implements Serializable {

    /**
     * ID Atribútu
     */
    @Column(name = "ATTR_ID", nullable = false, precision = 22, scale = 0)
    private BigInteger attrId;

    /**
     * kód externého systému
     */
    @Column(name = "EXT_SYSTEM_ID", nullable = false, length = 30)
    private String extSystemId;

    /**
     * dátum platnosti záznamu
     */
    @Column(name = "VALID_FROM", nullable = false, length = 7)
    private Date validFrom;

}


