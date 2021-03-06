package com.asseco.ce.jtsrdigi.service.product_catalogue.repository;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueTs;
import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueTsId;

@Repository
public interface PcTProductCatalogueTsRepository extends PagingAndSortingRepository<PcTProductCatalogueTs, PcTProductCatalogueTsId>, QuerydslPredicateExecutor<PcTProductCatalogueTs> {

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE_TS ptpct WHERE ptpct.PRODUCTID = :productId " +
            "AND (ptpct.DATE_FROM <= :dateTo OR ptpct.DATE_FROM IS NULL) " +
            "AND (ptpct.DATE_TO >= :dateFrom OR ptpct.DATE_TO IS NULL)",
            nativeQuery = true)
    Slice<PcTProductCatalogueTs> findByProductidAndDateBetween(@Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo, Pageable pageabled);

    @Query(value = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE_TS ptpct WHERE ptpct.PRODUCTID = :productId " +
            "AND (ptpct.DATE_FROM <= :dateTo OR ptpct.DATE_FROM IS NULL) " +
            "AND (ptpct.DATE_TO >= :dateFrom OR ptpct.DATE_TO IS NULL)",
            nativeQuery = true)
    long countByLangAndProductidAndDateBetween(@Param("productId")BigInteger productId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

}