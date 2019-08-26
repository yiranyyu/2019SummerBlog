package com.shimh.common.util;

import com.shimh.common.constant.Base;
import com.shimh.entity.User;
import org.apache.shiro.SecurityUtils;


public class UserUtils {

    public static User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
        return user;
    }
}
