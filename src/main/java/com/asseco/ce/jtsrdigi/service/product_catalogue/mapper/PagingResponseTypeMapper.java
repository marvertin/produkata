package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.PagingResponseType;

@Mapper(componentModel = "spring", uses = {})
public abstract class PagingResponseTypeMapper implements EntityMapper<PagingResponseType, PcTProduct> {

    @Mapping(target = "recordCount", ignore = true)
    @Mapping(target = "recordCountTotal", ignore = true)
    @Mapping(target = "hasNextPage", ignore = true)
    @Mapping(target = "hasPreviousPage", ignore = true)
    @Mapping(target = "offset", ignore = true)
    @Mapping(target = "limit", ignore = true)
    public abstract PagingResponseType toDto(PcTProduct source);

    @Mappings({
    })
    public abstract PcTProduct toEntity(PagingResponseType source);


}