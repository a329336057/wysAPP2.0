package com.winhex.wys.wys.bean;

public class Startokenbean {
    /**
            * token : false
            * message : token失效了
     */

    private boolean token;
    private String message;

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
