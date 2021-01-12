package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.EnumTProdcatAttr;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EnumTProdcatAttrRepository extends PagingAndSortingRepository<EnumTProdcatAttr, BigInteger>, QuerydslPredicateExecutor<EnumTProdcatAttr> {

    @Query(value = "SELECT DISTINCT etpa.ATTR_NAME FROM PC_T_PRODUCT ptp, PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa " +
            "WHERE ptp.ENTITY_TYPE = :categoryId AND ptpc.LANG_CODE = :lang AND ptp.PRODUCTID = ptpc.PRODUCTID AND ptpc.ATTR_ID = etpa.ATTR_ID " +
            "AND etpa.ATTR_MULTIPLICITY > 1 ",
            countQuery = "SELECT DISTINCT COUNT(etpa.ATTR_NAME) FROM PC_T_PRODUCT ptp, PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa " +
                    "WHERE ptp.ENTITY_TYPE = :categoryId AND ptpc.LANG_CODE = :lang AND ptp.PRODUCTID = ptpc.PRODUCTID AND ptpc.ATTR_ID = etpa.ATTR_ID " +
                    "AND etpa.ATTR_MULTIPLICITY > 1",
            nativeQuery = true)
    List<String> findComboBoxAttributesByLangAndCategoryId(@Param("lang") String lang, @Param("categoryId") String categoryId);

    @Query(value = "SELECT DISTINCT etpa.ATTR_NAME FROM PC_T_PRODUCT ptp, PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa " +
            "WHERE ptp.ENTITY_TYPE = :categoryId AND ptpc.LANG_CODE = :lang AND ptp.PRODUCTID = ptpc.PRODUCTID AND ptpc.ATTR_ID = etpa.ATTR_ID",
            countQuery = "SELECT DISTINCT COUNT(etpa.ATTR_NAME) FROM PC_T_PRODUCT ptp, PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa " +
                    "WHERE ptp.ENTITY_TYPE = :categoryId AND ptpc.LANG_CODE = :lang AND ptp.PRODUCTID = ptpc.PRODUCTID AND ptpc.ATTR_ID = etpa.ATTR_ID",
            nativeQuery = true)
    List<String> findAttributesByLangAndCategoryId(@Param("lang") String lang, @Param("categoryId") String categoryId);

}