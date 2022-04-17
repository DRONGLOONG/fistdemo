package com.zisu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zisu.domain.Book;

public interface IBookService extends IService<Book> {

    IPage<Book> getPage(Integer current, Integer pageSize);

    IPage<Book> getPage(Integer current, Integer pageSize,Book book);
}
