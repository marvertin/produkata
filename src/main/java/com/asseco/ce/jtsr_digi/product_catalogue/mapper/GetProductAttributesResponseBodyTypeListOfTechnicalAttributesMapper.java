package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.EnumTProdcatAttr;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductAttributesResponseBodyTypeListOfTechnicalAttributes;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class GetProductAttributesResponseBodyTypeListOfTechnicalAttributesMapper implements EntityMapper<GetProductAttributesResponseBodyTypeListOfTechnicalAttributes, EnumTProdcatAttr> {

    @Named("attributesListItemMapping")
    @Mappings({
            @Mapping(target = "attrTechnicalName", source = "attrName")

    })
    public abstract GetProductAttributesResponseBodyTypeListOfTechnicalAttributes toDto(EnumTProdcatAttr source);

    @Named("stringListItemMapping")
    @Mappings({
            @Mapping(target = "attrTechnicalName", expression = "java(source)")

    })
    public abstract GetProductAttributesResponseBodyTypeListOfTechnicalAttributes stringToDto(String source);

    @IterableMapping(qualifiedByName = "attributesListItemMapping")
    public abstract List<GetProductAttributesResponseBodyTypeListOfTechnicalAttributes> GetProductAttributesResponseBodyTypeListOfTechnicalAttributesList(List<EnumTProdcatAttr> record);

    @IterableMapping(qualifiedByName = "stringListItemMapping")
    public abstract List<GetProductAttributesResponseBodyTypeListOfTechnicalAttributes> ListStringToGetProductAttributesResponseBodyTypeListOfTechnicalAttributesList(List<String> record);

    @Mappings({
    })
    public abstract EnumTProdcatAttr toEntity(GetProductAttributesResponseBodyTypeListOfTechnicalAttributes source);

}