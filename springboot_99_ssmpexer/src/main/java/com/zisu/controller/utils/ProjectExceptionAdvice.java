package com.zisu.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler
    public R doException(Exception ex){
        ex.printStackTrace();
        return new R(false,null,"系统错误，请稍后再试！");
    }

}
