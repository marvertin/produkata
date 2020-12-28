package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsr_digi.product_catalogue.model.GetProductDetailResponseBodyType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Slf4j
@Mapper(componentModel = "spring", uses = {ListOfProductsDetailTypeMapper.class})
public abstract class GetProductDetailResponseBodyTypeMapper implements EntityMapper<GetProductDetailResponseBodyType, PcTProduct> {

    @Mappings({
            @Mapping(target = "lang", ignore = true),
            @Mapping(target = "categoryId", source = "entityType"),
            @Mapping(target = "listOfProducts", ignore = true)

    })
    public abstract GetProductDetailResponseBodyType toDto(PcTProduct source);

    @Mappings({
            @Mapping(target = "lang", source = "lang"),
            @Mapping(target = "categoryId", source = "pcTProduct.entityType")

    })
    public abstract GetProductDetailResponseBodyType toDtoWithLangAndPcProduct(PcTProduct pcTProduct, String lang);

    @Mappings({
    })
    public abstract PcTProduct toEntity(GetProductDetailResponseBodyType source);


}