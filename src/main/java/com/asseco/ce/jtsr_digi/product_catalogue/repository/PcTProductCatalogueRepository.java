package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PcTProductCatalogueRepository extends PagingAndSortingRepository<PcTProductCatalogue, PcTProductCatalogueId>, QuerydslPredicateExecutor<PcTProductCatalogue> {

    List<PcTProductCatalogue> findByIdProductidAndIdLangCode(BigInteger productid, String langCode);

    @Query(value = "SELECT * FROM PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa WHERE ptpc.LANG_CODE = :lang AND ptpc.PRODUCTID IN :productIds AND ptpc.ATTR_ID = etpa.ATTR_ID AND etpa.ATTR_NAME IN :productAttrs",
            countQuery = "SELECT COUNT(*) FROM PC_T_PRODUCT_CATALOGUE ptpc, ENUM_T_PRODCAT_ATTR etpa WHERE ptpc.LANG_CODE = :lang AND ptpc.PRODUCTID IN :productIds AND ptpc.ATTR_ID = etpa.ATTR_ID AND etpa.ATTR_NAME IN :productAttrs",
            nativeQuery = true)
    List<PcTProductCatalogue> findByLangAndProductIdsAndProductAttrs(@Param("lang") String lang, @Param("productIds") List<BigInteger> productIds, @Param("productAttrs") List<String> productAttrs);
}