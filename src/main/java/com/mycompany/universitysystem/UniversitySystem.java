package com.mycompany.universitysystem;

import com.mycompany.universitysystem.course.CourseDashboard;
import com.mycompany.universitysystem.instructor.Courses;
import com.mycompany.universitysystem.instructor.InstructorDashboard;

public class UniversitySystem {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //new LoginForm().setVisible(true);
        //new InstructorDashboard().setVisible(true);
        new Courses().setVisible(true);
        
        
    }    
}
