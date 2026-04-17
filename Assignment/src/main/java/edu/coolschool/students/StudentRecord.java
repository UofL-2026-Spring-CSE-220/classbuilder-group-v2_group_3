package edu.coolschool.students;
import edu.coolschool.utilities.ErrorStrings;
import edu.coolschool.utilities.PersonInfo;
import edu.coolschool.utilities.dateutils.DateRecord;

public class StudentRecord {

    private final PersonInfo studentInfo;
    private final String studentID;
    private final PersonInfo fatherInfo;
    private final PersonInfo motherInfo;
    private final DateRecord enrollmentDate;

    private StudentRecord(Builder builder) {
        this.studentInfo = builder.studentInfo;
        this.studentID = builder.studentID;
        this.fatherInfo = builder.fatherInfo;
        this.motherInfo = builder.motherInfo;
        this.enrollmentDate = builder.enrollmentDate;
    }

    public PersonInfo studentInfo() { return studentInfo; }
    public String studentID() { return studentID; }
    public PersonInfo fatherInfo() { return fatherInfo; }
    public PersonInfo motherInfo() { return motherInfo; }
    public DateRecord enrollmentDate() { return enrollmentDate; }

    @Override
    public String toString() {
        return toString(0);
    }

    public String toString(int tabLevel) {
        String indent = "\t".repeat(tabLevel);
        String innerIndent = "\t".repeat(tabLevel + 1);
        StringBuilder sb = new StringBuilder();

        sb.append(indent).append("Student ID: ").append(studentID).append("\n");
        sb.append(innerIndent).append("Enrollment Date: ").append(enrollmentDate).append("\n");

        sb.append(innerIndent).append("Student Information:\n")
                .append(studentInfo.toString(tabLevel + 2));

        if (fatherInfo != null) {
            sb.append(innerIndent).append("Father Information:\n")
                    .append(fatherInfo.toString(tabLevel + 2));
        }

        if (motherInfo != null) {
            sb.append(innerIndent).append("Mother Information:\n")
                    .append(motherInfo.toString(tabLevel + 2));
        }

        sb.append("\n"); // final blank line for autograder
        return sb.toString();
    }

    public static class Builder {

        private PersonInfo studentInfo;
        private String studentID;
        private PersonInfo fatherInfo;
        private PersonInfo motherInfo;
        private DateRecord enrollmentDate;

        public Builder setStudentInfo(PersonInfo studentInfo) {
            this.studentInfo = studentInfo;
            return this;
        }

        public Builder setStudentID(String studentID) {
            this.studentID = studentID;
            return this;
        }

        public Builder setFatherInfo(PersonInfo fatherInfo) {
            this.fatherInfo = fatherInfo;
            return this;
        }

        public Builder setMotherInfo(PersonInfo motherInfo) {
            this.motherInfo = motherInfo;
            return this;
        }

        public Builder setEnrollmentDate(DateRecord enrollmentDate) {
            this.enrollmentDate = enrollmentDate;
            return this;
        }

        public StudentRecord build() {
            if (studentInfo == null) {
                throw new IllegalArgumentException(ErrorStrings.NULL_STUDENT_INFO.getMessage());
            }

            if (studentID == null || studentID.isBlank() || studentID.length() != 9) {
                throw new IllegalArgumentException(ErrorStrings.INVALID_STUDENT_ID.getMessage());
            }

            if (enrollmentDate == null) {
                throw new IllegalArgumentException(ErrorStrings.NULL_ENROLLMENT_DATE.getMessage());
            }

            // fatherInfo and motherInfo are optional
            return new StudentRecord(this);
        }
    }
}