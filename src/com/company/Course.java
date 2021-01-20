package com.company;

public enum Course {
    JAVA("Java", 16),
    JDBC("Jdbc", 24),
    SPRING("Spring", 16),
    TEST_DESIGN("Test design", 10),
    PAGE_OBJECT("Page Object", 16),
    SELENIUM("Selenium", 16);

    private final String name;
    private final int duration;

    Course(String name, int duration){
        this.name = name;
        this.duration = duration;
    }
    public String getName() {
        return name;
    }
    public Integer getDuration() {
        return duration;
    }
}
