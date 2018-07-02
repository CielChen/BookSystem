package com.ciel.appoint.service.Impl;


import com.ciel.appoint.dao.AdminDao;
import com.ciel.appoint.dao.AppointmentDao;
import com.ciel.appoint.dao.BookDao;
import com.ciel.appoint.dao.StudentDao;
import com.ciel.appoint.dto.AppointExecution;
import com.ciel.appoint.entity.Admin;
import com.ciel.appoint.entity.Appointment;
import com.ciel.appoint.entity.Book;
import com.ciel.appoint.entity.Student;
import com.ciel.appoint.enums.AppointStateEnum;
import com.ciel.appoint.exception.AppointException;
import com.ciel.appoint.exception.NoNumberException;
import com.ciel.appoint.exception.RepeatAppoint;
import com.ciel.appoint.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookDao bookDao;
	@Autowired
	private AppointmentDao appointmentDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private AdminDao adminDao;

	/**
	 * 通过图书ID，检索图书
	 */
	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}  

	public List<Book> getList(int pageNumber, int recordNumber) {
		int startNumber = (pageNumber-1) * recordNumber + 1;
		return bookDao.queryAll(startNumber, recordNumber);
	}
	public Student validateStu(String studentId, String password){
		return studentDao.quaryStudent(studentId, password);
	}
	public Admin validateAdmin(Long adminId, String password){
		return adminDao.queryAdmin(adminId, password);
	}
	public List<Book> getSomeList(String name) {
		return bookDao.querySome(name);
	}


	public List<Appointment> getAppointByStu(long studentId) {
		return appointmentDao.query(studentId);
	}

	public void addBook(long book_id, String name, String introd, int number) {
		bookDao.addBook(book_id, name, introd, number);
	}
	
	@Override
	public boolean deleteBook(long book_id) {
		// TODO Auto-generated method stub
		return bookDao.deleteBook(book_id);
	}
	
	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return bookDao.updateBook(book);
	}

	// @Transactional：spring中对数据库事务的定义
	@Transactional
	public AppointExecution appoint(long bookId, long studentId) {//在Dao的基础上组织逻辑，形成与web成交互用的方法
		try{													  //返回成功预约的类型。		
			int update=bookDao.reduceNumber(bookId);//减库存
			if(update<=0){//已经无库存！
				throw new NoNumberException("no number");
			}else{
				//执行预约操作
				int insert=appointmentDao.insertAppointment(bookId, studentId);
				if(insert<=0){//重复预约
					throw new RepeatAppoint("repeat appoint");
				}else{//预约成功
					return new AppointExecution(bookId, AppointStateEnum.SUCCESS);
				}
				
			}
		} catch (NoNumberException e1) {
			throw e1;
		} catch (RepeatAppoint e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 所有编译期异常转换为运行期异常
			throw new AppointException("appoint inner error:" + e.getMessage());
		}
	}

 
}
