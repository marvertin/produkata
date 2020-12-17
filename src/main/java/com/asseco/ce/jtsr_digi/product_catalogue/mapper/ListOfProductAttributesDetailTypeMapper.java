package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfProductAttributesDetailType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Slf4j
@Mapper(componentModel = "spring", uses = {ListOfProductAttributesDetailTypeMapper.class})
public abstract class ListOfProductAttributesDetailTypeMapper implements EntityMapper<ListOfProductAttributesDetailType, PcTProductCatalogue> {

    @Mappings({
            @Mapping(target = "attrName", source = "enumTProdcatAttr.attrName"),
            @Mapping(target = "attrPresentationValue", source = "CAttrValue"),
            @Mapping(target = "attrDataType", source = "enumTProdcatAttr.attrType"),
            @Mapping(target = "attrOrder", source = "id.orderValue"),
            @Mapping(target = "attrNumericValue", source = "NAttrValue"),
            @Mapping(target = "attrDateValue", source = "DAttrValue"),
            @Mapping(target = "listOfValues", ignore = true)

    })
    public abstract ListOfProductAttributesDetailType toDto(PcTProductCatalogue source);

    @Mappings({
    })
    public abstract PcTProductCatalogue toEntity(ListOfProductAttributesDetailType source);

}