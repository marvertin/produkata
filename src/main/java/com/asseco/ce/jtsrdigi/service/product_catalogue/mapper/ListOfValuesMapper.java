package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfValues;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfValuesMapper implements EntityMapper<ListOfValues, PcTProductCatalogue> {

    @Named("valuesListItemMapping")
    @Mappings({
            @Mapping(target = "attrPresentationValue", source = "CAttrValue"),
            @Mapping(target = "attrNumericValue", source = "NAttrValue"),
            @Mapping(target = "attrOrder", source = "id.orderValue")

    })
    public abstract ListOfValues toDto(PcTProductCatalogue source);

    @IterableMapping(qualifiedByName = "valuesListItemMapping")
    public abstract List<ListOfValues> ListOfValuesList(List<PcTProductCatalogue> record);

    @Named("distinctValuesListItemMapping")
    @Mappings({
            @Mapping(target = "attrPresentationValue", expression = "java(source)"),
            @Mapping(target = "attrNumericValue", ignore = true),
            @Mapping(target = "attrOrder", ignore = true)

    })
    public abstract ListOfValues toDtoDistinct(String source);

    @IterableMapping(qualifiedByName = "distinctValuesListItemMapping")
    public abstract List<ListOfValues> DistinctListOfValuesList(List<String> record);

    @Mappings({
    })
    public abstract PcTProductCatalogue toEntity(ListOfValues source);

}