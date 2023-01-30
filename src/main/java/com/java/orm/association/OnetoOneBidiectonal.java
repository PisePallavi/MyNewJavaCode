package com.java.orm.association;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OnetoOneBidiectonal {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.configure("MySql.cfg.xml");
		conf.addAnnotatedClass(Student.class);
		conf.addAnnotatedClass(Department.class);
		
		Department deptobj1 = new Department(5, "IT");
		Student stuobj1 = new Student(105,"A", "a@gmail.com", "Pune", deptobj1);
		
		SessionFactory sfacFactory = conf.buildSessionFactory();
		Session session1 = sfacFactory.openSession();
		
		System.out.println("Session Created : " +session1.hashCode());
		
		Transaction tr = session1.beginTransaction();
		session1.save(stuobj1);
		session1.save(deptobj1);
		
		System.out.println("Record Added........");
		
		Department dept = session1.get(Department.class, 5);
		System.out.println("Deaprtment is : " +dept);
		
		Student stu = session1.get(Student.class, 105);
		System.out.println("Student is : " +stu);
		
		session1.flush();
		tr.commit();
	}

}
