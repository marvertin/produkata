package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsr_digi.product_catalogue.model.PagingResponseType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class PagingResponseTypeMapper implements EntityMapper<PagingResponseType, PcTProduct> {

    @Mappings({
            @Mapping(target = "recordCount", source = "entityType"),
            @Mapping(target = "recordCountTotal", source = "entityType"),
            @Mapping(target = "hasNextPage", source = "entityType"),
            @Mapping(target = "hasPreviousPage", source = "entityType"),
            @Mapping(target = "offset", source = "entityType"),
            @Mapping(target = "limit", source = "entityType")
    })
    public abstract PagingResponseType toDto(PcTProduct source);

    @Mappings({
    })
    public abstract PcTProduct toEntity(PagingResponseType source);


}