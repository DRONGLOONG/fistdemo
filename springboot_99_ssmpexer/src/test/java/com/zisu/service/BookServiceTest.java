package com.zisu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zisu.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private IBookService bookService;

    @Test
    public void testGetById(){
        System.out.println(bookService.getById(5));;
    }

    @Test
    public void testGetPage(){
        IPage<Book> page = new Page<>(2,10);
        bookService.page(page);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());
    }

    @Test
    public void testGetBy(){
        IPage<Book> page = new Page<>();
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(true,Book::getName,"文");
        bookService.page(page,lqw);
    }



}
