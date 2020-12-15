package com.asseco.ce.jtsr_digi.product_catalogue.domain;
// Generated Dec 15, 2020, 11:05:00 AM by Hibernate Tools 6.0.0.Alpha2


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CfgTLang generated by hbm2java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CFG_T_LANG"
)
public class CfgTLang  implements java.io.Serializable {


        @Id 

    
    @Column(name="LANG_CODE", unique=true, nullable=false, length=2)
  private String langCode;
   
    
    @Column(name="TEXT", length=200)
  private String text;
   
    
    @Column(name="TEXT_MEDIUM", length=50)
  private String textMedium;
   
    
    @Column(name="TEXT_SHORT", length=30)
  private String textShort;
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="cfgTLang")
  private Set enutTCurrencies = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="cfgTLang")
  private Set enutTProdcatAttrs = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="cfgTLang")
  private Set pcTProductCatalogues = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="cfgTLang")
  private Set enutTCountries = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="cfgTLang")
  private Set pcTProductCatalogueDocumentses = new HashSet(0);


}


