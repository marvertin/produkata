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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EnumTProdcatAttr generated by hbm2java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ENUM_T_PRODCAT_ATTR"
)
public class EnumTProdcatAttr  implements java.io.Serializable {


        @Id 

    
    @Column(name="ATTR_ID", unique=true, nullable=false, precision=30, scale=0)
  private BigInteger attrId;
   
    
    @Column(name="ATTR_NAME", nullable=false, length=80)
  private String attrName;
   
    
    @Column(name="ATTR_MULTIPLICITY", precision=22, scale=0)
  private BigInteger attrMultiplicity;
   
    
    @Column(name="ATTR_TYPE", length=8)
  private String attrType;
   
    
    @Column(name="ATTR_ATTR_ID", precision=30, scale=0)
  private BigInteger attrAttrId;
   
    @Temporal(TemporalType.DATE)
    @Column(name="VALID_FROM", nullable=false, length=7)
  private Date validFrom;
   
    @Temporal(TemporalType.DATE)
    @Column(name="VALID_TO", length=7)
  private Date validTo;
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTProdcatAttr")
  private Set pcTProductCatalogueTses = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTProdcatAttr")
  private Set pcTProductCatalogues = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTProdcatAttr")
  private Set enuiTProdcatAttrs = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTProdcatAttr")
  private Set pcTProductCatalogueDocumentses = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="enumTProdcatAttr")
  private Set enutTProdcatAttrs = new HashSet(0);


}

