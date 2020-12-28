package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfProductAttributesDetailType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfProductAttributesDetailTypeMapper implements EntityMapper<ListOfProductAttributesDetailType, PcTProductCatalogue> {

    @Named("attributesListItemMapping")
    @Mappings({
            @Mapping(target = "attrName", source = "enumTProdcatAttr.attrName"),
            @Mapping(target = "attrPresentationValue", source = "CAttrValue"),
            @Mapping(target = "attrDataType", source = "enumTProdcatAttr.attrType"),
            @Mapping(target = "attrOrder", source = "id.orderValue"),
            @Mapping(target = "attrNumericValue", source = "NAttrValue"),
            @Mapping(target = "attrDateValue", source = "DAttrValue", qualifiedByName = "dateToLocalDate"),
            @Mapping(target = "listOfValues", ignore = true)

    })
    public abstract ListOfProductAttributesDetailType toDto(PcTProductCatalogue source);

    @IterableMapping(qualifiedByName = "attributesListItemMapping")
    public abstract List<ListOfProductAttributesDetailType> ListOfProductsAttributesTypeList(List<PcTProductCatalogue> record);

    @Mappings({
    })
    public abstract PcTProductCatalogue toEntity(ListOfProductAttributesDetailType source);

    // Mapstruct ma problem s konverziou java.util.Date na java.time.LocalDate
    @Named("dateToLocalDate")
    static LocalDate localDateString(Date date) {

        if (date == null) return null;

        if (date instanceof Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return LocalDate.from(date.toInstant());
        }
    }
}