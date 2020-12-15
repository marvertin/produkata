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
 * PcTProduct generated by hbm2java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PC_T_PRODUCT"
)
public class PcTProduct  implements java.io.Serializable {


        @Id 

    
    @Column(name="PRODUCTID", unique=true, nullable=false, precision=22, scale=0)
  private BigInteger productid;
   
    
    @Column(name="ISIN", length=12)
  private String isin;
   
    
    @Column(name="PRODUCT_BUSINESS_NAME", nullable=false, length=255)
  private String productBusinessName;
   
    
    @Column(name="PRODUCT_BUSINESS_ID", nullable=false, length=50)
  private String productBusinessId;
   
    
    @Column(name="PRODUCT_TECHNICAL_ID", nullable=false, length=50)
  private String productTechnicalId;
   
    
    @Column(name="PRODUCT_STATE", nullable=false, length=30)
  private String productState;
   
    
    @Column(name="DESCRIPTION", length=4000)
  private String description;
   
    
    @Column(name="OTHER_COLLATERAL", length=128)
  private String otherCollateral;
   
    
    @Column(name="PRODUCTID_EXT", nullable=false, length=128)
  private String productidExt;
   
    @Temporal(TemporalType.DATE)
    @Column(name="VALID_FROM", nullable=false, length=7)
  private Date validFrom;
   
    @Temporal(TemporalType.DATE)
    @Column(name="VALID_TO", nullable=false, length=7)
  private Date validTo;
   
    
    @Column(name="ENTITY_TYPE", length=50)
  private String entityType;
   
    
    @Column(name="STATUS_INFO", length=128)
  private String statusInfo;
   
    
    @Column(name="PARENT_PRODUCT", length=80)
  private String parentProduct;
   
    
    @Column(name="IS_ABSTRACT", length=1)
  private Character isAbstract;
   
    
    @Column(name="DYNAMIC_SOURCE", length=128)
  private String dynamicSource;
   
    
    @Column(name="NODE_TYPE", length=30)
  private String nodeType;
   
    
    @Column(name="SHORTNAME", length=128)
  private String shortname;
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="pcTProduct")
  private Set pcTProductCatalogueTses = new HashSet(0);
   
@OneToMany(fetch=FetchType.LAZY, mappedBy="pcTProduct")
  private Set pcTProductCatalogueDocumentses = new HashSet(0);


}

