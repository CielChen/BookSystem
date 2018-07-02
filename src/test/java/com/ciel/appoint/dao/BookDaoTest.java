package com.ciel.appoint.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciel.appoint.BaseTest;
import com.ciel.appoint.entity.Book;

public class BookDaoTest extends BaseTest {
	@Autowired
	private BookDao bookDao;
	
	/**
	 * 测试依据图书ID检索图书
	 * case 1: 有该图书ID
	 * case 2: 无该图书ID
	 * @throws Exception
	 */
//	@Test
//	public void testQueryById() throws Exception{
////		long bookId = 1000;   // case 1: 有该图书ID
//		long bookId = 1100;   // case 2: 无该图书ID
//		Book book = bookDao.queryById(bookId);
//		System.out.println(book);
//	}
	
	/**
	 * 测试依据图书名检索图书
	 * case 1: 无该图书
	 * case 2: 精确搜索
	 * case 3: 模糊搜索
	 */
//	@Test
//	public void testQuerySome() throws Exception{
////		String bookName = "booksearch";   // case 1: 无该图书
////		String bookName = "设计模式";   //case 2: 精确搜索
//		String bookName = "设计";    //case 3: 模糊搜索
//		List<Book> books = bookDao.querySome(bookName);
//		for(Book book: books){
//			System.out.println(book);
//		}
//	}
	
	/**
	 * 检索全部图书
	 * @throws Exception
	 */
//	@Test
//	public void testQueryAll() throws Exception{
//		List<Book> books = bookDao.queryAll(0, 1000);
//		for(Book book : books){
//			System.out.println(book);
//		}
//	}
	
	/**
	 * 减少图书数量
	 * @throws Exception
	 */
//	@Test
//	public void testReduceNumber() throws Exception{
//		long bookId = 1000;
//		int number =  bookDao.reduceNumber(bookId);
//		System.out.println("update=" + number);
//	}

//	/**
//	 * 增加图书
//	 * @throws Exception
//	 */
//	@Test
//	public void testInsertBook() throws Exception{
//		Book newBook = new Book();
//		newBook.setBookId(1010);
//		newBook.setName("bookAddTest04");
//		newBook.setIntrod("junit测试增加图书");
//		newBook.setNumber(10);
//		bookDao.addBook(newBook.getBookId(), newBook.getName(), newBook.getIntrod(), newBook.getNumber());
//		
//		Book newBookInfo = bookDao.queryById(newBook.getBookId());
//		System.out.println(newBookInfo);
//	}

	/**
	 * 删除图书
	 * @throws Exception
	 */
//	@Test
//	public void testDeleteBook() throws Exception{
//		long bookId = 1010;
//		boolean deleteBook = bookDao.deleteBook(bookId);
//	}
	
	/**
	 * 更新图书信息
	 * @throws Exception
	 */
	@Test
	public void testUpdateBook() throws Exception{
		long updateId = 1009;
		Book updateBook = new Book();
		updateBook.setBookId(updateId);
		updateBook.setName("bookUpdateTest");
		updateBook.setIntrod("updateIntro");
		updateBook.setNumber(22);
		boolean update = bookDao.updateBook(updateBook);
	}
}
