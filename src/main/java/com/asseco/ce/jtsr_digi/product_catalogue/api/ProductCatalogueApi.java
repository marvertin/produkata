/*
 * Copyright (C) 2020 Asseco Central Europe, a.s. All rights reserved.
 *
 */

package com.asseco.ce.jtsr_digi.product_catalogue.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.asseco.ce.jtsr_digi.product_catalogue.model.CompareProductResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetListOfProductCategoriesResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetListOfProductsInCategoryResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductAttributesDetailResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductAttributesResponseType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductDetailResponseType;
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
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#langAttributesAttrTechnicalNameDetailGet(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductAttributesDetailResponseType> langAttributesAttrTechnicalNameDetailGet(
            String lang, String attrTechnicalName, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.langAttributesAttrTechnicalNameDetailGet(
                lang, attrTechnicalName, xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#langAttributesGet(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductAttributesResponseType> langAttributesGet(
            String lang, String categoryId, Boolean comboBoxAttributes,
            String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.langAttributesGet(lang, categoryId,
                comboBoxAttributes, xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#langCompareGet(java.lang.String, java.util.List, java.util.List, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<CompareProductResponseType> langCompareGet(
            String lang, List<String> listOfProductIds,
            List<String> listOfProductAttrs, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.langCompareGet(lang,
                listOfProductIds, listOfProductAttrs, xCorrelationID, xRequestID,
                initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#langProductCategoriesCategoryIdProductsGet(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType, com.asseco.ce.jtsr_digi.product_catalogue.model.PagingRequestType)
     */
    @Override
    public ResponseEntity<GetListOfProductsInCategoryResponseType> langProductCategoriesCategoryIdProductsGet(
            String lang, String categoryId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem,
            PagingRequestType paging) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.langProductCategoriesCategoryIdProductsGet(
                lang, categoryId, xCorrelationID, xRequestID, initiatorSystem, paging);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#langProductCategoriesGet(java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetListOfProductCategoriesResponseType> langProductCategoriesGet(
            String lang, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.langProductCategoriesGet(lang,
                xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#langProductDetailProductIdGet(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<GetProductDetailResponseType> langProductDetailProductIdGet(
            String lang, String productId, String xCorrelationID,
            String xRequestID, InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.langProductDetailProductIdGet(lang,
                productId, xCorrelationID, xRequestID, initiatorSystem);
    }

    /**
     * @see com.asseco.ce.jtsr_digi.product_catalogue.api.ProductCatalogueApiApiDelegate#langSearchGet(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.asseco.ce.jtsr_digi.product_catalogue.model.InitiatorSystemType)
     */
    @Override
    public ResponseEntity<SearchProductResponseType> langSearchGet(String lang,
            String searchQuery, String xCorrelationID, String xRequestID,
            InitiatorSystemType initiatorSystem) {
        // TODO Auto-generated method stub
        return ProductCatalogueApiApiDelegate.super.langSearchGet(lang, searchQuery,
                xCorrelationID, xRequestID, initiatorSystem);
    }

}
