package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Tabuľka obsahuje spoločné atribúty pre jednotlivé domény produktového katalógu:
 * - Banking
 * - Investment
 * - Stock
 * - Combined
 * Dáta sú uložené vo forme KEY => VALUE, teda nie vo forme stĺpcov ale riadkov.
 * Pre plnenie jednotlivých atribútov sa využívajú hodnotové atribúty:
 * - D_ATTR_VALUE – dátumová hodnota
 * - E_ATTR_VALUE – číselníková hodnota
 * - C_ATTR_VALUE – textová hodnota
 * - N_ATTR_VALUE – numerická hodnota
 * V prípade, že je naplnený viac ako 1 hodnotový atribút, je potrebné do C_ATTR_VALUE explicitne uviesť naformátovaný text do atribútu C_ATTR_VALUE.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PC_T_PRODUCT_CATALOGUE")
public class PcTProductCatalogue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč PcTProductCatalogueId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "productid", column = @Column(name = "PRODUCTID", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "attrId", column = @Column(name = "ATTR_ID", nullable = false, precision = 30, scale = 0)),
            @AttributeOverride(name = "langCode", column = @Column(name = "LANG_CODE", nullable = false, length = 2)),
            @AttributeOverride(name = "orderValue", column = @Column(name = "ORDER_VALUE", nullable = false, precision = 22, scale = 0)),
            @AttributeOverride(name = "validFrom", column = @Column(name = "VALID_FROM", nullable = false, length = 7))})
    private PcTProductCatalogueId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LANG_CODE", nullable = false, insertable = false, updatable = false)
    private CfgTLang cfgTLang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTR_ID", nullable = false, insertable = false, updatable = false)
    private EnumTProdcatAttr enumTProdcatAttr;

    /**
     * Dátumová hodnota
     */
    //@Temporal(TemporalType.DATE)
    @Column(name = "D_ATTR_VALUE", length = 7)
    private LocalDate dAttrValue;  //vygenerovane to bolo povodne s Date, len bol problem koly tomu ze z db chodil LocalDate

    /**
     * Číselníková hodnota (číselník/kód hodnoty)
     */
    @Column(name = "E_ATTR_VALUE", length = 80)
    private String eAttrValue;

    /**
     * Textová hodnota
     */
    @Column(name = "C_ATTR_VALUE", length = 2000)
    private String cAttrValue;

    /**
     * Numerická hodnota
     */
    @Column(name = "N_ATTR_VALUE", precision = 22, scale = 0)
    private BigInteger nAttrValue;

    /**
     * Platnosť do
     */
    //@Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO", length = 7)
    private LocalDate validTo; //vygenerovane to bolo povodne s Date, len bol problem koly tomu ze z db chodil LocalDate

}


