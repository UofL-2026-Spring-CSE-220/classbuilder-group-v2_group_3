package edu.coolschool;

import edu.coolschool.students.StudentRecord;
import edu.coolschool.utilities.CountriesEnum;
import edu.coolschool.utilities.PersonInfo;
import edu.coolschool.utilities.dateutils.DateRecord;
import edu.coolschool.utilities.dateutils.MonthsEnum;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to the Cool School Student Record System!");

        // Student date of birth
        DateRecord studentDOB = new DateRecord.Builder()
                .setDay(15)
                .setMonth(MonthsEnum.MARCH)
                .setYear(2002)
                .build();

        // Enrollment date
        DateRecord enrollmentDate = new DateRecord.Builder()
                .setDay(1)
                .setMonth(MonthsEnum.SEPTEMBER)
                .setYear(2020)
                .build();

        // Student info
        PersonInfo studentInfo = new PersonInfo.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setDateOfBirth(studentDOB)
                .setCountryOfResidence(CountriesEnum.US)
                .setCountryOfBirth(CountriesEnum.US)
                .build();

        // Father info
        PersonInfo fatherInfo = new PersonInfo.Builder()
                .setFirstName("Michael")
                .setLastName("Doe")
                .setDateOfBirth(new DateRecord.Builder()
                        .setDay(20)
                        .setMonth(MonthsEnum.MAY)
                        .setYear(1970)
                        .build())
                .setCountryOfResidence(CountriesEnum.GB)
                .setCountryOfBirth(CountriesEnum.US)
                .build();

        // Mother info
        PersonInfo motherInfo = new PersonInfo.Builder()
                .setFirstName("Sarah")
                .setLastName("Doe")
                .setDateOfBirth(new DateRecord.Builder()
                        .setDay(10)
                        .setMonth(MonthsEnum.AUGUST)
                        .setYear(1975)
                        .build())
                .setCountryOfResidence(CountriesEnum.GB)
                .setCountryOfBirth(CountriesEnum.US)
                .build();

        // Build student record
        StudentRecord studentRecord = new StudentRecord.Builder()
                .setStudentInfo(studentInfo)
                .setStudentID("123456789")
                .setFatherInfo(fatherInfo)
                .setMotherInfo(motherInfo)
                .setEnrollmentDate(enrollmentDate)
                .build();

        // Print the full record
        System.out.println(studentRecord);
    }
}