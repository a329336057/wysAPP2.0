package com.winhex.wys.wys.bean;

public class Loginbean {
    /**
     * Code : 200
     * message : 登陆成功
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dpbm5hbWUiOiIxMTEiLCJleHAiOjE1NTIwMzY1MjUsInVzZXJpZCI6IjEifQ.vPibRkO1vGUIH1qJZHcSFUq9oSnJZ8tZFk0KWQZGsd4
     */

    private int Code;
    private String message;
    private String token;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
