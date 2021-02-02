package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.EnumTProductType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductCategoriesItemType;

@Mapper(componentModel = "spring", uses = {ListOfProductAttributesTypeMapper.class})
public abstract class ListOfProductCategoriesItemMapper implements EntityMapper<ListOfProductCategoriesItemType, EnumTProductType> {

    @Named("listItemMapping")
    @Mapping(target = "categoryId", source = "productCode")
    @Mapping(target = "categoryName", source = "text")
    public abstract ListOfProductCategoriesItemType toDto(EnumTProductType source);

    @IterableMapping(qualifiedByName = "listItemMapping")
    public abstract List<ListOfProductCategoriesItemType> ListOfProductCategoriesItemList(List<EnumTProductType> record);

    @Mappings({
    })
    public abstract EnumTProductType toEntity(ListOfProductCategoriesItemType source);

}