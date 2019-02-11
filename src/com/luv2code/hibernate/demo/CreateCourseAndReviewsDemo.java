package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Course course = new Course("Maths");
			course.addReview(new Review("Good Course...."));
			course.addReview(new Review("Great Course...."));
			course.addReview(new Review("awesome Course..."));
			course.addReview(new Review("Bad Course..."));
			course.addReview(new Review("Cool Course..."));

			System.out.println(course);
			System.out.println(course.getReviews());
			
			session.save(course);
			session.getTransaction().commit();
		 } finally {
			session.close();
			factory.close();
		  }
		}
}
