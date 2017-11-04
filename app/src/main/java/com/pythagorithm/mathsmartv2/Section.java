package com.pythagorithm.mathsmartv2;

/**
 * Created by b00061342 on 11/4/2017.
 */
public class Section {
    String grade;
    String sectionID;
    Student [] students;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
