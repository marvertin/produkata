package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueTs;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfSimpleGraphDataType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfSimpleGraphDataTypeMapper implements EntityMapper<ListOfSimpleGraphDataType, PcTProductCatalogueTs> {

    @Named("listOfSimpleGraphDataTypeMapping")
    @Mappings({
            @Mapping(target = "attrValue", source = "value"),
            @Mapping(target = "dateValue", source = "id.dateFrom")

    })
    public abstract ListOfSimpleGraphDataType toDto(PcTProductCatalogueTs source);

    @IterableMapping(qualifiedByName = "listOfSimpleGraphDataTypeMapping")
    public abstract List<ListOfSimpleGraphDataType> ListOfDocumentsTypeList(List<PcTProductCatalogueTs> record);

    @Mappings({
    })
    public abstract PcTProductCatalogueTs toEntity(ListOfSimpleGraphDataType source);

}