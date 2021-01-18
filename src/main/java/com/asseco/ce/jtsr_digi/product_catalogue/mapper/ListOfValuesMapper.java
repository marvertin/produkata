package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfValues;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

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