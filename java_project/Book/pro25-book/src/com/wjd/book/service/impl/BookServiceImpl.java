package com.wjd.book.service.impl;

import com.wjd.book.dao.BookDAO;
import com.wjd.book.pojo.Book;
import com.wjd.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO ;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
}
