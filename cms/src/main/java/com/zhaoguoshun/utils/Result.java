package com.zhaoguoshun.utils;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public class Result {
    //状态码

    public static final Integer SUCCESS_CODE=200;
    public static final Integer ERROR_CODE=500;
    private Integer status = 200 ;
    //消息
    private   String message;

    private Object data =null;
    private Object vo =null;

    public static Result ok() {
        return new Result(SUCCESS_CODE,"操作成功",null);
    }


    public static Result ok(Integer status,String message){
        return new Result();
    }


    public static Map ok(PageInfo pageInfo){
       return Maps.build()
                .put("status",200).put("message","操作成功")
                .put("list",pageInfo.getList())
                .put("pages",pageInfo.getPages())
                .put("total",pageInfo.getTotal()).getMap();
    }


    public static Result ok(Object data){
        return new Result("操作成功",data);
    }

    public static Map ok(Object data,Object vo){
        return Maps.build()
                .put("status",200)
                .put("message","操作成功")
                .put("data", data)
                .put("vo",vo).getMap();
    }

    public static Result ok(String msg,Object data){
        return new Result(msg,data);
    }

    public static Result ok(Integer status,String msg,Object data){
        return new Result(SUCCESS_CODE,msg,data);
    }

//  ==================================失败===========================

    public static Result fail(String message){
        return new Result(ERROR_CODE,message,null);
    }

    public static Result fail(Integer status,String msg){
        return new Result(ERROR_CODE,msg,null);
    }

    public static Result fail(){
        return new Result(ERROR_CODE,"操作失败",null);
    }

    public Result (){

    }

    public Result(Integer status,String message) {
        this.status=status;
        this.message =message;
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(String message, Object data) {
        this.message=message;
        this.data = data;
    }

    public Result(String message, Object data,Object vo) {
        this.message=message;
        this.data = data;
        this.vo = vo;
    }


    public Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
