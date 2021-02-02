package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.EnumTProdcatAttr;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductAttributesResponseBodyTypeListOfTechnicalAttributes;

@Mapper(componentModel = "spring", uses = {})
public abstract class GetProductAttributesResponseBodyTypeListOfTechnicalAttributesMapper implements EntityMapper<GetProductAttributesResponseBodyTypeListOfTechnicalAttributes, EnumTProdcatAttr> {

    @Named("attributesListItemMapping")
    @Mapping(target = "attrTechnicalName", source = "attrName")
    public abstract GetProductAttributesResponseBodyTypeListOfTechnicalAttributes toDto(EnumTProdcatAttr source);

    @Named("stringListItemMapping")
    @Mapping(target = "attrTechnicalName", expression = "java(source)")
    public abstract GetProductAttributesResponseBodyTypeListOfTechnicalAttributes stringToDto(String source);

    @IterableMapping(qualifiedByName = "attributesListItemMapping")
    public abstract List<GetProductAttributesResponseBodyTypeListOfTechnicalAttributes> GetProductAttributesResponseBodyTypeListOfTechnicalAttributesList(List<EnumTProdcatAttr> record);

    @IterableMapping(qualifiedByName = "stringListItemMapping")
    public abstract List<GetProductAttributesResponseBodyTypeListOfTechnicalAttributes> ListStringToGetProductAttributesResponseBodyTypeListOfTechnicalAttributesList(List<String> record);

    @Mappings({
    })
    public abstract EnumTProdcatAttr toEntity(GetProductAttributesResponseBodyTypeListOfTechnicalAttributes source);

}