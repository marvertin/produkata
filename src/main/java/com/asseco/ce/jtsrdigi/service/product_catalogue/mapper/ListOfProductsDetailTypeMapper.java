package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProduct;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductsDetailType;

@Mapper(componentModel = "spring", uses = {ListOfProductAttributesDetailTypeMapper.class})
public abstract class ListOfProductsDetailTypeMapper implements EntityMapper<ListOfProductsDetailType, PcTProduct> {

    @Named("listItemMapping")
    @Mapping(target = "productId", source = "productBusinessId")
    @Mapping(target = "technicalProductId", source = "productTechnicalId")
    @Mapping(target = "listOfProductAttributes", ignore = true)
    public abstract ListOfProductsDetailType toDto(PcTProduct source);

    @IterableMapping(qualifiedByName = "listItemMapping")
    public abstract List<ListOfProductsDetailType> ListOfProductsDetailTypeList(List<PcTProduct> record);

    @Mappings({
    })
    public abstract PcTProduct toEntity(ListOfProductsDetailType source);

}