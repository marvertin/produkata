/*
 * Copyright (C) 2020 Asseco Central Europe, a.s. All rights reserved.
 *
 */

package com.asseco.ce.jtsr_digi.product_catalogue.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.asseco.ce.jtsr_digi.product_catalogue.model.CompareProductResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetListOfProductCategoriesResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetListOfProductsInCategoryResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductAttributesDetailResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductAttributesResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductDetailResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductDocumentsResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductPortfolioAssetStructureResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductPortfolioCompositionResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductPortfolioFundPerformanceResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductSimpleGraphResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.SearchProductResponseType;

/**
 * Service.
 *
 * @author fukna
 *
 */
@Service
public class ProductCatalogueApi implements ProductCatalogueApiApiDelegate {

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#compareProduct(java.lang.String, java.util.List, java.util.List, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<CompareProductResponseType> compareProduct(
            String lang, List<String> listOfProductIds,
            List<String> listOfProductAttrs, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.compareProduct(lang,
                listOfProductIds, listOfProductAttrs, xCorrelationID, xRequestID,
                initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getListOfProductCategories(java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetListOfProductCategoriesResponseType> getListOfProductCategories(
            String lang, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getListOfProductCategories(lang,
                xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getListOfProductsInCategory(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @Override
    public ResponseEntity<GetListOfProductsInCategoryResponseType> getListOfProductsInCategory(
            String lang, String categoryId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem,
            PagingRequestType paging) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getListOfProductsInCategory(lang,
                categoryId, xCorrelationID, xRequestID, initiatorSystem, paging);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductAttributes(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductAttributesResponseType> getProductAttributes(
            String lang, String categoryId, Boolean comboBoxAttributes,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductAttributes(lang,
                categoryId, comboBoxAttributes, xCorrelationID, xRequestID,
                initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductAttributesDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductAttributesDetailResponseType> getProductAttributesDetail(
            String lang, String attrTechnicalName, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductAttributesDetail(lang,
                attrTechnicalName, xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductDetailResponseType> getProductDetail(
            String lang, String productId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductDetail(lang, productId,
                xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductDocuments(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductDocumentsResponseType> getProductDocuments(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductDocuments(lang, productId,
                dateFrom, dateTo, xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioAssetStructure(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @Override
    public ResponseEntity<GetProductPortfolioAssetStructureResponseType> getProductPortfolioAssetStructure(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem, PagingRequestType paging) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductPortfolioAssetStructure(
                lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID,
                initiatorSystem, paging);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioComposition(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductPortfolioCompositionResponseType> getProductPortfolioComposition(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductPortfolioComposition(lang,
                productId, dateFrom, dateTo, xCorrelationID, xRequestID,
                initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductPortfolioFundPerformance(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductPortfolioFundPerformanceResponseType> getProductPortfolioFundPerformance(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductPortfolioFundPerformance(
                lang, productId, dateFrom, dateTo, xCorrelationID, xRequestID,
                initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#getProductSimpleGraph(java.lang.String, java.lang.String, java.time.LocalDate, java.time.LocalDate, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @Override
    public ResponseEntity<GetProductSimpleGraphResponseType> getProductSimpleGraph(
            String lang, String productId, LocalDate dateFrom, LocalDate dateTo,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem, PagingRequestType paging) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.getProductSimpleGraph(lang,
                productId, dateFrom, dateTo, xCorrelationID, xRequestID,
                initiatorSystem, paging);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#searchProduct(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<SearchProductResponseType> searchProduct(String lang,
            String searchQuery, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.searchProduct(lang, searchQuery,
                xCorrelationID, xRequestID, initiatorSystem);
    }

}
