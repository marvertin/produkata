package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductDetailResponseBodyType;

@Mapper(componentModel = "spring", uses = {ListOfProductsDetailTypeMapper.class})
public abstract class GetProductDetailResponseBodyTypeMapper implements EntityMapper<GetProductDetailResponseBodyType, PcTProduct> {

    @Mapping(target = "lang", ignore = true)
    @Mapping(target = "categoryId", source = "entityType")
    @Mapping(target = "listOfProducts", ignore = true)
    public abstract GetProductDetailResponseBodyType toDto(PcTProduct source);

    @Mapping(target = "lang", source = "lang")
    @Mapping(target = "categoryId", source = "pcTProduct.entityType")
    public abstract GetProductDetailResponseBodyType toDtoWithLangAndPcProduct(PcTProduct pcTProduct, String lang);

    @Mappings({
    })
    public abstract PcTProduct toEntity(GetProductDetailResponseBodyType source);

}