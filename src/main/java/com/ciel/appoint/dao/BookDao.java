package com.ciel.appoint.dao;


import com.ciel.appoint.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
	/**
	 * 根据id查询书
	 * @param id
	 * @return
	 */
	Book queryById(long id);
	
	/**
	 *  根据名字搜索图书
	 * @param name
	 * @return
	 */
	List<Book> querySome(String name);
	
	/**
	 * 检索全部图书
	 * @param startNumber
	 * @param recordNum
	 * @return
	 */
	List<Book> queryAll(@Param("startNumber") int startNumber, @Param("recordNum") int recordNum);
	
	/**
	 * 增加图书
	 * @param book_id
	 * @param name
	 * @param introd
	 * @param number
	 * @author CIEL
	 * */
	void addBook(long book_id, String name, String introd, int number);
	
	/**
	 * 删除图书
	 * @param book_id
	 * @param name
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


	/*减少管存数量
	 * 用返回值可判断当前库存是否还有书
	 */
	int reduceNumber(long bookId);
}
