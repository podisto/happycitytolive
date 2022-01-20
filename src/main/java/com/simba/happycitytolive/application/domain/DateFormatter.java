package com.simba.happycitytolive.application.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by podisto on 16/01/2022.
 */
public class DateFormatter {

    private static final String DD_MM_YYYY = "dd/MM/yyyy";

    private DateFormatter() {}

    public static LocalDate toDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
        return LocalDate.parse(dateString, formatter);
    }

    public static String toString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
    }
}
