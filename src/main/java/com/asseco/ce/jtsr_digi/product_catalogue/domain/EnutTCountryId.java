package com.asseco.ce.jtsr_digi.product_catalogue.domain;
// Generated Dec 15, 2020, 11:05:00 AM by Hibernate Tools 6.0.0.Alpha2


import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EnutTCountryId generated by hbm2java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnutTCountryId  implements java.io.Serializable {


   

    @Column(name="COUNTRY_ID", nullable=false, precision=22, scale=0)
  private BigInteger countryId;
   

    @Column(name="LANG_CODE", nullable=false, length=2)
  private String langCode;
   

    @Column(name="VALID_FROM", nullable=false, length=7)
  private Date validFrom;


}


