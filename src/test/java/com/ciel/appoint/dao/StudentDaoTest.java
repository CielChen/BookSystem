package com.ciel.appoint.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciel.appoint.BaseTest;
import com.ciel.appoint.entity.Student;

public class StudentDaoTest extends BaseTest{
	@Autowired
	private StudentDao studentDao;
	
	/**
	 * 验证学生的学号与密码是否匹配
	 * case 1: 学号与密码匹配
	 * case 2： 学号与密码不匹配
	 * @throws Exception
	 */
	@Test
	public void testRegisterByStudentnameAndPassword() throws Exception{  
//		// case 1: 学号与密码匹配
//		String studentId = "201528007329041";   
//		String studentPassword = "920122";
		
		// case 2： 学号与密码不匹配
		String studentId = "000000";   
		String studentPassword = "920122";
		Student student = studentDao.quaryStudent(studentId, studentPassword);
		System.out.println("学号：" + student.getStudentId() + "; 密码：" + student.getPassword());
	}
}


