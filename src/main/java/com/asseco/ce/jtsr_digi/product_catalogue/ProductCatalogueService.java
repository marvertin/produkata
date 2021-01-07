/*
 * Copyright (C) 2020 Asseco Central Europe, a.s. All rights reserved.
 *
 */

package com.asseco.ce.jtsr_digi.product_catalogue;

import com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.mapper.*;
import com.asseco.ce.jtsr_digi.product_catalogue.model.*;
import com.asseco.ce.jtsr_digi.product_catalogue.repository.PcTProductCatalogueRepository;
import com.asseco.ce.jtsr_digi.product_catalogue.repository.PcTProductRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service.
 *
 * @author fukna
 *
 */
@Service
@Slf4j
public class ProductCatalogueService implements ProductCatalogueApiApiDelegate {

    @Autowired
    private PcTProductCatalogueRepository pcTProductCatalogueRepository;

    @Autowired
    private PcTProductRepository pcTProductRepository;

    @Autowired
    private ListOfProductsDetailTypeMapper listOfProductsDetailTypeMapper;

    @Autowired
    private ListOfProductsTypeMapper listOfProductsTypeMapper;

    @Autowired
    private ListOfProductAttributesDetailTypeMapper listOfProductAttributesDetailTypeMapper;

    @Autowired
    private ListOfProductAttributesTypeMapper listOfProductAttributesTypeMapper;

    @Autowired
    private ListOfValuesMapper listOfValuesMapper;

    @Autowired
    private NativeWebRequest request;

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getRequest()
     */
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#compareProduct(java.lang.String, java.util.List, java.util.List, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<CompareProductResponseType> compareProduct(
            String lang, List<String> listOfProductIds,
            List<String> listOfProductAttrs, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("compareProduct({}, {}, {}, {}, {}, {}) - >", lang, listOfProductIds, listOfProductAttrs, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<CompareProductResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.compareProduct(lang, listOfProductIds, listOfProductAttrs, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("compareProduct() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getListOfProductCategories(java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetListOfProductCategoriesResponseType> getListOfProductCategories(
            String lang, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("getListOfProductCategories({}, {}, {}, {}) - >", lang, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetListOfProductCategoriesResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getListOfProductCategories(lang, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("getListOfProductCategories() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getListOfProductsInCategory(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @Override
    public ResponseEntity<GetListOfProductsInCategoryResponseType> getListOfProductsInCategory(
            String lang, String categoryId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem,
            PagingRequestType paging) {

        if (log.isDebugEnabled()) {
            log.debug("getListOfProductsInCategory({}, {}, {}, {}, {}, {}) - >", lang, categoryId, xCorrelationID, xRequestID, initiatorSystem, paging);
        }

        int pageSize = paging.getLimit().intValue();
        int pageNo = paging.getOffset().intValue()/pageSize;

        Page<PcTProduct> pcTProducts = pcTProductRepository.findByEntityType(categoryId, PageRequest.of(pageNo, pageSize));

        log.info("######## pcTProducts = {}", pcTProducts.getContent());
        /*List<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                .findByIdProductidAndIdLangCode(new BigInteger(productId), lang);*/

        GetListOfProductsInCategoryResponseType getListOfProductsInCategoryResponseType = new GetListOfProductsInCategoryResponseType();

        CommonResponseType params = new CommonResponseType();
        PagingResponseType pagingResponseType  = new PagingResponseType();
        pagingResponseType.setHasNextPage(pcTProducts.getTotalPages()-pageNo-1);
        pagingResponseType.setHasPreviousPage(pageNo);
        pagingResponseType.setLimit(paging.getLimit());
        pagingResponseType.setOffset(paging.getOffset());
        pagingResponseType.setRecordCount(pcTProducts.getNumberOfElements());
        pagingResponseType.setRecordCountTotal(Integer.valueOf((int)pcTProducts.getTotalElements()));
        params.setPaging(pagingResponseType);

        GetListOfProductsInCategoryResponseBodyType data = new GetListOfProductsInCategoryResponseBodyType();
        data.setCategoryId(categoryId);
        data.setLang(lang);

        List<ListOfProductsType> listOfProductsTypeList = listOfProductsTypeMapper
                .ListOfProductsTypeList(pcTProducts.getContent());
        data.setListOfProducts(listOfProductsTypeList);

        getListOfProductsInCategoryResponseType.setParams(params);
        getListOfProductsInCategoryResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getListOfProductsInCategory() - < - return value={}", getListOfProductsInCategoryResponseType);
        }

        return new ResponseEntity<GetListOfProductsInCategoryResponseType>(getListOfProductsInCategoryResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductAttributes(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductAttributesResponseType> getProductAttributes(
            String lang, String categoryId, Boolean comboBoxAttributes,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("getProductAttributes({}, {}, {}, {}, {}, {}) - >", lang, categoryId, comboBoxAttributes, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetProductAttributesResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getProductAttributes(lang, categoryId, comboBoxAttributes, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("getProductAttributes() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductAttributesDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductAttributesDetailResponseType> getProductAttributesDetail(
            String lang, String attrTechnicalName, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("getProductAttributesDetail({}, {}, {}, {}, {}) - >", lang, attrTechnicalName, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetProductAttributesDetailResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getProductAttributesDetail(lang, attrTechnicalName, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("getProductAttributesDetail() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductDetailResponseType> getProductDetail(
            String lang, String productId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {

        if (log.isDebugEnabled()) {
            log.debug("getProductDetail({}, {}, {}, {}, {}) - >", lang, productId, xCorrelationID, xRequestID, initiatorSystem);
        }

        Iterable<PcTProduct> pcTProducts = pcTProductRepository.findAllById(Arrays.asList(new BigInteger(productId)));

        List<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                .findByIdProductidAndIdLangCode(new BigInteger(productId), lang);

        // TODO: namapovat aj params
        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType params = new CommonResponseType();
        GetProductDetailResponseBodyType data = new GetProductDetailResponseBodyType();
        data.setCategoryId(pcTProducts.iterator().next().getEntityType());
        data.setLang(lang);
        List<ListOfProductsDetailType> listOfProductsDetailTypeList = listOfProductsDetailTypeMapper
                .ListOfProductsDetailTypeList(Lists.newArrayList(pcTProducts));

        data.setListOfProducts(listOfProductsDetailTypeList.stream()
                .flatMap(list -> {
                    // Zoznam atributov DISTINCT
                    List<ListOfProductAttributesDetailType> listOfProductAttributesDetailTypes =
                            listOfProductAttributesDetailTypeMapper.ListOfProductsAttributesTypeList(
                                    pcTProductCatalogues
                                            .stream()
                                            .collect(Collectors.groupingBy(p -> p.getId().getAttrId()))
                                            .values().stream().map(plans -> plans.stream().findFirst().get())
                                            .collect(Collectors.toList()));

                    list.setListOfProductAttributes(listOfProductAttributesDetailTypes.stream()
                            .flatMap(listOfProductAttributesDetailType -> {
                                List<ListOfValues> listOfValues = listOfValuesMapper.ListOfValuesList(
                                        pcTProductCatalogues
                                                .stream()
                                                .filter(pcTProductCatalogue -> (pcTProductCatalogue.getEnumTProdcatAttr().getAttrName() == listOfProductAttributesDetailType.getAttrName()))
                                                .collect(Collectors.toList()));
                                listOfProductAttributesDetailType.setListOfValues(listOfValues);

                                return Stream.of(listOfProductAttributesDetailType);
                            }).collect(Collectors.toList()));

                    return Stream.of(list);
                }).collect(Collectors.toList()));

        getProductDetailResponseType.setParams(params);
        getProductDetailResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductDetail() - < - return value={}", getProductDetailResponseType);
        }

        return new ResponseEntity<GetProductDetailResponseType>(getProductDetailResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductDocuments(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductDocumentsResponseType> getProductDocuments(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("getProductDocuments({}, {}, {}, {}, {}, {}, {}) - >", lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetProductDocumentsResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getProductDocuments(lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("getProductDocuments() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioAssetStructure(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @Override
    public ResponseEntity<GetProductPortfolioAssetStructureResponseType> getProductPortfolioAssetStructure(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem, PagingRequestType paging) {
        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioAssetStructure({}, {}, {}, {}, {}, {}, {}, {}) - >", lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem, paging);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetProductPortfolioAssetStructureResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getProductPortfolioAssetStructure(lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem, paging);

        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioAssetStructure() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioComposition(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductPortfolioCompositionResponseType> getProductPortfolioComposition(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioComposition({}, {}, {}, {}, {}, {}, {}) - >", lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetProductPortfolioCompositionResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getProductPortfolioComposition(lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioComposition() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioFundPerformance(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductPortfolioFundPerformanceResponseType> getProductPortfolioFundPerformance(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioFundPerformance({}, {}, {}, {}, {}, {}, {}) - >", lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetProductPortfolioFundPerformanceResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getProductPortfolioFundPerformance(lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioFundPerformance() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductSimpleGraph(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @Override
    public ResponseEntity<GetProductSimpleGraphResponseType> getProductSimpleGraph(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem, PagingRequestType paging) {
        if (log.isDebugEnabled()) {
            log.debug("getProductSimpleGraph({}, {}, {}, {}, {}, {}, {}, {}) - >", lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem, paging);
        }

        // TODO Auto-generated method stub
        ResponseEntity<GetProductSimpleGraphResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.getProductSimpleGraph(lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem, paging);

        if (log.isDebugEnabled()) {
            log.debug("getProductSimpleGraph() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#searchProduct(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<SearchProductResponseType> searchProduct(String lang,
            String searchQuery, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        if (log.isDebugEnabled()) {
            log.debug("searchProduct({}, {}, {}, {}, {}) - >", lang, searchQuery, xCorrelationID, xRequestID, initiatorSystem);
        }

        // TODO Auto-generated method stub
        ResponseEntity<SearchProductResponseType> returnResponseEntity = ProductCatalogueApiApiDelegate.super.searchProduct(lang, searchQuery, xCorrelationID, xRequestID, initiatorSystem);

        if (log.isDebugEnabled()) {
            log.debug("searchProduct() - < - return value={}", returnResponseEntity);
        }
        return returnResponseEntity;
    }

}
