package com.ciel.appoint.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciel.appoint.BaseTest;
import com.ciel.appoint.entity.Admin;;

public class AdminDaoTest extends BaseTest{
	@Autowired
	private AdminDao adminDao;
	
	/**
	 * 验证学生的学号与密码是否匹配
	 * case 1: 账号与密码匹配
	 * case 2： 账号与密码不匹配
	 * @throws Exception
	 */
	@Test
	public void testQueryAdmin() throws Exception{  
		// case 1: 账号与密码匹配
		long adminId = 123456;   
		String adminPassword = "123456";
		
//		// case 2： 账号与密码不匹配
//		long adminId = 0;   
//		String adminPassword = "123456";
		Admin admin = adminDao.queryAdmin(adminId, adminPassword);
		System.out.println("学号：" + admin.getAdminId() + "; 密码：" + admin.getPassword());
	}
}

