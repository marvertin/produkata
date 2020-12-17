package com.asseco.ce.jtsr_digi.product_catalogue.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Kompozitny klúč pre entitu ErTPeriodCatalouge
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ErTPeriodCatalougeId implements Serializable {

    @Column(name = "PERIOD_CODE", nullable = false, precision = 3, scale = 0)
    private short periodCode;

    @Column(name = "TEXT", nullable = false, length = 20)
    private String text;

    @Column(name = "SYSREF", nullable = false, precision = 15, scale = 0)
    private long sysref;

}


