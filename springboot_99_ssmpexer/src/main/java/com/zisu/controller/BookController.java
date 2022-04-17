package com.zisu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zisu.controller.utils.R;
import com.zisu.domain.Book;
import com.zisu.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    //增
    @PostMapping
    public R save(@RequestBody Book book){
        boolean flag = bookService.save(book);
        return new R(flag,flag ? "保存成功" : "保存失败");
    }

    //删
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.removeById(id));
    }

    //改
    @PutMapping
    public R update(@RequestBody Book book){
        boolean flag = bookService.updateById(book);
        return new R(flag,flag ? "修改成功" : "修改失败");
    }

    //查询全部
    @GetMapping
    public R getAll(){
        List<Book> bookList = bookService.list();
        return new R(bookList != null,bookList);
    }

    //根据id查询
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        Book book = bookService.getById(id);
        return new R(true,book);
    }

//    //分页查询
//    @GetMapping("{current}/{pageSize}")
//    public R getPage(@PathVariable Integer current,@PathVariable Integer pageSize){
//        IPage<Book> page = bookService.getPage(current, pageSize);
//        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
//        if (current > page.getPages()){
//            page = bookService.getPage((int) page.getPages(), pageSize);
//        }
//
//        return new R(true,page);
//    }

    //条件查询
    @GetMapping("{current}/{pageSize}")
    public R getPage(@PathVariable Integer current,@PathVariable Integer pageSize,Book book){
        IPage<Book> page = bookService.getPage(current, pageSize, book);
        if (current > page.getPages()){
            page = bookService.getPage((int) page.getPages(),pageSize,book);
        }
        return new R(true,page);
    }

}
