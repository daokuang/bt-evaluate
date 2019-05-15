package com.btjf.response;

import java.io.Serializable;

/**
 * Created by yj on 2019/5/15.
 */
public class UserResponse implements Serializable{
    private static final long serialVersionUID = 3401295192087222757L;

    private String usernae;
    private String idCard;

    public String getUsernae() {
        return usernae;
    }

    public void setUsernae(String usernae) {
        this.usernae = usernae;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
