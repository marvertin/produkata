package com.asseco.ce.jtsrdigi.service.product_catalogue.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueDocuments;
import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueDocumentsId;

@Repository
public interface PcTProductCatalogueDocumentsRepository extends PagingAndSortingRepository<PcTProductCatalogueDocuments, PcTProductCatalogueDocumentsId>, QuerydslPredicateExecutor<PcTProductCatalogueDocuments> {

    List<PcTProductCatalogueDocuments> findByIdProductidAndIdLangCode(BigInteger productid, String langCode);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE_DOCUMENTS ptpcd WHERE ptpcd.PRODUCTID = :productId AND ptpcd.LANG_CODE = :lang " +
            "AND (TO_DATE(ptpcd.VALID_FROM) <= TO_DATE(:dateTo) OR ptpcd.VALID_FROM IS NULL) " +
            "AND (TO_DATE(ptpcd.VALID_TO) >= TO_DATE(:dateFrom) OR ptpcd.VALID_TO IS NULL)",
            countQuery = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE_DOCUMENTS ptpcd WHERE ptpcd.PRODUCTID = :productId AND ptpcd.LANG_CODE = :lang " +
                    "AND (TO_DATE(ptpcd.VALID_FROM) <= TO_DATE(:dateTo) OR ptpcd.VALID_FROM IS NULL) " +
                    "AND (TO_DATE(ptpcd.VALID_TO) >= TO_DATE(:dateFrom) OR ptpcd.VALID_TO IS NULL)",
            nativeQuery = true)
    List<PcTProductCatalogueDocuments> findByIdProductidAndIdLangCodeAndDateBetween(@Param("productId")BigInteger productId, @Param("lang") String lang, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

}