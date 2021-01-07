package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueId;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PcTProductCatalogueRepository extends PagingAndSortingRepository<PcTProductCatalogue, PcTProductCatalogueId>, QuerydslPredicateExecutor<PcTProductCatalogue> {

    List<PcTProductCatalogue> findByIdProductidAndIdLangCode(BigInteger productid, String langCode);
}