package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PcTProductCatalogueRepository extends JpaRepository<PcTProductCatalogue, PcTProductCatalogueId>, JpaSpecificationExecutor<PcTProduct> {

    List<PcTProductCatalogue> findByIdProductidAndIdLangCode(BigInteger productid, String langCode);
}