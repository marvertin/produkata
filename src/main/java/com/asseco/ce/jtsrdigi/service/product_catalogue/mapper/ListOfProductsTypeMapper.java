package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductsType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Mapper(componentModel = "spring", uses = {ListOfProductAttributesTypeMapper.class})
public abstract class ListOfProductsTypeMapper implements EntityMapper<ListOfProductsType, PcTProduct> {

    @Named("listItemMapping")
    @Mappings({
            @Mapping(target = "productId", source = "productBusinessId"),
            @Mapping(target = "technicalProductId", source = "productTechnicalId"),
            @Mapping(target = "listOfProductAttributes", ignore = true)

    })
    public abstract ListOfProductsType toDto(PcTProduct source);

    @IterableMapping(qualifiedByName = "listItemMapping")
    public abstract List<ListOfProductsType> ListOfProductsTypeList(List<PcTProduct> record);

    @Mappings({
    })
    public abstract PcTProduct toEntity(ListOfProductsType source);

}