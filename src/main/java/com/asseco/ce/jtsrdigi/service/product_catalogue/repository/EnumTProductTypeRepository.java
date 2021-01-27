package com.asseco.ce.jtsrdigi.service.product_catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.EnumTProductType;

public interface EnumTProductTypeRepository extends JpaRepository<EnumTProductType, String>, JpaSpecificationExecutor<EnumTProductType> {

}