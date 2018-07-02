package com.ciel.appoint.dao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciel.appoint.BaseTest;
import com.ciel.appoint.entity.Appointment;
import com.ciel.appoint.entity.Book;

public class AppointmentDaoTest extends BaseTest{
	@Autowired
	private AppointmentDao appointmentDao;
	
	/**
	 * 通过图书ID和学生ID预约书籍，并插入
	 * @throws Exception
	 */
//	@Test
//	public void testInsertAppointment() throws Exception{
//		long bookId = 1003;
//		long studentId = 0;
//		int number = appointmentDao.insertAppointment(bookId, studentId);
//		System.out.println("insert = " + number);
//	}
	
	/**
	 * 通过一个学生ID查询已经预约了哪些书
	 * @throws Exception
	 */
	@Test
	public void testQueryByStudent() throws Exception{
		long studentId = 0;
		List<Appointment> appointments = appointmentDao.query(studentId);
		for(Appointment appointment : appointments){
			System.out.println(appointment);
		}
	}
}
