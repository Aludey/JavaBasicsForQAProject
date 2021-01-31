package org.griddynamics.javaforqaproject.entities;

import org.griddynamics.javaforqaproject.tools.Evaluator;

import java.util.ArrayList;
import java.util.Date;

public class CourseSet {

    private final ArrayList<Course> setOfCourses;
    private final Date startDate;
    private final Date endDate;

    CourseSet(ArrayList<Course> setOfCourses, Date startDate){
        this.setOfCourses = setOfCourses;
        this.startDate = startDate;
        this.endDate = Evaluator.calculateEndDate(startDate, getDuration());
    }

    public int getDuration(){
        int duration = 0;
        for(Course course:setOfCourses){
            duration += course.getDuration();
        }
        return duration;
    }

    public Date getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }
}
