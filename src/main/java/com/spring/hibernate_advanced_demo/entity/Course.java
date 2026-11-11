package com.spring.hibernate_advanced_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/** Represents a course entity. */
/** Represents a course in the persistence model. */
@Entity
@Table(name = "course")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "title")
  private String title;

  @ManyToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "instructor_id")
  private Instructor instructor;

  /** Creates an empty course. */
  public Course() {}

  /** Creates a course with the given title. */
  public Course(String title) {
    this.title = title;
  }

  /** Returns this course's title. */
  public String getTitle() {
    return title;
  }

  /** Sets this course's title. */
  public void setTitle(String title) {
    this.title = title;
  }

  /** Returns the instructor associated with this course. */
  public Instructor getInstructor() {
    return instructor;
  }

  /** Associates this course with an instructor. */
  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  @Override
  public String toString() {
    return "Course{id=" + id + ", title='" + title + '\'' + '}';
  }

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "course_id")
  private List<Review> review;

  /**
   * Adds a review to this course.
   *
   * @param rev the review to add
   */
  public void addReview(Review rev) {
    if (review == null) {
      review = new ArrayList<>();
    }
    review.add(rev);
  }

  /** Returns the reviews associated with this course. */
  public List<Review> getReview() {
    return review;
  }

  /** Replaces the reviews associated with this course. */
  public void setReview(List<Review> review) {
    this.review = review;
  }

  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
      name = "course_student",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<Student> students;

  /** Adds a student to this course. */
  public void addStudent(Student stud) {
    if (students == null) {
      students = new ArrayList<>();
    }
    students.add(stud);
  }

  /** Returns the students enrolled in this course. */
  public List<Student> getStudents() {
    return students;
  }

  /** Replaces the students enrolled in this course. */
  public void setStudents(List<Student> students) {
    this.students = students;
  }
}
