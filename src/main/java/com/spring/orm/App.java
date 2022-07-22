package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

import java.io.*;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
        
//        Student student = new Student(121, "Rajat Gupta", "Patel Nagar");
//        
//        int r = studentDao.insert(student);
//        System.out.println("Inserted Row: " + r);
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while(flag) {
        	System.out.println("Press 1 for add new student");
        	System.out.println("Press 2 to get details for single student");
        	System.out.println("Press 3 for display all student");
        	System.out.println("Press 4 for delete student");
        	System.out.println("Press 5 for update student");
        	System.out.println("Press 6 for exit");
        	int choice = Integer.parseInt(br.readLine());
        	switch(choice) {
        	case 1:
        		System.out.println("Please Enter Student Id, Student Name, Student City (separated with spaces) :");
        		String[] str = br.readLine().split(" ");
        		Student student = new Student();
        		student.setId(Integer.parseInt(str[0]));
        		student.setName(str[1]);
        		student.setCity(str[2]);
        		int r = studentDao.insert(student);
        		System.out.println("Inserted successfully : " + r);
        		break;
        	case 2:
        		System.out.println("Enter the student id of that student : ");
        		int id = Integer.parseInt(br.readLine());
        		System.out.println("Data would be ");
        		Student st = studentDao.get(id);
        		System.out.println(st);
        		break;
        	case 3:
        		System.out.println("Data would be shown as: ");
        		List<Student> list = studentDao.getAllStudent();
        		for(Student s : list) {
        			System.out.println(s);
        		}
        		break;
        	case 4:
        		System.out.println("Enter student id of that student : ");
        		int deletedId = Integer.parseInt(br.readLine());
        		studentDao.delete(deletedId);
        		System.out.println("Deleted successfully");
        		break;
        	case 5:
        		System.out.println("Enter details of student : ");
        		String[] detail = br.readLine().split(" ");
        		Student result = new Student();
        		result.setId(Integer.parseInt(detail[0]));
        		result.setName(detail[1]);
        		result.setCity(detail[2]);
        		studentDao.updateStudent(result);
        		System.out.println("Data Updated successfully");
        		break;
        	case 6:
        		flag = false;
        		break;
        	default:
        		System.out.println("Enter Valid Choice");
        	}
        }
        System.out.println("Program Terminated successfully");
    }
}
