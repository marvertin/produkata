package com.asseco.ce.jtsr_digi.product_catalogue.domain;

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
 * Číselník externých idnetifikátorov štátov.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUI_T_COUNTRY")
public class EnuiTCountry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč EnuiTCountryId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "countryId", column = @Column(name = "COUNTRY_ID", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "extSystemId", column = @Column(name = "EXT_SYSTEM_ID", nullable = false, length = 30)),
            @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false, length = 7))})
    private EnuiTCountryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = false, insertable = false, updatable = false)
    private EnumTCountry enumTCountry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXT_SYSTEM_ID", nullable = false, insertable = false, updatable = false)
    private CfgTExternalSystem cfgTExternalSystem;

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


