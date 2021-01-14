/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author vaggelis
 */
public class CourseAssignment {
    private int CourseAssignmentId;
    private int courseId;
    private int assignmentId;

    public CourseAssignment(int CourseAssignmentId, int courseId, int assignmentId) {
        this.CourseAssignmentId = CourseAssignmentId;
        this.courseId = courseId;
        this.assignmentId = assignmentId;
    }

    public CourseAssignment(int courseId, int assignmentId) {
        this.courseId = courseId;
        this.assignmentId = assignmentId;
    }

    public int getCourseAssignmentId() {
        return CourseAssignmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }
    
    
    
}
