package com.btjf.response;

import java.io.Serializable;

/**
 * Created by yj on 2019/5/15.
 */
public class UserResponse implements Serializable{
    private static final long serialVersionUID = 3401295192087222757L;

    private String username;
    private String idCard;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
