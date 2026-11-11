package com.spring.hibernate_advanced_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/** Represents a student and the courses in which the student is enrolled. */
@Entity
@Table(name = "Student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String first_name;
  private String last_name;
  private String email;

  /** Creates an empty student. */
  public Student() {}

  /** Creates a student with the given name and email address. */
  public Student(String first_name, String last_name, String email) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
  }

  /** Returns this student's first name. */
  public String getFirst_name() {
    return first_name;
  }

  /** Sets this student's first name. */
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  /** Returns this student's last name. */
  public String getLast_name() {
    return last_name;
  }

  /** Sets this student's last name. */
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  /** Returns this student's email address. */
  public String getEmail() {
    return email;
  }

  /** Sets this student's email address. */
  public void setEmail(String email) {
    this.email = email;
  }

  /** Returns a string representation of this student. */
  @Override
  public String toString() {
    return "Student [first_name="
        + first_name
        + ", last_name="
        + last_name
        + ", email="
        + email
        + "]";
  }

  @ManyToMany(
      mappedBy = "students",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Course> courses;

  /** Adds a course to this student and links the student to that course. */
  public void addCourse(Course course) {
    if (courses == null) {
      courses = new ArrayList<>();
    }
    courses.add(course);
    course.addStudent(this);
  }

  /** Returns this student's identifier. */
  public int getId() {
    return id;
  }

  /** Sets this student's identifier. */
  public void setId(int id) {
    this.id = id;
  }

  /** Returns the courses in which this student is enrolled. */
  public List<Course> getCourses() {
    return courses;
  }

  /** Replaces the courses in which this student is enrolled. */
  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
