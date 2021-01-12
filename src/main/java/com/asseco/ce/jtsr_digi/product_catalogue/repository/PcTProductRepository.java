package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PcTProductRepository extends PagingAndSortingRepository<PcTProduct, BigInteger>, QuerydslPredicateExecutor<PcTProduct> {

    Slice<PcTProduct> findByEntityType(String entityType, Pageable pageabled);

    List<PcTProduct> findByEntityType(String entityType);

    long countByEntityType(String entityType);

    @Query(value = "SELECT DISTINCT ENTITY_TYPE FROM PC_T_PRODUCT",
            countQuery = "SELECT DISTINCT COUNT(ENTITY_TYPE) FROM PC_T_PRODUCT",
            nativeQuery = true)
    List<String> findDistinctEntityType();

}