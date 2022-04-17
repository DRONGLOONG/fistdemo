package com.zisu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zisu.dao.BookDao;
import com.zisu.domain.Book;
import com.zisu.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {

    @Autowired
    private BookDao bookDao;

    //分页查询
    public IPage<Book> getPage(Integer current,Integer pageSize){
        IPage<Book> page = new Page<>(current,pageSize);
        bookDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Book> getPage(Integer current, Integer pageSize, Book book) {
        Page<Book> page = new Page<>(current, pageSize);
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());
        bookDao.selectPage(page, lqw);
        return page;
    }

}
