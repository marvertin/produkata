package com.asseco.ce.jtsr_digi.product_catalogue.domain;
// Generated Dec 15, 2020, 11:05:00 AM by Hibernate Tools 6.0.0.Alpha2


import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EnumTCountry generated by hbm2java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ENUM_T_COUNTRY"
    , uniqueConstraints = @UniqueConstraint(columnNames={"ALFA_CODE", "VALID_FROM"}) 
)
public class EnumTCountry  implements java.io.Serializable {


        @Id 

    
    @Column(name="COUNTRY_ID", unique=true, nullable=false, precision=22, scale=0)
  private BigInteger countryId;
   
    
    @Column(name="COUNTRY_CODE", nullable=false, length=3)
  private String countryCode;
   
    
    @Column(name="ALFA_CODE", length=3)
  private String alfaCode;
   
    
    @Column(name="ALFA2_CODE", length=2)
  private String alfa2Code;
   
    
    @Column(name="TEXT", length=200)
  private String text;
   
    @Temporal(TemporalType.DATE)
    @Column(name="VALID_FROM", length=7)
  private Date validFrom;
   
    @Temporal(TemporalType.DATE)
    @Column(name="VALID_TO", length=7)
  private Date validTo;
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTCountry")
  private Set enutTCountries = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTCountry")
  private Set enuiTCountries = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTCountry")
  private Set exrTExchangeRateses = new HashSet(0);


}

