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

@Entity
@Table(name = "Student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String first_name;
  private String last_name;
  private String email;

  public Student() {}

  public Student(String first_name, String last_name, String email) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

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

  public void addCourse(Course course) {
    if (courses == null) {
      courses = new ArrayList<>();
    }
    courses.add(course);
    course.addStudent(this);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
