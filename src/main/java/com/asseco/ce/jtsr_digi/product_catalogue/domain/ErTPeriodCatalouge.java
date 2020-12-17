package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ER_T_PERIOD_CATALOUGE")
public class ErTPeriodCatalouge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Kompozitny klúč ErTPeriodCatalougeId
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "periodCode", column = @Column(name = "PERIOD_CODE", nullable = false, precision = 3, scale = 0)),
            @AttributeOverride(name = "text", column = @Column(name = "TEXT", nullable = false, length = 20)),
            @AttributeOverride(name = "sysref", column = @Column(name = "SYSREF", nullable = false, precision = 15, scale = 0))})
    private ErTPeriodCatalougeId id;

}


