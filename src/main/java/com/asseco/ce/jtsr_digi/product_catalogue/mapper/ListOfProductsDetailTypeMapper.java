package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfProductsDetailType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Slf4j
@Mapper(componentModel = "spring", uses = {ListOfProductsDetailTypeMapper.class})
public abstract class ListOfProductsDetailTypeMapper implements EntityMapper<ListOfProductsDetailType, PcTProduct> {

    @Mappings({
            @Mapping(target = "productId", source = "productBusinessId"),
            @Mapping(target = "technicalProductId", source = "productTechnicalId"),
            @Mapping(target = "listOfProductAttributes", ignore = true)

    })
    public abstract ListOfProductsDetailType toDto(PcTProduct source);

    @Mappings({
    })
    public abstract PcTProduct toEntity(ListOfProductsDetailType source);

}