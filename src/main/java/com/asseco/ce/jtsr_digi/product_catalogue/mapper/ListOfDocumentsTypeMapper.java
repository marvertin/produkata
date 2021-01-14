package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogueDocuments;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfDocumentsType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfDocumentsTypeMapper implements EntityMapper<ListOfDocumentsType, PcTProductCatalogueDocuments> {

    @Named("listOfDocumentsTypeItemMapping")
    @Mappings({
            @Mapping(target = "documentUrl", source = "url"),
            @Mapping(target = "documentPresentationText", ignore = true), // TODO: namapovat ked budeme vediet co
            @Mapping(target = "urlLabel", ignore = true), // TODO: namapovat ked budeme vediet co
            @Mapping(target = "validFrom", source = "id.validFrom"),
            @Mapping(target = "validTo", source = "validTo"),
            @Mapping(target = "documentOrder", source = "id.orderValue")

    })
    public abstract ListOfDocumentsType toDto(PcTProductCatalogueDocuments source);

    @IterableMapping(qualifiedByName = "listOfDocumentsTypeItemMapping")
    public abstract List<ListOfDocumentsType> ListOfDocumentsTypeList(List<PcTProductCatalogueDocuments> record);

    @Mappings({
    })
    public abstract PcTProductCatalogueDocuments toEntity(ListOfDocumentsType source);

}