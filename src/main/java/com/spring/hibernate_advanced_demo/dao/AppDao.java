package com.spring.hibernate_advanced_demo.dao;

import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Student;
import java.util.List;

/** Defines persistence operations for instructors, courses, and students. */
public interface AppDao {

  /** Persists an instructor. */
  void save(Instructor theInstructor);

  /** Finds an instructor by identifier. */
  Instructor findInstructorById(int theId);

  /** Finds instructor details by identifier. */
  InstructorDetail findInstructorDetailById(int theId);

  /** Deletes instructor details by identifier. */
  void deleteInstructorDetail(int theId);

  /** Deletes instructor details by identifier. */
  void deleteInstructorDetailById(int theId);

  /** Finds courses belonging to an instructor. */
  List<Course> findCoursesByInstructorId(int theId);

  /** Finds an instructor with courses and details loaded. */
  Instructor findInstructorByIdJoinFetch(int theId);

  /** Updates an instructor. */
  void update(Instructor in);

  /** Deletes an instructor by identifier. */
  void deleteInstructorById(int theId);

  /** Persists a course. */
  void save(Course theCourse);

  /** Finds a course and its reviews by course identifier. */
  Course findCourseAndReviewsByCourseId(int theId);

  /** Deletes a course by identifier. */
  void deleteCourseById(int theId);

  /** Finds a course and its students by course identifier. */
  Course findCourseAndStudentByCourseId(int theId);

  /** Finds a student and their courses by student identifier. */
  Student findStudentAndCoursesByStudentId(int theId);

  /** Updates a student. */
  void update(Student student);
}
