package com.spring.hibernate_advanced_demo;

import com.spring.hibernate_advanced_demo.dao.AppDao;
import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Review;
import com.spring.hibernate_advanced_demo.entity.Student;
import jakarta.persistence.TypedQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateAdvancedDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(HibernateAdvancedDemoApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(AppDao app) {
    return runner -> {
      // createInstructor(app);
      // findInstructor(app);
      // deleteInstructor(app);
      // findInstructorDetail(app);
      // deleteInstructorDetail(app);
      // createInstructorWithCourses(app);
      // findInstructorWithCourses(app);
      // findCoursesforInstructor(app);
      // updateInstructor(app);
      // deleteInstructorOnly(app);
      // createInstructorAndReviews(app);
      // retrieveCourseAndReviews(app);
      // deleteCourseAndReviews(app);
      // createCourseAndStudents(app);
      // findCourseAndStudents(app);
      // findStudentAndCourses(app);
      // addMoreCoursesForStudent(app);
      deleteCourseById(app);
    };
  }

  private void deleteCourseById(AppDao app) {
    int id = 28;
    System.out.println("Delete course: " + id);

    app.deleteCourseById(id);
    System.out.println("Deleted successfully!");
  }

  private void addMoreCoursesForStudent(AppDao app) {
    int id = 13;
    Student student = app.findStudentAndCoursesByStudentId(id);

    Course course1 = new Course("Ten Shadows Technique Training");
    Course course2 = new Course("Domain Expansion: Chimera Shadow Garden");
    Course course3 = new Course("Jujutsu Sorcerer Combat Training");

    student.addCourse(course1);
    student.addCourse(course2);
    student.addCourse(course3);

    app.update(student);

    System.out.println("Successfully enrolled to new courses!");

    app.update(student);
  }

  private void findStudentAndCourses(AppDao app) {
    int id = 14;
    Student student = app.findStudentAndCoursesByStudentId(id);

    System.out.println("Student: " + student);
    System.out.println("Courses: " + student.getCourses());
  }

  private void findCourseAndStudents(AppDao app) {
    int id = 21;
    Course course = app.findCourseAndStudentByCourseId(id);
    System.out.println("Course: " + course);
    System.out.println("Students: " + course.getStudents());
  }

  private void createCourseAndStudents(AppDao app) {
    Course course = new Course("Jujutsu High: Sorcerer Training");

    Student student1 = new Student("Megumi", "Fushiguro", "megumi.fushiguro@example.com");
    Student student2 = new Student("Yuji", "Itadori", "yuji.itadori@example.com");
    Student student3 = new Student("Nobara", "Kugisaki", "nobara.kugisaki@example.com");

    course.addStudent(student1);
    course.addStudent(student2);
    course.addStudent(student3);

    app.save(course);
    System.out.println("Successfully enrolled!");
  }

  private void deleteCourseAndReviews(AppDao app) {
    int id = 12;
    System.out.println("Delete course: " + id);
    app.deleteCourseById(id);
    System.out.println("Deleted successfully!");
  }

  private void retrieveCourseAndReviews(AppDao app) {
    int id = 11;
    System.out.println("Get course and reviews from id: " + id);
    Course course = app.findCourseAndReviewsByCourseId(id);
    System.out.println(course);
    System.out.println(course.getReview());
  }

  private void createInstructorAndReviews(AppDao app) {
    // Course advancedCourse = new Course("Mastering Limitless with Gojo");
    // advancedCourse.addReview(new Review("Gojo-sensei explains advanced techniques in a way that finally makes sense."));
    // advancedCourse.addReview(new Review("The training was intense, but I feel much more confident using cursed energy."));
    // advancedCourse.addReview(new Review("Best jujutsu class I’ve taken—though Gojo-sensei definitely knows he’s the strongest."));
    // System.out.println("Advanced course saved!");
    // app.save(advancedCourse);
    Course course = new Course("How to become a Jujutsu Sorcerer");
    course.addReview(new Review("Things are becoming weird."));
    course.addReview(new Review("I see curses now!"));
    course.addReview(new Review("The topic is nice but the sensei is too unhumble for my taste."));
    System.out.println("Course saved!");
    app.save(course);
  }

  private void deleteInstructorOnly(AppDao app) {
    int id = 22;
    System.out.println("Delete instructor: " + id);

    app.deleteInstructorById(id);

    System.out.println("Deleted successfully!");
  }

  private void updateInstructor(AppDao app) {
    int id = 3;
    System.out.println("Find instructor id: " + id);

    Instructor ins = app.findInstructorById(id);
    ins.setLastName("Pogi");

    System.out.println("Updated!");
    app.update(ins);
  }

  private void findCoursesforInstructor(AppDao app) {
    int id = 22;
    System.out.println("Find instructor id: " + id);

    Instructor ins = app.findInstructorByIdJoinFetch(id);
    System.out.println("Instructor: " + ins);
    System.out.println("Courses: " + ins.getCourses());
  }

  private void findInstructorWithCourses(AppDao app) {
    int id = 1;
    System.out.println("Find instructor id: " + id);

    Instructor ins = app.findInstructorById(id);

    System.out.println("Instructor: " + ins);
    System.out.println("Courses: " + ins.getCourses());
  }

  private void createInstructorWithCourses(AppDao app) {
    Instructor ins = new Instructor("Satoru", "Gojo", "gojo.satoru@email.com");
    InstructorDetail deets = new InstructorDetail("Jujutsu", "Cursed Energy");
    ins.setInstructorDetail(deets);
    Course course1 = new Course("Jujutsu 101");
    Course course2 = new Course("Cursed Energy 101");
    ins.add(course1);
    ins.add(course2);
    System.out.println("Courses saved: " + ins.getCourses());
    app.save(ins);
  }

  private void deleteInstructorDetail(AppDao app) {
    int id = 5;
    System.out.println("Delete id: " + id);

    app.deleteInstructorDetail(id);

    System.out.println("Deleted successfully!");
  }

  private void findInstructorDetail(AppDao app) {
    int id = 2;
    System.out.println("Find id: " + id);

    InstructorDetail inDeets = app.findInstructorDetailById(id);

    System.out.println("Details: " + inDeets);
    System.out.println("Instructor: " + inDeets.getInstructor());
  }

  private void deleteInstructor(AppDao app) {
    int id = 1;
    System.out.println("Delete id: " + id);

    app.deleteInstructorById(id);

    System.out.println("Deleted successfully!");
  }

  private void findInstructor(AppDao app) {
    int id = 1;
    System.out.println("Find id: " + id);

    Instructor ins = app.findInstructorById(id);

    System.out.println("Instructor: " + ins);
    System.out.println("Details: " + ins.getInstructorDetail());
  }

  private void createInstructor(AppDao app) {
    Instructor ins = new Instructor("Satoru", "Gojo", "gojo.satoru@email.com");
    InstructorDetail deets = new InstructorDetail("Jujutsu", "Cursed Energy");
    ins.setInstructorDetail(deets);
    System.out.println("Information saved!");
    app.save(ins);
  }
}
