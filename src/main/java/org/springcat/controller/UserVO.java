package org.springcat.controller;

import org.hibernate.validator.constraints.Length;
import org.springcat.entity.User;

import javax.validation.constraints.NotNull;

/**
 * Created by springcat on 16/9/27.
 */
public class UserVO {

    @NotNull
    private String loginName;

    @NotNull
    private String password;

    @Length(max = 6,min = 6)
    private String salt;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public static User toUser(UserVO userVO) {
        if (userVO == null) {
            return null;
        }
        User user = new User();
        user.setLoginName(userVO.getLoginName());
        user.setPassword(userVO.getPassword());
        user.setSalt(userVO.getSalt());
        return user;
    }


}
