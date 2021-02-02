package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogueDocuments;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfDocumentsType;

@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfDocumentsTypeMapper implements EntityMapper<ListOfDocumentsType, PcTProductCatalogueDocuments> {

    @Named("listOfDocumentsTypeItemMapping")
    @Mapping(target = "documentUrl", source = "url")
    @Mapping(target = "documentPresentationText", ignore = true) // TODO: namapovat ked budeme vediet co
    @Mapping(target = "urlLabel", ignore = true) // TODO: namapovat ked budeme vediet co
    @Mapping(target = "validFrom", source = "id.validFrom")
    @Mapping(target = "validTo", source = "validTo")
    @Mapping(target = "documentOrder", source = "id.orderValue")
    public abstract ListOfDocumentsType toDto(PcTProductCatalogueDocuments source);

    @IterableMapping(qualifiedByName = "listOfDocumentsTypeItemMapping")
    public abstract List<ListOfDocumentsType> ListOfDocumentsTypeList(List<PcTProductCatalogueDocuments> record);

    @Mappings({
    })
    public abstract PcTProductCatalogueDocuments toEntity(ListOfDocumentsType source);

}