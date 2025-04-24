package com.example.common;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;

    public CommonResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getter 和 Setter 方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 静态方法，用于快速创建成功响应
    public static <T> CommonResponse<T> success(T data) {

        return new CommonResponse<>(200, "Success", data);
    }

    // 静态方法，用于快速创建失败响应
    public static <T> CommonResponse<T> fail(int code, String message) {
        return new CommonResponse<>(code, message, null);
    }
}
