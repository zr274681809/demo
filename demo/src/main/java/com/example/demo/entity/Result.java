package com.example.demo.entity;

/** @author lyn
 * @description //TODO 统一返回
 * @date 2020/4/9 10:46
*/
public class Result<T> {
   private T data;
   /*
  -  0 -> 成功
  -  1 -> 失败
  -  2 -> 无此查询结果
  -  other -> 其他情况，设置errorMsg以描述错误情况，视情况而定，以下内容不再赘述
    */
   private Integer errorCod = 0;
   private String  errorMsg = "";

    public Result(T data) {
        this.data = data;
    }

    public Result(Integer errorCod, String errorMsg) {
        this.errorCod = errorCod;
        this.errorMsg = errorMsg;
    }

    public Result(T data, Integer errorCod, String errorMsg) {
        this.data = data;
        this.errorCod = errorCod;
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCod() {
        return errorCod;
    }

    public void setErrorCod(Integer errorCod) {
        this.errorCod = errorCod;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", errorCod=" + errorCod +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
