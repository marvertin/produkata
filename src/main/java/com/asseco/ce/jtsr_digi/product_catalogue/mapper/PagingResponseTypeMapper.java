package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsr_digi.product_catalogue.model.PagingResponseType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Slice;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class PagingResponseTypeMapper implements EntityMapper<PagingResponseType, PcTProduct> {

    @Mappings({
            @Mapping(target = "recordCount", ignore = true),
            @Mapping(target = "recordCountTotal", ignore = true),
            @Mapping(target = "hasNextPage", ignore = true),
            @Mapping(target = "hasPreviousPage", ignore = true),
            @Mapping(target = "offset", ignore = true),
            @Mapping(target = "limit", ignore = true)
    })
    public abstract PagingResponseType toDto(PcTProduct source);

    @Mappings({
    })
    public abstract PcTProduct toEntity(PagingResponseType source);


}