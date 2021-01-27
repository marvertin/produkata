package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.EnumTProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface EnumTProductTypeRepository extends JpaRepository<EnumTProductType, BigInteger> {

    @Query(value = "SELECT emtpt.PRODUCT_TYPE_ID, emtpt.PRODUCT_CODE, " +
            "COALESCE(ettpt.TEXT, emtpt.TEXT) AS TEXT, emtpt.VALID_FROM, emtpt.VALID_TO " +
            "FROM ENUM_T_PRODUCT_TYPE emtpt " +
            "LEFT JOIN ENUT_T_PRODUCT_TYPE ettpt ON (emtpt.PRODUCT_TYPE_ID = ettpt.PRODUCT_TYPE_ID ) " +
            "WHERE ettpt.LANG_CODE = :langCode",
            nativeQuery = true)
    List<EnumTProductType> findAllByLangCode(@Param("langCode") String langCode);

}