package com.spring.hibernate_advanced_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Represents a review of a course. */
@Entity
@Table(name = "review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "comments")
  private String comments;

  /** Creates an empty review. */
  public Review() {}

  /** Creates a review with the given comments. */
  public Review(String comments) {
    this.comments = comments;
  }

  /** Returns this review's identifier. */
  public int getId() {
    return id;
  }

  /** Sets this review's identifier. */
  public void setId(int id) {
    this.id = id;
  }

  /** Returns the review comments. */
  public String getComments() {
    return comments;
  }

  /** Sets the review comments. */
  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "Review [id=" + id + ", comments=" + comments + "]";
  }
}
