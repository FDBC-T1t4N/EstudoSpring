package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.spi.id.local.LocalTemporaryTableBulkIdStrategy;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class EagerLazyDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				                  .configure("hibernate.cfg.xml")
				                  .addAnnotatedClass(Instructor.class)
				                  .addAnnotatedClass(InstructorDetail.class)
				                  .addAnnotatedClass(Course.class)
				                  .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
		    session.beginTransaction();
		    
		    //get the instructor from db
		    int theId=3;
		    Instructor tempInstructor = session.get(Instructor.class, theId);
		   
		    System.out.println("luv2code: Instructor: " + tempInstructor);
		    
		    //get course for the instructor
		    System.out.println("luv2code: Course: " + tempInstructor.getCourses());
		    
			//commit transaction
		    session.getTransaction().commit();
		    
		    System.out.println("luv2code: Done!");
			
		}
		finally{
			//add clean up code
			session.close();
			
			factory.close();
		}
	}

}
