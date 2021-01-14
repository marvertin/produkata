/*
 * Copyright (C) 2020 Asseco Central Europe, a.s. All rights reserved.
 *
 */

package com.asseco.ce.jtsr_digi.product_catalogue;

import com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueDocuments;
import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueTs;
import com.asseco.ce.jtsr_digi.product_catalogue.mapper.*;
import com.asseco.ce.jtsr_digi.product_catalogue.model.*;
import com.asseco.ce.jtsr_digi.product_catalogue.repository.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
    private EnumTProdcatAttrRepository enumTProdcatAttrRepository;

    @Autowired
    private PcTProductCatalogueDocumentsRepository pcTProductCatalogueDocumentsRepository;

    @Autowired
    private PcTProductCatalogueTsRepository pcTProductCatalogueTsRepository;

    @Autowired
    private ListOfProductsDetailTypeMapper listOfProductsDetailTypeMapper;

    @Autowired
    private ListOfProductsTypeMapper listOfProductsTypeMapper;

    @Autowired
    private ListOfProductAttributesDetailTypeMapper listOfProductAttributesDetailTypeMapper;

    @Autowired
    private ListOfProductAttributesTypeMapper listOfProductAttributesTypeMapper;

    @Autowired
    private GetProductAttributesResponseBodyTypeListOfTechnicalAttributesMapper getProductAttributesResponseBodyTypeListOfTechnicalAttributesMapper;

    @Autowired
    private ListOfValuesMapper listOfValuesMapper;

    @Autowired
    private ListOfDocumentsTypeMapper listOfDocumentsTypeMapper;

    @Autowired
    private ListOfSimpleGraphDataTypeMapper listOfSimpleGraphDataTypeMapper;

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

        List<BigInteger> productIds = listOfProductIds.stream().map(x -> new BigInteger(x)).collect(Collectors.toList());

        Iterable<PcTProduct> pcTProducts = pcTProductRepository.findAllById(productIds);

        CompareProductResponseType compareProductResponseType = new CompareProductResponseType();
        CommonResponseType params = new CommonResponseType();
        CompareProductResponseBodyType data = new CompareProductResponseBodyType();

        data.setLang(lang);

        List<ListOfProductsDetailType> listOfProductsDetailTypeList = listOfProductsDetailTypeMapper
                .ListOfProductsDetailTypeList(Lists.newArrayList(pcTProducts));
        data.setListOfProducts(StreamSupport.stream(pcTProducts.spliterator(), false)
                .flatMap(product -> {

                    List<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                            .findByLangAndProductIdsAndProductAttrs(lang, Arrays.asList(product.getProductid()), listOfProductAttrs);

                    // Zoznam atributov DISTINCT pre konkretny produkt
                    List<ListOfProductAttributesDetailType> listOfProductAttributesDetailTypes =
                            listOfProductAttributesDetailTypeMapper.ListOfProductsAttributesTypeList(
                                    pcTProductCatalogues
                                            .stream()
                                            .collect(Collectors.groupingBy(p -> p.getId().getAttrId()))
                                            .values().stream().map(plans -> plans.stream().findFirst().get())
                                            .collect(Collectors.toList()));

                    ListOfProductsDetailType listOfProductsDetailType = listOfProductsDetailTypeMapper.toDto(product);

                    listOfProductsDetailType.setListOfProductAttributes(listOfProductAttributesDetailTypes.stream()
                            .flatMap(listOfProductAttributesDetailType -> {
                                List<ListOfValues> listOfValues = listOfValuesMapper.ListOfValuesList(
                                        pcTProductCatalogues
                                                .stream()
                                                .filter(pcTProductCatalogue -> (pcTProductCatalogue.getEnumTProdcatAttr().getAttrName() == listOfProductAttributesDetailType.getAttrName()))
                                                .collect(Collectors.toList()));
                                listOfProductAttributesDetailType.setListOfValues(listOfValues);

                                return Stream.of(listOfProductAttributesDetailType);
                            }).collect(Collectors.toList()));

                    return Stream.of(listOfProductsDetailType);
                }).collect(Collectors.toList()));

        compareProductResponseType.setParams(params);
        compareProductResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("compareProduct() - < - return value={}", compareProductResponseType);
        }

        return new ResponseEntity<CompareProductResponseType>(compareProductResponseType, HttpStatus.OK);
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
// TODO: Milan dospecifikuje prepojenie ciselniku s produktom/produktovym katalogom
        GetListOfProductCategoriesResponseType getListOfProductCategoriesResponseType = new GetListOfProductCategoriesResponseType();
        CommonResponseType params = new CommonResponseType();
        GetListOfProductCategoriesResponseBodyType data = new GetListOfProductCategoriesResponseBodyType();
        data.setLang(lang);

        List<String> pcTProducts = pcTProductRepository.findDistinctEntityType();
        log.info("######## pcTProducts = {}", pcTProducts);

        //data.setListOfProductCategories();

        getListOfProductCategoriesResponseType.setParams(params);
        getListOfProductCategoriesResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getListOfProductCategories() - < - return value={}", getListOfProductCategoriesResponseType);
        }

        return new ResponseEntity<GetListOfProductCategoriesResponseType>(getListOfProductCategoriesResponseType, HttpStatus.OK);
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
        long recordCountTotal = 0;

        Slice<PcTProduct> pcTProducts = pcTProductRepository.findByEntityType(categoryId, PageRequest.of(pageNo, pageSize));

        if (paging.getReturnTotalCount()) {
            recordCountTotal = pcTProductRepository.countByEntityType(categoryId);
        }

        GetListOfProductsInCategoryResponseType getListOfProductsInCategoryResponseType = new GetListOfProductsInCategoryResponseType();

        CommonResponseType params = new CommonResponseType();
        PagingResponseType pagingResponseType  = new PagingResponseType();
        pagingResponseType.setHasNextPage(pcTProducts.hasNext() ? 1 : 0);
        pagingResponseType.setHasPreviousPage(pcTProducts.hasPrevious() ? 1 : 0);
        pagingResponseType.setLimit(paging.getLimit());
        pagingResponseType.setOffset(paging.getOffset());
        pagingResponseType.setRecordCount(pcTProducts.getNumberOfElements());
        if (paging.getReturnTotalCount())
            pagingResponseType.setRecordCountTotal(Integer.valueOf((int)recordCountTotal));
        params.setPaging(pagingResponseType);

        GetListOfProductsInCategoryResponseBodyType data = new GetListOfProductsInCategoryResponseBodyType();
        data.setCategoryId(categoryId);
        data.setLang(lang);

        List<ListOfProductsType> listOfProductsTypeList = pcTProducts.getContent().stream()
                .flatMap(product -> {
                    List<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                            .findByIdProductidAndIdLangCode(product.getProductid(), lang);

                    List<ListOfProductAttributesType> listOfProductAttributesType = listOfProductAttributesTypeMapper
                            .ListOfProductsAttributesTypeList(pcTProductCatalogues);

                    ListOfProductsType listOfProductsType = listOfProductsTypeMapper.toDto(product);
                    listOfProductsType.setListOfProductAttributes(listOfProductAttributesType);

                    return Stream.of(listOfProductsType);
                }).collect(Collectors.toList());

        data.setListOfProducts(listOfProductsTypeList);

        getListOfProductsInCategoryResponseType.setParams(params);
        getListOfProductsInCategoryResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getListOfProductsInCategory() - < - return value={}", getListOfProductsInCategoryResponseType);
        }

        return new ResponseEntity<GetListOfProductsInCategoryResponseType>(getListOfProductsInCategoryResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductAttributes(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductAttributesResponseType> getProductAttributes(
            String lang, String categoryId, Integer comboBoxAttributes,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {

        if (log.isDebugEnabled()) {
            log.debug("getProductAttributes({}, {}, {}, {}, {}, {}) - >", lang, categoryId, comboBoxAttributes, xCorrelationID, xRequestID, initiatorSystem);
        }

        List<String> enumTProdcatAttrStringList = new ArrayList<String>();
        if (comboBoxAttributes == 0)
            enumTProdcatAttrStringList = enumTProdcatAttrRepository.findAttributesByLangAndCategoryId(lang, categoryId);
        else
            enumTProdcatAttrStringList = enumTProdcatAttrRepository.findComboBoxAttributesByLangAndCategoryId(lang, categoryId);

        //List<EnumTProdcatAttr> enumTProdcatAttrs = new ArrayList<EnumTProdcatAttr>();

        GetProductAttributesResponseType getProductAttributesResponseType = new GetProductAttributesResponseType();
        CommonResponseType params = new CommonResponseType();
        GetProductAttributesResponseBodyType data = new GetProductAttributesResponseBodyType();
        data.setLang(lang);
        data.setCategoryId(categoryId);
        data.setListOfTechnicalAttributes(getProductAttributesResponseBodyTypeListOfTechnicalAttributesMapper
                .ListStringToGetProductAttributesResponseBodyTypeListOfTechnicalAttributesList(enumTProdcatAttrStringList));

        getProductAttributesResponseType.setParams(params);
        getProductAttributesResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductAttributes() - < - return value={}", getProductAttributesResponseType);
        }

        return new ResponseEntity<GetProductAttributesResponseType>(getProductAttributesResponseType, HttpStatus.OK);
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
// TODO: Ked Milan fixne tak treba dorobit
        GetProductAttributesDetailResponseType getProductAttributesDetailResponseType = new GetProductAttributesDetailResponseType();
        CommonResponseType params = new CommonResponseType();
        GetProductAttributesDetailResponseBodyType data = new GetProductAttributesDetailResponseBodyType();


        getProductAttributesDetailResponseType.setParams(params);
        getProductAttributesDetailResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductAttributesDetail() - < - return value={}", getProductAttributesDetailResponseType);
        }

        return new ResponseEntity<GetProductAttributesDetailResponseType>(getProductAttributesDetailResponseType, HttpStatus.OK);
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

        Optional<PcTProduct> pcTProduct = pcTProductRepository.findById(new BigInteger(productId));

        List<PcTProductCatalogueDocuments> pcTProductCatalogueDocumentsList = pcTProductCatalogueDocumentsRepository
                .findByIdProductidAndIdLangCodeAndDateBetween(new BigInteger(productId), lang, dateFrom, dateTo);

        // TODO: namapovat aj params
        GetProductDocumentsResponseType getProductDocumentsResponseType = new GetProductDocumentsResponseType();
        CommonResponseType params = new CommonResponseType();
        GetProductDocumentsResponseBodyType data = new GetProductDocumentsResponseBodyType();

        data.setLang(lang);

        pcTProduct.ifPresent(pctp -> {

            data.setProductId(pctp.getProductBusinessId());
            data.setTechnicalProductId(pctp.getProductTechnicalId());
            data.setListOfDocuments(listOfDocumentsTypeMapper.ListOfDocumentsTypeList(pcTProductCatalogueDocumentsList));
        });

        getProductDocumentsResponseType.setParams(params);
        getProductDocumentsResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductDocuments() - < - return value={}", getProductDocumentsResponseType);
        }

        return new ResponseEntity<GetProductDocumentsResponseType>(getProductDocumentsResponseType, HttpStatus.OK);
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

        int pageSize = paging.getLimit().intValue();
        int pageNo = paging.getOffset().intValue()/pageSize;
        long recordCountTotal = 0;

        Optional<PcTProduct> pcTProduct = pcTProductRepository.findById(new BigInteger(productId));

        Slice<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                .findByLangAndProductidAndDateBetween(lang, new BigInteger(productId), dateFrom, dateTo, PageRequest.of(pageNo, pageSize));

        if (paging.getReturnTotalCount()) {
            recordCountTotal = pcTProductCatalogueRepository.countByLangAndProductidAndDateBetween(lang, new BigInteger(productId), dateFrom, dateTo);
        }

        GetProductPortfolioAssetStructureResponseType getProductPortfolioAssetStructureResponseType = new GetProductPortfolioAssetStructureResponseType();
        CommonResponseType params = new CommonResponseType();

        PagingResponseType pagingResponseType  = new PagingResponseType();
        pagingResponseType.setHasNextPage(pcTProductCatalogues.hasNext() ? 1 : 0);
        pagingResponseType.setHasPreviousPage(pcTProductCatalogues.hasPrevious() ? 1 : 0);
        pagingResponseType.setLimit(paging.getLimit());
        pagingResponseType.setOffset(paging.getOffset());
        pagingResponseType.setRecordCount(pcTProductCatalogues.getNumberOfElements());
        if (paging.getReturnTotalCount())
            pagingResponseType.setRecordCountTotal(Integer.valueOf((int)recordCountTotal));
        params.setPaging(pagingResponseType);

        GetProductPortfolioAssetStructureResponseBodyType data = new GetProductPortfolioAssetStructureResponseBodyType();

        data.setLang(lang);

        pcTProduct.ifPresent(pctp -> {

            data.setProductId(pctp.getProductBusinessId());
            data.setTechnicalProductId(pctp.getProductTechnicalId());
            //data.setDateOfValidity(); //TODO: doplnit ked sa dospecifikuje
            data.setListOfValues(listOfValuesMapper.ListOfValuesList(pcTProductCatalogues.getContent()));
        });

        getProductPortfolioAssetStructureResponseType.setParams(params);
        getProductPortfolioAssetStructureResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioAssetStructure() - < - return value={}", getProductPortfolioAssetStructureResponseType);
        }

        return new ResponseEntity<GetProductPortfolioAssetStructureResponseType>(getProductPortfolioAssetStructureResponseType, HttpStatus.OK);
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

        Optional<PcTProduct> pcTProduct = pcTProductRepository.findById(new BigInteger(productId));

        List<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                .findByLangAndProductidAndDateBetween(lang, new BigInteger(productId), dateFrom, dateTo);

        GetProductPortfolioCompositionResponseType getProductPortfolioCompositionResponseType = new GetProductPortfolioCompositionResponseType();
        CommonResponseType params = new CommonResponseType();

        GetProductPortfolioCompositionResponseBodyType data = new GetProductPortfolioCompositionResponseBodyType();

        data.setLang(lang);

        pcTProduct.ifPresent(pctp -> {

            data.setProductId(pctp.getProductBusinessId());
            data.setTechnicalProductId(pctp.getProductTechnicalId());
            //data.setDateOfValidity(); //TODO: doplnit ked sa dospecifikuje
            data.setListOfValues(listOfValuesMapper.ListOfValuesList(pcTProductCatalogues));

        });

        getProductPortfolioCompositionResponseType.setParams(params);
        getProductPortfolioCompositionResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioComposition() - < - return value={}", getProductPortfolioCompositionResponseType);
        }

        return new ResponseEntity<GetProductPortfolioCompositionResponseType>(getProductPortfolioCompositionResponseType, HttpStatus.OK);
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

        Optional<PcTProduct> pcTProduct = pcTProductRepository.findById(new BigInteger(productId));

        List<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                .findByLangAndProductidAndDateBetween(lang, new BigInteger(productId), dateFrom, dateTo);

        GetProductPortfolioFundPerformanceResponseType getProductPortfolioFundPerformanceResponseType = new GetProductPortfolioFundPerformanceResponseType();
        CommonResponseType params = new CommonResponseType();

        GetProductPortfolioFundPerformanceResponseBodyType data = new GetProductPortfolioFundPerformanceResponseBodyType();

        data.setLang(lang);

        pcTProduct.ifPresent(pctp -> {

            data.setProductId(pctp.getProductBusinessId());
            data.setTechnicalProductId(pctp.getProductTechnicalId());
            //data.setDateOfValidity(); //TODO: doplnit ked sa dospecifikuje
            data.setListOfProductAttributes(
                    listOfProductAttributesDetailTypeMapper.ListOfProductsAttributesTypeList(pcTProductCatalogues)
                            .stream()
                            .flatMap(listOfProductAttributesDetailType -> {

                                List<ListOfValues> listOfValues = listOfValuesMapper.ListOfValuesList(
                                        pcTProductCatalogues
                                                .stream()
                                                .filter(pcTProductCatalogue -> (pcTProductCatalogue.getEnumTProdcatAttr().getAttrName() == listOfProductAttributesDetailType.getAttrName()))
                                                .collect(Collectors.toList()));
                                listOfProductAttributesDetailType.setListOfValues(listOfValues);

                                return Stream.of(listOfProductAttributesDetailType);
                            }).collect(Collectors.toList()));

        });

        getProductPortfolioFundPerformanceResponseType.setParams(params);
        getProductPortfolioFundPerformanceResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductPortfolioFundPerformance() - < - return value={}", getProductPortfolioFundPerformanceResponseType);
        }

        return new ResponseEntity<GetProductPortfolioFundPerformanceResponseType>(getProductPortfolioFundPerformanceResponseType, HttpStatus.OK);
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

        List<PcTProductCatalogueTs> pcTProductCatalogueTs = pcTProductCatalogueTsRepository.findByProductidAndDateBetween(new BigInteger(productId), dateFrom, dateTo);

        GetProductSimpleGraphResponseType getProductSimpleGraphResponseType = new GetProductSimpleGraphResponseType();

        CommonResponseType params = new CommonResponseType();
        GetProductSimpleGraphResponseBodyType data = new GetProductSimpleGraphResponseBodyType();

        data.setLang(lang);

        if (pcTProductCatalogueTs.size() > 0) {

            PcTProductCatalogueTs pctpct = pcTProductCatalogueTs.get(0);

            data.setProductId(pctpct.getPcTProduct().getProductBusinessId());
            data.setTechnicalProductId(pctpct.getPcTProduct().getProductTechnicalId());
            data.setIsin(pctpct.getPcTProduct().getIsin());
            data.setCurrency(pctpct.getEnumTCurrency().getCurrencyCode());
            data.setDateFrom(dateFrom);
            data.setDateTo(dateTo);
            data.setListOfGraphData(listOfSimpleGraphDataTypeMapper.ListOfDocumentsTypeList(pcTProductCatalogueTs));

        }

        getProductSimpleGraphResponseType.setParams(params);
        getProductSimpleGraphResponseType.setData(data);

        if (log.isDebugEnabled()) {
            log.debug("getProductSimpleGraph() - < - return value={}", getProductSimpleGraphResponseType);
        }

        return new ResponseEntity<GetProductSimpleGraphResponseType>(getProductSimpleGraphResponseType, HttpStatus.OK);
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
