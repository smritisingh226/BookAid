package com.example.database_implementation;

public class CourseModal {
    // variables for our coursename,
    // description, tracks and duration, id.
    private String courseName;
    private String courseDuration;
    private String courseDescription;
    private String courseTracks;
    private int id;
    public CourseModal(String courseName,
                       String courseDuration,
                       String courseDescription,
                       String courseTracks)
    {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseDescription = courseDescription;
        this.courseTracks = courseTracks;
    }

    // creating getter and setter methods
    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseDuration()
    {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration)
    {
        this.courseDuration = courseDuration;
    }

    public String getCourseTracks() { return courseTracks; }

    public void setCourseTracks(String courseTracks)
    {
        this.courseTracks = courseTracks;
    }

    public String getCourseDescription()
    {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription)
    {
        this.courseDescription = courseDescription;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
    // constructor
}
