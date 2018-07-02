package com.ciel.appoint.service.Impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciel.appoint.dao.BookDaoTest;
import com.ciel.appoint.entity.Book;
import com.ciel.appoint.service.BookService;
import com.ciel.appoint.service.Impl.BookServiceImpl;

public class BookServiceImplTest extends BookDaoTest{
	@Autowired
	private BookService bookService;
	
	/**
	 * 通过图书ID检索图书
	 * case 1：存在该图书ID
	 * case 2： 不存在该图书ID
	 */
	@Test
	public void getBookById(){
//		Book book = bookService.getById(1001);  // case 1：存在该图书ID
		Book book = bookService.getById(1101);  // case 2： 不存在该图书ID
		System.out.println(book);
	}
}
