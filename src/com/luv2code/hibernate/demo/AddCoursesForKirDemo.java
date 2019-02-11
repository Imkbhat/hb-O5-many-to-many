package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForKirDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			int StudId = 106;
			Student stu = session.get(Student.class, StudId);
			System.out.println(stu);
			System.out.println(stu.getCourses());
			
			Course c1 = new Course("History");
			Course c2 = new Course("GeoLogy");
			Course c3 = new Course("Algebra");
			
			c1.addStudent(stu);
			c2.addStudent(stu);
			c3.addStudent(stu);
			
			session.save(c1);
			session.save(c2);
			session.save(c3);
			
			session.getTransaction().commit();
		 } finally {
			session.close();
			factory.close();
		  }
		}
}
