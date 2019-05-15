package com.btjf.response;

import java.io.Serializable;

/**
 * Created by yj on 2019/5/15.
 */
public class PersonResponse implements Serializable{

    private static final long serialVersionUID = 8702955028643635262L;

    private String callCode;
    private String name;
    private String telphone;
    private String affairName;

    public String getCallCode() {
        return callCode;
    }

    public void setCallCode(String callCode) {
        this.callCode = callCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAffairName() {
        return affairName;
    }

    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }
}
