package com.ciel.appoint.comparator;

import com.ciel.appoint.entity.Book;

import java.util.Comparator;

/**
 * Author: CIEL
 * Date: 2018/06
 */
public class BookComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        Book book1 = (Book) o1;
        Book book2 = (Book) o2;
        if(book1.getNumber() < book2.getNumber()){
            return 1;
        }else {
            return 0;
        }
    }
}
