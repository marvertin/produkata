package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.EnumTProductType;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfProductCategoriesItemType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

@Slf4j
@Mapper(componentModel = "spring", uses = {ListOfProductAttributesTypeMapper.class})
public abstract class ListOfProductCategoriesItemMapper implements EntityMapper<ListOfProductCategoriesItemType, EnumTProductType> {

    @Named("listItemMapping")
    @Mappings({
            @Mapping(target = "categoryId", source = "productCode"),
            @Mapping(target = "categoryName", source = "text")

    })
    public abstract ListOfProductCategoriesItemType toDto(EnumTProductType source);

    @IterableMapping(qualifiedByName = "listItemMapping")
    public abstract List<ListOfProductCategoriesItemType> ListOfProductCategoriesItemList(List<EnumTProductType> record);

    @Mappings({
    })
    public abstract EnumTProductType toEntity(ListOfProductCategoriesItemType source);

}