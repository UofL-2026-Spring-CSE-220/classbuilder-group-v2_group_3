package edu.coolschool.utilities.dateutils;

public record DateRecord(int dayInteger, int monthInteger, int yearInteger) {

    // Compact constructor with validation
    public DateRecord {
        if (!DateValidator.isValidDate(dayInteger, monthInteger, yearInteger)) {
            throw new IllegalArgumentException(
                    "Invalid date: " + dayInteger + "/" + monthInteger + "/" + yearInteger
            );
        }
    }

    // Convenience constructor with enum
    public DateRecord(int day, MonthsEnum month, int year) {
        this(day, month.getMonthNumber(), year);
    }

    // Default toString format
    public String toString() {
        return toString(DateFormatOptionsEnum.MM_DD_YYYY);
    }

    // Format according to DateFormatOptionsEnum
    public String toString(DateFormatOptionsEnum format) {
        switch (format) {
            case DD_MM_YYYY:
                return String.format("%02d/%02d/%04d", dayInteger, monthInteger, yearInteger);
            case MM_DD_YYYY:
                return String.format("%02d/%02d/%04d", monthInteger, dayInteger, yearInteger);
            case YYYY_MM_DD:
                return String.format("%04d/%02d/%02d", yearInteger, monthInteger, dayInteger);
            case MONTH_DD_YYYY:
                // Adapt to existing MonthsEnum
                String monthName = MonthsEnum.values()[monthInteger - 1].name();
                monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();
                return String.format("%s %02d, %04d", monthName, dayInteger, yearInteger);
            default:
                throw new IllegalArgumentException("Unknown date format: " + format);
        }
    }

    // Builder class
    public static class Builder {
        private int day, month, year;

        public Builder setDay(int day) { this.day = day; return this; }
        public Builder setMonth(MonthsEnum month) { this.month = month.getMonthNumber(); return this; }
        public Builder setYear(int year) { this.year = year; return this; }

        public DateRecord build() {
            if (!DateValidator.isValidDate(day, month, year)) {
                throw new IllegalArgumentException(
                        "Invalid date: " + day + "/" + month + "/" + year
                );
            }
            return new DateRecord(day, month, year);
        }
    }

    // Optional main method to test outputs
    public static void main(String[] args) {
        DateRecord date = new DateRecord(15, MonthsEnum.MARCH, 2024);
        System.out.println(date); // MM/DD/YYYY
        System.out.println(date.toString(DateFormatOptionsEnum.DD_MM_YYYY));
        System.out.println(date.toString(DateFormatOptionsEnum.YYYY_MM_DD));
        System.out.println(date.toString(DateFormatOptionsEnum.MONTH_DD_YYYY));

        DateRecord leap = new DateRecord(29, MonthsEnum.FEBRUARY, 2020);
        System.out.println(leap);
    }
}
