package com.spring.hibernate_advanced_demo.dao;

import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/** Implements persistence operations using a JPA entity manager. */
@Repository
public class AppDaoImplement implements AppDao {

  private EntityManager em;

  /** Creates the DAO with the entity manager used for persistence. */
  public AppDaoImplement(EntityManager em) {
    this.em = em;
  }

  /** Persists a course. */
  @Override
  @Transactional
  public void save(Course theCourse) {
    em.persist(theCourse);
  }

  /** Updates an instructor. */
  @Override
  @Transactional
  public void update(Instructor in) {
    em.merge(in);
  }

  /** Persists an instructor. */
  @Override
  @Transactional
  public void save(Instructor theInstructor) {
    em.persist(theInstructor);
  }

  /** Finds an instructor by identifier. */
  @Override
  public Instructor findInstructorById(int theId) {
    return em.find(Instructor.class, theId);
  }

  /** Finds instructor details by identifier. */
  @Override
  public InstructorDetail findInstructorDetailById(int theId) {
    return em.find(InstructorDetail.class, theId);
  }

  /** Deletes instructor details by identifier. */
  @Override
  @Transactional
  public void deleteInstructorDetailById(int theId) {
    InstructorDetail deets = em.find(InstructorDetail.class, theId);
    em.remove(deets);
  }

  /** Clears the relationship and deletes instructor details by identifier. */
  @Override
  @Transactional
  public void deleteInstructorDetail(int theId) {
    InstructorDetail deets = em.find(InstructorDetail.class, theId);
    deets.getInstructor().setInstructorDetail(null);
    em.remove(deets);
  }

  /** Finds courses belonging to an instructor. */
  @Override
  public List<Course> findCoursesByInstructorId(int theId) {
    TypedQuery<Course> query =
        em.createQuery("FROM Course WHERE instructor.id=:data", Course.class);
    query.setParameter("data", theId);
    List<Course> courses = query.getResultList();
    return courses;
  }

  /** Finds an instructor with courses and details loaded. */
  @Override
  public Instructor findInstructorByIdJoinFetch(int theId) {
    TypedQuery<Instructor> query =
        em.createQuery(
            "SELECT i FROM Instructor i "
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail "
                + "WHERE i.id=:data",
            Instructor.class);
    query.setParameter("data", theId);
    Instructor in = query.getSingleResult();
    return in;
  }

  /** Deletes an instructor and clears its course relationships. */
  public void deleteInstructorById(int theId) {
    Instructor in = em.find(Instructor.class, theId);
    List<Course> courses = in.getCourses();
    for (Course c : courses) {
      c.setInstructor(null);
    }
    em.remove(in);
  }

  /** Finds a course and its reviews by course identifier. */
  @Override
  public Course findCourseAndReviewsByCourseId(int theId) {
    TypedQuery<Course> query =
        em.createQuery(
            "SELECT c FROM Course c " + "JOIN FETCH c.review " + "WHERE c.id=:data", Course.class);
    query.setParameter("data", theId);

    Course course = query.getSingleResult();
    return course;
  }

  /** Deletes a course by identifier. */
  @Override
  @Transactional
  public void deleteCourseById(int theId) {
    Course course = em.find(Course.class, theId);
    em.remove(course);
  }

  /** Finds a course and its students by course identifier. */
  @Override
  public Course findCourseAndStudentByCourseId(int theId) {
    TypedQuery<Course> query =
        em.createQuery(
            "SELECT c FROM Course c " + "JOIN FETCH c.students " + "WHERE c.id=:data",
            Course.class);
    query.setParameter("data", theId);

    Course course = query.getSingleResult();
    return course;
  }

  /** Finds a student and their courses by student identifier. */
  @Override
  public Student findStudentAndCoursesByStudentId(int theId) {
    TypedQuery<Student> query =
        em.createQuery(
            "SELECT s FROM Student s " + "JOIN FETCH s.courses " + "WHERE s.id=:data",
            Student.class);
    query.setParameter("data", theId);
    Student student = query.getSingleResult();

    return student;
  }

  /** Updates a student. */
  @Override
  @Transactional
  public void update(Student student) {
    em.merge(student);
  }
}
