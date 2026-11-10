package com.spring.hibernate_advanced_demo.dao;

import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Student;
import com.spring.hibernate_advanced_demo.entity.Course;
import java.util.List;

public interface AppDao {

  void save (Instructor theInstructor);
  Instructor findInstructorById(int theId);
  InstructorDetail findInstructorDetailById(int theId);
  void deleteInstructorDetail(int theId);
  void deleteInstructorDetailById(int theId);
  List<Course> findCoursesByInstructorId(int theId);
  Instructor findInstructorByIdJoinFetch(int theId);
  void update(Instructor in);
  void deleteInstructorById(int theId);
  void save(Course theCourse);
  Course findCourseAndReviewsByCourseId(int theId);
  void deleteCourseById(int theId);
  Course findCourseAndStudentByCourseId(int theId);
  Student findStudentAndCoursesByStudentId(int theId);
  void update (Student student);
}
