package com.spring.hibernate_advanced_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/** Stores an instructor's subject, hobby, and associated instructor. */
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "subject")
  private String subject;

  @Column(name = "hobby")
  private String hobby;

  /** Creates empty instructor details. */
  public InstructorDetail() {}

  /** Creates instructor details with the given subject and hobby. */
  public InstructorDetail(String subject, String hobby) {
    this.subject = subject;
    this.hobby = hobby;
  }

  /** Returns this record's identifier. */
  public int getId() {
    return id;
  }

  /** Returns the instructor's subject. */
  public String getSubject() {
    return subject;
  }

  /** Returns the instructor's hobby. */
  public String getHobby() {
    return hobby;
  }

  @Override
  public String toString() {
    return "InstructorDetail{id="
        + id
        + ", subject='"
        + subject
        + '\''
        + ", hobby='"
        + hobby
        + '\''
        + '}';
  }

  @OneToOne(
      mappedBy = "instructorDetail",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Instructor instructor;

  /** Returns the instructor associated with these details. */
  public Instructor getInstructor() {
    return instructor;
  }

  /** Associates these details with an instructor. */
  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }
}
