package com.spring.hibernate_advanced_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/** Represents an instructor, including profile details and associated courses. */
@Entity
@Table(name = "instructor")
public class Instructor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "instructor_detail_id")
  private InstructorDetail instructorDetail;

  @OneToMany(
      mappedBy = "instructor",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Course> courses;

  /** Creates an empty instructor. */
  public Instructor() {}

  /** Creates an instructor with the given contact information. */
  public Instructor(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  /** Returns this instructor's identifier. */
  public int getId() {
    return id;
  }

  /** Returns this instructor's first name. */
  public String getFirstName() {
    return firstName;
  }

  /** Returns this instructor's last name. */
  public String getLastName() {
    return lastName;
  }

  /** Returns this instructor's email address. */
  public String getEmail() {
    return email;
  }

  /** Sets this instructor's first name. */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /** Sets this instructor's last name. */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /** Sets this instructor's email address. */
  public void setEmail(String email) {
    this.email = email;
  }

  /** Replaces the courses associated with this instructor. */
  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  /** Sets the details associated with this instructor. */
  public void setInstructorDetail(InstructorDetail instructorDetail) {
    this.instructorDetail = instructorDetail;
  }

  /** Returns the details associated with this instructor. */
  public InstructorDetail getInstructorDetail() {
    return instructorDetail;
  }

  /** Returns a string representation of this instructor. */
  @Override
  public String toString() {
    return "Instructor{id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", instructorDetail="
        + instructorDetail
        + '}';
  }

  /** Adds a course and sets this instructor as its owner. */
  public void add(Course tcourse) {
    if (this.courses == null) {
      this.courses = new ArrayList<>();
    }

    this.courses.add(tcourse);
    tcourse.setInstructor(this);
  }

  /** Returns the courses associated with this instructor. */
  public List<Course> getCourses() {
    return courses;
  }
}
