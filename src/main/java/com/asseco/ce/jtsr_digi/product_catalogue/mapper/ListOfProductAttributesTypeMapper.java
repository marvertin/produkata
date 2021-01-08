package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import com.asseco.ce.jtsr_digi.product_catalogue.domain.PcTProductCatalogue;
import com.asseco.ce.jtsr_digi.product_catalogue.model.ListOfProductAttributesType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Mapper(componentModel = "spring", uses = {})
public abstract class ListOfProductAttributesTypeMapper implements EntityMapper<ListOfProductAttributesType, PcTProductCatalogue> {

    @Named("attributesListItemMapping")
    @Mappings({
            @Mapping(target = "attrName", source = "enumTProdcatAttr.attrName"),
            @Mapping(target = "attrPresentationValue", source = "CAttrValue"),
            @Mapping(target = "attrDataType", source = "enumTProdcatAttr.attrType"),
            @Mapping(target = "attrOrder", source = "id.orderValue"),
            @Mapping(target = "attrNumericValue", source = "NAttrValue"),
            @Mapping(target = "attrDateValue", source = "DAttrValue", qualifiedByName = "dateToLocalDate")

    })
    public abstract ListOfProductAttributesType toDto(PcTProductCatalogue source);

    @IterableMapping(qualifiedByName = "attributesListItemMapping")
    public abstract List<ListOfProductAttributesType> ListOfProductsAttributesTypeList(List<PcTProductCatalogue> record);

    @Mappings({
    })
    public abstract PcTProductCatalogue toEntity(ListOfProductAttributesType source);

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