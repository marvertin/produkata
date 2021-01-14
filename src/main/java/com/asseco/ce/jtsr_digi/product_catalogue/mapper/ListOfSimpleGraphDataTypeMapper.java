package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueTs;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfSimpleGraphDataType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

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