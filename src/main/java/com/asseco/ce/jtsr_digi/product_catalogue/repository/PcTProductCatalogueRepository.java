package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PcTProductCatalogueRepository extends PagingAndSortingRepository<PcTProductCatalogue, PcTProductCatalogueId>, QuerydslPredicateExecutor<PcTProductCatalogue> {

    List<PcTProductCatalogue> findByIdProductidAndIdLangCode(BigInteger productid, String langCode);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa WHERE ptpc.LANG_CODE = :lang AND ptpc.PRODUCTID IN :productIds AND ptpc.ATTR_ID = etpa.ATTR_ID AND etpa.ATTR_NAME IN :productAttrs",
            countQuery = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa WHERE ptpc.LANG_CODE = :lang AND ptpc.PRODUCTID IN :productIds AND ptpc.ATTR_ID = etpa.ATTR_ID AND etpa.ATTR_NAME IN :productAttrs",
            nativeQuery = true)
    List<PcTProductCatalogue> findByLangAndProductIdsAndProductAttrs(@Param("lang") String lang, @Param("productIds") List<BigInteger> productIds, @Param("productAttrs") List<String> productAttrs);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE ptpc WHERE ptpc.PRODUCTID = :productId AND ptpc.LANG_CODE = :lang " +
            "AND (TO_DATE(ptpc.VALID_FROM) <= TO_DATE(:dateTo) OR ptpc.VALID_FROM IS NULL) " +
            "AND (TO_DATE(ptpc.VALID_TO) >= TO_DATE(:dateFrom) OR ptpc.VALID_TO IS NULL)",
            countQuery = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE ptpc WHERE ptpc.PRODUCTID = :productId AND ptpc.LANG_CODE = :lang " +
                    "AND (TO_DATE(ptpc.VALID_FROM) <= TO_DATE(:dateTo) OR ptpc.VALID_FROM IS NULL) " +
                    "AND (TO_DATE(ptpc.VALID_TO) >= TO_DATE(:dateFrom) OR ptpc.VALID_TO IS NULL)",
            nativeQuery = true)
    Slice<PcTProductCatalogue> findByLangAndProductidAndDateBetween(@Param("lang") String lang, @Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo, Pageable pageabled);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE ptpc WHERE ptpc.PRODUCTID = :productId AND ptpc.LANG_CODE = :lang " +
            "AND (TO_DATE(ptpc.VALID_FROM) <= TO_DATE(:dateTo) OR ptpc.VALID_FROM IS NULL) " +
            "AND (TO_DATE(ptpc.VALID_TO) >= TO_DATE(:dateFrom) OR ptpc.VALID_TO IS NULL)",
            nativeQuery = true)
    List<PcTProductCatalogue> findByLangAndProductidAndDateBetween(@Param("lang") String lang, @Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

    @Query(value = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE ptpc WHERE ptpc.PRODUCTID = :productId AND ptpc.LANG_CODE = :lang " +
            "AND (TO_DATE(ptpc.VALID_FROM) <= TO_DATE(:dateTo) OR ptpc.VALID_FROM IS NULL) " +
            "AND (TO_DATE(ptpc.VALID_TO) >= TO_DATE(:dateFrom) OR ptpc.VALID_TO IS NULL)",
            nativeQuery = true)
    long countByLangAndProductidAndDateBetween(@Param("lang") String lang, @Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}