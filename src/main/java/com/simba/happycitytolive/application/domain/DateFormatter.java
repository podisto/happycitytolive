package com.simba.happycitytolive.application.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by podisto on 16/01/2022.
 */
class DateFormatter {

    private DateFormatter() {}

    public static LocalDate toDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
