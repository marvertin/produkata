package com.asseco.ce.jtsr_digi.product_catalogue.mapper;

import java.time.LocalDate;
import java.time.ZoneId;

public class SqlDateMapper {

    public java.util.Date asDate(LocalDate date) {
        return java.util.Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate asLocalDate(java.util.Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return LocalDate.from(date.toInstant());
        }
    }
}

