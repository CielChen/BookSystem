package com.ciel.appoint.web;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ciel.appoint.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ciel.appoint.dto.AppointExecution;
import com.ciel.appoint.dto.Result;
import com.ciel.appoint.entity.Appointment;
import com.ciel.appoint.entity.Book;
import com.ciel.appoint.entity.Student;
import com.ciel.appoint.enums.AppointStateEnum;
import com.ciel.appoint.exception.NoNumberException;
import com.ciel.appoint.exception.RepeatAppoint;
import com.ciel.appoint.service.BookService;

/**
 * Author: CIEL
 * Date: 2018/06
 */
@Controller
@RequestMapping("/books")
public class BookController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookService bookService;

    // 获取图书列表
    @RequestMapping(
        value  = "/list",
        method = RequestMethod.GET
    )
    private String List(Model model) {
        List<Book> list = bookService.getList(1,5);;
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    @ResponseBody
    private List<Book> sortedList(Model model, HttpServletRequest request, HttpServletResponse response) {
        String sortType = request.getParameter("sortType");
        String secondColumn = request.getParameter("column");
        String record = request.getParameter("recordNum");
        String adescValue = request.getParameter("adescValue");
        List<Book> list = null;
        Integer pageNumber = Integer.parseInt(secondColumn);
        // 默认一页显示的条数为5条
        if (record.equals("")){
            record = "5";
        }
        Integer recordNumber = Integer.parseInt(record);
        list = bookService.getList(pageNumber,recordNumber);
        //Integer adescValue = Integer.parseInt(value);
        if(sortType.equals("number")&&adescValue.equals("1")){
            Collections.sort(list, new Comparator<Book>() {
                public int compare(Book o1, Book o2) {
                    return o1.compareTo(o2);
                }});
            }else if (sortType.equals("number")&&adescValue.equals("0")){
            Collections.sort(list, new Comparator<Book>() {
                public int compare(Book o1, Book o2) {
                    return o2.compareTo(o1);
                }});
        } else if (sortType.equals("bookId")&&adescValue.equals("1")){
                Collections.sort(list, new Comparator<Book>() {
                    public int compare(Book o1, Book o2) {
                        return (int)(o1.getBookId() - o2.getBookId());
                    }
                });
            }else if (sortType.equals("bookId")&&adescValue.equals("0")){
                Collections.sort(list, new Comparator<Book>() {
                    public int compare(Book o1, Book o2) {
                        return (int)(o2.getBookId() - o1.getBookId());
                    }
                });
        }
        //System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    private List<Book> pageList(Model model, HttpServletRequest request, HttpServletResponse response) {
        String secondColumn = request.getParameter("columnNum");
        String record = request.getParameter("recordNum");
        List<Book> list = null;
        if (record.equals("")){
            record = "5";
        }
        if (secondColumn!=null){
            Integer pageNumber = Integer.parseInt(secondColumn);
            Integer recordNumber = Integer.parseInt(record);
            list = bookService.getList(pageNumber,recordNumber);
        }else {
            list = bookService.getList(1, 5);
        }
        System.out.println(list);
        return list;
    }


    // 跳转添加图书页面
    @RequestMapping(
        value  = "/add",
        method = RequestMethod.GET
    )
    private String add(Model model) {
        return "add";
//    	//重定向
//    	return "redirect:/books/adminlist";
    }
    
    
    // 管理员获取图书列表
 	@RequestMapping(value = "/adminlist", method = RequestMethod.GET)
 	private String adminlist(Model model){
 		List<Book> bookList = bookService.getList(1, 1000); 
 		model.addAttribute("list", bookList);
 		
 		return "adminlist";   // WEB-INF/jsp/"adminlist.jsp"
 	}
 	
// 	// 跳转修改图书页面
//    @RequestMapping(value  = "/adminlist/updateBook", method = RequestMethod.GET)
//    private String updateBook(Model model) {
//        return "updateBook";
//    }
    
    
    /**
     * 编辑图书
     * @param book
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateBook")
    public String update(Book book, Model model){
    	if(bookService.updateBook(book)){
    		System.out.println(book.getBookId());
    		book = bookService.getById(book.getBookId());
    		model.addAttribute("book", book);
    		return "redirect:/books/adminlist";
    	}
    	else{
    		return "/error";
    	}
    }
    
    /**
     * 根据id删除图书
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/delBook")
    public String deleteBook(long id, Model model){
    	model.addAttribute("book", bookService.deleteBook(id));
    	return "redirect:/books/adminlist";
    }
    
    /**
     * 根据id查询单个图书
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getBook")
    public String getBook(long id, Model model){
    	model.addAttribute("book", bookService.getById(id));
    	return "editBook";
    }
    
    
// // 跳转管理图书页面
//    @RequestMapping(value="/manageBook", method=RequestMethod.GET)
//    private String manageBook(Model model){
//    	return "manageBook";
//    }


    @RequestMapping(value = "/appoint")
    private String appointBooks(@RequestParam("studentId") long studentId, Model model) {
        List<Appointment> appointList = new ArrayList<Appointment>();
        appointList = bookService.getAppointByStu(studentId);
        model.addAttribute("appointList", appointList);

        return "appointBookList";
    }

    @RequestMapping(value = "/bookData")
    @ResponseBody
    private String bookData(HttpServletRequest request, HttpServletResponse response) {
        String bookId     = request.getParameter("bookId");
        String bookName   = request.getParameter("bookName");
        String bookIntrod = request.getParameter("bookIntrod");
        String bookNumber = request.getParameter("bookNumber");

        System.out.println("bookId:" + bookId + "\t bookName:" + bookName + "\t bookIntrod:" + bookIntrod
                           + "\t bookNumber: " + bookNumber);

        Long id = Long.parseLong(bookId);
        Integer number = Integer.parseInt(bookNumber);
        Book book = bookService.getById(id);

        if (book == null) {
            bookService.addBook(id, bookName, bookIntrod, number);

            return "success";
        } else {

            // 数据库存在相同的书籍ID
            return "failed";
        }
    }

    // 查看某图书的详细情况
    @RequestMapping(
        value  = "/{bookId}/detail",
        method = RequestMethod.GET
    )
    private String detail(@PathVariable("bookId") Long bookId, Model model) {
        if (bookId == null) {
            return "redict:/booksystem/books/list";
        }

        Book book = bookService.getById(bookId);

        if (book == null) {
            return "forward:/booksystem/books/list";
        }

        model.addAttribute("book", book);
        System.out.println(book);

        return "detail";
    }

    // 执行预约的逻辑
    @RequestMapping(
        value    = "/{bookId}/appoint",
        method   = RequestMethod.POST,
        produces = { "application/json; charset=utf-8" }
    )
    @ResponseBody
    private Result<AppointExecution> execute(@PathVariable("bookId") Long bookId,
                                             @RequestParam("studentId") Long studentId) {
        Result<AppointExecution> result;
        AppointExecution         execution = null;

        try {    // 手动try catch,在调用appoint方法时可能报错
            execution = bookService.appoint(bookId, studentId);
            result = new Result<AppointExecution>(true, execution);

            return result;
        } catch (NoNumberException e1) {
            execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
            result = new Result<AppointExecution>(true, execution);

            return result;
        } catch (RepeatAppoint e2) {
            execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
            result = new Result<AppointExecution>(true, execution);

            return result;
        } catch (Exception e) {
            execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
            result = new Result<AppointExecution>(true, execution);

            return result;
        }
    }

    // 搜索是否有某图书
    @RequestMapping(
        value = "/search",
        method = RequestMethod.POST,
        produces = { "application/json; charset=utf-8" }
    )
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收页面的值
        String name = req.getParameter("name");

        System.out.println(name);
        name = name.trim();

        // 向页面传值
        req.setAttribute("name", name);
        req.setAttribute("list", bookService.getSomeList(name));
        req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
    }

    // 验证学生登录
    @RequestMapping(
        value    = "/verifyStudent",
        method   = RequestMethod.POST,
        produces = { "application/json; charset=utf-8" }
    )
    @ResponseBody
    private Map validateStudent(HttpServletRequest request, HttpServletResponse response) {
        Map  studentMap = new HashMap();
        Student student   = null;
        System.out.println("======");
        String studentId = request.getParameter("userId");
        String password = request.getParameter("password");
        student = bookService.validateStu(studentId, password);
        if (student != null) {
            System.out.println("SUCCESS");
            studentMap.put("result", "SUCCESS");
            return studentMap;
        } else {
            studentMap.put("result", "FAILED");
            return studentMap;
        }
    }
    //验证管理员登录
    @RequestMapping(
        value    = "/verifyAdmin",
        method   = RequestMethod.POST,
        produces = { "application/json; charset=utf-8" }
    )
    @ResponseBody
    private Map validateAdmin(HttpServletRequest request, HttpServletResponse response) {
        Map adminMap = new HashMap();
        Admin admin   = null;
        String adminId = request.getParameter("userId");
        Long Id = Long.parseLong(adminId);
        String password = request.getParameter("password");
        System.out.println("验证函数");
        admin = bookService.validateAdmin(Id, password);
        if (admin != null) {
            adminMap.put("result", "SUCCESS");
            return adminMap;
        } else {
            adminMap.put("result", "FAILED");
            return adminMap;
        }
    }
}


