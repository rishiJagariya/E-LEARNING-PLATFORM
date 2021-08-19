package com.elp.backend;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Enrollment;
import com.elp.entity.Student;
import com.elp.entity.Trainer;
import com.elp.entity.User;


public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		
		User u= new User();
		u.setDob("01/-6/1223");
		u.setFname("roli");
		u.setLname("rai");
		u.setPassword("123");
		u.setPhoneNo("123456754");
		u.setUsername("rari123");
		u.setUserType("user");
        Trainer t= new Trainer(u);
        t.setUsername("ritika");
        Student s= new Student(u);
        s.setUsername("Yaswant");
        Course c= new Course();
        Enrollment e= new Enrollment();
        Cart ca= new Cart();
        
		session.save(u);
		session.save(t);
		session.save(s);
		session.save(c);
		session.save(e);
		session.save(ca);
		System.out.println(u);
        session.getTransaction().commit();
		
		System.out.println("Txn completed.");

	}

}