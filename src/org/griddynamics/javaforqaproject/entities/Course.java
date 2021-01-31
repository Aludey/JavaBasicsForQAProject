package org.griddynamics.javaforqaproject.entities;

public class Course{

    private final String name;
    private final int duration;

    Course(String name, int duration){
        this.name = name;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
