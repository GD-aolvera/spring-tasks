package com.gd.clinic.enums;

import java.util.Arrays;

//TODO: Implement enums from remaining entities/DTOs
public enum DaysEnum {

    MONDAY("Monday"),

    TUESDAY("Tuesday"),

    WEDNESDAY("Wednesday"),

    THURSDAY("Thursday"),

    FRIDAY("Friday"),

    SATURDAY("Saturday"),

    SUNDAY("Sunday");

    private String value;

    DaysEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static DaysEnum fromValue(String value) {
        return Arrays
                .stream(DaysEnum.values())
                .filter(daysEnum -> daysEnum.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Value " + value + " not found in enum"));
    }

}