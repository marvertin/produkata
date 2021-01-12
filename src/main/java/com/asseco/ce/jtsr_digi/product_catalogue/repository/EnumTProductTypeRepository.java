package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.EnumTProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnumTProductTypeRepository extends JpaRepository<EnumTProductType, String>, JpaSpecificationExecutor<EnumTProductType> {

}