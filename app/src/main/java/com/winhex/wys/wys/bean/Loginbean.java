package com.winhex.wys.wys.bean;

public class Loginbean {

    /**
     * Code : 200
     * message : 登陆成功
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dpbm5hbWUiOiIxMTEiLCJleHAiOjE1NTIzNjI4MzQsInVzZXJpZCI6IjEifQ.V9KA_aZFWbE769Q6OW4oi_6HRdQ_6-VbpffAejN-Aro
     * isPerfectinformation : 0
     */

    private int Code;
    private String message;
    private String token;
    private String isPerfectinformation;

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

    public String getIsPerfectinformation() {
        return isPerfectinformation;
    }

    public void setIsPerfectinformation(String isPerfectinformation) {
        this.isPerfectinformation = isPerfectinformation;
    }
}
