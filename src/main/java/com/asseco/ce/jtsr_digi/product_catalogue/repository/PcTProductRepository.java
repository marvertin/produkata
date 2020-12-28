package com.asseco.ce.jtsr_digi.product_catalogue.repository;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PcTProductRepository extends PagingAndSortingRepository<PcTProduct, BigInteger> {

}