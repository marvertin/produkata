/*
 * Copyright (C) 2020 Asseco Central Europe, a.s. All rights reserved.
 *
 */

package com.asseco.ce.jtsrdigi.service.product_catalogue;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.google.common.collect.Lists;

import com.asseco.ce.jtsrdigi.common.aop.logging.LogExecutionTime;
import com.asseco.ce.jtsrdigi.service.product_catalogue.api.ProductCatalogueApiApiDelegate;
import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueDocuments;
import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueTs;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.GetProductAttributesResponseBodyTypeListOfTechnicalAttributesMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.ListOfDocumentsTypeMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.ListOfProductAttributesDetailTypeMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.ListOfProductAttributesTypeMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.ListOfProductsDetailTypeMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.ListOfProductsTypeMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.ListOfSimpleGraphDataTypeMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.mapper.ListOfValuesMapper;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.CommonResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.CompareProductResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.CompareProductResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetListOfProductCategoriesResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetListOfProductCategoriesResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetListOfProductsInCategoryResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetListOfProductsInCategoryResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductAttributesDetailResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductAttributesDetailResponseBodyTypeTechnicalAttributeDetail;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductAttributesDetailResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductAttributesResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductAttributesResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductDetailResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductDetailResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductDocumentsResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductDocumentsResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductPortfolioAssetStructureResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductPortfolioAssetStructureResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductPortfolioCompositionResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductPortfolioCompositionResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductPortfolioFundPerformanceResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductPortfolioFundPerformanceResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductSimpleGraphResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductSimpleGraphResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.InitiatorSystemType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductAttributesDetailType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductAttributesType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductsDetailType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductsType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfValues;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.PagingRequestType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.PagingResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.SearchProductResponseBodyType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.SearchProductResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.repository.EnumTProdcatAttrRepository;
import com.asseco.ce.jtsrdigi.service.product_catalogue.repository.PcTProductCatalogueDocumentsRepository;
import com.asseco.ce.jtsrdigi.service.product_catalogue.repository.PcTProductCatalogueRepository;
import com.asseco.ce.jtsrdigi.service.product_catalogue.repository.PcTProductCatalogueTsRepository;
import com.asseco.ce.jtsrdigi.service.product_catalogue.repository.PcTProductRepository;

import lombok.extern.slf4j.Slf4j;

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
    @LogExecutionTime
    @Override
    public ResponseEntity<CompareProductResponseType> compareProduct(
            String lang, List<String> listOfProductIds,
            List<String> listOfProductAttrs, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {

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

        return new ResponseEntity<CompareProductResponseType>(compareProductResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getListOfProductCategories(java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetListOfProductCategoriesResponseType> getListOfProductCategories(
            String lang, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {

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

        return new ResponseEntity<GetListOfProductCategoriesResponseType>(getListOfProductCategoriesResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getListOfProductsInCategory(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetListOfProductsInCategoryResponseType> getListOfProductsInCategory(
            String lang, String categoryId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem,
            PagingRequestType paging) {

        int pageSize = paging.getLimit().intValue();
        int pageNo = paging.getOffset().intValue() / pageSize;
        long recordCountTotal = 0;

        Slice<PcTProduct> pcTProducts = pcTProductRepository.findByEntityType(categoryId, PageRequest.of(pageNo, pageSize));

        if (paging.getReturnTotalCount() != 0) {
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
        if (paging.getReturnTotalCount() != 0)
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

        return new ResponseEntity<GetListOfProductsInCategoryResponseType>(getListOfProductsInCategoryResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductAttributes(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductAttributesResponseType> getProductAttributes(
            String lang, String categoryId, Integer comboBoxAttributes,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {

        List<String> enumTProdcatAttrStringList = new ArrayList<String>();
        if (comboBoxAttributes == 0)
            enumTProdcatAttrStringList = enumTProdcatAttrRepository.findAttributesByLangAndCategoryId(lang, categoryId);
        else
            enumTProdcatAttrStringList = enumTProdcatAttrRepository.findComboBoxAttributesByLangAndCategoryId(lang, categoryId);

        GetProductAttributesResponseType getProductAttributesResponseType = new GetProductAttributesResponseType();
        CommonResponseType params = new CommonResponseType();
        GetProductAttributesResponseBodyType data = new GetProductAttributesResponseBodyType();
        data.setLang(lang);
        data.setCategoryId(categoryId);
        data.setListOfTechnicalAttributes(getProductAttributesResponseBodyTypeListOfTechnicalAttributesMapper
                .ListStringToGetProductAttributesResponseBodyTypeListOfTechnicalAttributesList(enumTProdcatAttrStringList));

        getProductAttributesResponseType.setParams(params);
        getProductAttributesResponseType.setData(data);

        return new ResponseEntity<GetProductAttributesResponseType>(getProductAttributesResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductAttributesDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductAttributesDetailResponseType> getProductAttributesDetail(
            String lang, String attrTechnicalName, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {

        /*List<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                .findByEnumTProdcatAttr_AttrNameAndId_LangCode(attrTechnicalName, lang);*/

        List<String> attrValueListDistinct = pcTProductCatalogueRepository.findByAttrNameAndLangCodeDistinct(attrTechnicalName, lang);


        GetProductAttributesDetailResponseType getProductAttributesDetailResponseType = new GetProductAttributesDetailResponseType();
        CommonResponseType params = new CommonResponseType();
        GetProductAttributesDetailResponseBodyType data = new GetProductAttributesDetailResponseBodyType();

        data.setLang(lang);

        GetProductAttributesDetailResponseBodyTypeTechnicalAttributeDetail technicalAttributeDetail = new GetProductAttributesDetailResponseBodyTypeTechnicalAttributeDetail();
        technicalAttributeDetail.setAttrTechnicalName(attrTechnicalName);
        /*technicalAttributeDetail.setAttrDataType(
                GetProductAttributesDetailResponseBodyTypeTechnicalAttributeDetail
                        .AttrDataTypeEnum.fromValue(pcTProductCatalogues.get(0).getEnumTProdcatAttr().getAttrType().toLowerCase()));*/
        technicalAttributeDetail.setListOfValues(listOfValuesMapper.DistinctListOfValuesList(attrValueListDistinct));
            /*technicalAttributeDetail.setListOfValues(listOfValuesMapper.ListOfValuesList(pcTProductCatalogues
                    .stream()
                    .collect(Collectors.groupingBy(p -> p.getCAttrValue()))
                    .values().stream().map(plans -> plans.stream().findFirst().get())
                    .collect(Collectors.toList())));*/

        data.setTechnicalAttributeDetail(technicalAttributeDetail);

        getProductAttributesDetailResponseType.setParams(params);
        getProductAttributesDetailResponseType.setData(data);

        return new ResponseEntity<GetProductAttributesDetailResponseType>(getProductAttributesDetailResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductDetailResponseType> getProductDetail(
            String lang, String productId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {

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

        return new ResponseEntity<GetProductDetailResponseType>(getProductDetailResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductDocuments(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductDocumentsResponseType> getProductDocuments(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {

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

        return new ResponseEntity<GetProductDocumentsResponseType>(getProductDocumentsResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioAssetStructure(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductPortfolioAssetStructureResponseType> getProductPortfolioAssetStructure(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem, PagingRequestType paging) {

        int pageSize = paging.getLimit().intValue();
        int pageNo = paging.getOffset().intValue() / pageSize;
        long recordCountTotal = 0;

        Optional<PcTProduct> pcTProduct = pcTProductRepository.findById(new BigInteger(productId));

        Slice<PcTProductCatalogue> pcTProductCatalogues = pcTProductCatalogueRepository
                .findByLangAndProductidAndDateBetween(lang, new BigInteger(productId), dateFrom, dateTo, PageRequest.of(pageNo, pageSize));

        if (paging.getReturnTotalCount() != 0) {
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
        if (paging.getReturnTotalCount() != 0)
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

        return new ResponseEntity<GetProductPortfolioAssetStructureResponseType>(getProductPortfolioAssetStructureResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioComposition(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductPortfolioCompositionResponseType> getProductPortfolioComposition(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {

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

        return new ResponseEntity<GetProductPortfolioCompositionResponseType>(getProductPortfolioCompositionResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioFundPerformance(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductPortfolioFundPerformanceResponseType> getProductPortfolioFundPerformance(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {

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

        return new ResponseEntity<GetProductPortfolioFundPerformanceResponseType>(getProductPortfolioFundPerformanceResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductSimpleGraph(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<GetProductSimpleGraphResponseType> getProductSimpleGraph(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem, PagingRequestType paging) {

        int pageSize = paging.getLimit().intValue();
        int pageNo = paging.getOffset().intValue() / pageSize;
        long recordCountTotal = 0;

        Slice<PcTProductCatalogueTs> pcTProductCatalogueTs = pcTProductCatalogueTsRepository.findByProductidAndDateBetween(new BigInteger(productId), dateFrom, dateTo, PageRequest.of(pageNo, pageSize));

        GetProductSimpleGraphResponseType getProductSimpleGraphResponseType = new GetProductSimpleGraphResponseType();

        CommonResponseType params = new CommonResponseType();
        PagingResponseType pagingResponseType  = new PagingResponseType();
        pagingResponseType.setHasNextPage(pcTProductCatalogueTs.hasNext() ? 1 : 0);
        pagingResponseType.setHasPreviousPage(pcTProductCatalogueTs.hasPrevious() ? 1 : 0);
        pagingResponseType.setLimit(paging.getLimit());
        pagingResponseType.setOffset(paging.getOffset());
        pagingResponseType.setRecordCount(pcTProductCatalogueTs.getNumberOfElements());
        if (paging.getReturnTotalCount() != 0)
            pagingResponseType.setRecordCountTotal(Integer.valueOf((int)recordCountTotal));
        params.setPaging(pagingResponseType);

        GetProductSimpleGraphResponseBodyType data = new GetProductSimpleGraphResponseBodyType();

        data.setLang(lang);

        if (!pcTProductCatalogueTs.getContent().isEmpty()) {

            PcTProductCatalogueTs pctpct = pcTProductCatalogueTs.getContent().get(0);

            data.setProductId(pctpct.getPcTProduct().getProductBusinessId());
            data.setTechnicalProductId(pctpct.getPcTProduct().getProductTechnicalId());
            data.setIsin(pctpct.getPcTProduct().getIsin());
            data.setCurrency(pctpct.getEnumTCurrency().getCurrencyCode());
            data.setDateFrom(dateFrom);
            data.setDateTo(dateTo);
            data.setListOfGraphData(listOfSimpleGraphDataTypeMapper.ListOfDocumentsTypeList(pcTProductCatalogueTs.getContent()));

        }

        getProductSimpleGraphResponseType.setParams(params);
        getProductSimpleGraphResponseType.setData(data);

        return new ResponseEntity<GetProductSimpleGraphResponseType>(getProductSimpleGraphResponseType, HttpStatus.OK);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#searchProduct(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @LogExecutionTime
    @Override
    public ResponseEntity<SearchProductResponseType> searchProduct(String lang,
            String searchQuery, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {

        SearchProductResponseType searchProductResponseType = new SearchProductResponseType();
        CommonResponseType params = new CommonResponseType();
        SearchProductResponseBodyType data = new SearchProductResponseBodyType();



        searchProductResponseType.setParams(params);
        searchProductResponseType.setData(data);

        return new ResponseEntity<SearchProductResponseType>(searchProductResponseType, HttpStatus.OK);
    }

}