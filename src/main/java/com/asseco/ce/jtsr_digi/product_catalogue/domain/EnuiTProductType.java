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
 * Číselník externých identifikátorov pre typy produktov
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "ENUI_T_PRODUCT_TYPE")
public class EnuiTProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primárny kľúč
     */
    @Id
    @Column(name = "PRODUCT_TYPE_ID", nullable = false)
    private String productTypeId;

    /**
     * kód externého systému
     */
    @Id
    @Column(name = "EXT_SYSTEM_ID", nullable = false)
    private String extSystemId;

    /**
     * ID v externom systéme
     */
    @Column(name = "EXT_ID")
    private String extId;

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
