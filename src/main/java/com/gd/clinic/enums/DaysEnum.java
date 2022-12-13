package com.gd.clinic.enums;

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
        for (DaysEnum b : DaysEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

}