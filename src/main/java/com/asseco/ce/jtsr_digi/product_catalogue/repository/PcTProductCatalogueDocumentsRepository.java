package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueDocuments;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueDocumentsId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PcTProductCatalogueDocumentsRepository extends PagingAndSortingRepository<PcTProductCatalogueDocuments, PcTProductCatalogueDocumentsId>, QuerydslPredicateExecutor<PcTProductCatalogueDocuments> {

    List<PcTProductCatalogueDocuments> findByIdProductidAndIdLangCode(BigInteger productid, String langCode);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE_DOCUMENTS ptpcd WHERE ptpcd.PRODUCTID = :productId AND ptpcd.LANG_CODE = :lang " +
            "AND (ptpcd.VALID_FROM <=:dateTo OR ptpcd.VALID_FROM IS NULL) " +
            "AND (ptpcd.VALID_TO >= :dateFrom OR ptpcd.VALID_TO IS NULL)",
            countQuery = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE_DOCUMENTS ptpcd WHERE ptpcd.PRODUCTID = :productId AND ptpcd.LANG_CODE = :lang " +
                    "AND (ptpcd.VALID_FROM <= :dateTo OR ptpcd.VALID_FROM IS NULL) " +
                    "AND (ptpcd.VALID_TO >= :dateFrom OR ptpcd.VALID_TO IS NULL)",
            nativeQuery = true)
    List<PcTProductCatalogueDocuments> findByIdProductidAndIdLangCodeAndDateBetween(@Param("productId")BigInteger productId, @Param("lang") String lang, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

}