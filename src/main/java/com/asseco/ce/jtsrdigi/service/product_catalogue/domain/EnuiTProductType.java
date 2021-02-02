package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Číselník externých identifikátorov pre typy produktov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUI_T_PRODUCT_TYPE")
public class EnuiTProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč EnuiTProductTypeId
     */
    @EmbeddedId
    @AttributeOverride(name = "productTypeId", column = @Column(name = "PRODUCT_TYPE_ID", nullable = false, precision = 22, scale = 0))
    @AttributeOverride(name = "extSystemId", column = @Column(name = "EXT_SYSTEM_ID", nullable = false, length = 30))
    @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false, length = 7))
    private EnuiTProductTypeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXT_SYSTEM_ID", nullable = false, insertable = false, updatable = false)
    private CfgTExternalSystem cfgTExternalSystem;

    /**
     * ID v externom systéme
     */
    @Column(name = "EXT_ID")
    private String extId;

    /**
     * Platnosť do
     */
    @Column(name = "VALID_TO")
    private Date validTo;

}
