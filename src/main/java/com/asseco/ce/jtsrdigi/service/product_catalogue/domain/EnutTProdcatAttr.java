package com.asseco.ce.jtsrdigi.service.product_catalogue.domain;

import java.io.Serializable;
import java.sql.Clob;
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
 * Tabuľka obsahuje textové popisy pre jednotlivé atribúty v rôznych jazykových mutáciách.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENUT_T_PRODCAT_ATTR")
public class EnutTProdcatAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč EnutTProdcatAttrId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "attrId", column = @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)),
            @AttributeOverride(name = "langCode", column = @Column(name = "LANG_CODE", nullable = false, length = 2)),
            @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false, length = 7))})
    private EnutTProdcatAttrId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LANG_CODE", nullable = false, insertable = false, updatable = false)
    private CfgTLang cfgTLang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTR_ID", nullable = false, insertable = false, updatable = false)
    private EnumTProdcatAttr enumTProdcatAttr;

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
     * krátky názov
     */
    @Column(name = "TEXT_SHORT", length = 30)
    private String textShort;

    /**
     * Hodnota prekladu v danej jazykovej mutácii
     */
    @Column(name = "DESCRIPTION")
    private Clob description;

    /**
     * Platnosť do
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO", length = 7)
    private Date validTo;

}


