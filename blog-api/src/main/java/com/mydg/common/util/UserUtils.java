package com.mydg.common.util;

import com.mydg.common.constant.Base;
import com.mydg.entity.User;
import org.apache.shiro.SecurityUtils;


public class UserUtils {

    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
    }
}
