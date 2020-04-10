package com.example.demo.exception;

/*import org.apache.shiro.authz.UnauthorizedException;*/
import com.example.demo.entity.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.time.DateTimeException;
import java.util.*;
import java.util.concurrent.CompletionException;

/* @Author lyn
 * @Description //TODO 异常捕获统一格式返回
 * @Date 2020
**/
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static Map<Class,String> map = new LinkedHashMap();
    {
        map.put(NullPointerException.class,"10060:空指针异常");
        map.put(IllegalArgumentException.class,"10061:不合法的参数异常");
        map.put(ArithmeticException.class,"10062:异常算术条件");
        map.put(ArrayStoreException .class,"10063:尝试将错误类型的对象存储到对象数组中");
        map.put(BufferOverflowException.class,"10064:达到目标缓冲区限制");
        map.put(BufferUnderflowException.class,"10065:达到源缓冲区的限制");
        map.put(ClassCastException.class,"10066:尝试将对象转换为不属于实例的子类");
        map.put(CompletionException.class,"10067:在完成结果或任务的过程中遇到错误或其他异常");
        map.put(DateTimeException.class,"10068:操作日期时间对象异常");
        map.put(EmptyStackException.class,"10069:堆栈为空");
        map.put(FileSystemAlreadyExistsException.class,"10070:尝试创建已存在的文件系统异常");
        map.put(FileSystemNotFoundException.class,"10071:无法找到文件系统异常");
        map.put(IndexOutOfBoundsException .class,"10072:索引超出范围异常");
        map.put(MissingResourceException.class,"10073:资源丢失异常");
        map.put(NoSuchElementException.class,"10074:被请求的元素不存在异常");
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest req, Exception e){
        return "对不起，你没有访问权限！";
    }

    @ExceptionHandler(BException.class)
    @ResponseBody
    public Result defaultBExceptionHandler(BException e){
        return new Result(e.getCode(),e.getMsg());
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result allRuntimeException(RuntimeException e){
        Set<Map.Entry<Class, String>> entries = map.entrySet();
        for (Map.Entry<Class, String> entry : entries) {
            if (entry.getKey()==e.getClass()){
                return new Result(Integer.valueOf(entry.getValue().split(":")[0]),entry.getValue().split(":")[1]);
            }
        }
        return new Result(-999,"未知异常");
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result allException(Exception e){
        return new Result(-999,e.getMessage());
    }
}