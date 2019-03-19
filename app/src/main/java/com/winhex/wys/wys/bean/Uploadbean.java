package com.winhex.wys.wys.bean;

public class Uploadbean {

    /**
     * meassage : 上传成功
     * Code : 200
     */

    private String meassage;
    private int Code;

    public String getMsg() {
        return meassage;
    }

    public void setMsg(String msg) {
        this.meassage = msg;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }
}
