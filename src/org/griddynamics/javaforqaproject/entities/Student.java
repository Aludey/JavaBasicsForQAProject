package org.griddynamics.javaforqaproject.entities;

public class Student {

    private final String name;
    private final String curriculum;
    private final CourseSet setOfCourses;

    public Student(String name, String curriculum, CourseSet setOfCourses){
        this.name = name;
        this.curriculum = curriculum;
        this.setOfCourses = setOfCourses;
    }

    public String getName(){
        return name;
    }

    public String getCurriculum(){
        return curriculum;
    }

    public int getWorkingStartTime(){
        return 10;}

    public int getWorkingEndTime(){
        return 18;}

    public CourseSet getCourseSet(){
        return setOfCourses;
    }
}
