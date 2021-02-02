package com.asseco.ce.jtsrdigi.service.product_catalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.EnumTProductType;

public interface EnumTProductTypeRepository extends JpaRepository<EnumTProductType, String>, JpaSpecificationExecutor<EnumTProductType> {

    @Query(value = "SELECT emtpt.PRODUCT_TYPE_ID, emtpt.PRODUCT_CODE, " +
            "COALESCE(ettpt.TEXT, emtpt.TEXT) AS TEXT, emtpt.VALID_FROM, emtpt.VALID_TO " +
            "FROM ENUM_T_PRODUCT_TYPE emtpt " +
            "LEFT JOIN ENUT_T_PRODUCT_TYPE ettpt ON (emtpt.PRODUCT_TYPE_ID = ettpt.PRODUCT_TYPE_ID ) " +
            "WHERE ettpt.LANG_CODE = :langCode",
            nativeQuery = true)
    List<EnumTProductType> findAllByLangCode(@Param("langCode") String langCode);

}