package com.asseco.ce.jtsrdigi.service.product_catalogue.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.asseco.ce.jtsrdigi.service.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.ListOfProductAttributesType;

@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfProductAttributesTypeMapper implements EntityMapper<ListOfProductAttributesType, PcTProductCatalogue> {

    @Named("attributesListItemMapping")
    @Mapping(target = "attrName", source = "enumTProdcatAttr.attrName")
    @Mapping(target = "attrPresentationValue", source = "CAttrValue")
    @Mapping(target = "attrDataType", source = "enumTProdcatAttr.attrType")
    @Mapping(target = "attrOrder", source = "id.orderValue")
    @Mapping(target = "attrNumericValue", source = "NAttrValue")
    @Mapping(target = "attrDateValue", source = "DAttrValue")
    public abstract ListOfProductAttributesType toDto(PcTProductCatalogue source);

    @IterableMapping(qualifiedByName = "attributesListItemMapping")
    public abstract List<ListOfProductAttributesType> ListOfProductsAttributesTypeList(List<PcTProductCatalogue> record);

    @Mappings({
    })
    public abstract PcTProductCatalogue toEntity(ListOfProductAttributesType source);

    // Mapstruct ma problem s konverziou java.util.Date na java.time.LocalDate
    /*@Named("dateToLocalDate")
    static LocalDate localDateString(Date date) {

        if (date == null) return null;

        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return LocalDate.from(date.toInstant());
        }
    }*/
}