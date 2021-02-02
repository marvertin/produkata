package com.asseco.ce.jtsrdigi.service.product_catalogue.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueId;

@Repository
public interface PcTProductCatalogueRepository extends PagingAndSortingRepository<PcTProductCatalogue, PcTProductCatalogueId>, QuerydslPredicateExecutor<PcTProductCatalogue> {

    List<PcTProductCatalogue> findByIdProductidAndIdLangCode(BigInteger productid, String langCode);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa WHERE ptpc.LANG_CODE = :lang AND ptpc.PRODUCTID IN :productIds AND ptpc.ATTR_ID = etpa.ATTR_ID AND etpa.ATTR_NAME IN :productAttrs",
            countQuery = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa WHERE ptpc.LANG_CODE = :lang AND ptpc.PRODUCTID IN :productIds AND ptpc.ATTR_ID = etpa.ATTR_ID AND etpa.ATTR_NAME IN :productAttrs",
            nativeQuery = true)
    List<PcTProductCatalogue> findByLangAndProductIdsAndProductAttrs(@Param("lang") String lang, @Param("productIds") List<BigInteger> productIds, @Param("productAttrs") List<String> productAttrs);

    List<PcTProductCatalogue> findByEnumTProdcatAttr_AttrNameAndId_LangCode(String attrName, String langCode);

    @Query(value = "SELECT DISTINCT ptpc.c_attr_value FROM ENUM_T_PRODCAT_ATTR etpa " +
            "LEFT JOIN PC_T_PRODUCT_CATALOGUE ptpc on (etpa.attr_id=ptpc.attr_id)  " +
            "WHERE etpa.attr_name = :attrName AND ptpc.lang_code = :langCode ",
            nativeQuery = true)
    List<String> findByAttrNameAndLangCodeDistinct(@Param("attrName") String attrName, @Param("langCode") String langCode);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE ptpc WHERE ptpc.PRODUCTID = :productId AND ptpc.LANG_CODE = :lang " +
            "AND (ptpc.VALID_FROM <= :dateTo OR ptpc.VALID_FROM IS NULL) " +
            "AND (ptpc.VALID_TO >= :dateFrom OR ptpc.VALID_TO IS NULL)",
            nativeQuery = true)
    Slice<PcTProductCatalogue> findByLangAndProductidAndDateBetween(@Param("lang") String lang, @Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo, Pageable pageabled);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE ptpc WHERE ptpc.PRODUCTID = :productId AND ptpc.LANG_CODE = :lang " +
            "AND (ptpc.VALID_FROM <= :dateTo OR ptpc.VALID_FROM IS NULL) " +
            "AND (ptpc.VALID_TO >= :dateFrom OR ptpc.VALID_TO IS NULL)",
            nativeQuery = true)
    List<PcTProductCatalogue> findByLangAndProductidAndDateBetween(@Param("lang") String lang, @Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

    @Query(value = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE ptpc WHERE ptpc.PRODUCTID = :productId AND ptpc.LANG_CODE = :lang " +
            "AND (ptpc.VALID_FROM <= :dateTo OR ptpc.VALID_FROM IS NULL) " +
            "AND (ptpc.VALID_TO >= :dateFrom OR ptpc.VALID_TO IS NULL)",
            nativeQuery = true)
    long countByLangAndProductidAndDateBetween(@Param("lang") String lang, @Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}