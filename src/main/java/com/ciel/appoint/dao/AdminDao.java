package com.ciel.appoint.dao;

import com.ciel.appoint.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
	/**
	 * 验证管理员的学号与密码是否匹配
	 * @param adminId
	 * @param password
	 * @return
	 */
    Admin queryAdmin(@Param("adminId") long adminId, @Param("password")String password);

}
