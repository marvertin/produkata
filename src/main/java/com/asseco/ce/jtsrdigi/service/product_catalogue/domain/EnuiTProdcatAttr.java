package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
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
 * Číselník externých idnetifikátorov štátov.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUI_T_PRODCAT_ATTR")
public class EnuiTProdcatAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč EnuiTProdcatAttrId
     */
    @EmbeddedId
    @AttributeOverride(name = "attrId", column = @Column(name = "ATTR_ID", nullable = false, precision = 22, scale = 0))
    @AttributeOverride(name = "extSystemId", column = @Column(name = "EXT_SYSTEM_ID", nullable = false, length = 30))
    @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false, length = 7))
    private EnuiTProdcatAttrId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXT_SYSTEM_ID", nullable = false, insertable = false, updatable = false)
    private CfgTExternalSystem cfgTExternalSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTR_ID", nullable = false, insertable = false, updatable = false)
    private EnumTProdcatAttr enumTProdcatAttr;

    /**
     * ID v externom systéme
     */
    @Column(name = "EXT_ID", length = 50)
    private String extId;

    /**
     * Platnosť do
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO", length = 7)
    private Date validTo;

}


