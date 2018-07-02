package com.ciel.appoint.service;

import java.util.List;

import com.ciel.appoint.dto.AppointExecution;
import com.ciel.appoint.entity.Admin;
import com.ciel.appoint.entity.Appointment;
import com.ciel.appoint.entity.Book;
import com.ciel.appoint.entity.Student;

public interface BookService {
	/**
	 * 增加图书
	 * @param book_id
	 * @param name
	 * @param introd
	 * @param number
	 */
    void addBook(long book_id, String name, String introd, int number);
    
    /**
     * 删除图书
     * @param book_id
     */
    boolean deleteBook(long book_id);
    
    /**
     * 修改图书
     * @param book_id
     * @param name
     * @param introd
     * @param number
     */
    boolean updateBook(Book book);

    /**
     * 预约图书
     *
     * @param bookId
     * @param studentId
     * @return
     */
    AppointExecution appoint(long bookId, long studentId);    // 返回预约成功的实体类

    /**
     * 登陆时查询数据库是否有该学生记录。
     */
    Student validateStu(String studentId, String password);

    Admin validateAdmin(Long adminId, String password);
    /*
     * 查看某学生预约的所有图书
     *
     */
    List<Appointment> getAppointByStu(long studentId);

    /**
     * 查询一本图书
     *
     * @param bookId
     * @return
     */
    Book getById(long bookId);

    /**
     * 查询所有图书
     *
     * @return
     */
    List<Book> getList(int pageNumber, int recordNumber);

    /**
     * 按照图书名称查询
     * 按条件搜索图书
     *
     */
    List<Book> getSomeList(String name);

}

