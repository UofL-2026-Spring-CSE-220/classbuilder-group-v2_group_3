package BohannonUnitTest;

import edu.coolschool.students.StudentRecord;
import edu.coolschool.utilities.CountriesEnum;
import edu.coolschool.utilities.ErrorStrings;
import edu.coolschool.utilities.PersonInfo;
import edu.coolschool.utilities.dateutils.DateRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentRecordTest {
    private PersonInfo createValidPerson() {
        return new PersonInfo.Builder()
                .setFirstName("John")
                .setMiddleName("A")
                .setLastName("Smith")
                .setDateOfBirth(new DateRecord(15, 3, 2005))
                .setCountryOfBirth(CountriesEnum.US)
                .setCountryOfResidence(CountriesEnum.US)
                .build();
    }

    private DateRecord createValidEnrollmentDate() {
        return new DateRecord(20, 8, 2024);
    }

    @Test
    void buildCreatesValidStudentRecord() {
        PersonInfo studentInfo = createValidPerson();
        DateRecord enrollmentDate = createValidEnrollmentDate();

        StudentRecord record = new StudentRecord.Builder()
                .setStudentInfo(studentInfo)
                .setStudentID("123456789")
                .setEnrollmentDate(enrollmentDate)
                .build();

        assertEquals(studentInfo, record.studentInfo());
        assertEquals("123456789", record.studentID());
        assertEquals(enrollmentDate, record.enrollmentDate());
        assertNull(record.fatherInfo());
        assertNull(record.motherInfo());
    }

    @Test
    void buildCreatesValidStudentRecordWithParents() {
        PersonInfo studentInfo = createValidPerson();
        PersonInfo fatherInfo = new PersonInfo.Builder()
                .setFirstName("Robert")
                .setLastName("Smith")
                .setDateOfBirth(new DateRecord(10, 5, 1980))
                .setCountryOfBirth(CountriesEnum.US)
                .setCountryOfResidence(CountriesEnum.US)
                .build();

        PersonInfo motherInfo = new PersonInfo.Builder()
                .setFirstName("Mary")
                .setLastName("Smith")
                .setDateOfBirth(new DateRecord(12, 7, 1982))
                .setCountryOfBirth(CountriesEnum.US)
                .setCountryOfResidence(CountriesEnum.US)
                .build();

        DateRecord enrollmentDate = createValidEnrollmentDate();

        StudentRecord record = new StudentRecord.Builder()
                .setStudentInfo(studentInfo)
                .setStudentID("123456789")
                .setFatherInfo(fatherInfo)
                .setMotherInfo(motherInfo)
                .setEnrollmentDate(enrollmentDate)
                .build();

        assertEquals(studentInfo, record.studentInfo());
        assertEquals(fatherInfo, record.fatherInfo());
        assertEquals(motherInfo, record.motherInfo());
        assertEquals(enrollmentDate, record.enrollmentDate());
    }

    @Test
    void buildThrowsExceptionWhenStudentInfoIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StudentRecord.Builder()
                        .setStudentID("123456789")
                        .setEnrollmentDate(createValidEnrollmentDate())
                        .build()
        );
        assertEquals(ErrorStrings.NULL_STUDENT_INFO.getMessage(), exception.getMessage());
    }

    @Test
    void buildThrowsExceptionWhenStudentIdIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StudentRecord.Builder()
                        .setStudentInfo(createValidPerson())
                        .setEnrollmentDate(createValidEnrollmentDate())
                        .build()
        );

        assertEquals(ErrorStrings.INVALID_STUDENT_ID.getMessage(), exception.getMessage());
    }

    @Test
    void buildThrowsExceptionWhenStudentIdIsBlank() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StudentRecord.Builder()
                        .setStudentInfo(createValidPerson())
                        .setStudentID("   ")
                        .setEnrollmentDate(createValidEnrollmentDate())
                        .build()
        );

        assertEquals(ErrorStrings.INVALID_STUDENT_ID.getMessage(), exception.getMessage());
    }

    @Test
    void buildThrowsExceptionWhenStudentIdIsNotNineCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StudentRecord.Builder()
                        .setStudentInfo(createValidPerson())
                        .setStudentID("12345")
                        .setEnrollmentDate(createValidEnrollmentDate())
                        .build()
        );
        assertEquals(ErrorStrings.INVALID_STUDENT_ID.getMessage(), exception.getMessage());
    }

    @Test
    void buildThrowsExceptionWhenEnrollmentDateIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StudentRecord.Builder()
                        .setStudentInfo(createValidPerson())
                        .setStudentID("123456789")
                        .build()
        );
        assertEquals(ErrorStrings.NULL_ENROLLMENT_DATE.getMessage(), exception.getMessage());
    }


    @Test
    void toStringContainsImportantFields() {
        StudentRecord record = new StudentRecord.Builder()
                .setStudentInfo(createValidPerson())
                .setStudentID("123456789")
                .setEnrollmentDate(createValidEnrollmentDate())
                .build();
        String output = record.toString();
        assertTrue(output.contains("Student ID: 123456789"));
        assertTrue(output.contains("Enrollment Date:"));
        assertTrue(output.contains("Student Information:"));
        assertTrue(output.contains("First Name: John"));
        assertTrue(output.contains("Last Name: Smith"));
    }
}