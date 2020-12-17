package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfValues;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfValuesMapper implements EntityMapper<ListOfValues, PcTProductCatalogue> {

    @Mappings({
            @Mapping(target = "attrPresentationValue", source = "CAttrValue"),
            @Mapping(target = "attrNumericValue", source = "NAttrValue"),
            @Mapping(target = "attrOrder", source = "id.orderValue")

    })
    public abstract ListOfValues toDto(PcTProductCatalogue source);

    @Mappings({
    })
    public abstract PcTProductCatalogue toEntity(ListOfValues source);

}