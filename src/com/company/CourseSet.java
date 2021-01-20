package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.company.Course.*;

public enum CourseSet {
    JAVA_DEVELOPER("Java developer", Arrays.asList(JAVA, JDBC, SPRING)),
    AQE("AQE", Arrays.asList(TEST_DESIGN, PAGE_OBJECT, SELENIUM));

    private final String name;
    private final ArrayList<Course> courseSet;

    CourseSet(String name, List<Course> courseSet){
        this.name = name;
        this.courseSet = (ArrayList<Course>) courseSet;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getCourseSet() {
        return courseSet;
    }

    public int getCourseSetDuration(){
        int duration = 0;
        for(Course course:courseSet){
            duration += course.getDuration();
        }
        return duration;
    }
}
